package ga.lab.functions;

import java.util.Arrays;

public abstract class Function implements IFunction {

    @Override
    public Double calculate(Double... args) {
        return calculate(Arrays.asList(args));
    }
}
