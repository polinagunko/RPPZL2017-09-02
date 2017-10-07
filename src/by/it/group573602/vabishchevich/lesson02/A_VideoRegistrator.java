package by.it.group573602.vabishchevich.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/
public class A_VideoRegistrator {
    public static void main(String[] args) {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts); //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events){
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;
        result = new ArrayList<>();
        int i = 0; //i - это индекс события events[i]
        Arrays.sort(events); //T(Sort) = O(n Log n)
        while (i < events.length-1){
            result.add(events[i]);
            double r = events[i] + (double) 1;
            while (events[i] < r){
                i = i + 1;
            }
        }
        return result; //вернем итог
        //T(Sort) + O(n) = O(n Log n) - время работы
    }
}
