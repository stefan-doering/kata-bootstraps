import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class GameOfLifeFeatureTest {

    @Test
    void simpleGameOfLifeRun() {
        String initialGeneration = "Generation 1:\n" +
                "4 8\n" +
                "........\n" +
                "....*...\n" +
                "...**...\n" +
                "........";
        String nextGeneration = "Generation 2:\n" +
                "4 8\n" +
                "........\n" +
                "...**...\n" +
                "...**...\n" +
                "........";
        GameOfLifeBoardPrinter gameOfLifeBoardPrinter = new GameOfLifeBoardPrinter();
        GameOfLife game = new GameOfLife(initialGeneration, EvolutionRuleSet.ofDefaultRuleSet(), gameOfLifeBoardPrinter);

        game.createNewGeneration();

        assertThat(game.getCurrentBoard().getStringRepresentation()).isEqualTo(nextGeneration);
    }

}
