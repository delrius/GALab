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
            final String chr = string.substring(i * chrLength, i * chrLength + chrLength);
            list[i] = new SimpleChromosome(chr);
        }
        return new Individual(list);
    }
}
