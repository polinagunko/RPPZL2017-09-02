package by.it.group573601.laysha.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class
A_EditDist {
    String s_1;
    String s_2;
    int[][] D;

    public A_EditDist() {
    }

    private int function(int i, int j) {
        if (this.D[i][j] == 2147483647) {
            if (i == 0) {
                this.D[i][j] = j;
            } else if (j == 0) {
                this.D[i][j] = i;
            } else {
                byte diff;
                if (this.s_1.charAt(i - 1) == this.s_2.charAt(j - 1)) {
                    diff = 0;
                } else {
                    diff = 1;
                }

                int ins = this.function(i, j - 1) + 1;
                int del = this.function(i - 1, j) + 1;
                int sub = this.function(i - 1, j - 1) + diff;
                this.D[i][j] = Math.min(Math.min(ins, del), sub);
            }
        }

        return this.D[i][j];
    }

    int getDistanceEdinting(String one, String two) {
        this.s_1 = one;
        this.s_2 = two;
        System.out.println("one=" + one);
        System.out.println("two=" + two);
        int m = this.s_1.length();
        int n = this.s_2.length();
        this.D = new int[m + 1][n + 1];
        int[][] var5 = this.D;
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            int[] res = var5[var7];
            Arrays.fill(res, 2147483647);
        }

        return this.function(m, n);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/laysha/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
