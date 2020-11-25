package Algorithm.SlidingWindow;

import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/2 23:36
 *   @Description :
 *      Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
        Example:
        MovingAverage m = new MovingAverage(3);
        m.next(1) = 1
        m.next(10) = (1 + 10) / 2
        m.next(3) = (1 + 10 + 3) / 3
        m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageFromDataStream {

    private int sum;
    private Queue<Integer> queue;
    private int capacity;

    public MovingAverageFromDataStream(int size){
        this.capacity = size;
        sum = 0;
        queue = new LinkedList<>();
    }
    public double next(int val){
        if(queue.size() == capacity){
            int sub = queue.poll();
            sum = sum - sub;
        }
        queue.offer(val);
        sum += val;
        return (double)sum/ queue.size();
    }

}
