package ga.lab.stages;

import ga.lab.entities.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouleteSelection implements ISelection {

    public List<Individual> performSelection(List<Individual> aPopulation, int popSize) {
        Random random = new Random();
        List<Individual> populationNew = new ArrayList<>();
        double totalFitness = 0.;
        //sum the total fitness of the population
        for (int i = 0; i < aPopulation.size(); i++) {
            Individual currentIndividual = aPopulation.get(i);
            totalFitness += currentIndividual.getFitness();
        }
        /* sum the fitness of each individual in the population again
         * until the running sum is >= to the randomly chosen number.
         */
        for (int i = 0; i < popSize; i++) {
            //pick a random number between 0 and that sum.
            double randomNumber = random.nextDouble() * totalFitness;
            int runningSum = 0;
            int index = 0;
            int lastAddedIndex = 0;
            assert (index < aPopulation.size());
            while (runningSum < randomNumber) {
                runningSum += aPopulation.get(index).getFitness();
                lastAddedIndex = index;
                index++;
            }
            populationNew.add(aPopulation.get(lastAddedIndex));
        }
        return populationNew;
    }
}
