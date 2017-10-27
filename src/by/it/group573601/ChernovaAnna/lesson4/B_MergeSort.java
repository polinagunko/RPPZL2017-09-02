package by.it.group573601.ChernovaAnna.lesson4;

import com.sun.scenario.effect.Merge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {


    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return  mergeSort(a, 0, a.length - 1);
    }

    static int[] mergeSort(int[] a, int l, int r) {
        if (a.length < 2)
            return a;
        int m = a.length / 2;
        int[] a1 = Arrays.copyOfRange(a, 0, m);
        int[] a2 = Arrays.copyOfRange(a, m, a.length);
        return merge(mergeSort(a1, 0, m), mergeSort(a2, m , a.length));
    }

    static int[] merge(int[] a1, int[] a2) {
        int len_1 = a1.length, len_2 = a2.length;
        int a = 0, b = 0, len = len_1 + len_2; // a, b - счетчики в массивах
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            if (a == a1.length)
                result[i] = a2[b++];
            else if (b == a2.length)
                result[i] = a1[a++];
            else if (a1[a] < a2[b])
                result[i] = a1[a++];
            else result[i] = a2[b++];
        }
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
