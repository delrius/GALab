package ga.lab.functions;

import org.junit.Assert;
import org.junit.Test;

public class F19Test {
	@Test
	public void testCalculate() throws Exception {
		Double[] xs = new Double[] { 0.080, 0.247, 0.451, 0.681, 0.934 };
		Double[] ys = new Double[] { 1.0, 0.948, 0.770, 0.503, 0.250 };

		// for (Double x: xs) {
		for (int i = 0; i < xs.length; i++) {
			Double actual = Functions.F19.calculate(xs[i]);
			Assert.assertEquals("x = " + xs[i] + " y = " + actual, ys[i],
					actual, 0.01);
		}
	}
}
