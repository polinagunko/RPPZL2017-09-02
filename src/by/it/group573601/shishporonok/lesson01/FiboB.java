package by.it.group573601.shishporonok.lesson01;

import java.math.BigInteger;

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
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        BigInteger C[] = new BigInteger[n+1];
        C[0]= BigInteger.ZERO;
        C[1]= BigInteger.ONE;
        for(int i=2; i<=n; i++)
            C[i] = C[i - 1].add(C[i - 2]);
        return C[n];
    }

}

