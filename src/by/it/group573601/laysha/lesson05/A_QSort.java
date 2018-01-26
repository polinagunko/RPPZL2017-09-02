package by.it.group573601.laysha.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_QSort {
    public A_QSort() {
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        A_QSort.Segment[] points = new A_QSort.Segment[2 * n + m];
        int[] result = new int[m];

        int counter;
        for(counter = 0; counter < 2 * n; ++counter) {
            points[counter++] = new A_QSort.Segment(scanner.nextInt(), 0);
            points[counter] = new A_QSort.Segment(scanner.nextInt(), m + 1);
        }

        for(counter = 2 * n; counter < 2 * n + m; ++counter) {
            points[counter] = new A_QSort.Segment(scanner.nextInt(), counter - 2 * n + 1);
        }

        A_QSort.Segment[] var11 = points;
        int i = points.length;

        int var9;
        A_QSort.Segment point;
        for(var9 = 0; var9 < i; ++var9) {
            point = var11[var9];
            System.out.println(point);
        }

        this.qSort(points, 0, 2 * n + m);
        var11 = points;
        i = points.length;

        for(var9 = 0; var9 < i; ++var9) {
            point = var11[var9];
            System.out.println(point);
        }

        counter = 0;

        for(i = 0; i < 2 * n + m; ++i) {
            if (points[i].index == 0) {
                ++counter;
            } else if (points[i].index > m) {
                --counter;
            } else {
                result[points[i].index - 1] = counter;
            }
        }

        return result;
    }

    private int part(A_QSort.Segment[] p, int l, int h) {
        A_QSort.Segment x = p[l];
        int m = l;

        for(int i = l + 1; i < h; ++i) {
            if (p[i].compareTo(x) <= 0) {
                ++m;
                A_QSort.Segment tmp = p[i];
                p[i] = p[m];
                p[m] = tmp;
            }
        }

        A_QSort.Segment tmp = p[l];
        p[l] = p[m];
        p[m] = tmp;
        return m;
    }

    public void qSort(A_QSort.Segment[] p, int left, int right) {
        if (left < right) {
            int newArray = this.part(p, left, right);
            this.qSort(p, left, newArray);
            this.qSort(p, newArray + 1, right);
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        int[] var5 = result;
        int var6 = result.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            int index = var5[var7];
            System.out.print(index + " ");
        }

    }

    private class Segment implements Comparable<A_QSort.Segment> {
        int point;
        int index;

        public Segment(int point, int index) {
            this.point = point;
            this.index = index;
        }

        public int compareTo(A_QSort.Segment o) {
            return this.point != o.point ? this.point - o.point : this.index - o.index;
        }

        public String toString() {
            String p = "point:" + this.point + " ,index:" + this.index;
            return p;
        }
    }
}
