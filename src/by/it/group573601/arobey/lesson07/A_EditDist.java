package by.it.group573601.arobey.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

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

public class A_EditDist {
    String str_1;
    String str_2;
    int[][] M;

    private int function(int i, int j) {
        if (M[i][j] == Integer.MAX_VALUE) {
            if (j==0) {
                M[i][j] = i;
            } else if (i == 0) {
                M[i][j] = j;
            } else {
                int diff;
                if (str_1.charAt(i - 1) == str_2.charAt(j - 1))
                    diff = 0;
                else diff = 1;
                int ins = function(i, j - 1) + 1;
                int del = function(i - 1, j) + 1;
                int sub = function(i - 1, j - 1) + diff;
                M[i][j] = Math.min(Math.min(ins, del), sub);
            }
        }
        return M[i][j];
    }


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        str_1 = one;
        str_2 = two;
        System.out.println("one=" + one);
        System.out.println("two=" + two);
        int m = str_1.length();
        int n = str_2.length();
        M = new int[m + 1][n + 1];
        for (int[] res : M){
            Arrays.fill(res, Integer.MAX_VALUE);}
        int i = m;
        int j = n;
        return function(i, j);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/arobey/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

