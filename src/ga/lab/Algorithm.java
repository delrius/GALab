package ga.lab;

import ga.lab.functions.Functions;

import java.util.HashMap;
import java.util.TreeMap;

public class Algorithm {
	
	public static HashMap<Chromosome,Double> encodeMap(Functions fun) {
		HashMap<Chromosome,Double> encodeResult = new HashMap<Chromosome,Double>();
		for (Integer i = 1; i <= 1000; i++) {
			encodeResult.put(new Chromosome(i), (double)(i)/1000);
		}
		return encodeResult;
	}	
	
	
//	public static HashMap<Chromosome,HashMap<Double,Double>> encodeMapInitialFitnesss(Functions fun) {
//		HashMap<Chromosome,HashMap<Double,Double>>encodeResult = new HashMap<Chromosome,HashMap<Double,Double>>();
//		for (Integer i = 1; i <= 1000; i++) {
//			encodeResult.put(new Chromosome(i),new HashMap<Double,Double>(double)(i)/1000,199.0s);
//		}
//		return encodeResult;
//	}
	
	
//	
//	public static TreeMap<Chromosome,Double> encodeTreeMap(Functions fun) {
//		TreeMap<Chromosome,Double> encodeResult = new TreeMap<Chromosome,Double>();
//		for (Integer i = 1; i <= 1000; i++) {
//			encodeResult.put(new Chromosome(i), (double)(i)/1000);
//		}
//		return encodeResult;
//	}
	
}
//	public static HashMap<Chromosome,Double> encodeMap(Functions fun) {
//		HashMap<Chromosome,Double> encodeResult = new HashMap<Chromosome,Double>();
//		for (Integer i = 1; i <= 1000; i++) {
//			encodeResult.put(new Chromosome(i), (double)(i)/1000);
//		}
//		return encodeResult;
//	}	
//
//}
