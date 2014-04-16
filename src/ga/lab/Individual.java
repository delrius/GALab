package ga.lab;

import java.util.Random;

public class Individual {
    private double fitness;


    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public static Individual getRandom() {
        Individual ind = new Individual();
        ind.setFitness(new Random().nextDouble() * 1000);
        return ind;
    }
}
