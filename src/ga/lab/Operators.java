package ga.lab;

import ga.lab.functions.Functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Operators {

	public static final int LENGTH = 10;

	public static int getHammingDistance(String ch1, String ch2) {
		if (ch1.length() != ch2.length()) {
			System.out.println("The length of two chromosomes don't match");
		}
		int distance = 0;
		for (int i = 0; i < ch1.length(); i++) {
			if (ch1.charAt(i) != ch2.charAt(i))
				distance++;
		}
		return distance;
	}

	public static double sharingFunction(String ch1, String ch2, int radius,
			int alpha) {
		double sharingFunction;
		if (getHammingDistance(ch1, ch2) < radius) {
			sharingFunction = (1 - (Math.pow(
					(getHammingDistance(ch1, ch2) / radius), alpha)));
		} else {
			sharingFunction = 0;
		}
		return sharingFunction;
	}

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

		double r = (1. / 2) * Math.sqrt(accum);

		return r;
	}

	public static double calculateNicheNumber(String ch1,
			ArrayList<String> selectedChList, int N, int radius, int alpha) {
		double m = 0;
		for (int i = 0; i < N; i++) {
			m = m + sharingFunction(ch1, selectedChList.get(i), radius, alpha);
		}
		return m;
	}

	public static HashMap<Integer, Double> calcInitialFitnessMap(int n,
			Functions function) {
		HashMap<Integer, Double> initialFitnessMap = new HashMap<Integer, Double>();
		for (int i = 1; i <= 1000; i++) {
			initialFitnessMap.put(i, function.calculate(i / 1000.));
		}
		return initialFitnessMap;
	}

	public static String mutate(String ch) {
		char[] chr = ch.toCharArray();
		Random rand = new Random();
		int index = rand.nextInt(LENGTH + 1);
		// ch.replace(oldChar, newChar);
		System.out.println(index);
		if (chr[index] == '0') {
			chr[index] = '1';
		} else if (chr[index] == '1') {
			chr[index] = '0';
		}

		System.out.println(chr[index]);
		System.out.println(chr.toString());
		return String.valueOf(chr);

	}
	
//	public Chromosome mutate() {
//		char[] arr  = gene.toCharArray();
//		int idx     = rand.nextInt(arr.length);
//		int delta   = (rand.nextInt() % 90) + 32;
//		arr[idx]    = (char) ((arr[idx] + delta) % 122);
//
//		return new Chromosome(String.valueOf(arr));
//	}
//
}
