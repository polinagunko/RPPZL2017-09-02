package by.it.group573601.markovski.lesson04;

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
    private static int inversions = 0;

    private static int getInversions() {
        return inversions;
    }

    private static void setInversions(int length, int index) {
        C_GetInversions.inversions += length - index;
    }

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

        divideWithInversions(a);

        return getInversions();
    }

    private static int[] divideWithInversions(int[] source){
        int len = source.length;
        if (len < 2) return source;
        int middle = len / 2;

        return mergeWithInversions(divideWithInversions(Arrays.copyOfRange(source, 0, middle)),
                divideWithInversions(Arrays.copyOfRange(source, middle, len)));
    }

    private static int[] mergeWithInversions(int[] left_arr, int[] right_arr){
        int left_len = left_arr.length, right_len = right_arr.length;
        int a = 0, b = 0, len = left_len + right_len; //a, b - счетчики в массивах
        int[] result = new int[len];

        for (int i = 0; i < len; i ++){
            if (b < right_len && a < left_len){
                if ( left_arr[a] > right_arr[b]) {
                    result[i] = right_arr[b++];
                    setInversions(left_len, a);
                } else result[i] = left_arr[a++];
            } else if (b < right_len){
                result[i] = right_arr[b++];
            } else {
                result[i] = left_arr[a++];
            }
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/markovski/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
