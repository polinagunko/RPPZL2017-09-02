package by.it.group573601.podgaiskii.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost;       //цена
        int weight;     //вес
        int price;      //стоимость

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
            if (price > o.price)
                return -1;
            else if (price < o.price)
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
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        for (Item item:items)
            item.price = item.cost / item.weight;
        Arrays.sort(items);
        int totalPrice = 0, currentN = n, currentW = W;
        for (Item item:items) {
            if (item.weight <= currentW) {
                currentW -= item.weight;
                totalPrice += item.cost;
                currentN--;
            }
            else if (item.weight > currentW && currentN != 0) {
                totalPrice += item.price * currentW;
                currentW = 0;
                break;
            }
            else break;
        }
        System.out.printf("Удалось собрать рюкзак на сумму %d\n",totalPrice);
        return totalPrice;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group573601/podgaiskii/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}