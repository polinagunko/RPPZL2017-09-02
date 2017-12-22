package by.it.group573601.SudzilovskyVl.lesson07;

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
        int[][]D= new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            D[i][0]=i;
        }
        for(int j=0;j<n+1;j++){
            D[0][j]=j;
        }
        int diff=0;
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(s_1.charAt(i-1)==s_2.charAt(j-1))
                    diff=0;else diff=1;
                int ins=D[i][j-1]+1;
                int del=D[i-1][j]+1;
                int sub=D[i-1][j-1]+diff;
                D[i][j]=Math.min(Math.min(ins,del),sub);
            }
        }

        char[] mas_1 = one.toCharArray();
        char[] mas_2 = two.toCharArray();

        int i = mas_1.length;
        int j = mas_2.length;
        String result = "";
        while (i > 0 || j > 0)
        {
            char op = '#';//копировать
            int el = D[i][j];
            if (i > 0 && j > 0 && D[i - 1][j - 1] < el) {
                el = D[i - 1][j - 1];
                op = '~';//заменить
            }
            if (i > 0 && D[i - 1][j] < el) {
                el = D[i - 1][j];
                op = '-';//удалить
            }
            if (j > 0 && D[i][j - 1] < el) {
                op = '+';//вставить
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
                    result = String.format("%s%s,%s", op, mas_1[i], result);
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
        InputStream stream = new FileInputStream(root + "by/it/group573601/SudzilovskyVl/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}
