package de.sdo.katas.gameoflife;

import de.sdo.katas.gameoflife.rules.EvolutionRuleSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class GameOfLifeBoardTest {

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
		game = new GameOfLife(INITIAL_BOARD_STATE, EvolutionRuleSet.ofDefaultRuleSet(), gameOfLifeBoardPrinter);
	}

	@Test
	void shouldInitializeTheBoard() {

		final GameOfLifeBoard board = game.getCurrentBoard();

		assertThat(board.getGeneration()).isEqualTo(1);
		assertThat(board.getMatrixRowDimension()).isEqualTo(4);
		assertThat(board.getMatrixColDimension()).isEqualTo(8);
		assertThat(Arrays.stream(board.getMatrix()).flatMap(Arrays::stream)).allMatch(Objects::nonNull);
	}

	@Test
	void shouldInitializeTheCorrectCellValues() {

		final GameOfLifeBoard board = game.getCurrentBoard();

		assertThat(board.getMatrix()[1][4].getCellState()).isEqualByComparingTo(CellState.ALIVE);
		assertThat(board.getMatrix()[3][7].getCellState()).isEqualByComparingTo(CellState.DEAD);
	}
}
