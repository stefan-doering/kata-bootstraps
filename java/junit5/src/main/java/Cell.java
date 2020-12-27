public class Cell {
    private final CellState cellState;
    private long aliveNeighbours;

    public Cell(CellState cellState) {
        this.cellState = cellState;
    }

    public CellState getCellState() {
        return cellState;
    }

    public long getAliveNeighbours() {
        return aliveNeighbours;
    }

    public void setAliveNeighbours(long aliveNeighbours) {
        this.aliveNeighbours = aliveNeighbours;
    }
}
