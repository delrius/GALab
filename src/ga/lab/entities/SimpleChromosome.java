package ga.lab.entities;

import java.util.Random;

public class SimpleChromosome extends Chromosome {

    private int value;
    private String representation;

    private SimpleChromosome() {
    }

    // TODO: check for range
    public SimpleChromosome(String value) {
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

    public SimpleChromosome(int value) {
        this.value = value;
        this.representation = encodeChromosome(value);
    }

    public static int grayEncode(Integer ch) {
        return ch ^ (ch >>> 1);
    }

    public static int grayDecode(int n) {
        int p = n;
        while ((n >>>= 1) != 0)
            p ^= n;
        return p;
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

}
