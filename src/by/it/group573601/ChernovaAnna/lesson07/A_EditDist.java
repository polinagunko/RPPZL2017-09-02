package by.it.group573601.ChernovaAnna.lesson07;

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

    private String oneStr;
    private String twoStr;

    int diff(char a, char b) {
        if (a == b)
            return 0;
        else
            return 1;
    }

    int min(int a, int b, int c) {
        if (a < b && a < c)
            return a;
        else if (b < a && b < c)
            return b;
        else
            return c;
    }

    private int EditDistTD(int[][] d, int i, int j) {
        int ins, del, sub;
        if (d[i][j] == 1000) {
            if (i == 0)
                d[i][j] = j;
            else if (j == 0)
                d[i][j] = i;
            else {
                ins = EditDistTD(d, i, j - 1) + 1;
                del = EditDistTD(d, i - 1, j) + 1;
                sub = EditDistTD(d, i - 1, j - 1) + diff(oneStr.charAt(i-1), twoStr.charAt(j-1));
                d[i][j] = min(ins,del,sub);
            }
        }
        return d[i][j];
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        oneStr = one;
        twoStr = two;
        int n = one.length(), m = two.length();

        n++;
        m++;

        int[][] d = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                d[i][j] = 1000;
            }
        }
        int i = n - 1, j = m - 1;
        int result = EditDistTD(d, i, j);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/ChernovaAnna/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}

