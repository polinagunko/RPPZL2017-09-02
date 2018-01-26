package by.it.group573601.laysha.lesson03;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class Lesson3Test {
    public Lesson3Test() {
    }

    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        String result = instance.encode(f);
        boolean ok = result.equals("01001100100111");
        Assert.assertTrue("A failed", ok);
    }
}