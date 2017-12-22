package by.it.group573601.kondratiev.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

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
        int n = scanner.nextInt();
        //сам массива
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);

        }

        return sortMerge(a);
    }


        private int[] sortMerge(int[] a) {
            int len = a.length;
            if (len < 2) return a;
            int middle = len / 2;
            return merge(sortMerge(Arrays.copyOfRange(a, 0, middle)),
                    sortMerge(Arrays.copyOfRange(a, middle, len)));
        }


        private int[] merge(int[] arr_1, int[] arr_2){
            int len_1 = arr_1.length, len_2 = arr_2.length;
            int a = 0, b = 0, len = len_1 + len_2;
            int[] result = new int[len];
            for (int i = 0; i < len; i++) {
                if (b < len_2 && a < len_1) {
                    if (arr_1[a] > arr_2[b]) result[i] = arr_2[b++];
                    else result[i] = arr_1[a++];
                } else if (b < len_2) {
                    result[i] = arr_2[b++];
                } else {
                    result[i] = arr_1[a++];
                }
            }
            return result;
        }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/kondratiev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
