package by.it.group573602.gurskiy2.lesson05;

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
   /* private class Segment implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
//подумайте, что должен возвращать компаратор отрезков
            return 0;
        }
    }*/

    private class Point implements Comparable<Point> {
        int p;
        int i;

        /*private Point(int x, int p) {
            this.p = p;
            this.i = p;
        }*/
        private Point(int x, int i) {
            this.p = x;
            this.i = i;
        }
        @Override
        public int compareTo(Point obj) {
            if ((p - obj.p) != 0)
                return p - obj.p;
            else return i - obj.i;
        }
    }

    private void swap(Point[] mas, int i, int j) {
        Point t = mas[i];
        mas[i] = mas[j];
        mas[j] = t;
    }

    private int[] part(Point[] mas, int l, int h) {
        Random rendom = new Random();
        int r = l + rendom.nextInt(h - l);
        int pivot = l;
        int pivEnd = l;
        swap(mas, pivot, r);
        for (int i = l + 1; i <= h; i++) {
            if (mas[i].compareTo(mas[pivot]) <= 0) {
                pivEnd++;
                swap(mas, i, pivEnd);
                if (mas[pivEnd].compareTo(mas[pivot]) < 0) {
                    swap(mas, pivEnd, pivot);
                    pivot++;
                }
            }
        }
        return new int[]{pivot, pivEnd};
    }

    private void qsort(Point[] mas) {
        qsorttt(mas, 0, mas.length-1 );
    }

    private void qsorttt(Point[] mas, int l, int h) {
        while (l < h) {
            int[] m = part(mas, l, h);
            qsorttt(mas, l, m[0] - 1);
            l = m[1] + 1;
        }

    }
    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
//подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
//!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
//число отрезков отсортированного массива
        int n = scanner.nextInt();
        //Segment[] segments=new Segment[n];
//число точек
        int m = scanner.nextInt();
        int[] result=new int[m];
//int[] points=new int[m];
        Point[] points = new Point[n + n + m];

        int index = 0;
//читаем сами отрезки
        for (int i = 0; i < n; i++) {
//читаем начало и конец каждого отрезка
//segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if (start > stop) {
                int tmp = start;
                start = stop;
                stop = tmp;
            }
            points[index++] = new Point(start, - 1);
            points[index++] = new Point(stop, m + 1);
        }
//читаем точки
        for (int i = 0; i < m; i++) {
            //points[i]=scanner.nextInt();
            int x = scanner.nextInt();
            points[index++] = new Point(x, i);
        }
//тут реализуйте логику задачи с применением быстрой сортировки
//в классе отрезка Segment реализуйте нужный для этой задачи компаратор
       qsort(points);
        int s = 0;
        for (Point point : points) {
            if (point.i < 0)
                s++;
            else if (point.i > m)
                s--;
            else result[point.i] = s;
        }

//!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
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