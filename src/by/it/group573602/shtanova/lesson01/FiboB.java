package by.it.group573602.shtanova.lesson01;

import java.math.BigInteger;
import java.util.Scanner;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n =55;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        int total;
        BigInteger total = new Biginteger("1000000000000000");
        BigInteger[] a = new BigInteger[total.intValue()];

        int i, d=0;
        if (n<=2)
            return BigInteger.ONE;
        else {
            a[0]=BigInteger.ONE;
            a[1]=BigInteger.ONE;
            for (i = 2; i < n; i++) {
                a[i] = a[i - 1]. add( a[i - 2]);
                System.out.printf("a[%d]=%d \n\t ", i, a[i]);

            }

            return a[i];
        }
    }
}

