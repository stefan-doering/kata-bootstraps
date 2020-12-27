import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

    private final EvolutionRuleSet evolutionRuleSet;
    private List<GameOfLifeBoard> boards = new ArrayList<>();
    private final GameOfLifeBoardPrinter gameOfLifeBoardPrinter;

    public GameOfLife(String initialBoardRepresentation, EvolutionRuleSet evolutionRuleSet, GameOfLifeBoardPrinter gameOfLifeBoardPrinter) {
        this.evolutionRuleSet = evolutionRuleSet;
        this.gameOfLifeBoardPrinter = gameOfLifeBoardPrinter;
        GameOfLifeBoard board = new GameOfLifeBoard(initialBoardRepresentation, this.gameOfLifeBoardPrinter);
        boards.add(board);
    }

    public void createNewGeneration() {
        GameOfLifeBoard newBoard = new GameOfLifeBoard(currentBoard(), gameOfLifeBoardPrinter);
        calculateCellStates(newBoard, currentBoard());
        boards.add(newBoard);
    }

    public void calculateCellStates(GameOfLifeBoard newBoard, GameOfLifeBoard oldBoard) {
        for (int row = 0; row < newBoard.getMatrixRowDimension(); row++) {
            for (int column = 0; column < newBoard.getMatrixColDimension(); column++) {
                long aliveNeighbors = oldBoard.countAliveNeighbours(row, column);

                Cell oldCell = oldBoard.getMatrix()[row][column];
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
