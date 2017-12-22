package by.it.group573601.budaev.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {


    int[] countSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных

        int min=Integer.MAX_VALUE,max=0;
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] points=new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
            if(points[i]>max)
                min=points[i];
            else if(points[i]<min)
                max=points[i];
        }
        System.out.println("min="+min);
        System.out.println("max=="+max);


int[] mas = new int[max-min+1];
for(int i=0; i<n;i++){
    mas[points[i]-min]++;
}
int ip=0;
        for(int i=min;i<=max;i++){
            for(int j=0;j<mas[i-min];j++){
                points[ip++]=i;
            }
        }

        return points;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}