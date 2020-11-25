package Algorithm.QueueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 20:04
 *   @Description :
 * 	    Given a non-negative integer array representing the heights of a list of adjacent bars.
 * 	    Suppose each bar has a width of 1. Find the largest rectangular area that can be formed in
 * 	    the histogram.
 *  Assumptions:
 * 	    The given array is not null or empty
 *  Examples:
 * 	    { 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9
 * 	    (starting from index 2 and ending at index 4)
 */
public class LargestRectangleHistogram {

    public int largestHistogram(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i <= array.length; i++){
            int cur = i == array.length ? 0 : array[i];
            // case 1: if cur > array[stack.peekFirst()] : offered to the ascending stack
            // case 2: if cur <= array[stack.peekFirst()] : offered to the first element that is smaller than cur, and update the result if needed
            while(!stack.isEmpty() && array[stack.peekFirst()] >= cur){
                int height = array[stack.pollFirst()];
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                res = Math.max(res, height * (i - left));
            }
            stack.offerFirst(i);
        }
        return res;
    }
}
