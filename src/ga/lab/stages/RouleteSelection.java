package ga.lab.stages;

import ga.lab.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouleteSelection implements ISelection {

    public List<Individual> performSelection(List<Individual> aPopulation, int popSize) {
        Random random = new Random();
        List<Individual> populationNew = new ArrayList<>(popSize);
        double totalFitness = 0.;
        //sum the total fitness of the population
        for (int i = 0; i < popSize; i++) {
            Individual currentIndividual = aPopulation.get(i);
            totalFitness += currentIndividual.getFitness();
        }
        /* sum the fitness of each individual in the population again
         * until the running sum is >= to the randomly chosen number.
         */
        for (int i = 0; i < popSize; i++) {
            //pick a random number between 0 and that sum.
            double randomNumber = random.nextDouble() * totalFitness + 1;
            int runningSum = 0;
            int index = 0;
            int lastAddedIndex = 0;
            while (runningSum < randomNumber) {
                runningSum += aPopulation.get(i).getFitness();
                lastAddedIndex = index;
                index++;
            }
            populationNew.set(i, aPopulation.get(lastAddedIndex));
        }
        return populationNew;
    }
}
