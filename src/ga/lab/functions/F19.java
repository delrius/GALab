package ga.lab.functions;

import java.util.List;

public class F19 extends Function {

	@Override
	public Double calculate(List<Double> args) {
		double fitness = 0;
		for (Double x : args) {
			fitness += Math.pow(Math.E,
					(-2 * Math.log(2) * (Math.pow((x - 0.08) / 0.854, 2))))
					* Math.pow(
							Math.sin(5 * Math.PI * (Math.pow(x, 0.75) - 0.05)),
							6);

		}
		return fitness;
	}
}