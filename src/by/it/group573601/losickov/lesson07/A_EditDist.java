package by.it.group573601.losickov.lesson07;

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
    String firstString;
    String secondString;

    private int min(int ins, int del, int sub){
        if(ins < del && ins < sub){
            return  ins;
        }
        else if(del < ins && del < sub){
            return del;
        }
        else {
            return sub;
        }
    }


    private int diff(char A, char B){
        if (A == B){
            return 0;
        }
        else return 1;
    }

    private int EditDistTD(int[][] D, int i, int j){
        int ins;
        int del;
        int sub;

        if (D[i][j] == 100){
            if(i == 0){
                D[i][j] = j;
            }
            else if(j == 0){
                D[i][j] = i;
            }
            else{
                ins = EditDistTD(D, i, j-1) + 1;
                del = EditDistTD(D, i-1, j) + 1;
                sub = EditDistTD(D, i-1, j-1) + diff(firstString.charAt(i-1), secondString.charAt(j-1));
                D[i][j] = min(ins, del, sub);
            }
        }

        return D[i][j];
    }


    int getDistanceEdinting(String one, String two) {

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //создание двумерного массива
        firstString = one;
        secondString = two;
        int n = one.length() + 1;
        int m = two.length() + 1;
        int[][] D = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                D[i][j] = 100;
            }
        }

        int i = n-1;
        int j = m-1;


        return EditDistTD(D, i, j);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/losickov/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

