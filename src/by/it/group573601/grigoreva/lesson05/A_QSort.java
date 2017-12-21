package by.it.group573601.grigoreva.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            if (start != o.start) return start - o.start;
            else return stop - o.stop;

        }

    }

    private void Sort(Segment[] a) {
        Sort(a, 0, a.length - 1);
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Segment[] segments=new Segment[n + n + m];
        //число точек
        //int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < 2* n; i++) {
            //читаем начало и конец каждого отрезка
//            int start = scanner.nextInt();
//            int stop  = scanner.nextInt();
//            if (start > stop) {
//                int temp = start;
//                start = stop;
//                stop = temp;
//
//            }
            segments[i++] = new Segment(scanner.nextInt(), 0);
            segments[i] = new Segment(scanner.nextInt(), m+1);
        }
        //читаем точки
        for (int i = 2 * n; i < (n + n + m); i++) {
            int b =scanner.nextInt();
            segments[i] = new Segment(b, i - 2 * n + 1);
        }

        Sort(segments, 0, n + n +m);
        for (Segment segment : segments) {
            System.out.println(segment);
        }
        int count = 0;
        for(int i = 0; i < n + n + m; i++){
        if (segments[i].stop == 0)
                count ++;
            else if (segments[i].stop > m)
                    count --;
            else  result [segments[i].stop - 1]=count;
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int div(Segment[] a, int low, int high){
        Segment x = a[low];
        int m = low;
        for(int i = low+1; i <= high; i++){
            if(a[i].compareTo(x) <= 0){
                m++;
                Segment temp = a[i];
                a[i] = a[m];
                a[m] = temp;
            }
        }
        Segment temp = a[low];
        a[low] = a[m];
        a[m] = temp;
        return m;
    }

    private void Sort(Segment[] a, int low, int high){
        if(low<high){
            int m = div(a, low, high);
            Sort(a, low, m);
            Sort(a, m+1, high);
        }
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
