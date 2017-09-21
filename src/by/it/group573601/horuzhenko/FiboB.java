package by.it.group573601.horuzhenko;

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
        Integer i=new Integer(1);
        BigInteger mas[]=new BigInteger[n+1];
        for(i=0;i<n;i++)
        {

                if(0==i || 1==i) {
                    mas[i] = BigInteger.ONE;
                }
                else mas[i] = mas[i-1].add( mas[i-2]);


        }
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        return  mas[i-1];
    }

}

