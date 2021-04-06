package Contest.Bloomberg;

import java.util.TreeMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/5 23:26
 *   @Description :
 *
 */
public class MyCalendar {


    // Boundary Count by TreeMap and Sweep Line
    // A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)
    TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<Integer, Integer>();
    }
    // each booking() operation (n + logn)
    // Space: O(n)
    public int bookIII(int start, int end) {
        // max event we held
        calendar.put(start,  calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);
        int ongoing = 0, max = 0;
        for(int v : calendar.values()) {
            ongoing += v;
            max = Math.max(max, ongoing);
        }
        return max;
    }

    // can held below 3 events
    public boolean bookII(int start, int end) {
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end,  calendar.getOrDefault(end, 0) -1 );
        int ongoing = 0;
        for(int v : calendar.values()) {
            ongoing += v;
            if(ongoing >=3) {
                // reset
                calendar.put(start, calendar.get(start) - 1);
                calendar.put(end, calendar.get(end) + 1);
                // remove the tentative key, part of reset because we add the data to calendar at the begining;
                if(calendar.get(start) == 0) {
                    calendar.remove(start);
                }
                if(calendar.get(end) == 0) {
                    calendar.remove(end);
                }
                return false;
            }
        }
        return true;
    }
}
