public class ComeToLifeWithExactlyThreeNeighbours implements EvolutionRule {

    @Override
    public boolean test(Cell cell) {
        return (cell.getCellState() == CellState.DEAD && cell.getAliveNeighbours() == 3);
    }
}
