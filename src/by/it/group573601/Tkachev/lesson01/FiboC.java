package by.it.group573601.Tkachev.lesson01;

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

        long Mass[] = new long[6 * m];
        long J = 1;
        int I = 2;

        Mass[0] = 0;
        Mass[1] = 1;

        do {
            Mass[I] = (Mass[I - 1] + Mass[I - 2]) % m;

            J += 1;

            if(Mass[I] == 1 && Mass[I - 1] == 0)
                break;

            I += 1;
        } while (I < n);

        return Mass[(int)(n % J)];
    }
}

