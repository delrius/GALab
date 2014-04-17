package ga.lab;

import ga.lab.algorithm.Algorithm;
import ga.lab.entities.Chromosome;
import ga.lab.entities.Individual;
import ga.lab.entities.Pair;
import ga.lab.functions.Functions;
import ga.lab.operators.OperatorUtils;
import ga.lab.utils.MathOps;

import java.util.HashMap;
import java.util.List;

public class Operators {

    public static final int LENGTH = 10;

    // map: pair encoded chromosome and double x
    public static HashMap<Individual, Double> encodeMap(Functions fun) {
        HashMap<Individual, Double> encodeResult = new HashMap<>();
        // for (Integer i = 0; i <= 1000; i++) {
        // encodeResult.put(new Individual(i), i / 1000.);
        // }
        return encodeResult;
    }

    // map: double x and double fitness
    public static HashMap<Double, Double> fitnessMap(Functions fun) {
        HashMap<Double, Double> fitnessMap = new HashMap<>();
        for (Integer i = 0; i <= 1000; i++) {
            fitnessMap.put(i / 1000., fun.calculate(i / 1000.));
        }
        return fitnessMap;
    }

    // calculate sharing functions
    public static double sharingFunction(double distance, double radius, int alpha) {
        double sharingFunction;
        if (distance < radius) {
            sharingFunction = (1 - (Math.pow(
                    (distance / radius), alpha)));
        } else {
            sharingFunction = 0;
        }
        return sharingFunction;
    }

    // calculate Niche radius
    public static double calculateRadius(int dimension, int numberOfExtr,
                                         double r) {
        final double euclRadius = r / MathOps.root(numberOfExtr, dimension);
        if (Algorithm.isEuclidian) {
//            return euclRadius;
            return 0.1;
        } else {
//            int n = (int) (1 / euclRadius);
//            if (n == 0) {
//                n = 10;
//            }
//            return (dimension * Chromosome.LENGTH) / n;
              return (dimension * Chromosome.LENGTH) / 10;
        }
    }

    /**
     * Calculates the r-value
     *
     * @param dimension dimension space
     * @param lower     lower bound of x
     * @param upper     upper bound of x
     * @return r
     */
    public static double calculateR(int dimension, double lower, double upper) {
        double accum = 0;
        for (int i = 0; i < dimension; i++) {
            accum += Math.pow((upper - lower), 2);
        }

        return (1. / 2) * Math.sqrt(accum);
    }

    // calculate Niche number
    public static double calculateNicheNumber(int selected,
                                              HashMap<Pair<Integer, Integer>, Double> sharing,
                                              List<Integer> selectedChList) {
        double m = 0;
        for (Integer j : selectedChList) {
            m = m + sharing.get(new Pair<>(selected, j));
        }
        return m;
    }

    // calculate shared fitness function for 1 chr with exact amount of fitness
    public static double calculateSharedFitness(Double fitness,
                                                double nicheNumber) {
        return fitness / nicheNumber;
    }

    // calculate euclidian distance
    public static double getDistance(int dimension, int i, int j, Individual[] pop) {
        return getDistance(dimension, pop[i], pop[j], pop);
    }

    public static double getDistance(int dimension, Individual individual1, Individual individual2, Individual[] pop) {
        if (Algorithm.isEuclidian) {
            return getEuclidianDistance(dimension, individual1, individual2, pop);
        }  else {
            return getHemmingDistance(dimension, individual1, individual2, pop);
        }
    }

    // calculate euclidian distance
    public static double getHemmingDistance(int dimension, Individual ind1, Individual ind2, Individual[] pop) {
        String chr1 = ind1.getRepresentation();
        String chr2 = ind2.getRepresentation();
        if (chr1.length() != chr2.length()) {
            System.out.println("The length of two chromosomes don't match");
        }
        int distance = 0;
        for (int i = 0; i < ind1.getRepresentation().length(); i++) {
            if (chr1.charAt(i) != chr2.charAt(i))
                distance++;
        }
        return distance;
    }

    // calculate euclidian distance
    public static double getEuclidianDistance(int dimension, Individual ind1, Individual ind2, Individual[] pop) {
        double euclidianDistance = 0;
        Chromosome[] chr1 = ind1.getChromosomes();
        Chromosome[] chr2 = ind2.getChromosomes();

        for (int it = 0; it < dimension; it++) {
            euclidianDistance = Math.sqrt(Math.abs((chr1[it].getValue() - chr2[it]
                    .getValue()))
                    / (OperatorUtils.maxInPop(pop, it) - OperatorUtils
                    .minInPop(pop, it)));

        }
        return euclidianDistance;
    }
}
