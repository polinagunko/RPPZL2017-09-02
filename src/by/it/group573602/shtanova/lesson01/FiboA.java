package by.it.group573602.shtanova.lesson01; // исходный код этого класса должен храниться в каталоге by.it.group573602.shtanova.lesson01

import java.math.BigInteger;// исп класса BigInteger (для работы с высокоточной арифмет)

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis(); // таймер, показ время работы программы

    private long time() {
        return System.currentTimeMillis() - startTime;
    } //Выведет в милисекундах

    public static void main(String[] args) {
        FiboA fibo = new FiboA();//объявление объекта класса FiboA
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    private int calc(int n) {
        if (n <=1) {
            return n;
        } else{
            return calc(n-1)+calc(n-2);
        }
    }


    BigInteger slowA(Integer n) {

       if (n==0) {
            return BigInteger.ZERO;
       }
        if (n==1) {
            return BigInteger.ONE;
        } else {
            return (slowA(n - 1).add(slowA(n - 2)));
        }
    }
}

