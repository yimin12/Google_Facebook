package Algorithm.SweepLine;

import DataStructure.AlgoUtils.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 22:38
 *   @Description :
 *  	Given a set of _non-overlapping _intervals, insert a new interval into the intervals (merge if necessary).
 * 	    You may assume that the intervals were initially sorted according to their start times.
 *  Example:
 *  	Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 *  	Output: [[1,5],[6,9]]
 *  	Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *  	Output: [[1,2],[3,10],[12,16]]
 *  	Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class IntervalOperations {

    // Insert Operations
    public List<Interval> insert(List<Interval> intervals, Interval interval){
        List<Interval> res = new ArrayList<>();
        int index = 0;
        while(index < intervals.size()){
            if(interval.start > intervals.get(index).start){
                index++;
            } else {
                break;
            }
        }
        intervals.add(index, interval);
        Interval last = null;
        for(Interval inter : intervals){
            if(last == null || last.end < inter.start){
                res.add(interval);
                last = inter;
            } else {
                last.end = Math.max(last.end, inter.end);
            }
        }
        return res;
    }

    // Merge Intervals
    /*
     * 	Given a collection of intervals, merge all overlapping intervals.
     * Example:
     * 	Input:  [[1,3],[2,6],[8,10],[15,18]]
     * 	Output:	 [[1,6],[8,10],[15,18]]
     * Explanation:
     * 	 Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     */
    public List<Interval> mergeInterval(List<Interval> intervals){
        Collections.sort(intervals, (a, b) -> a.start != b.start ? a.start - b.start : a.end - b.end);
        LinkedList<Interval> merged = new LinkedList<>();
        for(Interval interval : intervals){
            if(merged.isEmpty() || merged.getLast().end < interval.start){
                merged.add(interval);
            } else {
                merged.getLast().end = Math.max(interval.end, merged.getLast().end);
            }
        }
        return merged;
    }
}
