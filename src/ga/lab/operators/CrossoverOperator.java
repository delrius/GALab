package ga.lab.operators;

import ga.lab.entities.Chromosome;
import ga.lab.entities.DiploidChromosome;
import ga.lab.entities.Individual;
import ga.lab.entities.Pair;

import java.util.Random;

import static ga.lab.operators.OperatorUtils.extractFromString;

public class CrossoverOperator extends BinaryOperator<Individual> {

    public CrossoverOperator(Individual val1, Individual val2) {
        super(val1, val2);
    }

    @Override
    protected Individual[] doPerform() {
        final Chromosome chromosome = val1.getChromosomes()[0];
        if (chromosome instanceof DiploidChromosome) {
            return performDiploid();
        } else {
            return performGaploid();
        }
    }

    private Individual[] performDiploid() {
        Random rand = new Random(System.currentTimeMillis());
        final Pair<String, String> better;
        final Pair<String, String> worse;
        if (val1.getFitness() > val2.getFitness()) {
            better = OperatorUtils.getDiploidFromString(val1.getRepresentation());
            worse = OperatorUtils.getDiploidFromString(val2.getRepresentation());
        } else {
            worse = OperatorUtils.getDiploidFromString(val1.getRepresentation());
            better = OperatorUtils.getDiploidFromString(val2.getRepresentation());
        }

        final Pair<String, String> zygBetter = doActual(better.getVal1().toCharArray(), better.getVal2().toCharArray(), rand);
        final Pair<String, String> zygWorse = doActual(worse.getVal1().toCharArray(), worse.getVal2().toCharArray(), rand);

        Individual ind1 = extractFromString(String.valueOf(zygBetter.getVal1()), String.valueOf(zygWorse.getVal1()));
        Individual ind2 = extractFromString(String.valueOf(zygBetter.getVal2()), String.valueOf(zygWorse.getVal2()));
        return new Individual[]{ind1, ind2};
    }

    private Pair<String, String> doActual(char[] chr1, char[] chr2, Random rand) {

//        char[] chr1 = val1.getRepresentation().toCharArray();
//        char[] chr2 = val2.getRepresentation().toCharArray();

        // eigth positions - so rand (9)
//      TODO:maybe  int crossPoint = rand.nextInt(chr1.length - 2) + 1;
        int crossPoint = rand.nextInt(chr1.length - 1);

        char[] child1 = new char[chr1.length];
        char[] child2 = new char[chr1.length];

        // Copy the data from each chromosome to the first child.
        System.arraycopy(chr1, 0, child1, 0, crossPoint);
        System.arraycopy(chr2, crossPoint, child1, crossPoint,
                (child1.length - crossPoint));

        // Repeat for the second child, but in reverse order.
        System.arraycopy(chr2, 0, child2, 0, crossPoint);
        System.arraycopy(chr1, crossPoint, child2, crossPoint,
                (child2.length - crossPoint));

        return new Pair<>(String.valueOf(child1), String.valueOf(child2));
    }

    private Individual[] performGaploid() {
        Random rand = new Random(System.currentTimeMillis());
        final Pair<String, String> result = doActual(val1.getRepresentation().toCharArray(), val2.getRepresentation().toCharArray(), rand);
        return new Individual[]{extractFromString(result.getVal1()),
                extractFromString(String.valueOf(result.getVal2()))};
    }
}
