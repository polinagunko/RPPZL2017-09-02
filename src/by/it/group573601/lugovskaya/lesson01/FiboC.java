package by.it.group573601.lugovskaya.lesson01;

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
        int n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        long []mas=new long[6*m];
        mas[0]=0;
        mas[1]=1;
        long period=1;
        int i=2;
        do
        {
            mas[i]=(mas[i-1]+mas[i-2])%m;
            period++;
            if(mas[i]==1&&mas[i-1]==0) {break;}
            i++;
        }while(i<n);
        int nomer=(int)(n%period);
        return mas[nomer];
    }


}

