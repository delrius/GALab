package ga.lab.functions;

import java.util.List;

public class F18 extends Function {

	@Override
	public Double calculate(List<Double> args) {
		int n = args.size();
		double fitness = 0;
		for (Double x : args) {
			fitness += Math.pow(
					Math.sin(5 * Math.PI * (Math.pow(x, 0.75) - 0.05)), 6);
		}
		return (1. / n) * fitness;
	}
}