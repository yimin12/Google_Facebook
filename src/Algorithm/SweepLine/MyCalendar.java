package Algorithm.SweepLine;

import java.util.TreeMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 22:23
 *   @Description :
 * 	    Implement aMyCalendarclass to store your events. A new event can be added if adding the event will not cause a double booking.
 * 	    Your class will have the method,book(int start, int end). Formally, this represents a booking on the half open
 * 	    interval[start, end), the range of real numbersxsuch thatstart <= x < end.
 * 	    Adouble bookinghappens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 * 	    For each call to the methodMyCalendar.book, returntrueif the event can be added to the calendar successfully
 *      without causing a double booking. Otherwise, returnfalseand do not add the event to the calendar.
 *      Your class will be called like this:
 *   	MyCalendar cal = new MyCalendar();
 *   	MyCalendar.book(start, end)
 *      Example:
 * 	    MyCalendar();
		    MyCalendar.book(10, 20); // returns true
		    MyCalendar.book(15, 25); // returns false
		    MyCalendar.book(20, 30); // returns true
        Explanation:
  	    The first event can be booked.  The second can't because time 15 is already booked by another event.
	    The third event can be booked, as the first event takes every time less than 20, but not including 20.
 */
public class MyCalendar {

    // Naive way, not using sweep line
    private TreeMap<Integer, Integer> calendarMap;
    public MyCalendar(){
        this.calendarMap = new TreeMap<>();
    }

    public boolean bookCalendar(int start, int end){
        Integer prev = calendarMap.floorKey(start); // largest value smaller than start
        Integer next = calendarMap.ceilingKey(end);
        if((prev == null) || start >= calendarMap.get(prev) && (next == null || end >= next)){
            calendarMap.put(start, end);
            return true;
        }
        return false;
    }

    // follow up 2: sweep line algorithm
    /*
    * 	Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a
    * 	triple booking.
    * 	Your class will have one method,book(int start, int end). Formally, this represents a booking on the half open
    * 	interval[start, end), the range of real numbers x such that start <= x < end.
    * 	A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
    * 	For each call to the methodMyCalendar.book, return true if the event can be added to the calendar successfully
    *   without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
    *   MyCalendar();
		MyCalendar.book(10, 20); // returns true
		MyCalendar.book(50, 60); // returns true
		MyCalendar.book(10, 40); // returns true
		MyCalendar.book(5, 15); // returns false
		MyCalendar.book(5, 10); // returns true
		MyCalendar.book(25, 55); // returns true
     */
    // Time complexity for N times booking is O(n(logn + n)) and the worst case is O(n^2 + nlogn)
    // Space: O(n)
    // you can change 3 to k if ok
    public boolean bookCalendarII(int start, int end){
        calendarMap.put(start, calendarMap.getOrDefault(start, 0) + 1);
        calendarMap.put(end, calendarMap.getOrDefault(end, 0) - 1);
        int ongoing = 0;
        for(int v : calendarMap.keySet()){
            ongoing += v;
            if(ongoing >= 3){
                // reset
                calendarMap.put(start, calendarMap.get(start) - 1);
                calendarMap.put(end, calendarMap.get(end) + 1);
                if(calendarMap.get(start) == 0){
                    calendarMap.remove(start); // save a lot of memory, no need save an unused key
                }
                if(calendarMap.get(end) == 0){
                    calendarMap.remove(end);
                }
                return false;
            }
        }
        return true;
    }

    // Follow up 3:
    /*
    * 	Implement a MyCalendarThree class to store your events. A new event can always be added.
    * 	Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval[start, end), the range of real numbers x such that start <= x < end.
    * 	AK-bookinghappens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)
    * 	For each call to the methodMyCalendar.book, return an integer K representing the largest integer such that there exists aK-booking in the calendar.
    * 	Your class will be called like this:
    * 		MyCalendarThree cal = new MyCalendarThree();
    * 		MyCalendarThree.book(start, end)
    * Example:
    * 	MyCalendarThree.book(10, 20); // returns 1
        MyCalendarThree.book(50, 60); // returns 1
        MyCalendarThree.book(10, 40); // returns 2
        MyCalendarThree.book(5, 15); // returns 3
        MyCalendarThree.book(5, 10); // returns 3
        MyCalendarThree.book(25, 55); // returns 3
     */
    // each booking() operation (n + logn)
    // Space: O(n)
    public int bookCalenderIII(int start, int end){
        calendarMap.put(start, calendarMap.getOrDefault(start, 0) + 1);
        calendarMap.put(end, calendarMap.getOrDefault(end, 0) - 1);
        int ongonng = 0, max = 0;
        for(int v : calendarMap.values()){
            ongonng += v;
            max = Math.max(ongonng, max);
        }
        return max;
    }
}
