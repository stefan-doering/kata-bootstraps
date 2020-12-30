package de.sdo.katas.gameoflife.rules;

import de.sdo.katas.gameoflife.Cell;

import java.util.function.Predicate;

public interface EvolutionRule extends Predicate<Cell> {}
