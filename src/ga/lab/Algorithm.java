package ga.lab;

import ga.lab.functions.Functions;

import java.util.HashMap;

public class Algorithm {
	
	public static HashMap<Chromosome,Double> encodeMap(Functions fun) {
		HashMap<Chromosome,Double> encodeResult = new HashMap<Chromosome,Double>();
		for (Integer i = 1; i <= 1000; i++) {
			encodeResult.put(new Chromosome(i,fun), (double)(i)/1000);
		}
		return encodeResult;
	}	
}
