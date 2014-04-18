package ga.lab.entities;

import ga.lab.algorithm.Algorithm;

import java.util.Random;

public class Individual implements Comparable<Individual> {

	private String representation;
	private Chromosome[] chromosomes;
	private int length;
    private double fitness;

	private Individual() {
	}

	public Individual(Chromosome[] chromosomes) {
		this.chromosomes = chromosomes;
		this.length = chromosomes.length;
		this.representation = convert(chromosomes);
        this.fitness = Algorithm.FUNCTION.calculate(makeDoubleArray());
	}

	public static Individual random(int length, Random random) {
		Chromosome[] genes = new Chromosome[length];
		for (int i = 0; i < length; i++) {
            if (Algorithm.isDiploid) {
                genes[i] = DiploidChromosome.buildRandom(random);
            } else {
			    genes[i] = SimpleChromosome.buildRandom(random);
            }
        }
        return new Individual(genes);
	}

	private String convert(Chromosome[] chromosomes) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < chromosomes.length; i++) {
			builder.append(chromosomes[i].build());
		}
		return builder.toString();
	}

	public String getRepresentation() {
		return representation;
	}

    public double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public Double[] makeDoubleArray() {
		Double[] tmp = new Double[chromosomes.length];
		for (int i = 0; i < tmp.length; ++i) {
			tmp[i] = chromosomes[i].getValue();
		}
		return tmp;
	}

	public Chromosome[] getChromosomes() {
		return chromosomes;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Individual that = (Individual) o;

        if (representation != null ? !representation.equals(that.representation) : that.representation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return representation != null ? representation.hashCode() : 0;
    }

    @Override
    public int compareTo(Individual o) {
        return Double.valueOf(fitness).compareTo(o.getFitness());
    }
}
