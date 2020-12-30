package de.sdo.katas.gameoflife;

import de.sdo.katas.gameoflife.rules.EvolutionRuleSet;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

	private final EvolutionRuleSet evolutionRuleSet;
	private final List<GameOfLifeBoard> boards = new ArrayList<>();
	private final GameOfLifeBoardPrinter gameOfLifeBoardPrinter;

	public GameOfLife(
		final String initialBoardRepresentation,
		final EvolutionRuleSet evolutionRuleSet,
		final GameOfLifeBoardPrinter gameOfLifeBoardPrinter
	) {
		this.evolutionRuleSet = evolutionRuleSet;
		this.gameOfLifeBoardPrinter = gameOfLifeBoardPrinter;
		final GameOfLifeBoard board = new GameOfLifeBoard(initialBoardRepresentation, this.gameOfLifeBoardPrinter);
		boards.add(board);
	}

	public void createNewGeneration() {
		final GameOfLifeBoard newBoard = new GameOfLifeBoard(currentBoard(), gameOfLifeBoardPrinter);
		calculateCellStates(newBoard, currentBoard());
		boards.add(newBoard);
	}

	public void calculateCellStates(final GameOfLifeBoard newBoard, final GameOfLifeBoard oldBoard) {
		for (int row = 0; row < newBoard.getMatrixRowDimension(); row++) {
			for (int column = 0; column < newBoard.getMatrixColDimension(); column++) {

				final long aliveNeighbors = oldBoard.countAliveNeighbours(row, column);
				final Cell oldCell = oldBoard.getMatrix()[row][column];

				oldCell.setAliveNeighbours(aliveNeighbors);

				if (evolutionRuleSet.getKillingRules().stream().anyMatch(evolutionRule -> evolutionRule.test(oldCell))) {
					newBoard.getMatrix()[row][column] = new Cell(CellState.DEAD);
				}
				if (evolutionRuleSet.getComeToLifeRules().stream().anyMatch(evolutionRule -> evolutionRule.test(oldCell))) {
					newBoard.getMatrix()[row][column] = new Cell(CellState.ALIVE);
				}
			}
		}
		newBoard.setGeneration(oldBoard.getGeneration() + 1);
	}

	public GameOfLifeBoard getCurrentBoard() {
		return currentBoard();
	}

	private GameOfLifeBoard currentBoard() {
		return boards.get(boards.size() - 1);
	}
}
