package ga.lab;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Random;

public class ChromosomeTest extends TestCase {
    public void testGrayEncode() throws Exception {
        int[] n = new int[]{7, 9};
        int[] expected = new int[]{4, 13};
        for (int i = 0; i < n.length; ++i) {
            final int actual = Chromosome.grayEncode(n[i]);
            Assert.assertEquals(expected[i], actual);
        }
    }

    public void testEncode1000() throws Throwable {
        int n = 1000;
        final String s = Chromosome.encodeChromosome(n);
        Assert.assertEquals("Length should be 10, but instead is " + s.length(), 10, s.length());
    }

    public void testEncode0() throws Throwable {
        int n = 0;
        final String s = Chromosome.encodeChromosome(n);
        Assert.assertEquals("Length should be 10, but instead is " + s.length(), 10, s.length());
    }

    public void testEncodeRandom() throws Throwable {
        int n = 10;
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            final String s = Chromosome.encodeChromosome(rand.nextInt(1001));
            Assert.assertEquals("Length should be 10, but instead is " + s.length(), 10, s.length());
        }
    }

    public void testMutate() {
        int n = 867;
        Chromosome chr = new Chromosome(n);
        final Chromosome mutate = chr.mutate();
        mutate.getChromosome();
        Assert.assertNotSame(chr.getChromosome(), mutate.getChromosome());
    }
}
