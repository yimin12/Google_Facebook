package Algorithm.SweepLine;

import DataStructure.AlgoUtils.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 23:10
 *   @Description :
 * 	    Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b>
* 	    to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by
* 	    only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine
*       them.
*       Example 1:
* 	    Input:
		    s = "abcxyz123"
		    dict = ["abc","123"]
	        Output:
		    "<b>abc</b>xyz<b>123</b>"
	    Input:
		    s = "aaabbcc"
		    dict = ["aaa","aab","bc"]
	    Output:
		    "<b>aaabbc</b>c"
 */
public class AddBoldTagInString {

    // Method 1: Merge Interval + Sweep Line
    // Step 1: create a list of tuples/intervals with opening/closing positions, e.g. (open_index, close_index)
    // Step 2: merge the list of intervals
    // Step 3: go through the merged interval list and insert the tags into the string
    // IIRC Java's implementation of .indexOf() is just the naive string matching algorithm, which is O(n+m) average and O(n*m) worst case.
    public String addBoldTag(String s, String[] dict){
        List<Interval> intervals = new ArrayList<>();
        for(String str : dict){
            int index = s.indexOf(str, 0); // Consume O(n)
            while(index != -1){
                intervals.add(new Interval(index, index + str.length()));
                index = s.indexOf(str, index + 1);
            }
        }
        Collections.sort(intervals, (a, b) -> a.start -b.start);
        List<Interval> merged = mergeIntervals(intervals);
        int pre = 0;
        StringBuilder sb = new StringBuilder();
        for(Interval interval : intervals){
            sb.append(s.substring(pre, interval.start));
            sb.append("<b>" + s.substring(interval.start, interval.end) + "</b>");
            pre = interval.end;
        }
        if(pre < s.length()){
            sb.append(s.substring(pre));
        }
        return sb.toString();
    }
    private List<Interval> mergeIntervals(List<Interval> intervals){
        List<Interval> merged = new ArrayList<>();
        if(intervals == null || intervals.isEmpty()){
            return merged;
        }
        merged.add(intervals.get(0));
        for(int i = 1; i < intervals.size(); i++){
            Interval temp = intervals.get(i);
            if(temp.start > merged.get(merged.size() - 1).end){
                merged.add(temp);
            } else {
                int max =  Math.max(merged.get(merged.size() - 1).end, temp.end);
                merged.get(merged.size() - 1).end = max;
            }
        }
        return merged;
    }
}
