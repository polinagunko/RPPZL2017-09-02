package by.it.group573602.zayats.lesson01;

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

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> list=new ArrayList();
        list.add((long)0);
        list.add((long)1);
        for(int i=2;i<6*m;i++){
            list.add((list.get(i-1)+list.get(i-2))%m);
            if((1==list.get(i)) && 0 == list.get(i-1)){
                break;
            }
        }
      long p;
        p=list.size()-2;
        int despasito;
        despasito=(int)(n%p);
        return list.get(despasito);

    }


}

