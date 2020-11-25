package Algorithm.SlidingWindow;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/1 20:58
 *   @Description :
 *      Given an array of n positive integers and a positive integers, find the minimal length of a contiguous sub array of which the sum ≥s. If there isn't one, return 0 instead
 * 	    Input:
 * 		    s = 7, nums = [2,3,1,2,4,3]
 * 	    Output: 2
 *  	Explanation: the sub array [4,3]  has the minimal length under the problem constraint.
 */
public class MinimumSizeSubArraySum {

    // Use two pointer to simulate slide window
    // Time: O(n), Space : O(1)
    public int minSubArrayLen(int target, int[] nums){
        int sum = 0, left = 0, right = 0, res = Integer.MAX_VALUE;
        while(right < nums.length){
            sum += nums[right];
            while(sum >= target){
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // Method 2: Binary Search with prefix Sum
    public int minSubArrayLenII(int[] nums, int target){
        int[] prefixSum = new int[nums.length + 1];
        for(int i = 1; i <= prefixSum.length; i++){
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }
        int minLen = Integer.MAX_VALUE;
        for(int i = 0; i < prefixSum.length; i++){
            int end = binarySearch(i+1, prefixSum.length - 1, prefixSum[i] + target, prefixSum);
            if(end == prefixSum.length) break;
            if(end - i < minLen) minLen = end - i;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private int binarySearch(int left, int right, int target, int[] array){
        while(left <= right){
            int mid = left + (right - left)/2;
            if(array[mid] >= target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
