package by.it.group573601.horuzhenko.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;


public class C_LongNotUpSubSeq {

    private int findNonincreasingSub(int[] array) {
        int size = array.length;
        int[] numberOfJumps = new int[size];
        int[] prev = new int[size];
        for (int i = 0; i < size; i++) {
            numberOfJumps[i] = 1;
            prev[i] = -1;
            for (int j = 0; j <= i - 1; j++) {
                if (array[j] >= array[i] && numberOfJumps[j] + 1 > numberOfJumps[i]) {
                    numberOfJumps[i] = numberOfJumps[j] + 1;
                    prev[i] = j;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < size; i++) {
            if (numberOfJumps[i] > answer)
                answer = numberOfJumps[i];
        }

        int[] index = new int[answer];

        int ans_index = 0;
        for (int i = 1; i < size; i ++)
            if (numberOfJumps[i] > numberOfJumps[ans_index])
                ans_index = i;

        int i = answer-1;
        while (ans_index >= 0){
            index[i] = ans_index + 1;
            i--;
            ans_index = prev[ans_index];
        }
        System.out.println(Arrays.toString(index));

        return answer;
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int count = scanner.nextInt();
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = scanner.nextInt();
        }

        return findNonincreasingSub(array);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/horuzhenko/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}

