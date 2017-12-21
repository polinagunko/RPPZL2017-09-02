package by.it.group573601.arobey.lesson05;

import java.awt.*;
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
    private class Point  implements Comparable<Point>{
        int x;
        int index;


        private Point(int x, int pointIndex){
            this.x = x;
            this.index = pointIndex;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Point o) {
            //подумайте, что должен возвращать компаратор отрезков
            if ((x - o.x) !=0 )
                return x - o.x;
            else
                return index - o.index;
        }
    }

    private void swap(Point[] a, int i, int j){
        Point t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private int[] partition3(Point[] a, int low, int high){
        Random random = new Random();
        int rnd = low + random.nextInt(high - low);
        int pivot = low;
        int pivEnd=low;
        swap(a, pivot, rnd);
        for (int i = low + 1; i <= high; i++){
            if (a[i].compareTo(a[pivot])<=0) {
                {
                    pivEnd++;
                    swap(a, i, pivEnd);
                    if (a[pivEnd].compareTo(a[pivot]) < 0) {
                        swap(a, pivEnd, pivot);
                        pivot++;
                    }
                }
            }
        }
        return new int[]{pivot, pivEnd};
    }

    private void qsort3(Point[] a, int low, int high){
        while (low < high){
            int [] mid = partition3(a, low, high);
            qsort3(a, low, mid[0]-1);
            low = mid[1]+1;
        }
    }

    private void qsort3(Point[] a){
        qsort3(a,0,a.length-1);
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        //число точек
        int m = scanner.nextInt();
        Point[] points=new Point[m + n + n];
        int[] result = new int[m];
        //читаем сами отрезки
        int index = 0;
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if(start > stop){
                {
                    int tmp = start;
                    start = stop;
                    stop = tmp;
                }
            }
            points[index++] = new Point(start, -1);
            points[index++] = new Point(stop,m+1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            points[index++] = new Point(x, i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        qsort3(points);
        int segmentCount = 0;
        for(Point point : points){
            if (point.index < 0)
                segmentCount++;
            else if (point.index > m)
                segmentCount--;
            else result [point.index] = segmentCount;
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
