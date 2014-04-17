package ga.lab.operators;

import ga.lab.entities.Individual;

import java.util.Random;

import static ga.lab.operators.OperatorUtils.extractFromString;

public class CrossoverOperator extends BinaryOperator<Individual> {

    public CrossoverOperator(Individual val1, Individual val2) {
        super(val1, val2);
    }

    @Override
    protected Individual[] doPerform() {
        Random rand = new Random(System.currentTimeMillis());

        char[] chr1 = val1.getRepresentation().toCharArray();
        char[] chr2 = val2.getRepresentation().toCharArray();

        // eigth positions - so rand (9)
        int crossPoint = rand.nextInt(chr1.length - 1);

        char[] child1 = new char[val1.getRepresentation().length()];
        char[] child2 = new char[val1.getRepresentation().length()];

        // Copy the data from each chromosome to the first child.
        System.arraycopy(chr1, 0, child1, 0, crossPoint);
        System.arraycopy(chr2, crossPoint, child1, crossPoint,
                (child1.length - crossPoint));

        // Repeat for the second child, but in reverse order.
        System.arraycopy(chr2, 0, child2, 0, crossPoint);
        System.arraycopy(chr1, crossPoint, child2, crossPoint,
                (child2.length - crossPoint));

        return new Individual[]{extractFromString(String.valueOf(child1)),
                extractFromString(String.valueOf(child2))};
    }
}
