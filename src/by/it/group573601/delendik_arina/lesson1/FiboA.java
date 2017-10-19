package by.it.group573601.delendik_arina.lesson1;

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
        int n = 55;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 39;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        int fibo1=0;
        int fibo2=1;
        int fibonachi=0;
        System.out.print(fibo1+",");
        System.out.print(fibo2 +",");
        for(int i=1;  i<n; i++){
            fibonachi=fibo1+fibo2;
            fibo1=fibo2;
            fibo2=fibonachi;
            System.out.print(fibonachi +",");
        }
        return fibonachi;
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n == 1) return BigInteger.ONE;
        if (n < 1) return BigInteger.ZERO;
        else {
            BigInteger result = slowA(n - 1).add(slowA(n - 2));
            return result;
        }
    }
}


