package ga.lab.functions;

import java.util.List;

public class F15 extends Function {

    @Override
    public Double calculate(List<Double> args) {
        int n = args.size();
        double fitness = 0;
        for (Double x : args) {
            fitness += Math.pow(Math.sin(5 * Math.PI * x), 6);
        }
        return (1 / n) * fitness;
    }
}
