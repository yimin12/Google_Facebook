package Algorithm.SweepLine;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 22:52
 *   @Description :
 * 	    In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
 * 	    When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If there
 * 	    are multiple such seats, they sit in the seat with the lowest number. (Also, if no one is in the room, then the student
 * 	    sits at seat number 0.)
 * 	    Return a classExamRoom(int N) that exposes two functions:ExamRoom.seat() returning an int representing what seat the student sat in,
 * 	    and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room. It is guaranteed that
 *      any calls toExamRoom.leave(p)have a student sitting in seat p.
 *      Example:
 * 	    Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
	    Output: [null,0,9,4,2,null,5]
	    Explanation:
	    ExamRoom(10) -> null
	    seat() -> 0, no one is in the room, then the student sits at seat number 0.
	    seat() -> 9, the student sits at the last seat number 9.
	    seat() -> 4, the student sits at the last seat number 4.
	    seat() -> 2, the student sits at the last seat number 2.
	    leave(4) -> null
	    seat() -> 5, the student sits at the last seat number 5.
 */
public class ExamRooms {

    // Analysis:
    // Use priority queue to sort by customized class interval{int dist; int x, y;}.
    // Sort by larger distance and then sort by start index
    // seat(): pq.poll() to find interval of largest distance. Split and add new intervals back to queue.
    // leave(x): one seat will be in 2 intervals: remove both from pq, and merge to a new interval.
    PriorityQueue<Interval> pq;
    int N;
    class Interval{
        int x, y, dist;
        public Interval(int x, int y){
            this.x = x;
            this.y = y;
            if(x == -1){
                this.dist = y;
            } else if(y == N){
                this.dist = N - 1 - x;
            } else {
                this.dist = Math.abs(x - y) / 2;
            }
        }
    }
    public ExamRooms(int N){
        this.N = N;
        this.pq = new PriorityQueue<>((a, b)-> a.dist != b.dist ? b.dist - a.dist : a.x - b.x);
        pq.add(new Interval(-1, N));
    }
    public int seat(){
        // every one should start sitting on 0
        int seat = 0;
        Interval interval = pq.poll();
        if(interval.x == -1) seat = 0;
        else if(interval.y == N) seat = N - 1;
        else seat = interval.x + (interval.x + interval.y) /2;
        pq.offer(new Interval(interval.x, seat));
        pq.offer(new Interval(seat, interval.y));
        return seat;
    }
    public void leave(int p){
        Interval head = null, tail = null;
        List<Interval> intervals = new ArrayList<>(pq);
        for(Interval interval: intervals){
            if(interval.x == p) tail = interval;
            if(interval.y == p) head = interval;
            if(head != null && tail != null){
                break;
            }
        }
        pq.remove(head);
        pq.remove(tail);
        pq.offer(new Interval(head.x, tail.y));
    }

}
