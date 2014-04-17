package ga.lab.algorithm;

import ga.lab.entities.Individual;
import ga.lab.operators.CrossoverOperator;
import ga.lab.operators.Mutation;

import java.util.Random;

public class Algorithm {
    final static int N = 2;
    final static int POP_SIZE = 200;  // population size
    final static int MAX_ITER = 2000;             // max number of iterations
    final static double MUTATION_RATE = 0.05;     // probability of mutation
    final static double CROSSOVER_RATE = 0.7;     // probability of crossover

    private static Random m_rand = new Random();  // random-number generator
    private Individual[] m_population;
    private double totalFitness;

    public Algorithm() {
        m_population = new Individual[POP_SIZE];

        // init population
        for (int i = 0; i < POP_SIZE; i++) {
            m_population[i] = Individual.random(2);
        }

        // evaluate current population
        this.evaluate();
    }

    public void setPopulation(Individual[] newPop) {
        // this.m_population = newPop;
        System.arraycopy(newPop, 0, this.m_population, 0, POP_SIZE);
    }

    public Individual[] getPopulation() {
        return this.m_population;
    }

    public double evaluate() {
        this.totalFitness = 0.0;
        for (int i = 0; i < POP_SIZE; i++) {
            this.totalFitness += m_population[i].evaluate();
        }
        return this.totalFitness;
    }

    public Individual rouletteWheelSelection() {
        double randNum = m_rand.nextDouble() * this.totalFitness;
        int idx;
        for (idx = 0; idx < POP_SIZE && randNum > 0; ++idx) {
            randNum -= m_population[idx].getFitness();
        }
        return m_population[idx - 1];
    }

    public Individual findBestIndividual() {
        int idxMax = 0, idxMin = 0;
        double currentMax = 0.0;
        double currentMin = 1.0;
        double currentVal;

        for (int idx = 0; idx < POP_SIZE; ++idx) {
            currentVal = m_population[idx].getFitness();
            if (currentMax < currentMin) {
                currentMax = currentMin = currentVal;
                idxMax = idxMin = idx;
            }
            if (currentVal > currentMax) {
                currentMax = currentVal;
                idxMax = idx;
            }
            if (currentVal < currentMin) {
                currentMin = currentVal;
                idxMin = idx;
            }
        }

        //return m_population[idxMin];      // minimization
        return m_population[idxMax];        // maximization
    }


    public static void main(String[] args) {
        Algorithm pop = new Algorithm();
        Individual[] newPop = new Individual[POP_SIZE];
        Individual[] indiv = new Individual[2];

        // current population
        System.out.print("Total Fitness = " + pop.totalFitness);
//        System.out.println(" ; Best Fitness = " +
//                pop.findBestIndividual().getFitnessValue());

        // main loop
        int count;
        for (int iter = 0; iter < MAX_ITER; iter++) {
            count = 0;

            // build new Population
            while (count < POP_SIZE) {
                // Selection
                indiv[0] = pop.rouletteWheelSelection();
                indiv[1] = pop.rouletteWheelSelection();

                // Crossover
                if (m_rand.nextDouble() < CROSSOVER_RATE) {
                    indiv = new CrossoverOperator(indiv[0], indiv[1]).perform();
                }

                // Mutation
                if (m_rand.nextDouble() < MUTATION_RATE) {
                    indiv[0] = new Mutation(indiv[0]).perform();
                }
                if (m_rand.nextDouble() < MUTATION_RATE) {
                    indiv[0] = new Mutation(indiv[0]).perform();
                }

                // add to new population
                newPop[count] = indiv[0];
                newPop[count + 1] = indiv[1];
                count += 2;
            }
            pop.setPopulation(newPop);

            // reevaluate current population
            pop.evaluate();
            System.out.print("Total Fitness = " + pop.totalFitness);
//            System.out.println(" ; Best Fitness = " +
//                    pop.findBestIndividual().getFitnessValue());
        }

        // best indiv
        Individual bestIndiv = pop.findBestIndividual();
    }
}