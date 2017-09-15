package by.it.group573601.Stephanovich.Lesson1;

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
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)

        ArrayList<BigInteger> sequence = new ArrayList<>((int)n);
        sequence.add(BigInteger.ZERO);
        sequence.add(BigInteger.ONE);
        for(int i = 2; i <= (int) n; i++) {
            sequence.add(sequence.get(i-1).add(sequence.get(i-2)));
        }
        return sequence.get((int) n);

    }

}

