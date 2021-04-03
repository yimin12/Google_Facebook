package Contest.Bloomberg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/1 21:08
 *   @Description :
 *
 */
public class EmployeeFreeTime {

    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        if(schedule == null || schedule.size() == 0) return res;
        // flat the map first
        List<Interval> timeline = new ArrayList<>();
        schedule.forEach(e -> timeline.addAll(e));
        // sort it in one dimension(x)
        Collections.sort(timeline, (a, b) -> a.start == b.start ? a.end - b.end : a.start - b.start);
        int start = -1, end = 0;
        for(Interval interval : timeline){
            if(start == -1){
                // starting
                start = interval.start;
                end = interval.end;
            } else {
                if(interval.start > end){
                    res.add(new Interval(end, interval.start));
                    start = interval.start;
                    end = interval.end;
                } else {
                    // merge
                    end = Math.max(end, interval.end);
                }
            }
        }
        return res;

    }
}
