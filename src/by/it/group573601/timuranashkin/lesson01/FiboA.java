package by.it.group573601.timuranashkin.lesson01;

import java.math.BigInteger;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

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


    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        int resFiboA;
        double sqrtnum,doublefiboA;
        sqrtnum = sqrt(5);
        doublefiboA = (pow(((1+sqrtnum)/2),n)-pow(((1-sqrtnum)/2),n))/sqrtnum;
        resFiboA = (int) doublefiboA;
        return resFiboA;
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if(0==n) return BigInteger.ZERO;
        if(1==n) return BigInteger.ONE;
        return slowA(n-1).add(slowA(n-2));
    }



}

