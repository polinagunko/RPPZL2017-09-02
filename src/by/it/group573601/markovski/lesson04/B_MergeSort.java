package by.it.group573601.markovski.lesson04;

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

        a = mergeSort(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    private static int[] mergeSort(int[] source){
        int len = source.length;
        if (len < 2) return source;
        int middle = len / 2;

        return merge(mergeSort(Arrays.copyOfRange(source, 0, middle)),
                     mergeSort(Arrays.copyOfRange(source, middle, len)));
    }

    private static int[] merge(int[] left_arr, int[] right_arr){
        int left_len = left_arr.length, right_len = right_arr.length;
        int a = 0, b = 0, len = left_len + right_len; //a, b - счетчики в массивах
        int[] result = new int[len];

        for (int i = 0; i < len; i ++){
            if (b < right_len && a < left_len){
                if ( left_arr[a] > right_arr[b]) result[i] = right_arr[b++];
                else result[i] = left_arr[a++];
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
        InputStream stream = new FileInputStream(root + "by/it/group573601/markovski/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
