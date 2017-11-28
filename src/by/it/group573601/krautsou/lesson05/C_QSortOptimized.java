package by.it.group573601.krautsou.lesson05;

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

    private class Segment implements Comparable<Segment> {
        int n;
        int k;

        private Segment(int n, int k) {
            this.n = n;
            this.k = k;
        }
        @Override
        public int compareTo(Segment obj) {
            if ((n - obj.n) != 0)
                return n - obj.n;
            else return k - obj.k;
        }
    }

    public static Segment[] qSort(Segment n[], int left, int right) {
        int l = left;
        int r = right;
        Random rand = new Random();
        Segment x = n[l + rand.nextInt(r - l + 1)];
        while (l <= r) {
            while (n[l].compareTo(x) < 0) {
                l++;
            }
            while (n[r].compareTo(x) > 0) {
                r--;
            }
            if (l <= r) {
                Segment t = n[l];
                n[l] = n[r];
                n[r] = t;
                l++;
                r--;
            }
        }
        if (left < r) {
            qSort(n, left, r);
        }
        if (l < right) {
            qSort(n, l, right);
        }
        return n;
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        //число точек
        int m = scanner.nextInt();
        int[] result = new int[m];
        Segment[] segmets = new Segment[n + n + m];

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
            segmets[index++] = new Segment(start, -1);
            segmets[index++] = new Segment(stop, m + 1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            segmets[index++] = new Segment(x, i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        qSort(segmets, 0, segmets.length - 1);
        int s = 0;
        for (Segment segment : segmets) {
            if (segment.k < 0)
                s++;
            else if (segment.k > m)
                s--;
            else result[segment.k] = s;
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
