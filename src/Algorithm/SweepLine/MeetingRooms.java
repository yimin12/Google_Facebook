package Algorithm.SweepLine;


import java.util.Arrays;
import java.util.TreeMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 21:13
 *   @Description :
 *  	Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei), determine
 *  	if a person could attend all meetings.
 *      Input: [[0,30],[5,10],[15,20]]
 * 	    Output: false
 *      Input: [[7,10],[2,4]]
 * 	    Output: true;
 */
public class MeetingRooms {

    class Interval{
        int start, end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    // When you encounter Interval problem, preferred sorted first
    // Then, go through the meetings one by one and make sure that each meeting ends before the next one starts.
    // Time complexity : O(nlogn). The time complexity is dominated by sorting. Once the array has been sorted, only O(n) time is taken to go through the array and determine if there is any overlap
    // Space complexity : O(1). Since no additional space is allocated.
    public boolean canAttendMeetings(Interval[] intervals){
        if(intervals == null || intervals.length == 0){
            return true;
        }
        Arrays.sort(intervals, (a, b) -> a.start != b.start ? a.start - b.start : a.end - b.end);
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i - 1].end > intervals[i].start){
                return false;
            }
        }
        return true;
    }
    // Follow Up 2:
    /*
     * 	Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei),
     * 	find the minimum number of conference rooms required
     * Example:
     * 	Input: [[0, 30],[5, 10],[15, 20]]
     * 	Output:	2
     * 	Input:  [[7,10],[2,4]]
     * 	Output: 1
     */
    public int minMeetingRoomsI(Interval[] intervals){
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++){
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0, endsItr = 0;
        for(int i = 0; i < starts.length; i++){
            if(starts[i] < ends[endsItr]){
                rooms++;
            } else {
                endsItr++;
            }
        }
        return rooms;
    }
    // Version 2 of SweepLine: Using TreeMap
    public int minMeetingRoomsII(Interval[] intervals){
        TreeMap<Integer, Integer> sl = new TreeMap<>();
        int res = 0, cnt = 0;
        for(Interval interval : intervals){
            int start = interval.start, end = interval.end;
            sl.put(start, sl.getOrDefault(start, 0) + 1);
            sl.put(end, sl.getOrDefault(end, 0) - 1);
        }
        for(int k : sl.keySet()){
            cnt += sl.get(k);
            res = Math.max(res, cnt);
        }
        return res;
    }
    // Follow Up 3:
    /*
    you have a list intervals of current meetings, and some meeting rooms with start and end timestamp.When a stream of new meeting ask coming in, check if it can be scheduled.
    A meeting room can only hold one meeting at a time. Each inquiry is independent.
    Input:
    Intervals:[[1,2],[4,5],[8,10]], rooms = 1, ask: [[2,3],[3,4]]
    Output: [true,true]
    Explanation:
    For the ask of [2,3], we can arrange a meeting room room0.
    The following is the meeting list of room0:
    [[1,2], [2,3], [4,5], [8,10]]
    For the ask of [3,4], we can arrange a meeting room room0.
    The following is the meeting list of room0:
    [[1,2], [3,4], [4,5], [8,10]]
     */
    int[] sum = new int[50050];
    int[] vis = new int[50050];
    public boolean[] meetingRoomsIII(Interval[] intervals, int rooms, int[][] ask){
        int len = ask.length;
        boolean[] ans = new boolean[len];
        sum[0] = 0;
        int maxn = 0;
        int i;
        for(i = 0; i < intervals.length; i++){
            vis[intervals[i].start]++;
            vis[intervals[i].end]--;
            maxn = Math.max(maxn, intervals[i].end);
        }
        for(i = 0; i < ask.length; i++){
            maxn = Math.max(maxn, ask[i][1]);
        }
        int tmp = 0;
        for(i = 1; i <= maxn; i++){
            tmp += vis[i];
            if(tmp < rooms){
                sum[i] = 0;
            } else {
                sum[i] = 1;
            }
        }
        for(i = 1; i <= maxn; i++){
            sum[i] += sum[i-1];
        }
        for(i = 0; i < ask.length; i++){
            if(sum[ask[i][0] - 1] == sum[ask[i][1] - 1]){
                ans[i] = true;
            }
            else ans[i] = false;
        }
        return ans;
    }
}
