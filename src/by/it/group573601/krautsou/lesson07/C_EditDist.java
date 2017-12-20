package by.it.group573601.krautsou.lesson07;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {
    String result = "";
    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        String str1 = one;
        String str2 = two;
        System.out.println("one=" + one);
        System.out.println("two=" + two);
        int m = str1.length();
        int n = str2.length();
        int[][] D = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            D[0][j] = j;
        }
        int discrep = 0;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    discrep = 0;
                else discrep = 1;
                int ins = D[i][j - 1] + 1;
                int del = D[i - 1][j] + 1;
                int rep = D[i - 1][j - 1] + discrep;
                D[i][j] = Math.min(Math.min(ins, del), rep);
            }
        }

        char[] a = one.toCharArray();
        char[] b = two.toCharArray();

        int i = a.length;
        int j = b.length;
        String result = "";
        while (i > 0 || j > 0) {
            char op = '#';
            int curr = D[i][j];
            if (i > 0 && j > 0 && D[i - 1][j - 1] < curr) {
                curr = D[i - 1][j - 1];
                op = '~';
            }
            if (i > 0 && D[i - 1][j] < curr) {
                curr = D[i - 1][j];
                op = '-';
            }
            if (j > 0 && D[i][j - 1] < curr) {
                op = '+';
            }
            switch (op) {
                case '#':
                    i--;
                    j--;
                    result = String.format("%s,%s", op, result);
                    break;
                case '~':
                    i--;
                    j--;
                    result = String.format("%s%s,%s", op, b[j], result);
                    break;
                case '-':
                    i--;
                    result = String.format("%s%s,%s", op, a[i], result);
                    break;
                case '+':
                    j--;
                    result = String.format("%s%s,%s", op, b[j], result);
                    break;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}
