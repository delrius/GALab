package ga.lab;

import java.util.Random;

public class Chromosome {
	public static final int LENGTH = 10;
	private final String chromosome;

	private static final Random rand = new Random(System.currentTimeMillis());

	public Chromosome(Integer chromosome) {
		this.chromosome = encodeChromosome(chromosome);
	}

	public Chromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public String getChromosome() {
		return chromosome;
	}

	private static int grayEncode(Integer ch) {
		return ch ^ (ch >>> 1);
	}

	// public static int grayDecode(int n) {
	// int p = n;
	// while ((n >>>= 1) != 0)
	// p ^= n;
	// return p;
	// }

	public static String encodeChromosome(Integer ch) {
		String res = Integer.toBinaryString(grayEncode(ch)).toString();
		if (res.length() == LENGTH) {
		} else {
			String temp1 = "0000000000" + res;
			res = (temp1).substring(temp1.length() - 10, temp1.length());
		}
		return res;

	}

	// public static Integer decodeChromosome(String ch) {
	// int res=0;
	// //Byte t = new Byte(ch);
	// // Byte f = new Byte(Integer.valueOf(ch));
	//
	// // System.out.println(f);
	// grayDecode(Integer.valueOf(ch));
	// return res;
	// }

	// private static Double calculateFitness(Integer chromosome,
	// Functions function) {
	// return function.calculate((double) (chromosome) / 1000);
	// }

	// public Chromosome mutate(Functions function) {
	// char[] arr = chromosome.toCharArray();
	// int idx = rand.nextInt(arr.length);
	// int delta = (rand.nextInt() % 90) + 32;
	// arr[idx] = (char) ((arr[idx] + delta) % 122);
	//
	// return new Chromosome(String.valueOf(arr),
	// calculateFitness(chromosome,function));
	// }

	/*
	 * public Chromosome[] mate(Chromosome mate) { // Convert the chromosomes to
	 * arrays to make thing easier. char[] arr1 = chromosome.toCharArray();
	 * char[] arr2 = mate.chromosome.toCharArray();
	 * 
	 * // Select a random pivot point for the mating int pivot =
	 * rand.nextInt(arr1.length);
	 * 
	 * // Provide a container for the child chromosome data char[] child1 = new
	 * char[chromosome.length()]; char[] child2 = new char[chromosome.length()];
	 * 
	 * // Copy the data from each chromosome to the first child.
	 * System.arraycopy(arr1, 0, child1, 0, pivot); System.arraycopy(arr2,
	 * pivot, child1, pivot, (child1.length - pivot));
	 * 
	 * // Repeat for the second child, but in reverse order.
	 * System.arraycopy(arr2, 0, child2, 0, pivot); System.arraycopy(arr1,
	 * pivot, child2, pivot, (child2.length - pivot));
	 * 
	 * return new Chromosome[] { new Chromosome(String.valueOf(child1)), new
	 * Chromosome(String.valueOf(child2)) }; }
	 */
	//
	// static Chromosome generateRandom() {
	// char[] arr = new char[TARGET_chromosome.length];
	// for (int i = 0; i < arr.length; i++) {
	// arr[i] = (char) (rand.nextInt(90) + 32);
	// }
	//
	// return new Chromosome(String.valueOf(arr));
	// }

}
