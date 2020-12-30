package de.sdo.katas.gameoflife.rules;

import de.sdo.katas.gameoflife.Cell;
import de.sdo.katas.gameoflife.CellState;

public class BecomeALiveCellWithExactlyThreeNeighbours implements EvolutionRule {

	@Override
	public boolean test(final Cell cell) {
		return (cell.getCellState() == CellState.DEAD && cell.getAliveNeighbours() == 3);
	}
}
