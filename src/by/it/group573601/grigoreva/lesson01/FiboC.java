package by.it.group573601.grigoreva.lesson01;

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

    long fasterC(long n, int m) {//см. период Пизано

        ArrayList<Long> s = new ArrayList();
        s.add(0L);
        s.add(1L);
        for(int i=2; i< m*6; i++){
            s.add((s.get(i-1) + s.get(i-2)) % m);
            if(s.get(i)==1 && s.get(i-1)==0){
                break;
            }

        }
        long per = s.size()-2;
        int a = (int)(n%per);
        return s.get(a);
    }


}

