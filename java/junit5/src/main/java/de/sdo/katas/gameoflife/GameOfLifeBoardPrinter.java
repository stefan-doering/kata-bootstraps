package de.sdo.katas.gameoflife;

public class GameOfLifeBoardPrinter {

	public String printMatrix(final int generation, final Cell[][] matrix) {
		final StringBuilder sb = new StringBuilder();
		final int matrixRowDimension = matrix.length;
		final int matrixColDimension = matrix[0].length;

		sb.append("Generation " + generation + ":" + newLine());
		sb.append(matrixRowDimension + " " + matrixColDimension + newLine());

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				sb.append(matrix[row][col].getCellState() == CellState.ALIVE ? "*" : ".");
			}
			if (row != matrix.length - 1) {
				sb.append(newLine());
			}
		}
		return sb.toString();
	}

	private String newLine() {
		return "\n";
	}
}
