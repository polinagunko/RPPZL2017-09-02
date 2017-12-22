package by.it.group573601.voloshchuk.lesson01.lesson04;

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

    public static int[] Sort(int[] mas){
        if(mas.length<2) return mas;
        int l;
        l=mas.length/2;
        int[] s1=Arrays.copyOfRange(mas,0,l);
        int[] s2=Arrays.copyOfRange(mas,l, mas.length);
        return mergeSort(Sort(s1),Sort(s2));
    }

    public static int[] mergeSort(int[] s1,int []s2){


        int n=s1.length+s2.length;
        int [] res;
        res= new int[n];
        int i1=0, i2=0;
        for(int i=0;i<n;i++){
            if(i1==s1.length){
                res[i]=s2[i2++];
            } else if(i2==s2.length){
                res[i]=s1[i1++];
            } else{
                if(s1[i1]<s2[i2]){
                    res[i]=s1[i1++];
                }
                else{
                    res[i]=s2[i2++];
                }
            }
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
        int[] a;
        a= new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);

        }
        int[] mas1 = Arrays.copyOf(a,a.length);
        int[] mas2 = new int[a.length];
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        a = Sort(a);
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
