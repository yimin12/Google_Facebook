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

    private PriorityQueue<Integer> smallHalf;
    private PriorityQueue<Integer> largeHalf;
    public MedianTracker(){
        smallHalf = new PriorityQueue<>(Collections.reverseOrder());
        largeHalf = new PriorityQueue<>();
    }
    public void read(int value){
        if(smallHalf.isEmpty() || value <= smallHalf.peek()){
            smallHalf.offer(value);
        } else {
            largeHalf.offer(value);
        }
        if(smallHalf.size() - largeHalf.size() > 1){
            largeHalf.offer(smallHalf.poll());
        }  else if(largeHalf.size() > smallHalf.size()){
            smallHalf.offer(largeHalf.poll());
        }
    }
    public Double median(){
        int size = size();
        if(size == 0){
            return null;
        } else if(size % 2 == 0){
            return (double)(smallHalf.peek());
        } else {
            return (smallHalf.peek() + largeHalf.peek())/2.0;
        }
    }
    private int size(){
        return smallHalf.size() + largeHalf.size();
    }
}
