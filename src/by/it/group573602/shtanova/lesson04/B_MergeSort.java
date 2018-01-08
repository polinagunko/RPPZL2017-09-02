package by.it.group573602.shtanova.lesson04;

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

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        a = merge_Sort(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    private static int[] merge_Sort(int[] array){
        if (array.length == 1){
            return array;
        }
        int mid_point = array.length/2;
        int[] left = new int[mid_point];
        int[] right = new int[array.length-mid_point];
        System.arraycopy(array, 0, left, 0, mid_point);
        System.arraycopy(array, mid_point,right, 0, array.length-mid_point);
        return merge(merge_Sort(left), merge_Sort(right));
    }

    private static int[] merge(int[] firstArray, int[] secondArray){
        int x =0, y =0;
        int[] merge = new int[firstArray.length + secondArray.length];

        for(int i =0; i< firstArray.length + secondArray.length;i++)
        {
            if(y==secondArray.length || (x<firstArray.length && firstArray[x]<secondArray[y])){
                merge[i] = firstArray[x];
            x++;
            }
            else {
                merge[i] = secondArray[y];
                y++;
            }
        }
        return  merge;
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
