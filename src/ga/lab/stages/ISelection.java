package ga.lab.stages;

import ga.lab.Individual;

import java.util.List;

public interface ISelection {
    List<Individual> performSelection(List<Individual> population, int popSize);
}