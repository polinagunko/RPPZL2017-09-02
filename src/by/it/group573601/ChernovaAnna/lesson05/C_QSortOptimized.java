package by.it.group573601.ChernovaAnna.lesson05;

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

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            if ((start - o.start) != 0)
                return start - o.start;
            else
                return stop - o.stop;
        }
    }

    private void swap(Segment[] segments, int i, int j) {
        Segment temp = segments[i];
        segments[i] = segments[j];
        segments[j] = temp;
    }

    private int[] partition2(Segment[] segments, int low, int high) {
        Random random = new Random();
        int ran = low + random.nextInt(high - low);
        int pivot = low;
        int pivEnd = low;
        swap(segments, pivot, ran);
        for (int i = low + 1; i <= high; i++) {
            if (segments[i].compareTo(segments[pivot]) <= 0) {
                pivEnd++;
                swap(segments, i, pivEnd);
                if (segments[pivEnd].compareTo(segments[pivot]) < 0) {
                    swap(segments, pivEnd, pivot);
                    pivot++;
                }
            }
        }
        return new int[]{pivot,pivEnd};
    }

    private void quickSort2(Segment[] segments, int low, int high) {
        while (low < high) {
            int[] m = partition2(segments, low, high);
            quickSort2(segments, low, m[0] - 1);
            low = m[1] + 1;
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();

        //число точек
        int m = scanner.nextInt();
        Segment[] segments = new Segment[n + n + m];
        int[] result = new int[m];

        //читаем сами отрезки
        int index = 0;
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if (start > stop) {
                int temp = start;
                start = stop;
                stop = temp;
            }
            segments[index++] = new Segment(start, -1);
            segments[index++] = new Segment(stop, m + 1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            segments[index++] = new Segment(scanner.nextInt(), i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort2(segments, 0, segments.length - 1);

        int count = 0;
        for (Segment segment : segments) {
            if (segment.stop < 0)
                count++;
            else if (segment.stop > m)
                count--;
            else
                result[segment.stop] = count;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
