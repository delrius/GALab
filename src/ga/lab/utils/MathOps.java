package ga.lab.utils;

public class MathOps {
    public static Double root(int number, int dimension) {
        return root((double) number, dimension);
    }

    public static Double root(Double number, int dimension) {
        assert (dimension != 0);
        return Math.pow(Math.E, Math.log(number) / dimension);
    }
}
