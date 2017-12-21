package by.it.group573601.shishporonok.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lesson 3. C_Heap.
// Задача: построить max-кучу = пирамиду = бинарное сбалансированное дерево на массиве.
// ВАЖНО! НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ НИКАКИЕ КОЛЛЕКЦИИ, КРОМЕ ARRAYLIST (его можно, но только для массива)

//      Проверка проводится по данным файла
//      Первая строка входа содержит число операций 1 ≤ n ≤ 100000.
//      Каждая из последующих nn строк задают операцию одного из следующих двух типов:

//      Insert x, где 0 ≤ x ≤ 1000000000 — целое число;
//      ExtractMax.

//      Первая операция добавляет число x в очередь с приоритетами,
//      вторая — извлекает максимальное число и выводит его.

//      Sample Input:
//      6
//      Insert 200
//      Insert 10
//      ExtractMax
//      Insert 5
//      Insert 500
//      ExtractMax
//
//      Sample Output:
//      200
//      500


public class C_HeapMax {

    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        int siftDown(int u) { //просеивание вниз
            while (2 * u + 1 < heap.size()){
                int right = 2 * u + 2;
                int left = 2 * u + 1;

                int p = left;
                if (right < heap.size() && heap.get(right) > heap.get(left))
                    p = right;
                if (heap.get(u) >= heap.get(p))
                    break;
                long temp = heap.get(u);
                heap.set(u,heap.get(p));
                heap.set(p,temp);
                u = p;
            }
            return u;
        }

        int siftUp(int i) { //просеивание вверх
            while (heap.get(i) > heap.get((i - 1) / 2)){
                long temp = heap.get(i);
                heap.set(i,heap.get((i - 1) / 2));
                heap.set((i - 1) / 2,temp);
                i = (i - 1) / 2;
            }
            return i;
        }

        void insert(Long value) { //вставка
            heap.add(value);
            int index = heap.indexOf(value);
            siftUp(index);
        }

        Long extractMax() {
             //извлечение и удаление максимума
                long r = heap.get(0);
                heap.set(0, heap.get(heap.size() - 1));
                heap.remove(heap.size() - 1);
                siftDown(0);
                return r;


        }
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        MaxHeap heap = new MaxHeap();
        Long maxValue=0L;

        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res=heap.extractMax();
                if (res!=null && res>maxValue) maxValue=res;{
                System.out.println();
                i++;
            }}
            if (s.contains(" ")) {

                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert")){
                    heap.insert(Long.parseLong(p[1]));
                i++;
            }}
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream));
    }

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}
