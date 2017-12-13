package by.it.group573601.Tkachev.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment obj) {
            if((start - obj.start) != 0){
                return start - obj.start;
            }

            else {
                return stop - obj.stop;
            }
        }
    }

    protected void swap(Segment[] a, int i, int j){
        Segment t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected int[] division(Segment[] a, int low, int high){

        Random rand = new Random();
        int ran = low + rand.nextInt(high - low);

        int Priv;
        Priv = low;

        int PivEnd;
        PivEnd = low;

        swap(a, Priv, ran);

        for(int I = low + 1; I <= high; I++)
        {
            if(a[I].compareTo(a[Priv]) <= 0)
            {
                PivEnd += 1;
                swap(a, I, PivEnd);

                if(a[PivEnd].compareTo(a[Priv]) < 0)
                {
                    swap(a, PivEnd, Priv);
                    Priv = Priv + 1;
                }
            }
        }
        int res[] = new int[]{Priv, PivEnd};
        return res;
    }

    protected void quickSortC(Segment[] a, int low, int high){
        while(low < high)
        {
            int[] middle = division(a, low, high);
            quickSortC(a, low, middle[0] - 1);
            low = middle[1] + 1;
        }
    }

    protected void quickSortC(Segment[] a)
    {
        quickSortC(a, 0, a.length-1);
    }


    protected int[] getAccessory2(InputStream stream) throws FileNotFoundException {

        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Segment[] segments = new Segment[n+n+m];
        int[] result = new int[m];

        //читаем сами отрезки
        int index = 0;
        for (int i = 0; i < n; i++) {

            //читаем начало и конец каждого отрезка
            int start = scanner.nextInt();
            int stop = scanner.nextInt();

            if(start > stop)
            {
                int tmp = start;
                start = stop;
                stop = tmp;
            }

            segments[index++]=new Segment(start, -1);
            segments[index++] = new Segment(stop, m+1);
        }

        //читаем точки
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            segments[index++] = new Segment(x, i);
        }

        quickSortC(segments);
        int segmentCount = 0;

        for(Segment eachSegment : segments)
        {
            if(eachSegment.stop < 0) {
                segmentCount++;
            }
            else if (eachSegment.stop > m) {
                segmentCount--;
            }

            else {
                result[eachSegment.stop] = segmentCount;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
