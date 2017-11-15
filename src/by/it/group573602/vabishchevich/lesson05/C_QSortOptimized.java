package by.it.group573602.vabishchevich.lesson05;

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
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            return 0;
        }
    }

    private class Array implements Comparable<Array>{
        int s;
        int t;

        private Array(int s,int t){
            this.s = s;
            this.t = t;
        }
        @Override
        public int compareTo(Array obj){
            if((s - obj.s) != 0)
                return s - obj.s;
            else return t - obj.t;
        }
    }
    private void swap(Array[] array, int i, int j){
        Array r = array[i];
        array[i] = array[j];
        array[j] = r;
    }
    private int[] part(Array[] array, int begin, int end){
        Random random = new Random();
        int rend = begin + random.nextInt(end - begin);
        int poue = begin;
        int raEnd = begin;
        swap(array, poue, rend);
        for(int i = begin + 1; i <= end; i++){
            if(array[i].compareTo(array[poue]) <= 0){
                raEnd++;
                swap(array, i, raEnd);
                if(array[raEnd].compareTo(array[poue]) < 0){
                    swap(array, raEnd, poue);
                    poue++;
                }
            }
        }
        return new int[]{poue, raEnd};
    }

    private void qsort(Array[] array, int begin, int end) {
        while (begin < end) {
            int[] newArray = part(array, begin, end);
            qsort(array, begin, newArray[0] - 1);
            begin = newArray[1] + 1;
        }
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
        int[] result = new int[m];
        Array[] ray = new Array[n+n+m];
        int index = 0;
        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if(start > stop) {
                int tmp = start;
                start = stop;
                stop = tmp;
            }
            ray[index++] = new Array(start,-1);
            ray[index++] = new Array(stop,m + 1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            ray[index++] = new Array(x, i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        qsort(ray, 0, ray.length-1);
        int s = 0;
        for(Array point:ray){
            if(point.t < 0)
                s++;
            else if(point.t > m)
                s--;
            else result[point.t] = s;
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
