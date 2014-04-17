package ga.lab;

import ga.lab.entities.Chromosome;
import ga.lab.entities.Individual;
import ga.lab.functions.Functions;
import ga.lab.operators.OperatorUtils;
import ga.lab.utils.MathOps;

import java.util.ArrayList;
import java.util.HashMap;

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

	// calculate HammingDistance
	public static int getHammingDistance(Individual ch1, Individual ch2) {
		String chr1 = ch1.getRepresentation();
		String chr2 = ch2.getRepresentation();
		if (chr1.length() != chr2.length()) {
			System.out.println("The length of two chromosomes don't match");
		}
		int distance = 0;
		for (int i = 0; i < ch1.getRepresentation().length(); i++) {
			if (chr1.charAt(i) != chr2.charAt(i))
				distance++;
		}
		return distance;
	}

	// calculate sharing functions
	public static double sharingFunction(Individual ch1, Individual ch2,
			int radius, int alpha) {
		double sharingFunction;
		if (getHammingDistance(ch1, ch2) < radius) {
			sharingFunction = (1 - (Math.pow(
					(getHammingDistance(ch1, ch2) / radius), alpha)));
		} else {
			sharingFunction = 0;
		}
		return sharingFunction;
	}

	// calculate Niche radius
	public static double calculateRadius(int dimension, int numberOfExtr,
			double r) {

		return r / MathOps.root(numberOfExtr, dimension);
	}

	/**
	 * Calculates the r-value
	 * 
	 * @param dimension
	 *            dimension space
	 * @param lower
	 *            lower bound of x
	 * @param upper
	 *            upper bound of x
	 * @return r
	 */
	public static double calculateR(int dimension, double lower, double upper) {
		// TODO: check
		double accum = 0;
		for (int i = 0; i < dimension; i++) {
			accum += Math.pow((upper - lower), 2);
		}

		return (1. / 2) * Math.sqrt(accum);
	}

	// calculate Niche number
	public static double calculateNicheNumber(Individual ch1,
			ArrayList<Individual> selectedChList, int N, int radius, int alpha) {
		double m = 0;
		for (int i = 0; i < N; i++) {
			m = m + sharingFunction(ch1, selectedChList.get(i), radius, alpha);
		}
		return m;
	}

	// calculate shared fitness function for 1 chr with exact amount of fitness
	public static double calculateSharedFitness(Double fitness,
			double nicheNumber) {
		return fitness / nicheNumber;
	}

	// calculate euclidian distance
	public static double getEuclidianDistance(int dimension, double maxInPop,
			double minInPop, Individual ind1, Individual ind2, Individual[] pop) {
		double euclidianDistance = 0;
		Chromosome[] chr1 = ind1.getChromosomes();
		Chromosome[] chr2 = ind2.getChromosomes();

		for (int i = 0; i < dimension; i++) {
			euclidianDistance = Math.sqrt((chr1[i].getValue() - chr2[i]
					.getValue())
					/ (OperatorUtils.maxInPop(pop, i) - OperatorUtils
							.minInPop(pop, i)));

		}
		return euclidianDistance;
	}
}
