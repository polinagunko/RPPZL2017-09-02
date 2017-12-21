package by.it.group573601.budaev.lesson04;

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

        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        a = mergeSort(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    private int[] merge (int[] mI, int[] mJ){
        int j = 0;
        int i =0;

        int [] mK = new int [mI.length + mJ.length];
        for (int k =0;k<mK.length;k++){
            if (j ==mJ.length || (i< mI.length && mI[i]<=mJ[j]))
                mK[k] = mI[i++];
            else {
                mK[k] = mJ[j++];
            }
        }
        return mK;

    }

    private int[] mergeSort (int[] m){
        if (m.length<2) return m;
        int[] left = new int [m.length/2];
        int [] right = new int [m.length-left.length];
        System.arraycopy(m,0,left,0,left.length);
        System.arraycopy(m, left.length, right, 0, right.length);
        right=mergeSort(right);
        left=mergeSort(left);

        return merge(left,right);
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
