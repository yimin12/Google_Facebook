package Algorithm.SweepLine;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/29 0:37
 *   @Description :
 *      Question
        Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?
        Notice
        If landing and flying happens at the same time, we consider landing should happen at first.
        Example
        For interval list
        [
          [1,10],
          [2,3],
          [5,8],
          [4,7]
        ]
        Return 3
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class NumAirplanes {

    class Interval{
        int start, end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public int countAfAirplanes(List<Interval> airplanes){
        if(airplanes == null || airplanes.size() == 0){
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(Interval flight : airplanes){
            int start = flight.start;
            int end = flight.end;
            for(int i = start; i < end; i++){
                if(map.containsKey(i)){
                    map.put(i, map.get(i) + 1);
                } else {
                    map.put(i, 1);
                }
                max = Math.max(max, map.get(i));
            }
        }
        return max;
    }

    // sweep line
    class Point{
        int time;
        int delta;
        Point(int time, int delta){
            this.time = time;
            this.delta = delta;
        }
    }

    public int countOfAirplanes(List<Interval> airplanes){
        if(airplanes == null || airplanes.size() == 0){
            return 0;
        }
        List<Point> timePoints = new ArrayList<>();
        for(Interval flight : airplanes){
            timePoints.add(new Point(flight.start, 1));
            timePoints.add(new Point(flight.end, -1));
        }
        Collections.sort(timePoints, (a, b)-> a.time == b.time ? a.delta - b.delta : a.time - b.time);
        int max = 0;
        int sum = 0;
        for(Point p : timePoints){
            sum += p.delta;
            max = Math.max(sum, max);
        }
        return max;
    }
}
