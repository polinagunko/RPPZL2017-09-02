package by.it.group573601.shishporonok.lesson04;

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
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }
        a=sortMerge(a);
        return a;
    }
    private static int[] sortMerge (int [] arr){
        int leng = arr.length;
        if (leng<2) return arr;
        int mid = leng/2;
        return merge(sortMerge(Arrays.copyOfRange(arr,0,mid)),
                sortMerge(Arrays.copyOfRange(arr,mid,leng)));
    }

    private static int[] merge(int[] arr1,int[] arr2) {
        int leng1 = arr1.length, leng2 = arr2.length;
        int a = 0, b = 0, leng = leng1 + leng2;
        int[] result = new int[leng];
        for (int i = 0; i < leng; i++) {
            if (b < leng2 && a < leng1) {
                if (arr1[a] > arr2[b]) {
                    result[i] = arr2[b++];
                }
                else result[i] = arr1[a++];
            } else if (b < leng2) {
                result[i] = arr2[b++];
            } else {
                result[i] = arr1[a++];
            }
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
