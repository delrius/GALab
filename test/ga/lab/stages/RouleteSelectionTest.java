package ga.lab.stages;

import ga.lab.Individual;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouleteSelectionTest extends TestCase {
    public void testPerformSelectionSizeDifferent() throws Exception {
        List<Individual> list = new ArrayList<>();
        int n = 100;
        for (int i =0 ; i< n; i++) {
            list.add(Individual.getRandom());
        }
        ISelection selectionAlg = new RouleteSelection();
        int selNumber = new Random().nextInt(list.size()) + 1;
        final List<Individual> actual = selectionAlg.performSelection(list, selNumber);
        Assert.assertEquals(actual.size(), selNumber);
    }

    public void testPerformSelectionSizeEqual() throws Exception {
        List<Individual> list = new ArrayList<>();
        int n = 100;
        for (int i =0 ; i< n; i++) {
            list.add(Individual.getRandom());
        }
        ISelection selectionAlg = new RouleteSelection();
        int selNumber = list.size();
        final List<Individual> actual = selectionAlg.performSelection(list, selNumber);
        Assert.assertEquals(actual.size(), selNumber);
    }
}
