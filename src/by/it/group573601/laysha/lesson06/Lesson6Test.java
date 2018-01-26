package by.it.group573601.laysha.lesson06;

import java.io.FileInputStream;
import java.io.InputStream;
import org.junit.Assert;
import org.junit.Test;

public class Lesson6Test {
    public Lesson6Test() {
    }

    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/laysha/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        boolean ok = result == 3;
        Assert.assertTrue("A failed", ok);
    }
}
