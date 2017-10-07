package by.it.group573602.vabishchevich.lesson02;
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
import java.util.Comparator;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost; //стоимость
        int weight; //вес

        Item(int cost, int weight) { //дефолт для конструктора
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
            //тут может быть ваш компаратор
            long r1 = (long)cost / o.weight; //в double может появиться погрешность
            long r2 = (long)o.cost / weight;
            return Long.compare(r1, r2);
//            double r1 = (double)cost / weight;
//            double r2 = (double)o.cost / o.weight;
//            return Double.compare(r1, r2);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt()); //заполняем этот предмет
        }
        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);
        Arrays.sort(items);
        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)

        //Алгоритм заполнения рюкзака
        double result = 0; //переменная, в которую складываем текущую сумму
        for (Item item : items){ //перебираем предметы по очереди
            if (item.weight <= W){ //если он помещается целиком в рюкзак
                result += item.cost; //тогда положим его в рюкзак
                W -= item.weight; //вместимость рюкзака уменьшится на его вес
            }
            else{ //если нельзя положить, то отрубим такой кусочек, чтобы он влезал в рюкзак
                result += (double)item.cost * W / item.weight; //стоимость кусочка = стоимость всего предмета * на ту часть предмета, которую мы отпилим
//                W = 0;
                break; //когда отпилили у последнего предмета сколько нужно, прерываемся и дадьше предмет не рассматриваем
            }
        }
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