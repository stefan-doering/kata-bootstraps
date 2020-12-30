package de.sdo.katas.gameoflife;

import de.sdo.katas.gameoflife.rules.BecomeALiveCellWithExactlyThreeNeighbours;
import de.sdo.katas.gameoflife.rules.DieWithLessThanTwoAliveNeighbours;
import de.sdo.katas.gameoflife.rules.DieWithMoreThanThreeAliveNeighbours;
import de.sdo.katas.gameoflife.rules.EvolutionRuleSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

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
		game = new GameOfLife(INITIAL_BOARD_STATE, new EvolutionRuleSet(), gameOfLifeBoardPrinter);
	}

	@Test
	void shouldKillAliveCellsWithLessThanTwoAliveNeighbours() {

		final String initialBoardState =
			"Generation 1:" + LINE_BREAK +
				"3 3" + LINE_BREAK +
				".*." + LINE_BREAK +
				".*." + LINE_BREAK +
				".*.";

		final EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.fromRules(
			singletonList(new DieWithLessThanTwoAliveNeighbours()),
			emptyList()
		);
		final GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
		game.createNewGeneration();

		final GameOfLifeBoard board = game.getCurrentBoard();
		assertThat(board.countCellsAlive()).isEqualTo(1);
		assertThat(board.countCellsDead()).isEqualTo(8);
	}

	@Test
	void shouldKillAliveCellsWithMoreThanThreeAliveNeighbours() {

		final String initialBoardState =
			"Generation 1:" + LINE_BREAK +
				"3 3" + LINE_BREAK +
				".*." + LINE_BREAK +
				"***" + LINE_BREAK +
				".*.";

		final EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.fromRules(
			singletonList(new DieWithMoreThanThreeAliveNeighbours()),
			emptyList());
		final GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
		game.createNewGeneration();

		final GameOfLifeBoard board = game.getCurrentBoard();
		assertThat(board.countCellsAlive()).isEqualTo(4);
		assertThat(board.countCellsDead()).isEqualTo(5);
	}

	@Test
	void shouldSurviveWithTwoOrThreeAliveNeighbours() {

		final String initialBoardState =
			"Generation 1:" + LINE_BREAK +
				"3 3" + LINE_BREAK +
				".*." + LINE_BREAK +
				".**" + LINE_BREAK +
				".*.";

		final EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.fromRules(
			Arrays.asList(
				new DieWithLessThanTwoAliveNeighbours(),
				new DieWithMoreThanThreeAliveNeighbours()
			),
			emptyList());
		final GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
		game.createNewGeneration();

		final GameOfLifeBoard board = game.getCurrentBoard();
		assertThat(board.countCellsAlive()).isEqualTo(4);
		assertThat(board.countCellsDead()).isEqualTo(5);
	}

	@Test
	void shouldBeBornWithWhenDeadAndExactlyThreeAliveNeighbours() {

		final String initialBoardState =
			"Generation 1:" + LINE_BREAK +
				"3 3" + LINE_BREAK +
				".*." + LINE_BREAK +
				".**" + LINE_BREAK +
				".*.";

		final EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.fromRules(
			emptyList(),
			singletonList(new BecomeALiveCellWithExactlyThreeNeighbours())
		);
		final GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
		game.createNewGeneration();

		final GameOfLifeBoard board = game.getCurrentBoard();
		assertThat(board.countCellsAlive()).isEqualTo(7);
		assertThat(board.countCellsDead()).isEqualTo(2);
	}

	@Test
	void shouldIncrementGeneration() {
		final int initialGeneration = 1;
		final String initialBoardState =
			"Generation " + initialGeneration + ":" + LINE_BREAK +
				"3 3" + LINE_BREAK +
				".*." + LINE_BREAK +
				".**" + LINE_BREAK +
				".*.";

		final EvolutionRuleSet evolutionRuleSet = EvolutionRuleSet.ofDefaultRuleSet();
		final GameOfLife game = new GameOfLife(initialBoardState, evolutionRuleSet, gameOfLifeBoardPrinter);
		game.createNewGeneration();

		assertThat(game.getCurrentBoard().getGeneration()).isEqualTo(initialGeneration + 1);
	}

}
