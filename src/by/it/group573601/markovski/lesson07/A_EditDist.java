package by.it.group573601.markovski.lesson07;

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
    private String string1;
    private String string2;
    private int[][] D;

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        string1 = one; string2 = two;
        System.out.println("First string: " + string1 + "; Second string: " + string2);
        D = new int[string1.length() + 1][string2.length() + 1];
        for (int[] cell : D){
            Arrays.fill(cell, Integer.MAX_VALUE);
        }
        int result = calcDistance(string1.length(), string2.length());
        for (int i = 0; i <= string1.length(); i++){
            for (int j = 0; j <= string2.length(); j++){
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int calcDistance(int i, int j){
        if (D[i][j] == Integer.MAX_VALUE){
            if (i == 0)
                D[i][j] = j;
            else if (j == 0)
                D[i][j] = i;
            else{
                int ins = calcDistance(i, j - 1) + 1;
                int del = calcDistance(i - 1, j) + 1;
                int sub = calcDistance(i - 1, j - 1) + diff(string1.charAt(i - 1), string2.charAt(j - 1));
                D[i][j] = min(ins, del, sub);
            }
        }
        return D[i][j];
    }

    public static int diff(char a, char b){
        if (a == b)
            return 0;
        else
            return 1;
    }

    public static int min(int a, int b, int c){
        if (a <= b && a <= c)
            return a;
        else if (b <= a && b <= c)
            return b;
        else
            return c;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/markovski/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

