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
    Итерационно вычислить расстояние редактирования двух данных непустых строк

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

public class B_EditDist {


    int getDistanceEdinting(String first, String second) {
        int[][] levenDist = new int[first.length() + 1][second.length() + 1];

        for (int i = 0; i < first.length() + 1; i++)
            levenDist[i][0] = i;

        for (int j = 0; j < second.length() + 1; j++)
            levenDist[0][j] = j;

        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                int cost = getDiff(first.charAt(i), second.charAt(j));
                levenDist[i + 1][j + 1] = Math.min(Math.min(levenDist[i][j + 1] + 1,
                        levenDist[i + 1][j] + 1), levenDist[i][j] + cost);
            }
        }

        int result = levenDist[first.length()][second.length()];

        return result;
    }

    private static int getDiff(char one, char two) {
        return (one != two) ? 1 : 0;
    }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!





    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/timuranashkin/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
