package by.it.group573602.kondrashevich.lesson05;

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
    private class Point implements Comparable<Point>{
        int p;
        int i;
        private Point(int p, int i){
            this.p=p;
            this.i=i;
        }
        public int compareTo(Point obj){
            if((p-obj.p)!=0)
                return p-obj.p;
            else return i-obj.i;
        }
    }

    private int part(Point[] mas,int l, int h){
        Point x=mas[l];
        int m=l;
        for(int i=l+1; i<h; i++){
            if(mas[i].compareTo(x)<=0){
                m++;
                Point tmp=mas[i];
                mas[i]=mas[m];
                mas[m]=tmp;
            }
        }
        Point tmp = mas[l];
        mas[l]=mas[m];
        mas[m]=tmp;
        return m;
    }
    private void qsort(Point[] array, int begin, int end){
        if(begin<end){
            int newArray=part(array,begin,end);
            qsort(array,begin,newArray-1);
            qsort(array,newArray+1,end);
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        //Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        //int[] points=new int[m];
        Point[] points = new Point[n+n+m];
        int[] result=new int[m];

        int index=0;

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {

            //читаем начало и конец каждого отрезка
            //segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if(start>stop){
                int tmp = start;
                start=stop;
                stop = tmp;
            }
            points[index++] = new Point(start, -1);
            points[index++] = new Point(stop, m+1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            //points[i]=scanner.nextInt();
            int x = scanner.nextInt();
            points[index++]= new Point(x,i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        qsort(points, 0, points.length-1);
        int s = 0;
        for (Point point:points){
            if(point.i<0)
                s++;
            else if(point.i>n)
                s--;
            else result[point.i]=s;
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
