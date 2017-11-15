package by.it.group573602.vabishchevich.lesson05;

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
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            return 0;
        }
    }

    private class Array implements Comparable<Array>
    {
        int s;
        int t;
        private Array(int s,int t){
            this.s = s;
            this.t = t;
        }
        public int compareTo(Array obj){
            if((s - obj.s) != 0)
                return s - obj.s;
            else return t - obj.t;
        }
    }

    private int part(Array[] mas, int l, int r)
    {
        Array x = mas[l];
        int f = l;
        for(int i = l + 1; i < r; i++)
        {
            if(mas[i].compareTo(x) <= 0)
            {
                f++;
                Array tmp = mas[i];
                mas[i] = mas[f];
                mas[f] = tmp;
            }
        }
        Array tmp = mas[l];
        mas[l] = mas[f];
        mas[f] = tmp;
        return f;
    }
    private void Qsort(Array[] array, int begin, int end){
        if(begin < end){
            int newArray = part(array, begin, end);
            Qsort(array, begin,newArray - 1);
            Qsort(array,newArray + 1, end);
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        //число точек
        int m = scanner.nextInt();
        Array[] ray = new Array[n + n + m];
        int[] result = new int[m];
        int index = 0;
        //читаем сами отрезки
        for (int i = 0; i < n; i++)
        {
            //читаем начало и конец каждого отрезка
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if(start > stop){
                int tmp = start;
                start = stop;
                stop = tmp;
            }
            ray[index++] = new Array(start, -1);
            ray[index++] = new Array(stop,m + 1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            ray[index++] = new Array (x, i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        Qsort(ray, 0, ray.length - 1);
        int s = 0;
        for(Array hop:ray){
            if(hop.t < 0)
                s++;
            else if(hop.t > n)
                s--;
            else result[hop.t] = s;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
