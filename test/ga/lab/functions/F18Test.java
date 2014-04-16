package ga.lab.functions;

import org.junit.Assert;
import org.junit.Test;

public class F18Test {
	@Test
	public void testCalculate() throws Exception {
		Double[] xs = new Double[] { 0.080, 0.247, 0.451, 0.681, 0.934 };
		Double expected = 1.;

		for (Double x : xs) {
			Double actual = Functions.F18.calculate(x);
			Assert.assertEquals("x = " + x + " y = " + actual, expected,
					actual, 0.01);
		}
	}
}