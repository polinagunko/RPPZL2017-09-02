package by.it.group573602.demchenko.lesson04;

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
            System.out.println(a[i]);

        }
        a = mergeSort(a);
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        //a = mergeSort(a, 0, a.length - 1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static int[] mergeSort(int[] massive)
    {
        if (massive.length == 1)
            return massive;
        int mid_point = massive.length / 2;
        int[] left = new int[mid_point];
        int[] right = new int[massive.length-mid_point];
        System.arraycopy(massive,0,left,0, mid_point);
        System.arraycopy(massive,mid_point,right,0, massive.length-mid_point);
        return merge(mergeSort(left),mergeSort(right));
    }

    public static int[] merge(int[] mass1, int[] mass2)
    {
        int a = 0, b = 0;
        int[] merged = new int[mass1.length + mass2.length];
        for (int i = 0; i < mass1.length + mass2.length; i++)
        {
            if (b==mass2.length || (a < mass1.length && mass1[a]<mass2[b])) {
                merged[i] = mass1[a];
                a++;
            }
            else{
                merged[i] = mass2[b];
                b++;
            }
        }
        return merged;
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
