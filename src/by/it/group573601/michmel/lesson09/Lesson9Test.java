package by.it.group573601.michmel.lesson09;

import by.it.a_khmelev.lesson09.*;
import by.it.a_khmelev.lesson09.B_Knapsack;
import by.it.a_khmelev.lesson09.C_Stairs;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Lesson9Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson09/dataA.txt");
        by.it.group573601.michmel.lesson09.A_Knapsack instance = new by.it.group573601.michmel.lesson09.A_Knapsack();
        int res=instance.getMaxWeight(stream);
        assertEquals("A failed", res, 14);
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson09/dataB.txt");
        by.it.group573601.michmel.lesson09.B_Knapsack instance = new by.it.group573601.michmel.lesson09.B_Knapsack();
        int res=instance.getMaxWeight(stream);
        assertEquals("B failed", res, 9);
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson09/dataC.txt");
        by.it.group573601.michmel.lesson09.C_Stairs instance = new  by.it.group573601.michmel.lesson09.C_Stairs();
        int res=instance.getMaxSum(stream);
        assertEquals("C failed", res, 3);
    }

}
