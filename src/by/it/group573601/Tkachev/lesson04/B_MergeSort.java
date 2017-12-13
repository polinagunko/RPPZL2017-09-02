package by.it.group573601.Tkachev.lesson04;

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

    protected int[] mergeSort(int[] N) {
        if (N.length < 2)
            return N;

        int[] numLeft = new int[N.length / 2];

        int[] numRight = new int[N.length - numLeft.length];

        System.arraycopy(N, 0, numLeft, 0, numLeft.length);
        System.arraycopy(N, numLeft.length, numRight, 0, numRight.length);

        numLeft = mergeSort(numLeft);
        numRight = mergeSort(numRight);

        return merge(numLeft, numRight);
    }

    protected int[] merge(int[] N1, int[] N2) {
        int i = 0;
        int j = 0;
        int[] NewN = new int[N1.length + N2.length];
        for (int K = 0; K < NewN.length; K++) {
            if (j == N2.length || (i < N1.length && N1[i] <= N2[j])) {
                NewN[K] = N1[i++];
            } else {
                NewN[K] = N2[j++];
            }
        }
        return NewN;
    }

    protected int[] getMergeSort(InputStream stream) throws FileNotFoundException {
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

        a = mergeSort(a);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
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
