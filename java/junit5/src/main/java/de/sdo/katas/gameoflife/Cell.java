package de.sdo.katas.gameoflife;

public class Cell {
	private final CellState cellState;
	private long aliveNeighbours;

	public Cell(final CellState cellState) {
		this.cellState = cellState;
	}

	public CellState getCellState() {
		return cellState;
	}

	public long getAliveNeighbours() {
		return aliveNeighbours;
	}

	public void setAliveNeighbours(final long aliveNeighbours) {
		this.aliveNeighbours = aliveNeighbours;
	}
}
