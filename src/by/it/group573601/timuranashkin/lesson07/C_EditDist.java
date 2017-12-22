package by.it.group573601.timuranashkin.lesson07;

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

    String getDistanceEdinting(String one, String two) {

        int[][] levenDist = new int[one.length() + 1][two.length() + 1];

        for (int i = 0; i < one.length() + 1; i++)
            levenDist[i][0] = i;

        for (int j = 0; j < two.length() + 1; j++)
            levenDist[0][j] = j;

        for (int i = 0; i < one.length(); i++) {
            for (int j = 0; j < two.length(); j++) {
                int cost = getDiff(one.charAt(i), two.charAt(j));
                levenDist[i + 1][j + 1] = Math.min(Math.min(levenDist[i][j + 1] + 1,
                        levenDist[i + 1][j] + 1), levenDist[i][j] + cost);
            }
        }

        return formSeqChanges(levenDist, one, two);
    }

    private static String formSeqChanges(int[][] levenDist, String one, String two) {

        StringBuilder result = new StringBuilder();

        int a = one.length();
        int b = two.length();

        while (a > 0)
            while (b > 0) {

                int insert = levenDist[a][b - 1];
                int delete = levenDist[a - 1][b];
                int replace = levenDist[a - 1][b - 1];
                int minimum = Math.min(Math.min(insert, delete), replace);

                if (minimum == replace) {
                    int cost = getDiff(one.charAt(a-- - 1), two.charAt(b-- - 1));
                    if (cost == 0) {
                        result.append("#,");
                    }
                    if (cost == 1)
                        result.append("~" + two.charAt(b) + ",");
                }
                if (minimum == delete)
                    result.append("-" + one.charAt(--a) + ",");
                if (minimum == insert)
                    result.append("+" + two.charAt(--b) + ",");
            }
        return result.toString();

    }

    private static int getDiff(char one, char two) {
        return one != two ? 1 : 0;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/timuranashkin/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
