public class KillCellsWithLessThanTwoAliveNeighboursRule implements EvolutionRule {

    @Override
    public boolean test(Cell cell) {
        return (cell.getCellState() == CellState.ALIVE && cell.getAliveNeighbours() < 2);
    }
}
