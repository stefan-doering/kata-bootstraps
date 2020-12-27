import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class GameOfLifeSpecification {

    private static final String LINE_BREAK = "\n";
    private static final String INITIAL_BOARD_STATE = "Generation 1:" + LINE_BREAK +
        "4 8" + LINE_BREAK +
        "........" + LINE_BREAK +
        "....*..." + LINE_BREAK +
        "...**..." + LINE_BREAK +
        "........";

    private GameOfLife game;
    @Mock
    private GameOfLifeBoardPrinter gameOfLifeBoardPrinter;

    @BeforeEach
    void beforeEach() {
        this.game = new GameOfLife(INITIAL_BOARD_STATE, new EvolutionRuleSet(), gameOfLifeBoardPrinter);
    }

    @Test
    void shouldKillAliveCellsWithLessThanTwoAliveNeighbours() {

        String initialBoardState =
            "Generation 1:" + LINE_BREAK +
                "3 3" + LINE_BREAK +
                ".*." + LINE_BREAK +
                ".*." + LINE_BREAK +
                ".*.";

        EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.fromRules(
            singletonList(new KillCellsWithLessThanTwoAliveNeighboursRule()),
            emptyList()
        );
        GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
        game.createNewGeneration();

        GameOfLifeBoard board = game.getCurrentBoard();
        assertThat(board.countCellsAlive()).isEqualTo(1);
        assertThat(board.countCellsDead()).isEqualTo(8);
    }

    @Test
    void shouldKillAliveCellsWithMoreThanThreeAliveNeighbours() {

        String initialBoardState =
            "Generation 1:" + LINE_BREAK +
                "3 3" + LINE_BREAK +
                ".*." + LINE_BREAK +
                "***" + LINE_BREAK +
                ".*.";

        EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.fromRules(
            singletonList(new KillCellsWithMoreThanThreeAliveNeighboursRule()),
            emptyList());
        GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
        game.createNewGeneration();

        GameOfLifeBoard board = game.getCurrentBoard();
        assertThat(board.countCellsAlive()).isEqualTo(4);
        assertThat(board.countCellsDead()).isEqualTo(5);
    }

    @Test
    void shouldSurviveWithTwoOrThreeAliveNeighbours() {

        String initialBoardState =
            "Generation 1:" + LINE_BREAK +
                "3 3" + LINE_BREAK +
                ".*." + LINE_BREAK +
                ".**" + LINE_BREAK +
                ".*.";

        EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.fromRules(
            Arrays.asList(
                new KillCellsWithLessThanTwoAliveNeighboursRule(),
                new KillCellsWithMoreThanThreeAliveNeighboursRule()
            ),
            emptyList());
        GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
        game.createNewGeneration();

        GameOfLifeBoard board = game.getCurrentBoard();
        assertThat(board.countCellsAlive()).isEqualTo(4);
        assertThat(board.countCellsDead()).isEqualTo(5);
    }

    @Test
    void shouldBeBornWithWhenDeadAndExactlyThreeAliveNeighbours() {

        String initialBoardState =
            "Generation 1:" + LINE_BREAK +
                "3 3" + LINE_BREAK +
                ".*." + LINE_BREAK +
                ".**" + LINE_BREAK +
                ".*.";

        EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.fromRules(
            emptyList(),
            singletonList(new ComeToLifeWithExactlyThreeNeighbours())
        );
        GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
        game.createNewGeneration();

        GameOfLifeBoard board = game.getCurrentBoard();
        assertThat(board.countCellsAlive()).isEqualTo(7);
        assertThat(board.countCellsDead()).isEqualTo(2);
    }

    @Test
    void shouldIncrementGeneration() {
        int initialGeneration = 1;
        String initialBoardState =
            "Generation " + initialGeneration + ":" + LINE_BREAK +
                "3 3" + LINE_BREAK +
                ".*." + LINE_BREAK +
                ".**" + LINE_BREAK +
                ".*.";

        EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.ofDefaultRuleSet();
        GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
        game.createNewGeneration();

        assertThat(game.getCurrentBoard().getGeneration()).isEqualTo(initialGeneration + 1);
    }

}