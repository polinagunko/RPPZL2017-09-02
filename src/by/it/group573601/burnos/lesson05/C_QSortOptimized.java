package by.it.group573601.burnos.lesson05;

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

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            if((start - o.start) != 0){
                return start - o.start;
            }
            else
                return stop - o.stop;
        }
    }
    private void swap(Segment[] a, int i, int j){
        Segment t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private int[] division3(Segment[] a, int left, int right){
        Random random = new Random();
        int ran = left + random.nextInt(right - left);
        int pivot = left;
        int pivEnd = left;
        swap(a, pivot, ran);
        for(int i = left + 1; i <= right; i++){
            if(a[i].compareTo(a[pivot]) <= 0){
                pivEnd++;
                swap(a, i ,pivEnd);
                if(a[pivEnd].compareTo(a[pivot]) < 0){
                    swap(a, pivEnd, pivot);
                    pivot++;
                }
            }
        }
        return new int[]{pivot, pivEnd};
    }

    private void quickSort3(Segment[] a, int left, int right){
        while(left < right){
            int[] middle = division3(a, left, right);
            quickSort3(a, left, middle[0] - 1);
            left = middle[1] + 1;
        }
    }

    private void quickSort3(Segment[] a){
        quickSort3(a, 0, a.length-1);
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Segment[] segments=new Segment[n+n+m];
        //число точек

        int[] result=new int[m];

        int index=0;
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            int start = scanner.nextInt();

            int stop = scanner.nextInt();
            if(start > stop){
                int tmp = start;
                start = stop;
                stop = tmp;
            }
            segments[index++]=new Segment(start, -1);
            segments[index++] = new Segment(stop, m+1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            segments[index++]=new Segment(x, i);
        }

        quickSort3(segments);
        int segmentCount = 0;
        for(Segment segment : segments){
            if(segment.stop<0)
                segmentCount++;
            else if (segment.stop>m)
                segmentCount--;

            else
                result[segment.stop] = segmentCount;}
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор


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
