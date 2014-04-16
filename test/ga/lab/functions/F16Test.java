package ga.lab.functions;

import org.junit.Assert;
import org.junit.Test;

public class F16Test {
	@Test
	public void testCalculate() throws Exception {
		Double[] xs = new Double[] { 0.1, 0.3, 0.5, 0.7, 0.9 };
		Double[] ys = new Double[] { 1.0, 0.9172, 0.7078, 0.4595, 0.2510 };

		// for (Double x: xs) {
		for (int i = 0; i < xs.length; i++) {
			Double actual = Functions.F16.calculate(xs[i]);
			Assert.assertEquals("x = " + xs[i] + " y = " + actual, ys[i],
					actual, 0.01);
		}
	}
}
