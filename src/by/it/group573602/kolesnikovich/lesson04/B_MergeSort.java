package by.it.group573602.kolesnikovich.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {


    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);

        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        a = mergeSort(a, 0, a.length - 1);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    private int[] mergeSort(int[] array, int start, int end){
        if(start>=end)
            return null;
        int middle=start+(end-start)/2;
        mergeSort(array, start, middle);
        mergeSort(array,middle+1, end);

        int[] buff= Arrays.copyOf(array,array.length);
        int i=start, j=middle+1;
        for(int k=start; k<=end; k++){
            if(i>middle)
                array[k]=buff[j++];
            else if(j>end)
                array[k]=buff[i++];
            else if(buff[j]<buff[i])
                array[k]=buff[j++];
            else //else if(j>end) && else
                array[k]=buff[i++];
        }
        return array;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
/*
    private int[] mergeSort(int[] array, int start, int end){
        if(start>=end)
            return null;
        int middle=start+(end-start)/2;
        mergeSort(array, start, middle);
        mergeSort(array,middle+1, end);

        int[] buff= Arrays.copyOf(array,array.length);
        //for(int k=start; k<=end; k++)
        int i=start, j=middle+1;
        for(int k=start; k<=end; k++){
            if(i>middle || buff[j]<buff[i]){
                array[k]=buff[j];
                j++;
            }
            else{ //else if(j>end) && else
                array[k]=buff[i];
                i++;
            }
        }
        return array;
    }*/

/*
    private int[] mergeSort(int[] array, int start, int end){
        int[] buff=Arrays.copyOf(array,array.length);
        if(start==end) {
            buff[start]=array[start];
            return buff;
        }
        int middle=start+(end-start)/2;
        int[] startArray=mergeSort(array, start, middle);
        int[] endArray=mergeSort(array,middle+1, end);

        int[] newArray;
        if(startArray.equals(array))
            newArray=Arrays.copyOf(buff, buff.length);
        else newArray=Arrays.copyOf(array, array.length);
        int left=start, right=middle+1;
        for(int i=start; i<=end; i++){
            if(left<=middle && right<=middle){
                if(startArray[left]<endArray[right]){
                    newArray[i]=startArray[left];
                    left++;
                }
                else{
                    newArray[i]=endArray[right];
                    right++;
                }
            }
            else if(left<=middle){
                newArray[i]=startArray[left];
                left++;
            }
            else{
                newArray[i]=endArray[right];
                right++;
            }
        }
        return newArray;
    }
 */

/*    private int[] mergeSort(int[] array, int start, int end){
        if(start+1<end){
            int middle=(start+end)>>>1;
            mergeSort(array, start, middle);
            if(end%2==0){
                mergeSort(array, middle+1, end);
                System.out.println(start+"-"+end);
            }
            else mergeSort(array, middle, end);

            int size=end-start;
            int[] buff=new int[size];
            int i=start, j=middle;
            for(int k=0; k<size; k++){
                if(j>=end || i<middle && array[i]<array[j])
                    buff[k]=array[i++];
                else
                    buff[k]=array[j++];
            }
            System.arraycopy(buff, 0, array, start, size);
        }
        return array;
    }*/
