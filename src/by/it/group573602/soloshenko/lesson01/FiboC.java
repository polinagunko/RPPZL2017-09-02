package by.it.group573602.soloshenko.lesson01;


import java.util.Vector;
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

       long mas[];
       mas=new long[6*m];
       mas[0] = 0;
       mas[1]=1;
       long t=1;
       for  ( int j = 2; j < n; ++j){
           mas[j]=(mas[j-1]+mas[j-2])%m;
           ++t;
           if(mas[j] == 1 && (mas[j-1]==0)) break;
       }
return mas[(int)(n%t)];
        }




}

