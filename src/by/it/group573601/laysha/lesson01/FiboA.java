package by.it.group573601.laysha.lesson01;

import java.math.BigInteger;

public class FiboA {
    private long startTime = System.currentTimeMillis();

    public FiboA() {
    }

    private long time() {
        return System.currentTimeMillis() - this.startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", Integer.valueOf(n), fibo.calc(n), fibo.time());
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", Integer.valueOf(n), fibo.slowA(Integer.valueOf(n)), fibo.time());
    }

    private int calc(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n == 1 ? 1 : this.calc(n - 1) + this.calc(n - 2);
        }
    }

    BigInteger slowA(Integer n) {
        if (n.intValue() == 0) {
            return BigInteger.ZERO;
        } else {
            return n.intValue() == 1 ? BigInteger.ONE : this.slowA(n.intValue() - 1).add(this.slowA(n.intValue() - 2));
        }
    }
}
