package by.it.group573601.laysha.lesson05;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class Lesson5Test {
    public Lesson5Test() {
    }

    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        boolean ok = Arrays.equals(result, new int[]{1, 0, 0});
        Assert.assertTrue("A failed", ok);
    }
}
