package Algorithm.SlidingWindow;

import java.util.Collections;
import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/2 23:25
 *   @Description :
 ** 	Given an unlimited flow of numbers, keep track of the median of all elements seen so far.
 * 	    You will have to implement the following two methods for the class
 * 	    read(int value) - read one value from the flow
 * 	    median() - return the median at any time, return null if there is no value read so far
 *  Examples:
 * 	    read(1), median is 1
	    read(2), median is 1.5
	    read(3), median is 2
	    read(10), median is 2.5
 */
public class MedianTracker {

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    int count;

    /** initialize your data structure here. */
    public MedianTracker() {
        // store smaller half
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // store larger half
        minHeap = new PriorityQueue<>();
        count = 0;
    }

    public void addNum(int num) {
        count ++;
        if(maxHeap.isEmpty() || num <= maxHeap.peek()){
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        if(maxHeap.size() - minHeap.size() > 1){
            minHeap.offer(maxHeap.poll());
        } else if(minHeap.size() > maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if(count % 2 == 0){
            return (maxHeap.peek() + minHeap.peek())/2.0;
        }
        return maxHeap.peek();
    }
}
