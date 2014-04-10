package ga.lab;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class MathOpsTest {
    private Random random;

    @Test
    public void testRoot() throws Exception {
        random = new Random(System.currentTimeMillis());
        for (int i=1; i < 20; i++) {
            int dim = Math.abs(random.nextInt(10) + 1);
            int number = Math.abs(random.nextInt(1000));
            Double root = MathOps.root(Math.pow(number, dim), dim);
            String message = number + "^" + dim;
            Assert.assertEquals(message, root, number, 0.01);
        }
    }
}
