package by.it.group573602.charnushevich.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано

    long remainder = n % getPisanoPeriod(m);
    long first = 0;
    long second = 1;

    long result = remainder;

        for (int i = 1; i < remainder; i++) {
            result = (first + second) % m;
            first = second;
            second = result;
        }

        return result % m;
    }

    long getPisanoPeriod(long m) {
        long a = 0;
        long b = 1;
        long c;
        long length;
        for (length = 0; length < 6 * m; length++) {
            c = (a + b) % m;
            a = b;
            b = c;
            if (a == 0 && b == 1) break;     //т.к. период Пизано начинается с 01, то его длина найдена
        }
        return length+1;
    }

}

