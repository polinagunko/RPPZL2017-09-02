package by.it.group573602.gurskiy2.lesson01;

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
        int n = 8;
        int m = 3;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> numbersOfFibo = new ArrayList();
            numbersOfFibo.add((long)0);
            numbersOfFibo.add((long)1);
            for(int i = 2; i < m * 6; i++){
                numbersOfFibo.add((numbersOfFibo.get(i - 1) + numbersOfFibo.get(i - 2)) % m);
                if(numbersOfFibo.get(i) == 1 && numbersOfFibo.get(i-1) == 0){
                    break;
                }
        }
        long periodOfPizano = numbersOfFibo.size() - 2; // находим период Пизано
        int val = (int)(n % periodOfPizano);
        return numbersOfFibo.get(val);

    }


}

