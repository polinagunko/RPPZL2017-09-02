package by.it.group573601.laysha.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_BinaryFind {
    public A_BinaryFind() {
    }

    int[] findIndex(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];

        int k;
        for(k = 1; k <= n; ++k) {
            a[k - 1] = scanner.nextInt();
        }

        k = scanner.nextInt();
        int[] result = new int[k];

        for(int i = 0; i < k; ++i) {
            int value = scanner.nextInt();
            int l = 0;

            for(int r = n - 1; l <= r; result[i] = -1) {
                int m = (l + r) / 2;
                if (a[m] == value) {
                    result[i] = m + 1;
                    break;
                }

                if (a[m] > value) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        int[] result = instance.findIndex(stream);
        int[] var5 = result;
        int var6 = result.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            int index = var5[var7];
            System.out.print(index + " ");
        }

    }
}
