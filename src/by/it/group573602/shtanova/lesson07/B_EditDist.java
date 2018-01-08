package by.it.group573602.shtanova.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result;
        char[] arr = one.toCharArray();
        char[] arr2 = two.toCharArray();
        int[][] dist = new int[arr.length + 1][arr2.length + 1];
        for (int i = 0; i < arr.length + 1; i++)
            for (int j = 0; j < arr2.length + 1; j++)
                dist[i][j] = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length + 1; i++)
            for (int j = 0; j < arr2.length + 1; j++) {
                if (i == 0)
                    dist[i][j] = j;
                else if (j == 0)
                    dist[i][j] = i;
                else {
                    int ins = dist[i][j - 1] + 1;
                    int del = dist[i - 1][j] + 1;
                    int sub = dist[i - 1][j - 1] +
                            ((arr[i - 1] == arr2[j - 1]) ? 0 : 1);
                    dist[i][j] = Math.min(ins, Math.min(del, sub));
                }
            }
        result = dist[arr.length][arr2.length];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}