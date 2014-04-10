package ga.lab.functions;

import java.util.List;

public interface IFunction {
    public Double calculate(List<Double> args);

    public Double calculate(Double ... args);
}
