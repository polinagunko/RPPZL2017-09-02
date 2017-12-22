package by.it.group573601.PotapchukA.lesson_1;

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
        int n = 33;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        //(n<2) return BigInteger.valueOf(1); //Может, нам и вычислять ничего не нужно?

        BigInteger[] fs = new BigInteger[n+1]; //Создаём массив для значений
        fs[0]=BigInteger.valueOf(0);
        fs[1]=BigInteger.valueOf(1); //Задаём начальные состояния

        for(int i=2; i<n+1; i++){
                fs[i]=fs[i-1].add(fs[i-2]);
        }
        return fs[n];
    }
}

