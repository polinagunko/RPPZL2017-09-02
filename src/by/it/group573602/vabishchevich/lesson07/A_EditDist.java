package by.it.group573602.vabishchevich.lesson07;

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
    String One;
    String Two;
    private int priority(int insert, int deleting, int substitution)
    {
        if(insert < deleting && insert < substitution)
        {
            return  insert;
        }
        else if(deleting < insert && deleting < substitution)
        {
            return deleting;
        }
        else {
            return substitution;
        }
    }

    private int diff(char One, char Two)
    {
        if (One == Two)
        {
            return 0;
        }
        else return 1;
    }

    private int EditDistTD(int[][] D, int i, int j)
    {
        int insert;
        int deleting;
        int substitution;
        if (D[i][j] == 100)
        {
            if(i == 0)
            {
                D[i][j] = j;
            }
            else if(j == 0)
            {
                D[i][j] = i;
            }
            else
            {
                substitution = EditDistTD(D, i-1, j-1) + diff(One.charAt(i-1), Two.charAt(j-1));
                insert = EditDistTD(D, i, j-1) + 1;
                deleting = EditDistTD(D, i-1, j) + 1;
                D[i][j] = priority(insert, deleting, substitution);
            }
        }
        return D[i][j];
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //создание двумерного массива
        One = one;
        Two = two;
        int n = one.length() + 1;
        int m = two.length() + 1;
        int[][] D = new int[n][m];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                D[i][j] = 100;
            }
        }
        int i = n-1;
        int j = m-1;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return EditDistTD(D, i, j);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/akhmelev/lesson08/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

