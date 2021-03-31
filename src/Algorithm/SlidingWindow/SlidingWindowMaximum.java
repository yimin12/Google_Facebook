package Algorithm.SlidingWindow;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/30 23:43
 *   @Description :
 *      Given an array of n integer with duplicate number, and a moving window(size k), move the window at each iteration from the start of the array, find the maximum number inside the window at each moving.
        Example
        For array [1, 2, 7, 7, 8], moving window size k = 3. return [7, 7, 8]
        At first the window is at the start of the array like this
        [|1, 2, 7| ,7, 8] , return the maximum 7;
        then the window move one step forward.
        [1, |2, 7 ,7|, 8], return the maximum 7;
        then the window move one step forward again.
        [1, 2, |7, 7, 8|], return the maximum 8;
        Example 2
        Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
        Output: [3,3,5,5,6,7]
        Explanation:

        Window position                Max
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
         1 [3  -1  -3] 5  3  6  7       3
         1  3 [-1  -3  5] 3  6  7       5
         1  3  -1 [-3  5  3] 6  7       5
         1  3  -1  -3 [5  3  6] 7       6
         1  3  -1  -3  5 [3  6  7]      7
        Note:
        You may assume _k _is always valid, 1 ≤ k ≤ input array's size for non-empty array.
        Challenge
        o(n) time and O(k) memory
 */
public class SlidingWindowMaximum {

    // Store the value itself, monotonic queue
    public ArrayList<Integer> maxSlidingWindowI(int[] nums, int k){
        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        if(nums == null || nums.length == 0 || k <= 0){
                return res;
        }
        for(int i = 0; i < k - 1; i++){
            inQueue(queue, nums[i]);
        }
        for(int i = k - 1; i < nums.length; i++){
            inQueue(queue, nums[i]);
            res.add(queue.peekFirst());
            outQueue(queue, nums[i - k + 1]);
        }
        return res;
    }
    private void inQueue(Deque<Integer> queue, int val){
        while(!queue.isEmpty() && queue.peekFirst() < val){
            queue.pollLast();
        }
        queue.offerLast(val);
    }
    private void outQueue(Deque<Integer> queue, int val){
        if(queue.peekFirst() == val){
            queue.pollFirst();
        }
    }

    // Store the index rather than value itself
    public int[] maxSlidingWindow(int[] nums, int k){
        if(nums == null || nums.length == 1 || k <= 0){
            return nums;
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            // number of elements exceed the required k elements
            if(!queue.isEmpty() && queue.peekFirst() < i - k + 1){
                queue.pollFirst();
            }
            while(!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offerLast(i);
            if(i - k + 1 >= 0){
                res[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return res;
    }

    // Optimized solution, O(n), O(1), preferred
    public int[] maxSlidingWindowII(int[] nums, int k){
        if(nums == null || nums.length == 0 || k <= 0){
            return nums;
        }
        int size = nums.length;
        int[] res = new int[size - k + 1];
        int curMax_index = -1;
        int index = 0;
        for(int j = 0; j < size - k + 1; j++){
            if(j > curMax_index){
                curMax_index = j;
                for(int m = j; m < j + k; m++){
                    if(nums[m] >= nums[curMax_index]){
                        curMax_index = m;
                    }
                }
            } else {
                if(nums[j + k - 1] > nums[curMax_index]){
                    curMax_index = j + k - 1;
                }
            }
            res[index++] = nums[curMax_index];
        }
        return res;
    }

}
