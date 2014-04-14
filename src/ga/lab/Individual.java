package ga.lab;

import java.util.Random;

public class Individual
{
    public static final int LENGTH = 10;
    private int[] genes = new int[LENGTH];
    private int fitnessValue;

    public Individual() {}

    public int getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(int fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public int getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, int gene) {
        this.genes[index] = gene;
    }

    public void randGenes() {
        Random rand = new Random();
        for(int i=0; i<LENGTH; ++i) {
            this.setGene(i, rand.nextInt(2));
        }
    }

    public void mutate() {
        Random rand = new Random();
        int index = rand.nextInt(LENGTH);
        this.setGene(index, 1-this.getGene(index));    // flip
    }

    public int evaluate() {
        int fitness = 0;
        for(int i=0; i<LENGTH; ++i) {
            fitness += this.getGene(i);
        }
        this.setFitnessValue(fitness);

        return fitness;
    }
    
}