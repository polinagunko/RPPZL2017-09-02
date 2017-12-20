package by.it.group573602.kolesnikovich.lesson07;

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

    String A;
    String B;


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

    private int EditDistBU(int[][] D, int n, int m){

        for(int i = 0; i < n; i++) {
            D[i][0] = i;
        }

        for(int j = 0; j < m; j++){
            D[0][j] = j;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                int c = diff(A.charAt(i-1), B.charAt(j-1));
                D[i][j] = min(D[i-1][j]+1, D[i][j-1]+1, D[i-1][j-1] + c);

            }
        }

        return D[n-1][m-1];
    }


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //создание двумерного массива
        A = one;
        B = two;
        int n = one.length() + 1;
        int m = two.length() + 1;
        int[][] D = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                D[i][j] = 100;
            }
        }


        return EditDistBU(D, n, m);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573602/kolesnikovich/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
