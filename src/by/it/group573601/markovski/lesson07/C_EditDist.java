package by.it.group573601.markovski.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import static by.it.group573601.markovski.lesson07.B_EditDist.calcDistance;

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


public class C_EditDist{
    private static String string1;
    private static String string2;
    private static int[][] D;

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        string1 = one; string2 = two;
        System.out.println("First string: " + string1 + "; Second string: " + string2);
        D = new int[string1.length() + 1][string2.length() + 1];
        for (int i = 0; i <= string1.length(); i++){
            D[i][0] = i;
        }
        for (int j = 0; j <= string2.length(); j++){
            D[0][j] = j;
        }

        calcDistance(string1.length() + 1, string2.length() + 1);

        for (int i = 0; i <= string1.length(); i++){
            for (int j = 0; j <= string2.length(); j++){
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }

        String result = "";
        char[] i_mas = string1.toCharArray();
        char[] j_mas = string2.toCharArray();

        int i = i_mas.length, j = j_mas.length;

        while (i > 0 || j > 0){
            char op = '#';
            int el = D[i][j];
            if (i > 0 && j > 0 && D[i - 1][j - 1] < el) {
                el = D[i - 1][j - 1];
                op = '~';
            }
            if (i > 0 && D[i - 1][j] < el) {{
                el = D[i - 1][j];
                op = '-';
            }}

            if (j > 0 && D[i][j - 1] < el) {
                op = '+';
            }
            switch (op){
                case '#':
                    i--;j--;
                    result = String.format("%s,%s", op, result);
                    break;
                case '~':
                    i--;j--;
                    result = String.format("%s%s,%s", op, j_mas[j], result);
                    break;
                case '-':
                    i--;
                    result = String.format("%s%s,%s", op, i_mas[i], result);
                    break;
                case '+':
                    j--;
                    result = String.format("%s%s,%s", op, j_mas[j], result);
                    break;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private static void calcDistance(int n, int m){
        for (int i = 1; i < n; i++){
            for (int j = 1; j < m; j++){
                int same = A_EditDist.diff(string1.charAt(i - 1), string2.charAt(j - 1));
                D[i][j] = A_EditDist.min(D[i -1][j] + 1, D[i][j - 1] + 1, D[i - 1][j - 1] + same);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/markovski/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
