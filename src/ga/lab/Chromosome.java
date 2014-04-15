package ga.lab;

import java.util.Random;

public class Chromosome {
	public static final int LENGTH = 10;
	private final String chromosome;
	static Random rand = new Random();

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

	//encode chromosome
	public static String encodeChromosome(Integer ch) {
		String res = Integer.toBinaryString(grayEncode(ch));
		if (res.length() != LENGTH) {
			String temp1 = "0000000000" + res;
			res = temp1.substring(temp1.length() - 10, temp1.length());
		}
		return res;

	}
	
	//mutation of chromosome
	public Chromosome mutate() {
		char[] chr = chromosome.toCharArray();
		int position = rand.nextInt(LENGTH) + 1;
		if (chr[position] == '0') {
			chr[position] = '1';
		} else {
			chr[position] = '0';
		}
		return new Chromosome(String.valueOf(chr));

	}

	// single-point crossover
	public Chromosome[] crossover(Chromosome ch2) {

		char[] chr1 = chromosome.toCharArray();
		char[] chr2 = ch2.chromosome.toCharArray();

		int crossPoint = rand.nextInt(chr1.length);

		char[] child1 = new char[chromosome.length()];
		char[] child2 = new char[chromosome.length()];

		// Copy the data from each chromosome to the first child.
		System.arraycopy(chr1, 0, child1, 0, crossPoint);
		System.arraycopy(chr2, crossPoint, child1, crossPoint,
				(child1.length - crossPoint));

		// Repeat for the second child, but in reverse order.
		System.arraycopy(chr2, 0, child2, 0, crossPoint);
		System.arraycopy(chr1, crossPoint, child2, crossPoint,
				(child2.length - crossPoint));

		return new Chromosome[] { new Chromosome(String.valueOf(child1)),
				new Chromosome(String.valueOf(child2)) };
	}

	public static Chromosome generateRandom() {
		return new Chromosome(rand.nextInt(1001));
	}

}
