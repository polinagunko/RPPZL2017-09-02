package by.it.group573602.tumash.lesson04;

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
        mSort(a, 0, a.length - 1);
        return a;
    }

    private static void mSort(int[] array, int f, int ff) {

        if (ff <=f) {
            return;
        }
        int mid = f + (ff - f) / 2;//!!!!!!!!!!!!!
        mSort(array, f, mid);
        mSort(array, mid + 1, ff);

        int[] Buf = Arrays.copyOf(array, array.length);

        System.arraycopy(array, f, Buf, f, ff + 1 - f);

        int i = f;
        int j = mid + 1;
        for (int k = f; k <= ff; k++) {
            if (i > mid) {
                array[k] = Buf[j];//!!!!!!!!!!!!!
                j++;
            } else if (j > ff) {
                array[k] = Buf[i];
                i++;
            } else if (Buf[j] < Buf[i]) {//!!!!!!!!!!!!!
                array[k] = Buf[j];
                j++;
            } else {
                array[k] = Buf[i];
                i++;
            }
        }
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
