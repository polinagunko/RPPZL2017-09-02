package by.it.group573602.kolesnikovich.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_LIS {

    private int findIncreasingSub(int[] array) {
        int size = array.length;
        int[] numberOfJumps = new int[size];
        for (int i = 0; i < size; i++) {
            numberOfJumps[i] = 1;
            for (int j = 0; j+1 <= i; j++) {
                if (array[j] < array[i] && numberOfJumps[j] + 1 > numberOfJumps[i])
                    numberOfJumps[i] = numberOfJumps[j] + 1;
            }
        }
        int answer = 0;
        for (int i = 0; i < size; i++) {
            if (numberOfJumps[i] > answer)
                answer = numberOfJumps[i];
        }
        return answer;
    }

    int getSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int count = scanner.nextInt();
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = scanner.nextInt();
        }

        return findIncreasingSub(array);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573602/kolesnikovich/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
