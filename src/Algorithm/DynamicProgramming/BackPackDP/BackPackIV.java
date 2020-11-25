package Algorithm.DynamicProgramming.BackPackDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 20:02
 *   Description:
 * 	    Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. An integer target
 * 	    denotes the size of a backpack. Find the number of possible fill the backpack
 *  	Each item may be chosen unlimited number of times
 *  Example: Given candidate items[2,3,6,7] and target 7,
 * 	    A solution set is:
 * 	    [7], [2, 2, 3]
 */
public class BackPackIV {

    // Key Insight: Logic is exactly the logic in coin change
    // 2D array
    // dp[i][j] : use i items to fullfill the backpack
    // induction rule : dp[i][j] += dp[i-1][j-k*A[i-1];
    // base case: dp[i][0] = 1 (already fullfill) dp[0][j] = 0, can not be fullfill
    public int unboundedBackPack(int[] A, int target){
        if(A == null || A.length == 0 || target < 0){
            return 0;
        }
        int[][] dp = new int[A.length + 1][target + 1];
        for(int i = 1; i <= A.length; i++){
            for(int j = 1; j <= target; j++){
                dp[i][j] = dp[i - 1][j];
                for(int k = 0; k <= j/A[i - 1]; k++){
                    dp[i][j] += dp[i - 1][j - k*A[i-1]];
                }
            }
        }
        return dp[A.length][target];
    }

    public int unboundedBackPackII(int[] A, int target){
        if(A == null || A.length == 0 || target < 0){
            return 0;
        }
        int[][] dp = new int[A.length + 1][target + 1];
        for(int i = 1; i <= A.length; i++){
            for(int j = 1; j <= target; j++){
                dp[i][j] += dp[i-1][j];
                if(j >= A[i - 1]){
                    dp[i][j] += dp[i][j - A[i - 1]];
                }
            }
        }
        return dp[A.length][target];
    }

    // rolling array
    public int unboundedBackPackIII(int[] A, int target){
        if(A == null || A.length == 0 || target < 0){
            return 0;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1; // base case should start from 1: you take nothing that can form value of 0
        for(int i = 0; i < A.length; i++){
            for(int j = A[i-1]; j <= target; j++){
                dp[j] += dp[j - A[i]];
            }
        }
        return dp[target];
    }
}
