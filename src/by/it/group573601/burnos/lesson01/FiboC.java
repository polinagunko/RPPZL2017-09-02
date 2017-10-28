package by.it.group573601.burnos.lesson01;
import java.util.ArrayList;
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
//период пизано-это длина периода этой последовательности

    ArrayList<Long>getPeriod(long m){
        ArrayList<Long> list= new ArrayList();
        list.add((long)0);
        list.add((long)1);
        for (int i=2; i<m*6;i++)
        {
            list.add((list.get(i-1)+list.get(i-2))%m);
            if(list.get(i)==1&&list.get(i-1)==0){break;
            }


    }return list;
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> s = getPeriod(m);
        long p = s.size() - 2; // находим период Пизано
        int index = (int)(n % p);//статком от деления является такой элемент списка, индекс которого есть остаток от деления числа n на период Пизано
        return s.get(index);
    }


}

