import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public static EvolutionRuleSet fromRules(List<EvolutionRule> killingRules, List<EvolutionRule> comeToLifeRules) {
        EvolutionRuleSet evoluationRuleSet = new EvolutionRuleSet();
        evoluationRuleSet.addKillRules(killingRules);
        evoluationRuleSet.addComeToLifeRules(comeToLifeRules);
        return evoluationRuleSet;
    }

    public static EvolutionRuleSet ofDefaultRuleSet() {
        EvolutionRuleSet evoluationRuleSet = new EvolutionRuleSet();
        evoluationRuleSet.addKillRules(
            Arrays.asList(
                new KillCellsWithLessThanTwoAliveNeighboursRule(),
                new KillCellsWithMoreThanThreeAliveNeighboursRule())
        );
        evoluationRuleSet.addComeToLifeRules(
            Collections.singletonList(new ComeToLifeWithExactlyThreeNeighbours())
        );
        return evoluationRuleSet;
    }

    private void addKillRules(List<EvolutionRule> evolutionRules) {
        killingRules.addAll(evolutionRules);
    }

    private void addComeToLifeRules(List<EvolutionRule> evolutionRules) {
        comeToLifeRules.addAll(evolutionRules);
    }
}
