package by.it.group573601.SudzilovskyVl.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost;       //цена
        int weight;     //вес
        int unitPrice;      //стоимость

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
    //макс стоимость
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
        for(i=0;i<items.length;i++)
        {
            items[i].unitPrice=items[i].cost/items[i].weight;//цена за ед  продукции
        }
        Arrays.sort(items);//сорт цены по убыв
        int totalUnitPrice = 0, CostBackpack = n, WeightBackpack= W;//запом знач размера первонач
        for (i=0; i<items.length;i++) {//пока не дойдем до конца
            if (items[i].weight <= WeightBackpack) {//если вес меньше
                WeightBackpack=WeightBackpack- items[i].weight;//если вес меньше то мы из размера рюкзака вычит вес и
                totalUnitPrice = totalUnitPrice+ items[i].cost;//добав стоим тов
                CostBackpack--;
            }
            else if (items[i].weight > WeightBackpack&& CostBackpack != 0) {//проверяем часть вещи
                totalUnitPrice = totalUnitPrice+ items[i].unitPrice * WeightBackpack;//берем часть * на цену и обнуляем
                WeightBackpack= 0;
                break;
            }
            else break;
        }
        System.out.printf("Удалось собрать рюкзак на сумму %d\n",totalUnitPrice);
        return totalUnitPrice;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group573601/SudzilovskyVl/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}