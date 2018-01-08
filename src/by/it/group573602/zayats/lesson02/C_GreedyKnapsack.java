package by.it.group573602.zayats.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

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
        int n = input.nextInt();
        int W = input.nextInt();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        for (Item item : items)
            item.price = item.cost / item.weight;
        Arrays.sort(items);
        int totalPrice = 0, currentN = n, currentW = W;
        for (Item item : items) {
            if (item.weight <= currentW) {
                currentW -= item.weight;
                totalPrice += item.cost;
                currentN--;
            } else if ((item.weight > currentW && currentN != 0)) {
                totalPrice += item.price * currentW;
                currentW = 0;
                break;
            } else break;
        }
        System.out.printf("Удалось собрать рюкзак на сумму %d\n", totalPrice);
        return totalPrice;
    }
}