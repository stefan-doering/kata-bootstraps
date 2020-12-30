package de.sdo.katas.gameoflife.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvolutionRuleSet {
	private final List<EvolutionRule> killingRules = new ArrayList<>();
	private final List<EvolutionRule> comeToLifeRules = new ArrayList<>();

	public EvolutionRuleSet() {}

	public List<EvolutionRule> getKillingRules() {
		return killingRules;
	}

	public List<EvolutionRule> getComeToLifeRules() {
		return comeToLifeRules;
	}

	public static EvolutionRuleSet fromRules(final List<EvolutionRule> killingRules, final List<EvolutionRule> comeToLifeRules) {
		final EvolutionRuleSet evoluationRuleSet = new EvolutionRuleSet();
		evoluationRuleSet.addKillRules(killingRules);
		evoluationRuleSet.addComeToLifeRules(comeToLifeRules);
		return evoluationRuleSet;
	}

	public static EvolutionRuleSet ofDefaultRuleSet() {
		final EvolutionRuleSet evoluationRuleSet = new EvolutionRuleSet();
		evoluationRuleSet.addKillRules(
			Arrays.asList(
				new DieWithLessThanTwoAliveNeighbours(),
				new DieWithMoreThanThreeAliveNeighbours()
			)
		);
		evoluationRuleSet.addComeToLifeRules(
			Arrays.asList(
				new BecomeALiveCellWithExactlyThreeNeighbours(),
				new SurviveWithTwoOrThreeAliveNeighbours()
			)
		);
		return evoluationRuleSet;
	}

	private void addKillRules(final List<EvolutionRule> evolutionRules) {
		killingRules.addAll(evolutionRules);
	}

	private void addComeToLifeRules(final List<EvolutionRule> evolutionRules) {
		comeToLifeRules.addAll(evolutionRules);
	}
}
