package ga.lab.algorithm;

import ga.lab.Operators;
import ga.lab.entities.Individual;
import ga.lab.entities.Pair;
import ga.lab.functions.Functions;
import ga.lab.operators.CrossoverOperator;
import ga.lab.operators.Mutation;
import ga.lab.stages.EliteSelection;
import ga.lab.stages.RouleteSelection;

import java.io.*;
import java.util.*;

public class Algorithm {
    final static int N = 1;
    final static int POP_SIZE = 100;  // population size
    final static int MAX_ITER = 500;             // max number of iterations
    final static double MUTATION_RATE = 0.05;     // probability of mutation
    final static double CROSSOVER_RATE = 1;     // probability of crossover
    final public static Functions FUNCTION = Functions.F15;
    final static int alpha = 1;
    final public static int ELITE = 0;
    public static boolean isEuclidian = true;


    private static Random m_rand = new Random();  // random-number generator
    private Individual[] m_population;
    private double totalFitness;
    private List<Individual> all;

    public Algorithm() {
        //TODO: uncomment for hemming
//        isEuclidian = false;
        m_population = new Individual[POP_SIZE];

        Random rand = new Random(System.currentTimeMillis());
        // init population
        for (int i = 0; i < POP_SIZE; i++) {
            m_population[i] = Individual.random(N, rand.nextInt(1001));
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
        ArrayList<Double> fitnesses = new ArrayList<>();
        ArrayList<Individual> individuals = new ArrayList<>();
        for (int i = 0; i < POP_SIZE; i++) {
            final Double val = FUNCTION.calculate(m_population[i].makeDoubleArray());

            fitnesses.add(val);
            individuals.add(m_population[i]);

            this.totalFitness += m_population[i].getFitness();
        }
        all = individuals;
        return this.totalFitness;
    }

    private void reEvaluate() {
        HashMap<Pair<Integer, Integer>, Double> eucl = new HashMap<>();
        for (int i = 0; i < m_population.length; ++i) {
            for (int j = 0; j < m_population.length; j++) {
                Pair<Integer, Integer> newPair = new Pair<>(i, j);
                Pair<Integer, Integer> newPair1 = new Pair<>(j, i);
                double dist = Operators.getDistance(N, i, j, m_population);
                eucl.put(newPair, dist);
                eucl.put(newPair1, dist);
            }
        }

        double r = Operators.calculateR(N, 0, 1);
        final double sigmaS = Operators.calculateRadius(N, (int) Math.pow(5, N), r);

        HashMap<Pair<Integer, Integer>, Double> sharing = new HashMap<>();

        for (int i = 0; i < m_population.length; ++i) {
            for (int j = 0; j < m_population.length; j++) {
                Pair<Integer, Integer> newPair = new Pair<>(i, j);
                Pair<Integer, Integer> newPair1 = new Pair<>(j, i);
                final double sharingValue = Operators.sharingFunction(eucl.get(newPair), sigmaS, alpha);
                sharing.put(newPair, sharingValue);
                sharing.put(newPair1, sharingValue);
            }
        }

        for (int i = 0; i < m_population.length; ++i) {

            List<Integer> individualList = new ArrayList<>();
            for (int j = 0; j< m_population.length; ++j) {
                Pair<Integer, Integer> pair = new Pair<>(i, j);
                boolean isNotZero = sharing.get(pair) != 0.;
                if (isNotZero) {
                    individualList.add(j);
                }
            }

            final double nicheNumber = Operators.calculateNicheNumber(i, sharing, individualList);

            if (individualList.size()>0) {
                final double current = m_population[i].getFitness();
                m_population[i].setFitness(Operators.calculateSharedFitness(current, nicheNumber));
            }
        }
    }
//
//    public Individual rouletteWheelSelection() {
//        double randNum = m_rand.nextDouble() * this.totalFitness;
//        int idx;
//        for (idx = 0; idx < POP_SIZE && randNum > 0; ++idx) {
//            randNum -= m_population[idx].getFitness();
//        }
//        return m_population[idx - 1];
//    }

    private static <T> String printList(List<T> list) {
        StringBuilder builder = new StringBuilder();
        for (T item: list) {
            builder.append(item).append(" ");
        }
        return builder.toString();
    }


    public static List<Individual> sort(List<Individual> list, Individual[] pop) {
        Collections.sort(list);
        Collections.reverse(list);
        int i = 0;
        while (i < list.size() - 2 ) {
            int closest = i+1;
            double min = Double.POSITIVE_INFINITY;
            for (int j = i+1; j< list.size(); j++) {
                final double euclidianDistance = Operators.getDistance(N, list.get(i), list.get(j), pop);
                if (euclidianDistance < min) {
                    min = euclidianDistance;
                    closest = j;
                }
            }
            final Individual tmp = list.get(i + 1);
            list.set(i+1, list.get(closest));
            list.set(closest, tmp);
            i++;
        }

        return list;
    }

    public static void print(Algorithm pop) {
        File f = new File("del", "del" + ".txt");
        try (FileWriter c = new FileWriter(f)) {
            Collections.sort(pop.all);
            Collections.reverse(pop.all);
            for (Individual d: pop.all) {
                String x = "";
                for (Double xx: d.makeDoubleArray()) {
                    x+=xx + " ";
                }
                c.write(x + "-->" + d.getFitness() +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Algorithm pop = new Algorithm();
        Individual[] newPop = new Individual[POP_SIZE];
        Individual[] indiv = new Individual[2];
        System.out.println("start...");

        // main loop
        int count;
        for (int iter = 0; iter < MAX_ITER; iter++) {
            count = 0;
            pop.reEvaluate();

            Random random = new Random(System.currentTimeMillis());
            List<Individual> selected = new RouleteSelection().performSelection(pop.getPopulation(), pop.getPopulation().length - ELITE, random);
            List<Individual> elite = new EliteSelection().performSelection(pop.getPopulation(), ELITE, random);
            selected.addAll(elite);

            sort(selected, pop.getPopulation());
            // build new Population
            while (count < POP_SIZE) {
                // Selection
                indiv[0] = selected.get(count);
                indiv[1] = selected.get(count + 1);

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
//            System.out.println(iter + ":Average Fitness = " + pop.totalFitness / pop.getPopulation().length);
//            System.out.println("Best = " + printList(pop.best));
        }

        print(pop);
        System.out.println("End...");
    }
}