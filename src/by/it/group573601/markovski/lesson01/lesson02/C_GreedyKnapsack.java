package by.it.group573601.markovski.lesson01.lesson02;
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
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost;
        int weight;
        int unitcost;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
            this.unitcost = cost/weight;
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
            return Integer.compare(o.unitcost,this.unitcost);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле1
        int W = input.nextInt();      //какой вес у рюкзака1
        Item[] items = new Item[n];   //получим список предметов1
        for (int i = 0; i < n; i++) { //создавая каждый конструктором1
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        Arrays.sort(items);

        double stealed = 0;
        int freeWeight = W;

        for (Item item:items){
           if (freeWeight == 0){
               break;
           } else if (freeWeight >= item.weight){
               stealed += item.cost;
               freeWeight -= item.weight;
           } else if (freeWeight < item.weight){
               stealed += freeWeight * item.unitcost;
               freeWeight -= freeWeight;
           }
        }





        System.out.printf("Удалось собрать рюкзак на сумму %f\n",stealed);
        return stealed;
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