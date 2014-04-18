package ga.lab.operators;

import ga.lab.entities.Chromosome;
import ga.lab.entities.DiploidChromosome;
import ga.lab.entities.Individual;
import ga.lab.entities.Pair;

import java.util.Random;

public class Mutation extends UnaryOperator<Individual> {

    public Mutation(Individual val) {
        super(val);
    }

    @Override
    protected Individual doPerform() {
        final Chromosome chromosome = val.getChromosomes()[0];
        if (chromosome instanceof DiploidChromosome) {
            return performDiploid();
        } else {
            return performGaploid();
        }
    }

    private Individual performDiploid() {
        //mutation of chromosome

        final Pair<String,String> diploidFromString = OperatorUtils.getDiploidFromString(val.getRepresentation());

        Random rand = new Random(System.currentTimeMillis());
        final char[] chars1 = diploidFromString.getVal1().toCharArray();
        final char[] chars2 = diploidFromString.getVal2().toCharArray();
        int position1 = rand.nextInt(chars1.length - 1) + 1;
        if (chars1[position1] == 'r') {
            chars1[position1] = 'R';
        } else if (chars1[position1] == 'R') {
            chars1[position1] = 'r';
        } else if (chars1[position1] == 'D') {
            chars1[position1] = 'd';
        } else if (chars1[position1] == 'd') {
            chars1[position1] = 'D';
        }

        return OperatorUtils.extractFromString(String.valueOf(chars1), String.valueOf(chars2));
    }

    private Individual performGaploid() {
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
