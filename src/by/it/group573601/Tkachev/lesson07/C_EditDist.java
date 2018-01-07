package by.it.group573601.Tkachev.lesson07;

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
        String first = one;
        String second = two;
        System.out.println("1 = " + one);
        System.out.println("2 = " + two);
        int m = first.length();
        int n = second.length();
        int[][] table = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++){
            table[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++){
            table[0][j] = j;
        }
        int diff = 0;
        for (int i = 1; i < m + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(first.charAt(i - 1) == second.charAt(j - 1))
                    diff = 0;
                else
                    diff = 1;
                int ins = table[i][j - 1] + 1;
                int del = table[i - 1][j] + 1;
                int sub = table[i - 1][j - 1] + diff;
                table[i][j] = Math.min(Math.min(ins, del), sub);
            }
        }

        char[] firstArr = one.toCharArray();
        char[] mas_2 = two.toCharArray();

        int i = firstArr.length;
        int j = mas_2.length;
        String result = "";
        while (i > 0 || j > 0)
        {
            char op = '#';
            int el = table[i][j];
            if (i > 0 && j > 0 && table[i - 1][j - 1] < el) {
                el = table[i - 1][j - 1];
                op = '~';
            }
            if (i > 0 && table[i - 1][j] < el) {
                el = table[i - 1][j];
                op = '-';
            }
            if (j > 0 && table[i][j - 1] < el) {
                op = '+';
            }
            switch (op) {
                case '#':
                    i--;j--;
                    result = String.format("%s,%s", op, result);
                    break;
                case '~':
                    i--;j--;
                    result = String.format("%s%s,%s", op, mas_2[j], result);
                    break;
                case '-':
                    i--;
                    result = String.format("%s%s,%s", op, firstArr[i], result);
                    break;
                case '+':
                    j--;
                    result = String.format("%s%s,%s", op, mas_2[j], result);
                    break;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/akhmelev/lesson08/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
