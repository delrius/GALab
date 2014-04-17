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

    public static double minInPop(Individual[] pop, int k) {
        double min = 1.0;

        for (int i = 0; i < pop.length; i++) {
            Chromosome[] chr = pop[i].getChromosomes();
            if (chr[k].getValue() < min) {
                min = chr[k].getValue();
            }
        }
        return min;
    }

    public static double maxInPop(Individual[] pop, int k) {
        double max = 0.0;

        for (int i = 0; i < pop.length; i++) {
            Chromosome[] chr = pop[i].getChromosomes();
            if (chr[k].getValue() > max) {
                max = chr[k].getValue();
            }
        }
        return max;
    }
}
