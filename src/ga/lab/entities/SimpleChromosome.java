package ga.lab.entities;

import java.util.Random;

public class SimpleChromosome extends Chromosome {

    private int value;
    private String representation;

    private SimpleChromosome() {
    }

    private SimpleChromosome(String value) {
        Integer val = Integer.parseInt(value, 2);
        Random random = new Random(System.currentTimeMillis());
        if (val > 1000) {
            val = random.nextInt(1001);
        }
        this.representation = value;
        int decoded = grayDecode(val);
        if (decoded > 1000) {
            decoded = random.nextInt(1001);
        }
        this.value = decoded;
        this.representation = encodeChromosome(this.value);
    }

    private SimpleChromosome(int value) {
        this.value = value;
        this.representation = encodeChromosome(value);
    }

    // encode chromosome
    public static String encodeChromosome(Integer ch) {
        String res = Integer.toBinaryString(grayEncode(ch));
        if (res.length() != LENGTH) {
            String temp1 = "0000000000" + res;
            res = temp1.substring(temp1.length() - 10, temp1.length());
        }
        return res;
    }

    @Override
    public String build() {
        return representation;
    }

    public double getValue() {
        return value / 1000.;
    }

    public static SimpleChromosome buildRandom(Random rand) {
        return new SimpleChromosome(rand.nextInt(1001));
    }

    public static SimpleChromosome buildFromString(String st) {
        return new SimpleChromosome(st);
    }
}
