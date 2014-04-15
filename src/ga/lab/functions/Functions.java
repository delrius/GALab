package ga.lab.functions;

import java.util.List;

public enum Functions implements IFunction {
    F15(new F15()), F16(new F16()), F18(new F18()), F19(new F19());

    Functions(IFunction function) {
        this.function = function;
    }

    private IFunction function;

    @Override
    public Double calculate(List<Double> args) {
        return function.calculate(args);
    }

    @Override
    public Double calculate(Double... args) {
        return function.calculate(args);
    }
}
