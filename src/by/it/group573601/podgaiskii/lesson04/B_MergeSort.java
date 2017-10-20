package by.it.group573601.podgaiskii.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
            System.out.print(a[i] + " ");
        }
        System.out.println("");

        a = mergeSort(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    private int[] mergeSort(int[] a) {
        int left = 0, right = a.length - 1;
        int middle = (left + right) / 2;
        int[] result;
        if (a.length >= 2)
            result = merge(mergeSort(split(a, left, middle)), mergeSort(split(a, middle + 1, right)));
        else
            result = new int[]{a[0]};
        return result;
    }

    private int[] split(int[] a, int from, int to) {
        int[] result = new int[to - from + 1];
        int i = from;
        while (i <= to) {
            result[i - from] = a[i];
            i++;
        }
        return result;
    }

    private int[] merge(int[] first, int[] second) {
        int n = first.length + second.length;
        int[] result = new int[n];
        int i = 0, j = 0, k = 0;
        while(i < first.length || j < second.length) {
            if (i == first.length)
                result[k++] = second[j++];
            else if (j == second.length)
                result[k++] = first[i++];
            else if (first[i] <= second[j])
                result[k++] = first[i++];
            else
                result[k++] = second[j++];
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/podgaiskii/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
