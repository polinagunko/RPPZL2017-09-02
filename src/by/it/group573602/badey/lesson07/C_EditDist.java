package by.it.group573602.badey.lesson07;

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
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        String result = "";
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

        int i = arr.length;
        int j = arr2.length;
        while (i>0 || j>0){
            char oper='#';
            int elem = dist[i][j];
            if(i>0 && j>0 &&dist[i-1][j-1] < elem){
                elem = dist[i-1][j-1];
                oper='~';
            }
            if(i>0 && dist[i-1][j]<elem){
                elem=dist[i-1][j];
                oper='-';
            }
            if(j>0 && dist[i][j-1]<elem){
                //elem = dist[i][j-1];
                oper='+';
            }
            switch (oper){
                case '#':
                    i--;
                    j--;
                    result = String.format("%s,%s", oper, result);
                    break;
                case '~':
                    i--;
                    j--;
                    result = String.format("%s%s,%s", oper, arr2[j], result);
                    break;
                case '-':
                    i--;
                    result = String.format("%s%s,%s", oper, arr2[i], result);
                    break;
                case '+':
                    j--;
                    result = String.format("%s%s,%s", oper, arr2[j], result);
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
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
