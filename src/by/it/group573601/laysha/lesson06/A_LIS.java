package by.it.group573601.laysha.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_LIS {
    public A_LIS() {
    }

    int getSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] m = new int[n];

        int result;
        for(result = 0; result < n; ++result) {
            m[result] = scanner.nextInt();
        }

        result = 0;
        int[] succession = new int[n];

        int i;
        for(i = 0; i < n; ++i) {
            succession[i] = 1;

            for(int j = 0; j < i; ++j) {
                if (m[j] < m[i]) {
                    succession[i] = succession[j] + 1;
                }
            }
        }

        for(i = 0; i < n; ++i) {
            result = Math.max(result, succession[i]);
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/laysha/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
