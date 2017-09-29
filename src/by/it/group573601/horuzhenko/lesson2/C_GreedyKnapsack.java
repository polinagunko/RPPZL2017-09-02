package by.it.group573601.horuzhenko.lesson2;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;


public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost;
        int weight;
        int unitPrice;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            if (unitPrice > o.unitPrice)
                return -1;
            else if (unitPrice < o.unitPrice)
                return 1;
            return 0;
        }
    }


    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (int i=0;i<items.length;i++) {
            System.out.println(items[i]);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        for (int i=0;i<items.length;i++) {
            items[i].unitPrice = items[i].cost / items[i].weight;
        }

        Arrays.sort(items);
        int result = 0, currentN = n, currentW = W;
        for (int i=0;i<items.length;i++) {
            if (items[i].weight <= currentW) {
                currentW -= items[i].weight;
                result += items[i].cost;
                currentN--;
            }
            else if (items[i].weight > currentW && currentN != 0) {
                result += items[i].unitPrice * currentW;
                currentW = 0;
                break;
            }
            else break;
        }

        System.out.printf("Удалось собрать рюкзак на сумму %d\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}