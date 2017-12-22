package by.it.group573601.SudzilovskyVl.lesson05;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.InputStream;
        import java.util.Random;
        import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок
    private class Segment implements Comparable<Segment> {
        int point;
        int index;

        public Segment(int point, int index) {
            this.point = point;
            this.index = index;
        }

        @Override
        public int compareTo(Segment o) {
            if (point != o.point) return point - o.point;
            else return (index - o.index);
        }

        @Override
        public String toString() {
            String p = "point:" + point + " ,index:" + index;
            return p;
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //тут реализуйте логику задачи с применением быстрой сортировки

        //число отрезков отсортированного массива
        int n = scanner.nextInt();

        //число точек
        int m = scanner.nextInt();

        //массив точек
        Segment[] points = new Segment[2 * n + m];

        //массив ответов
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < 2 * n; i++) {
            //читаем начало отрезка
            points[i++] = new Segment(scanner.nextInt(), 0);
            //читаем конец отрезка
            points[i] = new Segment(scanner.nextInt(), m + 1);
        }
        //читаем точки
        for (int i = 2 * n; i < (2 * n + m); i++) {
            points[i] = new Segment(scanner.nextInt(), i - 2 * n + 1);
        }
        for (Segment point : points) {
            System.out.println(point);
        }
        qSort(points, 0, 2 * n + m);
        for (Segment point : points) {
            System.out.println(point);
        }
        int counter = 0;

        for (int i = 0; i < (2 * n + m); i++) {
            if (points[i].index == 0) counter++;
            else if (points[i].index > m) counter--;
            else {
                result[points[i].index - 1] = counter;
            }
        }
        return result;
    }

    //Реализация быстрой сортировки:
    private int part(Segment[] p, int l, int h) {
        Segment x = p[l];
        int m = l;
        for (int i = l + 1; i < h; i++) {
            if (p[i].compareTo(x) <= 0) {
                m++;
                Segment tmp = p[i];
                p[i] = p[m];
                p[m] = tmp;
            }
        }
        Segment tmp = p[l];
        p[l] = p[m];
        p[m] = tmp;
        return m;
    }

    public void qSort(Segment p[], int left, int right) {
        if (left < right) {
            int newArray = part(p, left, right);
            qSort(p, left, newArray);
            qSort(p, newArray + 1, right);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}

