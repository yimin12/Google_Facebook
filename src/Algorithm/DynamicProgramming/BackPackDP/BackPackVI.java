package Algorithm.DynamicProgramming.BackPackDP;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 21:05
 *   @Description :
* 	    Given an integer array nums with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target
* 	    A number in the array can be used multiple times in the combination Different orders are counted as different combinations.
*   Example:
* 	    Given nums =[1, 2, 4], target = 4
* 	    The possible combination ways are:
*   	[1, 1, 1, 1]
	    [1, 1, 2]
	    [1, 2, 1]
	    [2, 1, 1]
	    [2, 2]
	    [4]

	    backPackV is similar with combination, [1,2,1] and [1,1,2] is the same
	    backPackVI is similar with permutation, [1,2,1] and [1,1,2] is different
 */
public class BackPackVI {

    // It is exactly the same as Combination Sum
    // Key Insight:
    // Because all elements are orderless, this is core different between the former backpack question
    // If we want value m, and given some options [a1, a2, a3, a4];
    // We should know how many possibilities that can form (m - a1) + (m - a2) + (m - a3) + (m - a4);
    // i: use i types of items
    // j: value that we want to fullfill
    // induction rule: dp[j] = dp[j-A[0]] + dp[j-A[1]] + .... dp[j-A[i-1]] if possible
    // base case: dp[0] = 1 (there are only one way to fullfill value 0)
    // Time: O(n * m) Space: O(m)
    // rolling array
    public int backPackI(int[] A, int m){
        if(A == null || A.length == 0 || m < 0){
            return 0;
        }
        int[] dp = new int[m + 1];
        dp[0] = 1;
        for(int i = 1; i <= m; i++){
            dp[i] = 0;
            for(int j = 0; j < A.length; j++){
                if(A[j] <= i){
                    dp[i] += dp[i - A[j]];
                }
            }
        }
        return dp[m];
    }

    // Memory Search
    public int backPackII(int[] A, int m){
        if(A == null || A.length == 0 || m < 0){
            return 0;
        }
        int[] memory = new int[m + 1];
        Arrays.fill(memory, -1);
        memory[0] = 1;
        return dfs(A, m, memory);
    }
    private int dfs(int[] A, int m, int[] memory){
        if(memory[m] != -1) return memory[m];
        int res = 0;
        // should try every possibilities
        for(int i = 0; i < A.length; i++){
            if(m >= A[i]){
                res += dfs(A, m - A[i], memory);
            }
        }
        memory[m] = res;
        return res;
    }
    public static void main(String[] args) {
        BackPackVI solution = new BackPackVI();
        int[] array = {1,2,4};
        int res = solution.backPackII(array, 4);
        System.out.println(res);
    }
}
