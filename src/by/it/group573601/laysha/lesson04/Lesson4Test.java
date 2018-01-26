package by.it.group573601.laysha.lesson04;

import java.io.FileInputStream;
import java.io.InputStream;
import org.junit.Assert;
import org.junit.Test;

public class Lesson4Test {
    public Lesson4Test() {
    }

    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        int[] result = instance.findIndex(stream);
        StringBuilder sb = new StringBuilder();
        int[] var6 = result;
        int var7 = result.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            int index = var6[var8];
            sb.append(index).append(" ");
        }

        boolean ok = sb.toString().trim().equals("3 1 -1 1 -1");
        Assert.assertTrue("A failed", ok);
    }
}
