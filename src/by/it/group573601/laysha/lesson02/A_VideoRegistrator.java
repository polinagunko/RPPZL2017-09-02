package by.it.group573601.laysha.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A_VideoRegistrator {
    public A_VideoRegistrator() {
    }

    public static void main(String[] args) {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1.0D, 1.1D, 1.6D, 2.2D, 2.4D, 2.7D, 3.9D, 8.1D, 9.1D, 5.5D, 3.7D};
        List<Double> starts = instance.calcStartTimes(events, 1.0D);
        System.out.println(starts);
    }

    List<Double> calcStartTimes(double[] events, double workDuration) {
        List<Double> result = new ArrayList();
        int i = 0;
        Arrays.sort(events);

        while(i != events.length - 1) {
            result.add(events[i]);
            double start = events[i];

            for(double end = start + workDuration; events[i] < end; ++i) {
                ;
            }
        }

        return result;
    }
}
