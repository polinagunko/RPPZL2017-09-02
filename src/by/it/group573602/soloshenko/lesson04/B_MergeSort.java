package by.it.group573602.soloshenko.lesson04;

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

    public static int[] mergeSort(int[] mas1,int [] mas2,int ifi,int il){
        if(ifi>= il-1){
            return mas1;
        }

        int m=ifi+(il-ifi)/2;
        int[] s1 = mergeSort(mas1,mas2,ifi,m);
        int[] s2 = mergeSort(mas1,mas2,m,il);

        int i1=ifi;
        int i2=m;
        int s=ifi;

        int[] res ;
        if(s1 == mas1)
            res=mas2;
        else res=mas1;
        while(i1<m && i2<il){
            if(s1[i1]<s2[i2])
            res[s++] = s1[i1++];
            else  res[s++]=s2[i2++];
        }
        while(i1<m){
            res[s++]=s1[i1++];
        }
        while(i2<il){
            res[s++]=s2[i2++];
        }
        return res;
    }


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
        int[] mas1 = Arrays.copyOf(a,a.length);
        int[] mas2 = new int[a.length];

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        a = mergeSort(mas1,mas2,0,a.length);
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
