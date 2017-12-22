package by.it.group573601.michmel.lesson09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9

*/

public class B_Knapsack {

    int knapsackWithoutRep(int weights[],  int needed) {
        int n = weights.length;
        int D[][] = new int[needed + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            for (int w = 1; w <= needed; w++) {
                if (weights[j-1] <= w) {
                    D[w][j] = Math.max(D[w][j - 1], D[w - weights[j-1]][j - 1] + weights[j-1]);
                } else {
                    D[w][j] = D[w][j - 1];
                }
            }
        }
        return D[needed][n];
    }

    int getMaxWeight(InputStream stream ) {

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        Scanner scanner = new Scanner(stream);
        int maxWeight = scanner.nextInt();
        int quantity=scanner.nextInt();
        int gold[]=new int[quantity];
        for (int i = 0; i < quantity; i++) {
            gold[i]=scanner.nextInt();
        }


        return knapsackWithoutRep( gold,  maxWeight);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson09/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}

