package by.it.group573601.lugovskaya.lesson01;

import java.math.*;

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
        int n = 55555;
        BigInteger m=BigInteger.valueOf(321);
        System.out.printf("fastB(%d)=%d \n\t time=%d\n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        BigInteger []a=new BigInteger[n+1];
        a[0]=BigInteger.valueOf(0);
        a[1]=BigInteger.valueOf(1);
        for(int i=2;i<=n;i++)
        {
            a[i]=a[i-1].add(a[i-2]);
        }
        return a[n];
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
    }

}

