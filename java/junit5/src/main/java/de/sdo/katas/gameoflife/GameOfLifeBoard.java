package de.sdo.katas.gameoflife;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class GameOfLifeBoard {

	private static final char ALIVE_VALUE = '*';
	private int generation;
	private Cell[][] matrix;
	private int matrixRowDimension;
	private int matrixColumnDimension;
	private final GameOfLifeBoardPrinter gameOfLifeBoardPrinter;

	public GameOfLifeBoard(final String stringRepresentation, final GameOfLifeBoardPrinter gameOfLifeBoardPrinter) {
		this.gameOfLifeBoardPrinter = gameOfLifeBoardPrinter;
		final String[] input = evaluateInput(stringRepresentation);
		initializeBoard(input);
	}

	public GameOfLifeBoard(final GameOfLifeBoard oldBoard, final GameOfLifeBoardPrinter gameOfLifeBoardPrinter) {
		this.gameOfLifeBoardPrinter = gameOfLifeBoardPrinter;
		generation = oldBoard.getGeneration();
		matrixRowDimension = oldBoard.getMatrixRowDimension();
		matrixColumnDimension = oldBoard.getMatrixColDimension();
		matrix = new Cell[matrixRowDimension][matrixColumnDimension];

		for (int i = 0; i < oldBoard.matrix.length; i++) {
			for (int j = 0; j < oldBoard.matrix[i].length; j++) {
				matrix[i][j] = oldBoard.matrix[i][j];
			}
		}
	}

	private String[] evaluateInput(final String stringRepresentation) {
		final String[] lines = splitStringByNewLines(stringRepresentation);
		if (lines.length <= 4) {
			throw new RuntimeException(
				"A valid starting point should have at least a generation information line, a matrix size line as well as a minimum " +
					"two-dimensional matrix");
		} else {
			return lines;
		}
	}

	private void initializeBoard(final String[] input) {
		parseRowsAndColumns(input);
		generation = Integer.parseInt(input[0].trim().replace(":", "").split(" ")[1]);
		matrix = new Cell[matrixRowDimension][matrixColumnDimension];

		range(0, matrixRowDimension)
			.forEach(row -> range(0, matrixColumnDimension)
				.forEach(col -> matrix[row][col] = input[row + 2].charAt(col) == ALIVE_VALUE ?
					new Cell(CellState.ALIVE) : new Cell(CellState.DEAD)));
	}

	private String[] splitStringByNewLines(final String string) {
		return string.split("\\R");
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(final int generation) {
		this.generation = generation;
	}

	public int getMatrixRowDimension() {
		return matrix.length;
	}

	public int getMatrixColDimension() {
		return matrix[0].length;
	}

	public String getStringRepresentation() {
		return gameOfLifeBoardPrinter.printMatrix(generation, matrix);
	}

	private void parseRowsAndColumns(final String[] input) {
		matrixRowDimension = Integer.parseInt(input[1].split(" ")[0]);
		matrixColumnDimension = Integer.parseInt(input[1].split(" ")[1]);
	}

	public Cell[][] getMatrix() {
		return matrix;
	}

	public long countCellsAlive() {
		return allCellsWithState(CellState.ALIVE).count();
	}

	public long countCellsDead() {
		return allCellsWithState(CellState.DEAD).count();
	}

	private Stream<Cell> allCellsWithState(final CellState cellState) {
		return Arrays.stream(matrix).flatMap(Arrays::stream).filter(cell -> cell.getCellState() == cellState);
	}

	public long countAliveNeighbours(final int row, final int column) {
		final List<Map.Entry<Integer, Integer>> candidates = new ArrayList<>();

		for (int rowOffset = 1; rowOffset <= 3; rowOffset++) {
			for (int columnOffset = 1; columnOffset <= 3; columnOffset++) {

				final int calculatedRowCandidate = row - 2 + rowOffset;
				final int calculatedColumnCandidate = column - 2 + columnOffset;

				if (isNeighbourCell(row, column, calculatedRowCandidate, calculatedColumnCandidate)) {
					candidates.add(new SimpleImmutableEntry<>(calculatedRowCandidate, calculatedColumnCandidate));
				}
			}
		}

		return candidates
			.stream()
			.map(entry -> matrix[entry.getKey()][entry.getValue()])
			.filter(cell -> cell.getCellState() == CellState.ALIVE)
			.count();
	}

	private boolean isNeighbourCell(
		final int row,
		final int column,
		final int calculatedRowCandidate,
		final int calculatedColumnCandidate
	) {
		return !(calculatedRowCandidate == row && calculatedColumnCandidate == column) &&
			calculatedColumnCandidate >= 0 &&
			calculatedColumnCandidate < matrixColumnDimension &&
			calculatedRowCandidate >= 0 &&
			calculatedRowCandidate < matrixRowDimension;
	}
}
