package ga.lab.operators;

import ga.lab.entities.Chromosome;
import ga.lab.entities.Individual;
import ga.lab.entities.SimpleChromosome;

public class OperatorUtils {
	public static Individual extractFromString(String string) {
		int chrLength = Chromosome.LENGTH;
		int numberOfChromosomes = string.length() / chrLength;
		Chromosome[] list = new Chromosome[numberOfChromosomes];
		for (int i = 0; i < numberOfChromosomes; i++) {
			final String chr = string.substring(i * chrLength, i * chrLength
					+ chrLength);
			list[i] = new SimpleChromosome(chr);
		}
		return new Individual(list);
	}

	public static double minInPop(Individual[] pop) {
		double min = 1.0;

		for (int i = 0; i < pop.length; i++) {
			Chromosome[] chr = pop[i].getChromosomes();
			for (int j = 0; j < chr.length; j++) {
				if (chr[j].getValue() < min) {
					min = chr[j].getValue();
				}
			}
		}
		return min;
	}

	public static double maxInPop(Individual[] pop) {
		double max = 0.0;

		for (int i = 0; i < pop.length; i++) {
			Chromosome[] chr = pop[i].getChromosomes();
			for (int j = 0; j < chr.length; j++) {
				if (chr[j].getValue() > max) {
					max = chr[j].getValue();
				}
			}
		}
		return max;
	}
}
