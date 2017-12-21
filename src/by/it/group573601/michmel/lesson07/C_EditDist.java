package by.it.group573601.michmel.lesson07;

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
        String s_1 = one;
        String s_2 = two;
        System.out.println("one="+one);
        System.out.println("two="+two);
        int m=s_1.length();
        int n=s_2.length();
        int[][]Matrrix= new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            Matrrix[i][0]=i;
        }
        for(int j=0;j<n+1;j++){
            Matrrix[0][j]=j;
        }
        int flag=0;
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(s_1.charAt(i-1)==s_2.charAt(j-1))
                    flag=0;
                else
                    flag=1;
                int ins=Matrrix[i][j-1]+1;
                int del=Matrrix[i-1][j]+1;
                int sub=Matrrix[i-1][j-1]+flag;
                Matrrix[i][j]=Math.min(Math.min(ins,del),sub);
            }
        }

        char[] mas_1 = one.toCharArray();
        char[] mas_2 = two.toCharArray();

        int i = mas_1.length;
        int j = mas_2.length;
        String result = "";
        while (i > 0 || j > 0)
        {
            char symbol = '#';//копировать
            int el = Matrrix[i][j];
            if (i > 0 && j > 0 && Matrrix[i - 1][j - 1] < el) {
                el = Matrrix[i - 1][j - 1];
                symbol = '~';//заменить
            }
            if (i > 0 && Matrrix[i - 1][j] < el) {
                el = Matrrix[i - 1][j];
                symbol = '-';//удалить
            }
            if (j > 0 && Matrrix[i][j - 1] < el) {
                symbol = '+';//вставить
            }
            switch (symbol) {
                case '#':
                    i--;j--;
                    result = String.format("%s,%s", symbol, result);
                    break;
                case '~':
                    i--;j--;
                    result = String.format("%s%s,%s", symbol, mas_2[j], result);
                    break;
                case '-':
                    i--;
                    result = String.format("%s%s,%s", symbol, mas_1[i], result);
                    break;
                case '+':
                    j--;
                    result = String.format("%s%s,%s", symbol, mas_2[j], result);
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
