package by.it.group573601.timuranashkin.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j]A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        a=mergeSort(a);
        return count_inv;
    }
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        private static int count_inv;
        private static int[] mergeSort(int [] a)
        {
            if(a.length<2) return a;
            int mid = a.length/2;
            return merge(mergeSort(Arrays.copyOfRange(a,0,mid)),mergeSort(Arrays.copyOfRange(a,mid,a.length)));
        }
        private static int [] merge(int [] m ,int [] l)
        {
            int len_1 = m.length, len_2 = l.length;
            int h = 0, b = 0, len = len_1 + len_2; // a, b - счетчики в массивах
            int[] result = new int[len];
            for (int i = 0; i < len; i++) {
                if (b == len_2 ||( h < len_1 && m[h]<=l[b]))
                {
                    result[i] = m[h++];
                } else {
                    result[i] = l[b++];
                    count_inv+=(len_1-h);
                }
            }
            return result;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/timuranashkin/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
