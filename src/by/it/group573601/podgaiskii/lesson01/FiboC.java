package by.it.group573601.podgaiskii.lesson01;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(int n, int m) {
        int a = 0, b = 1;
        ArrayList<Integer> myArray = new ArrayList<Integer>();
        myArray.add(a);
        myArray.add(b);

        for (int i = 0; i < n; i++) {
            int bOld = b;
            b = (b + a) % m;
            a = bOld;
            if (a == 0 && b == 1)
            {
                myArray.remove(i - 1 );
                break;
            }
            else
                myArray.add(b);
        }
        return myArray.get(n % myArray.size());
    }


}

