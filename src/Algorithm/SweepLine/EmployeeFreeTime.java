package Algorithm.SweepLine;

import DataStructure.AlgoUtils.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 23:03
 *   @Description :
 *  	We are given a list schedule of employees, which represents the working time for each employee.
 *  	Each employee has a list of non-overlappingIntervals, and these intervals are in sorted order.
 *  	Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *   Example:
 *  	Input:	 schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 *  	Output:	 [[3,4]]
 *      Explanation:
 * 	    There are a total of three employees, and all common free time intervals would be [-inf, 1], [3, 4], [10, inf]. We discard any intervals that contain inf as they aren't finite.
 */
public class EmployeeFreeTime {

    // Use Sweep Algorithm
    public List<Interval> getFreeTime(List<List<Interval>> schedules){
        List<Interval> res = new ArrayList<>();
        List<Interval> timeLine = new ArrayList<>();
        schedules.forEach(e -> timeLine.addAll(e));
        Collections.sort(timeLine, (a, b) -> a.start - b.start);
        Interval temp = timeLine.get(0);
        for(Interval interval:timeLine){
            if(temp.end < interval.start){
                res.add(new Interval(temp.end, interval.start));
                temp = interval;
            } else {
                temp =  temp.end < interval.end ? interval : temp;
            }
        }
        return res;
    }
}
