package by.it.group573601.Stephanovich.lesson07;

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

    int getDistanceEdinting(String first, String second) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        String str_1 = first;
        String str_2 = second;
        System.out.println("first=" + first);
        System.out.println("second=" + second);
        int m=str_1.length();
        int n=str_2.length();
        int[][] D = new int[m + 1][n + 1];

        for (int i = 0; i < m+1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < n+1; j++) {
            D[0][j] = j;
        }
        int diff = 0;
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (str_1.charAt(i - 1) == str_2.charAt(j - 1))
                    diff = 0;
                else diff = 1;
                int ins = D[i][j - 1] + 1;
                int del = D[i - 1][j] + 1;
                int sub = D[i - 1][j - 1] + diff;
                D[i][j] = Math.min(Math.min(ins, del), sub);
            }
        }
        return D[m][n];
        
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/Stephanovich/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
