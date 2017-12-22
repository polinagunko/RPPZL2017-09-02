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
        long mass[];
        mass=new long[6*m];
        mass[0]=0;
        mass[1]=1;
        int k=2;
        long age=1;
        do{
            mass[k]=(mass[k-1]+mass[k-2])%m;
            age++;
            if(mass[k]==1&&mass[k-1]==0){
                break;
                }
                k++;
        }while (k<n);
        int temp=(int)(n%age);
        return mass[temp];
    }


}

