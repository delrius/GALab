package ga.lab;

import ga.lab.functions.Functions;

import java.util.HashMap;

public class Encoding {

	// static int par1 = 10;

	public static HashMap<Integer, String> encode() {
		HashMap<Integer, String> encodeResult = new HashMap<Integer, String>();
		for (int i = 1; i <= 1000; i++) {
			String key;
			if (Integer.toBinaryString(i).toString().length() == 10) {
				key = Integer.toBinaryString(i).toString();
			} else {
				String temp1 = "0000000000"
						+ Integer.toBinaryString(i).toString();
				key = (temp1).substring(temp1.length() - 10, temp1.length());
			}

			encodeResult.put(i, key);
		}
		return encodeResult;
	}

	public static void main(String[] args) {

		HashMap<Integer, String> res = encode();
		System.out.println(res.get(55));
		System.out.println(res.get(1000));
		// System.out.println(Operators.getHammingDistance(res.get(55),
		// res.get(1000)));
		// System.out.println(Operators.mutate(res.get(55)).toCharArray());
		// res.get(55)
		// Chromosome a=new Chromosome(10, Functions.F15);
		// System.out.println(a.getChromosome());
		// System.out.println(Integer.valueOf(a.getChromosome()));
		// System.out.println(a.getFitness());
		// System.out.println(Chromosome.decodeChromosome(a.getChromosome()));
		HashMap<Chromosome, Double> b = Algorithm.encodeMap(Functions.F15);
		// System.out.println(b.get(a));
	}
}