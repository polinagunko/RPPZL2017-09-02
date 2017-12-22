package by.it.group573601.PotapchukA.lesson_1;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }

    int t=0, p=1, u=0;
    private int calc(int n) {

        for(int i=1; i<n; i++) {
            u = t + p;
            t = p;
            p = u;
        }
        return u;
    }


    BigInteger slowA(Integer n) {

        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n == 0) return BigInteger.valueOf(0);
        if (n == 1) return BigInteger.valueOf(1);
        else {
            BigInteger result = slowA(n - 1).add(slowA(n - 2));
            return result;
        }
    }




}
