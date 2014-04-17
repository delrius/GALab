package ga.lab.operators;

import ga.lab.entities.Chromosome;
import ga.lab.entities.Individual;

import java.util.Random;

public class Mutation extends UnaryOperator<Individual> {

    public Mutation(Individual val) {
        super(val);
    }

    @Override
    protected Individual doPerform() {
        //mutation of chromosome
        Random rand = new Random(System.currentTimeMillis());
        char[] chr = val.getRepresentation().toCharArray();
        int position = rand.nextInt(Chromosome.LENGTH - 1) + 1;
        if (chr[position] == '0') {
            chr[position] = '1';
        } else {
            chr[position] = '0';
        }
        return OperatorUtils.extractFromString(String.valueOf(chr));
    }
}
