package by.it.group573601.podgaiskii.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;

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
        Integer n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        BigInteger myArray[] = new BigInteger[n + 1];
        myArray[0] = BigInteger.valueOf(0);
        myArray[1] = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++)
            myArray[i] = myArray[i - 1].add(myArray[i - 2]);
        return myArray[n];
    }

}

