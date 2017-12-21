package by.it.group573601.arobey.lesson05;

import com.sun.org.apache.bcel.internal.generic.Select;

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

    private  int partition (Point[] a, int low,int high){
        Point x = a[low];
        int m = low;
        for(int i = low + 1; i<=high;i++) {
            if (a[i].compareTo(x) <= 0) {
                m++;
                Point tmp = a[i];
                a[i] = a[m];
                a[m] = tmp;
            }
        }
        Point tmp = a[low];
        a[low]=a[m];
        a[m]=tmp;
        return m;
    }

    private void qsort(Point[] a, int low, int high){
        if (low<high){
            int m = partition(a,low,high);
            qsort(a,low,m-1);
            qsort(a,m+1,high);
        }
}

    private void qsort(Point[] a){
        qsort(a,0,a.length-1);
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
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
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            points[index++] = new Point(x, i);
        }
        qsort(points);
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
        InputStream stream = new FileInputStream(root + "by/it/group573601/arobey/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
