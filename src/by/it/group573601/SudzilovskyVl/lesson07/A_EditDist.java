package by.it.group573601.SudzilovskyVl.lesson07;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.InputStream;
        import java.lang.reflect.Array;
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
    String s_1;
    String s_2;
    int[][] D;

    private int function(int i, int j) {
        if (D[i][j] == Integer.MAX_VALUE) {
            if (i == 0) {
                D[i][j] = j;
            } else if (j == 0) {
                D[i][j] = i;
            } else {
                int diff;
                if (s_1.charAt(i - 1) == s_2.charAt(j - 1))
                    diff = 0;
                else diff = 1;
                int ins = function(i, j - 1) + 1;
                int del = function(i - 1, j) + 1;
                int sub = function(i - 1, j - 1) + diff;
                D[i][j] = Math.min(Math.min(ins, del), sub);
            }
        }
        return D[i][j];
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        s_1 = one;
        s_2 = two;
        System.out.println("one=" + one);
        System.out.println("two=" + two);
        int m = s_1.length();
        int n = s_2.length();
        D = new int[m + 1][n + 1];
        for (int[] res : D)
            Arrays.fill(res, Integer.MAX_VALUE);
        int i = m;
        int j = n;
        return function(i, j);
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/SudzilovskyVl/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}

