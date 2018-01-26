package by.it.group573601.laysha.lesson01;

import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

public class FiboTest {
    public FiboTest() {
    }

    @Test(
            timeout = 2000L
    )
    public void slowA() throws Exception {
        FiboA fibo = new FiboA();
        BigInteger res = fibo.slowA(Integer.valueOf(33));
        boolean ok = res.toString().equals("3524578");
        Assert.assertTrue("slowA failed", ok);
    }
}