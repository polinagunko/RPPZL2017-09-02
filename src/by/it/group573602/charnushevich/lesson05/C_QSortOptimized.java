package by.it.group573602.charnushevich.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Random;
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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        private Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            if((start - o.start) != 0)
                return  start - o.start;
            else
                return stop - o.stop;
            //подумайте, что должен возвращать компаратор отрезков
            //return 0;
        }
    }
    private void swap(Segment[] array, int i, int j){
        Segment t=array[i];
        array[i]=array[j];
        array[j]=t;
    }
    private int[] part(Segment[] array, int begin, int end){
        Random random = new Random();
        int rend=begin+random.nextInt(end-begin);
        int pivot = begin;
        int pivEnd = begin;
        swap(array,pivot,rend);
        for(int i=begin+1;i<=end;i++){
            if(array[i].compareTo(array[pivot])<=0){
                pivEnd++;
                swap(array,i,pivEnd);
                if(array[pivEnd].compareTo(array[pivot])<0){
                    swap(array,pivEnd,pivot);
                    pivot++;
                }
            }
        }
        return new int[]{pivot,pivEnd};
    }

    private void qsort(Segment[] array, int begin, int end){
        while(begin < end){
            int[] newArray=part(array, begin, end);
            qsort(array, begin,newArray[0]-1);
            begin=newArray[1]+1;
        }

    }
    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] result=new int[m];
        //int[] points=new int[m];
        Segment[] points = new Segment[n+n+m];

        int index=0;
        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            //segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if(start>stop) {
                int tmp = start;
                start = stop;
                stop = tmp;
            }
            points[index++] = new Segment(start,-1);
            points[index++] = new Segment(stop,m+1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            //points[i]=scanner.nextInt();
            int x=scanner.nextInt();
            points[index++]= new Segment(x,i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        qsort(points, 0, points.length-1);
        int s=0;
        for(Segment point:points){
            if(point.stop<0)
                s++;
            else if(point.stop>m)
                s--;
            else result[point.stop] = s;
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
