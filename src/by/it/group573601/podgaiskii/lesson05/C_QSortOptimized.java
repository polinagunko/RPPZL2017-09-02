package by.it.group573601.podgaiskii.lesson05;

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
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop){
            if (start < stop) {
                this.start = start;
                this.stop = stop;
            }
            else
            {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment object) {
            return Integer.compare(start, object.start);
        }
    }

    private class Point implements Comparable<Point> {
        int x;
        int index;

        private Point(int x, int index) {
            this.x = x;
            this.index = index;
        }

        @Override
        public int compareTo(Point point) {
            if (x != point.x)
                return x - point.x;
            else
                return index - point.index;
        }
    }

    private void swap(Point[] array, int i, int j) {
        Point temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int[] partition3(Point[] array, int left, int right) {
        Random random = new Random();
        int rand = left + random.nextInt(right - left);
        int pivot = left;
        int pivotEnd = left;
        swap(array, pivot, rand);
        for (int i = left + 1; i <= right; i++)
            if (array[i].compareTo(array[pivot]) <= 0) {
                pivotEnd++;
                swap(array, i, pivotEnd);
                if (array[pivotEnd].compareTo(array[pivot]) < 0) {
                    swap(array, pivotEnd, pivot);
                    pivot++;
                }
            }
        return new int[] {pivot, pivotEnd};
    }

    private void quickSort3(Point[] array, int left, int right) {
        while (left < right) {
            int[] mid = partition3(array, left, right);
            quickSort3(array, left, mid[0] - 1);
            left = mid[1] + 1;
        }
    }

    private void quickSort3(Point[] array) {
        quickSort3(array, 0, array.length - 1);
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        Point[] points = new Point[n + n + m];
        int[] result = new int[m];

        //читаем сами отрезки
        int index = 0;
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(),scanner.nextInt());
            points[index++] = new Point(segments[i].start, -1);
            points[index++] = new Point(segments[i].stop, m + 1);
        }
        //читаем точки
        for (int i = 0; i < m; i++)
            points[index++] = new Point(scanner.nextInt(), i);
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort3(points);
        int segmentCount = 0;
        for (Point point : points) {
            if (point.index < 0)
                segmentCount++;
            else if (point.index > m)
                segmentCount--;
            else
                result[point.index] = segmentCount;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/podgaiskii/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
