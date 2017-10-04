package by.it.group573601.krautsou.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 15;
        int m = 4;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> numbers = new ArrayList();
        numbers.add((long) 0);
        numbers.add((long) 1);

        for (int i = 2; i < m * 6; i++) {
            numbers.add((numbers.get(i - 1) + numbers.get(i - 2)) % m);
            System.out.println("Nuberm" + numbers);
            if (numbers.get(i) == 1 && numbers.get(i - 1) == 0) {
                break;
            }
        }
        long periodOfPizano = numbers.size() - 2; // находим период Пизано
        int val = (int) (n % periodOfPizano);
        System.out.println("Val" + periodOfPizano);
        return numbers.get(val);
    }
}


