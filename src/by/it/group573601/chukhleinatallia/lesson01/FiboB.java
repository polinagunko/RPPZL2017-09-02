package by.it.group573601.chukhleinatallia.lesson01;

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
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger fiBon[];
        fiBon= new BigInteger[n+1];
        fiBon[0]= BigInteger.ZERO;
        fiBon[1]= BigInteger.ONE;
        for (int a=2 ; a<= n; a++){ //начиная 3 эл считаем сумму пред.
            fiBon[a]= fiBon[a-2].add(fiBon[a-1]);
        }
        return fiBon[n];
    }


}
