package by.it.group573601.michmel.lesson09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:
15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {

    int knapsackWithRep(int weights[],  int W)
    {
        int size = weights.length;
        int[] D = new int[W + 1];
        D[0] = 0;
        for (int w = 1; w <= W; w++)
        {
            D[w] = D[w-1];
            for (int i = 0; i < size; i++)
            {
                if (weights[i] <= w)
                {
                    D[w] = Math.max(D[w], D[w - weights[i]] + weights[i]);
                }
            }
        }
        return D[W];
    }


    public int getMaxWeight(InputStream stream ) {

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        Scanner scanner = new Scanner(stream);
        int maxWeight = scanner.nextInt();
        int quantity = scanner.nextInt();

        int gold[] = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            gold[i] = scanner.nextInt();
        }

        return knapsackWithRep(gold, maxWeight);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson09/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}

