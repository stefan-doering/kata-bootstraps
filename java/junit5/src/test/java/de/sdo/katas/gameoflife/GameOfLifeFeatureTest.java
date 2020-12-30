package de.sdo.katas.gameoflife;

import de.sdo.katas.gameoflife.rules.EvolutionRuleSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameOfLifeFeatureTest {

	@Test
	void simpleGameOfLifeRun() {
		final String initialGeneration = "Generation 1:\n" +
			"4 8\n" +
			"........\n" +
			"....*...\n" +
			"...**...\n" +
			"........";
		final String nextGeneration = "Generation 2:\n" +
			"4 8\n" +
			"........\n" +
			"...**...\n" +
			"...**...\n" +
			"........";
		final GameOfLifeBoardPrinter gameOfLifeBoardPrinter = new GameOfLifeBoardPrinter();
		final GameOfLife game = new GameOfLife(initialGeneration, EvolutionRuleSet.ofDefaultRuleSet(), gameOfLifeBoardPrinter);

		game.createNewGeneration();

		assertThat(game.getCurrentBoard().getStringRepresentation()).isEqualTo(nextGeneration);
	}

	@Test
	void multipleIterationsGameOfLifeRun() {
		final String initialGeneration = "Generation 1:\n" +
			"4 8\n" +
			"........\n" +
			"....*...\n" +
			"...**...\n" +
			"........";
		final String finalGeneration = "Generation 51:\n" +
			"4 8\n" +
			"........\n" +
			"...**...\n" +
			"...**...\n" +
			"........";
		final GameOfLifeBoardPrinter gameOfLifeBoardPrinter = new GameOfLifeBoardPrinter();
		final GameOfLife game = new GameOfLife(initialGeneration, EvolutionRuleSet.ofDefaultRuleSet(), gameOfLifeBoardPrinter);

		for (int i = 0; i < 50; i++) {
			game.createNewGeneration();
		}

		assertThat(game.getCurrentBoard().getStringRepresentation()).isEqualTo(finalGeneration);
	}

}
