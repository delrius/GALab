package ga.lab.stages;

import ga.lab.entities.Individual;

import java.util.List;
import java.util.Random;

public interface ISelection {
    List<Individual> performSelection(Individual[] population, int popSize, Random rng);
}
