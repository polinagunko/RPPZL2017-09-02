package by.it.group573601.gylinskiy.lesson01;
import java.util.ArrayList;
/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC
{

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args)
    {
        FiboC fibo = new FiboC();
        int n = 10000000;
        int m = 47;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m)
    {
        ArrayList<Long> mas = new ArrayList<>();
        mas.add((long)0);
        mas.add((long)1);
        for(int i = 2; i < m*6; i++)
        {
            mas.add((mas.get(i-1)+mas.get(i-2))%m);
            if(mas.get(i) == 1 && mas.get(i - 1) == 0)
                break;
        }
        long Pizano = mas.size() - 2; // длинна периода
        int a = (int)(n%Pizano); // остаток от деления числа на период даст тот же ответ
                                // если бы мы нашли число фибоначи и нашли остаток от деления на число m
        return mas.get(a);
    }


}

