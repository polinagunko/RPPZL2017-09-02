package by.it.group573601.burnos.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозрастающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner;
        scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int length = scanner.nextInt();
        int[] massive = new int[length];
        //читаем всю последовательность
        for (int i = 0; i < length; i++) {
            massive[i] = scanner.nextInt();
        }

        //тут реализуйте логику задачи методами динамического программирования (!!!)

        int[] buff = new int[length], prev = new int[length];
        for (int i = 0; i < length; i++) {
            buff[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++)
                if (massive[j] >= massive[i] && buff[j] + 1 > buff[i]) {
                    buff[i]=buff[i]+1;
                    prev[i] = j;
                }
        }

        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans = Math.max(ans, buff[i]);
        }

        int[] indexans = new int[ans];

        int ans_index = 0;
        for (int i = 1; i < length; i ++)
            if (buff[i] > buff[ans_index])
                ans_index = i;

        int i = ans-1;
        while (ans_index >= 0){
            indexans[i] = ans_index + 1;
            i--;
            ans_index = prev[ans_index];
        }
        System.out.println(Arrays.toString(indexans));
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return ans;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/burnos/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}