package ga.lab.entities;

import java.util.Random;

public class Individual {

    private String representation;
    private Chromosome[] chromosomes;
    private int length;

    private Individual() {}

	public Individual(Chromosome[] chromosomes) {
        this.chromosomes = chromosomes;
        this.length = chromosomes.length;
		this.representation = convert(chromosomes);
	}

    public static Individual random(int length) {
        Random random = new Random(System.currentTimeMillis());
        SimpleChromosome[] genes = new SimpleChromosome[length];
        for (int i=0; i<length; i++) {
            genes[i] = new SimpleChromosome(random.nextInt(1001));
        }
        return new Individual(genes);
    }

    private String convert(Chromosome[] chromosomes) {
        StringBuilder builder = new StringBuilder();
        for (int i=0 ; i< chromosomes.length ; i++) {
            builder.append(chromosomes[i].build());
        }
        return builder.toString();
    }

    public String getRepresentation() {
        return representation;
    }

    public double getFitness() {
        //TODO: implement
        return 0;
    }

    public double evaluate() {
        //TODO: implement
        return 0;
    }
}


