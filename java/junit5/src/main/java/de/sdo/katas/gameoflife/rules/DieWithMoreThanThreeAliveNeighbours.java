package de.sdo.katas.gameoflife.rules;

import de.sdo.katas.gameoflife.Cell;
import de.sdo.katas.gameoflife.CellState;

public class DieWithMoreThanThreeAliveNeighbours implements EvolutionRule {

	@Override
	public boolean test(final Cell cell) {
		return (cell.getCellState() == CellState.ALIVE && cell.getAliveNeighbours() > 3);
	}
}
