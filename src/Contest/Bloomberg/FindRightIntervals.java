package Contest.Bloomberg;

import java.util.TreeMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/5 21:55
 *   @Description :
 *
 */
public class FindRightIntervals {

    //  Find Right Interval
    // O(nlogn)
    public int[] findRightInterval(int[][] intervals) {
        if(intervals == null || intervals.length == 0 || intervals[0].length == 0) return new int[0];
        int[] res = new int[intervals.length];
        TreeMap<Integer, Integer> intervalMap = new TreeMap<>();
        for(int i = 0; i < intervals.length; i ++){
            intervalMap.put(intervals[i][0], i); // mapping the interval.start to index
        }
        for(int i = 0; i < intervals.length; i ++){
            Integer key = intervalMap.ceilingKey(intervals[i][1]); // use interval.end to query
            res[i] = key != null ? intervalMap.get(key) : -1;
        }
        return res;
    }


}
