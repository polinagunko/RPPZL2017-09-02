package by.it.group573602.soloshenko.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost;
        int weight;
        int price;

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
            return o.price-this.price;
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
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);
        int i=0;
        for (i=0;i<items.length;i++) {
            items[i].price = items[i].cost / items[i].weight;
        }

        Arrays.sort(items);

        int mycost=0, myW=W;
        for (i=0;i<items.length;i++) {

            if (items[i].weight <= myW) {
                myW = myW - items[i].weight;
                mycost = mycost + items[i].cost;
            } else if (myW != 0 && items[i].weight > myW) {

                int res = items[i].cost / items[i].weight;
                mycost = mycost + 2 * myW;
                myW = myW - myW;

            } else break;
        }
        double result = 0;

        result=mycost;



        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
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