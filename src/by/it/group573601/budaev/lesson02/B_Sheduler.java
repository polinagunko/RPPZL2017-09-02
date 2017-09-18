package by.it.group573601.budaev.lesson02;

import java.util.*;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.

Алгоритм жадный. Для реализации обдумайте надежный шаг.

*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event implements Comparable<Event> {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }


        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }




        @Override
        public int compareTo(Event o) {
            return this.stop-o.stop;
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                            new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                            new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                            new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                            new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                            new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
                          };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {

        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.
        List<Event> custom=new ArrayList<>();
        Collections.addAll(custom,events);
        Collections.sort(custom);

        for(int i=0;i<custom.size();i++){
            for(int j=i+1;j<custom.size();j++) {
                if (custom.get(i).start != custom.get(j).start) {


                } else {
                    custom.remove(j);
                    j--;
                }
            }
            result.add(custom.get(i));
        }

        for(int i=0;i<result.size();i++){

            for(int j=i+1;j<result.size();j++){

                if((result.get(i).start < result.get(j).start) && (result.get(i).stop == result.get(j).stop)){
                    result.remove(j);
                    j--;
                }
            }
        }





        return result;                        //вернем итог
    }
}
