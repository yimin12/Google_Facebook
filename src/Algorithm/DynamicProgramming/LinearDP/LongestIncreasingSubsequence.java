package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/3 21:32
 *   @Description :
 * 	    Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.
 *      Assumption:
 * 	    A is not null
 *      Examples:
 * 	        Input: A = {5, 2, 6, 3, 4, 7, 5}
	        Output: 4
	        Because [2, 3, 4, 5] is the longest ascending subsequence.
 */
public class LongestIncreasingSubsequence {

    // Method 1: O(n^2) to implement dynamic programming
    public int longest(int[] array){
        if(array.length == 0){
            return 0;
        }
        int[] dp = new int[array.length];
        int res = 1;
        for(int i = 0; i < array.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                dp[i] = Math.max(dp[i], dp[j] +1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // Method 2: O(nlogn) to record ascending array
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] f = new int[nums.length];
        int res = 0;
        for(int x : nums){
            int i = 0, j = res;
            while(i != j){
                int mid = i + ((j -  i) >> 1);
                if(f[mid] < x){ // find the smallest larger
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            f[i] = x;
            if(i == res) res ++;
        }
        return res;
    }
}
