package by.it.group573601.podgaiskii.lesson09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Даны число 1<=n<=100 ступенек лестницы и
целые числа −10000<=a[1],…,a[n]<=10000, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице
снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на
одну или на две ступеньки.

Sample Input 1:
2
1 2
Sample Output 1:
3

Sample Input 2:
2
2 -1
Sample Output 2:
1

Sample Input 3:
3
-1 2 1
Sample Output 3:
3

*/

public class C_Stairs {
    int n;
    int array[];
    int getSum(int step, int sum) {
        int s1, s2;

        if (step == n) {
            s1 = s2 = sum;
        }
        else {
            s1 = s2 = -10000;
            if(array[step] >= 0){
                s1 = getSum(step + 1, sum + array[step]);
            }
            else {
                if (step + 1 <= n)
                    s1 = getSum(step + 1, sum + array[step]);
                if (step + 2 <= n)
                    s2 = getSum(step + 2, sum + array[step + 1]);
            }
        }

        return Math.max(s1, s2);
    }

    int getMaxSum(InputStream stream ) {
        Scanner scanner = new Scanner(stream);
        n = scanner.nextInt();
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i]=scanner.nextInt();
        }
        int sum = getSum(0,0);
        return sum;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group573601/podgaiskii/lesson09/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(stream);
        System.out.println(res);
    }

}
