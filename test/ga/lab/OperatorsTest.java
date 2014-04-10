package ga.lab;

import org.junit.Assert;
import org.junit.Test;

public class OperatorsTest {
    @Test
    public void testCalculateR() throws Exception {
        double r = Operators.calculateR(3, 0, 1);
        Assert.assertEquals("R is calculated wrong", r, 1./2 * Math.sqrt(3), 0.001);
    }
}
