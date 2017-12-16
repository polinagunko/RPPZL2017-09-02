package by.it.group573601.gylinskiy.lesson02;
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

public class C_GreedyKnapsack
{
    private class Item implements Comparable<Item>
    {
        int cost;
        int weight;
        int unit_cost;

        Item(int cost, int weight)
        {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString()
        {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o)
        {
            if(o.unit_cost<this.unit_cost)
                return -1;
            else
                return 1;
        }
    }

    double calc(File source) throws FileNotFoundException
    {
        Scanner input = new Scanner(source);
        int n = input.nextInt();
        int W = input.nextInt();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++)
        {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        for (Item item:items)
        {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);
        for(int i=0;i<items.length;i++)
        {
            items[i].unit_cost=items[i].cost / items[i].weight;
        }
        Arrays.sort(items);

        int fin_cost=0, remaining_w=W,cur_n=n;
        for(int i=0;i<items.length;i++)
        {
            if(items[i].weight<remaining_w)
            {
                remaining_w = remaining_w - items[i].weight;
                fin_cost = fin_cost + items[i].cost;
                cur_n--;
                System.out.println("Запихали полностью: " + items[i]);
            }
            else
            if(items[i].weight > remaining_w && cur_n!=0)
            {
                fin_cost=fin_cost+items[i].unit_cost * remaining_w;
                System.out.println("Запихали частично: " + items[i] + ", цена которой поулчилась = " + items[i].unit_cost * remaining_w + ", вес которой = " + remaining_w);
                remaining_w = 0;
                i++;
            }
            else break;
        }
        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = fin_cost;
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.
        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}