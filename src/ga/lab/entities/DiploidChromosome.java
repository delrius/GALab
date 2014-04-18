package ga.lab.entities;

import java.util.Random;

public class DiploidChromosome extends Chromosome {
    public static char DOMINANT_ZERO = 'd';
    public static char DOMINANT_ONE = 'D';
    public static char RECESS_ZERO = 'r';
    public static char RECESS_ONE = 'R';
    private int length;

    private SimpleChromosome chromosome1;
    private SimpleChromosome chromosome2;

    private Boolean[] dominancy1;
    private Boolean[] dominancy2;

    private double value;
    private String representation;

    private DiploidChromosome() {
    }

    private DiploidChromosome(SimpleChromosome chromosome1, SimpleChromosome chromosome2, Random random) {
        assert (chromosome1.build().length() == chromosome2.build().length());
        this.chromosome1 = chromosome1;
        this.chromosome2 = chromosome2;
        this.length = chromosome1.build().length();
        dominancy1 = new Boolean[chromosome1.build().length()];
        dominancy2 = new Boolean[chromosome2.build().length()];
        for (int i=0; i< dominancy1.length; i++) {
            dominancy1[i] = random.nextBoolean();
        }
        for (int i=0; i< dominancy2.length; i++) {
            dominancy2[i] = random.nextBoolean();
        }
        representation = buildRepresentation();
        value = calcValue();
    }

    private DiploidChromosome(SimpleChromosome chromosome1, SimpleChromosome chromosome2, Boolean[] table1, Boolean[] table2) {
        assert (chromosome1.build().length() == chromosome2.build().length());
        this.length = chromosome1.build().length();
        this.chromosome1 = chromosome1;
        this.chromosome2 = chromosome2;
        this.dominancy1 = table1;
        this.dominancy2 = table2;
        representation = buildRepresentation();
        value = calcValue();
    }

    private static Pair<SimpleChromosome, Boolean[]> getChr(String str) {
        Boolean[] domrectable = new Boolean[str.length()];
        StringBuilder chr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            final Pair<Character, Boolean> gene = getGene(str.charAt(i));
            chr.append(gene.getVal1());
            domrectable[i] = gene.getVal2();
        }
        return new Pair<>(SimpleChromosome.buildFromString(chr.toString()), domrectable);
    }

    private static Pair<Character, Boolean> getGene(char enc) {
        if (enc == DOMINANT_ONE) {
            return new Pair<>('1', true);
        } else if (enc == DOMINANT_ZERO) {
            return new Pair<>('0', true);
        } else if (enc == RECESS_ONE) {
            return new Pair<>('1', false);
        } else if (enc == RECESS_ZERO) {
            return new Pair<>('0', false);
        }
        return null;
    }

    private static char getGene(char v, boolean dom) {
        if (v == '1') {
            return dom ? DOMINANT_ONE : RECESS_ONE;
        }
        if (v == '0') {
            return dom ? DOMINANT_ZERO : RECESS_ZERO;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String build() {
        return representation;
    }

    @Override
    public double getValue() {
        return value;
    }

    private String buildRepresentation() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < length; ++i) {
            builder.append(getGene(chromosome1.build().charAt(i), dominancy1[i]));
        }
        builder.append("--");
        for (int i=0; i < length; ++i) {
            builder.append(getGene(chromosome2.build().charAt(i), dominancy2[i]));
        }
        return builder.toString();
    }

    private double calcValue() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<length; ++i) {
            char c1 = build().charAt(i);
            char c2  = build().charAt(i);
            builder.append(combine(c1, c2));
        }
        Integer in = Integer.parseInt(builder.toString(), 2);
        return grayDecode(in) / 1000.;
    }

    private String combine(char gene1, char gene2) {
        //D
        if ((gene1 == 'D') &&  (gene2 == 'D')) {
            return "1";
        }
        if ((gene1 == 'D') &&  (gene2 == 'd')) {
            return "1";
        }
        if ((gene1 == 'D') &&  (gene2 == 'R')) {
            return "1";
        }
        if ((gene1 == 'D') &&  (gene2 == 'r')) {
            return "1";
        }
        //d
        if ((gene1 == 'd') &&  (gene2 == 'D')) {
            return "0";
        }
        if ((gene1 == 'd') &&  (gene2 == 'd')) {
            return "0";
        }
        if ((gene1 == 'd') &&  (gene2 == 'R')) {
            return "0";
        }
        if ((gene1 == 'd') &&  (gene2 == 'r')) {
            return "0";
        }
        //R
        if ((gene1 == 'R') &&  (gene2 == 'D')) {
            return "1";
        }
        if ((gene1 == 'R') &&  (gene2 == 'd')) {
            return "0";
        }
        if ((gene1 == 'R') &&  (gene2 == 'R')) {
            return "1";
        }
        if ((gene1 == 'R') &&  (gene2 == 'r')) {
            return "0";
        }
        //r
        if ((gene1 == 'r') &&  (gene2 == 'D')) {
            return "1";
        }
        if ((gene1 == 'r') &&  (gene2 == 'd')) {
            return "0";
        }
        if ((gene1 == 'r') &&  (gene2 == 'R')) {
            return "1";
        }
        if ((gene1 == 'r') &&  (gene2 == 'r')) {
            return "0";
        }
        throw new IllegalArgumentException();
    }

    public static Chromosome buildRandom(Random rand) {
        return new DiploidChromosome(SimpleChromosome.buildRandom(rand), SimpleChromosome.buildRandom(rand), rand);
    }

    public static Chromosome buildFromString(String st1, String st2) {
        final Pair<SimpleChromosome, Boolean[]> chr1 = getChr(st1);
        final Pair<SimpleChromosome, Boolean[]> chr2 = getChr(st2);
        return new DiploidChromosome(chr1.getVal1(), chr2.getVal1(), chr1.getVal2(), chr2.getVal2());
    }
}
