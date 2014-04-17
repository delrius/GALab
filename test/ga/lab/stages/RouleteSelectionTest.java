package ga.lab.stages;

import ga.lab.entities.Individual;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouleteSelectionTest extends TestCase {
    public void testPerformSelectionSizeDifferent() throws Exception {
        List<Individual> list = new ArrayList<>();
        int n = 100;
        for (int i = 0; i < n; i++) {
            list.add(Individual.random(3, 100));
        }
        ISelection selectionAlg = new RouleteSelection();

        // run for 20 times
        for (int i = 0; i < 20; i++) {
            int selNumber = new Random().nextInt(list.size()) + 1;
            final List<Individual> actual = selectionAlg.performSelection(list.toArray(new Individual[list.size()]), selNumber, new Random());
            Assert.assertEquals(actual.size(), selNumber);
        }
    }

    public void testPerformSelectionSizeEqual() throws Exception {
        List<Individual> list = new ArrayList<>();
        int n = 100;
        for (int i = 0; i < n; i++) {
            list.add(Individual.random(3, 100));
        }
        ISelection selectionAlg = new RouleteSelection();
        int selNumber = list.size();
        final List<Individual> actual = selectionAlg.performSelection(list.toArray(new Individual[list.size()]), selNumber, new Random());
        Assert.assertEquals(actual.size(), selNumber);
    }
}
