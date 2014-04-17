package ga.lab;

import ga.lab.algorithm.Algorithm;
import org.junit.Assert;
import org.junit.Test;

public class OperatorsTest {
	@Test
	public void testCalculateR() throws Exception {
		double r = Operators.calculateR(3, 0, 1);
		Assert.assertEquals("R is calculated wrong", r, 1. / 2 * Math.sqrt(3),
				0.001);
	}

	@Test
	public void testHammingDistance() throws Exception {
//TODO: revisit
//		int n1 = 1;
//		int n2 = 3;
//		Individual chr1 = new Individual(n1);
//		Individual chr2 = new Individual(n2);
//		int expected = 2;
//		double actual = Operators.getHammingDistance(chr1, chr2);
//		Assert.assertEquals("distance =" + actual, expected, actual, 0);
	}
	@Test
	public void testCalculateRadius() {
		int n = 1;
		int numberOfExtr = (int) Math.pow(5, n);
		double r = Operators.calculateR(n, 0, 1);
		double actual = Operators.calculateRadius(n, numberOfExtr, r);
		double expected = 0.1;
        Algorithm.isEuclidian = false;
        double actualHemming = Operators.calculateRadius(n, numberOfExtr, r);
        Assert.assertEquals("radius =" + actual, expected, actual, 0.01);
        Assert.assertEquals(2, actualHemming, 0.01);
	}
}
