package by.it.group573601.laysha.lesson07;

import org.junit.Assert;
import org.junit.Test;

public class Lesson8Test {
    public Lesson8Test() {
    }

    @Test
    public void A() throws Exception {
        A_EditDist instance = new A_EditDist();
        Assert.assertEquals("A1 failed", (long)instance.getDistanceEdinting("ab", "ab"), 0L);
        Assert.assertEquals("A2 failed", (long)instance.getDistanceEdinting("short", "ports"), 3L);
        Assert.assertEquals("A3 failed", (long)instance.getDistanceEdinting("distance", "editing"), 5L);
    }
}
