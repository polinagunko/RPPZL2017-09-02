package by.it.group573601.voloshchuk.lesson01;

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
        long array[]=new long[6*m];
        array[0]=0;
        array[1]=1;
        int i=2;
        long period=1;
        do{
            array[i]=(array[i-1]+array[i-2])%m;
            period++;
            if(array[i] == 1 && array[i-1] == 0){
                break;
            }
            i++;
        }while (i<n);
        int val=(int)(n%period);
        return array[val];
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        //return 0L;
    }


}

