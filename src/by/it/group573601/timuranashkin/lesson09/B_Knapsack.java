package by.it.group573601.timuranashkin.lesson09;

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

    int getMaxWeight(InputStream knapsack ) {
    Scanner scanner = new Scanner(knapsack);
    int w = scanner.nextInt();
    int n = scanner.nextInt();
    int gold[] = new int[n];
    for( int i = 0; i < n ;i++) {
        gold[i] = scanner.nextInt();
    }
    int result = 0;
    int[][] arr = new int[n + 1][w + 1];
    for( int i = 1; i<=n; i++)
    {
        int weight_one = gold[i - 1];
        int price_one = gold[i - 1];
        for (int j = 0; j <= w; j++) {
            arr[i][j] = arr[i - 1][j];
            if (weight_one <= j) {
                int weight = arr[i - 1][j - weight_one] + price_one;
                arr[i][j] = Math.max(arr[i][w], weight);
                result = arr[n][w];
            }
        }
    }
    return result;

}
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/timuranashkin/lesson09/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}

