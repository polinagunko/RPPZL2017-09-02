package by.it.group573602.mazhanov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
В первой строке источника данных даны:
        - целое число 1<=n<=100000 (размер массива)
        - сам массив A[1...n] из n различных натуральных чисел,
          не превышающих 10E9, в порядке возрастания,
Во второй строке
        - целое число 1<=k<=10000 (сколько чисел нужно найти)
        - k натуральных чисел b1,...,bk не превышающих 10E9 (сами числа)
Для каждого i от 1 до kk необходимо вывести индекс 1<=j<=n,
для которого A[j]=bi, или -1, если такого j нет.

        Sample Input:
        5 1 5 8 12 13
        5 8 1 23 1 11

        Sample Output:
        3 1 -1 1 -1

(!) Обратите внимание на смещение начала индекса массивов JAVA относительно условий задачи
*/

public class A_BinaryFind {
    int[] findIndex(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер отсортированного массива
        int length = scanner.nextInt();
        //сам отсортированный массива
        int[] mass=new int[length];
        for (int i = 1; i <= length; i++) {
            mass[i-1] = scanner.nextInt();
        }

        //размер массива индексов
        int key = scanner.nextInt();
        int[] res=new int[key];
        for (int i = 0; i < key; i++) {
            int val = scanner.nextInt();
            //тут реализуйте бинарный поиск индекса

            int l=0;
            int  rn=key-1;
            while(l<=rn){
                int m=0;
                m=  (int)Math.floor((l+rn)/2);
                if(val==mass[m]){
                    l=rn+1;
                    res[i]=m+1;
                }
                else if (mass[m]>val)
                    rn=m-1;
                else l=m+1;
            }
            if(res[i]==0)
                res[i]=-1;
        }



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return res;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
