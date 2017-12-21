package by.it.group573602.kolesnikovich.lesson01;

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

        Integer integers[] = new Integer[6*m];
        integers[0]=0;
        integers[1]= 1;
        int count = 1;
        for(int i =2; i< m*6; i++){
            integers[i]=((integers[i-1] +(integers[i-2]))%m);
            count ++;
            if(integers[i] == 1 && integers[i-1] == 0){
                break;
            }
        }
        int indexRes = (int)n%count;


        return integers[indexRes];
    }


}

