package by.it.group573601.laysha.lesson02;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class Lesson2Test {
    public Lesson2Test() {
    }

    @Test
    public void A_VideoRegistrator() throws Exception {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1.0D, 1.1D, 1.6D, 2.2D, 2.4D, 2.7D, 3.9D, 8.1D, 9.1D, 5.5D, 3.7D};
        List<Double> starts = instance.calcStartTimes(events, 1.0D);
        boolean ok = starts.toString().equals("[1.0, 2.2, 3.7, 5.5, 8.1]");
        Assert.assertTrue("slowA failed", ok);
    }
}
