package by.it.group573602.charnushevich.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

    Редакционное расстояние, или расстояние Левенштейна - метрика,
     позволяющая определить «схожесть» двух строк:
     минимальное количество операций вставки одного символа,
     удаления одного символа и замены одного символа на другой,
     необходимых для превращения одной строки в другую

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
    short               short->hort->horts->ports
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance            distance->edistance->editance->editince->editinc->editing
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {
    private String string1;
    private String string2;
    int[][] D;

    private int function(int i, int j) {
        if (D[i][j] == Integer.MAX_VALUE) {
            if (i == 0) {
                D[i][j] = j;
            } else if (j == 0) {
                D[i][j] = i;
            } else {
                int diff;
                if (string1.charAt(i - 1) == string2.charAt(j - 1))
                    diff = 0;
                else diff = 1;

                int insert = function(i, j - 1) + 1;
                int delete = function(i - 1, j) + 1;
                int substitute = function(i - 1, j - 1) + diff;
                D[i][j] = Math.min(Math.min(insert, delete), substitute);
            }
        }
        return D[i][j];
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        string1 = one;
        string2 = two;
        int m = string1.length();
        int n = string2.length();
        D = new int[m + 1][n + 1];
        for (int[] res : D)
            Arrays.fill(res, Integer.MAX_VALUE);
        return function(m, n);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/akhmelev/lesson08/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}

