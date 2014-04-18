package ga.lab.entities;

public abstract class Chromosome {
    public static int LENGTH = 10;
    public abstract String build();
    public abstract double getValue();

    protected static int grayEncode(Integer ch) {
        return ch ^ (ch >>> 1);
    }

    protected static int grayDecode(int n) {
        int p = n;
        while ((n >>>= 1) != 0)
            p ^= n;
        return p;
    }
}
