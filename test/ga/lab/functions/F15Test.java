package ga.lab.functions;

import org.junit.Assert;
import org.junit.Test;

public class F15Test {
    @Test
    public void testCalculate() throws Exception {
        Double [] xs = new Double[] {0.1, 0.3, 0.5, 0.7, 0.9};
        Double expected = 1.;

        for (Double x: xs) {
            Double actual = Functions.F15.calculate(x);
            Assert.assertEquals("x = " + x + " y = " + actual, expected, actual, 0.01);
        }
    }
}
