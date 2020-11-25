package Algorithm.DynamicProgramming.BackPackDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 20:24
 *    Description:
 * 	    Given n items with size nums[i] which an integer array and all positive numbers. An integer target denotes
 *       the size of a backpack. Find the number of possible fill the backpack.
 * 	    Each item may only be used once
 * 	    Given candidate items[1,2,3,3,7]and target 7,
 * 	    A solution set is:
 * 		    [7], [1, 3, 3]
 *
 *    When you compare it with BackPack 4, (can use ultimate times for each coins or can use at most once for each point)
 *    The key difference in the induction rule:
 * 	    1. When you can use ultimate times for each coin
 *  		dp[i][j] += dp[i-1][j] + dp[i][j-A[i-1]] <- Key Difference
 *  	2. When you can use at most once for each coin
 * 		dp[i][j] += dp[i-1][j] + dp[i-1][j-A[i-1]]; <- Key Difference
 *       Small trick for using rolling array
 * 	    1. when you can use ultimate times for each coins (start from nums[i-1] to target)
 * 		 for (int coin : coins)
	        for (int i = coin; i <= s; i++)
	            dp[i] += dp[i - coin];
	     return dp[s];
	    2. when you can use at most once for each coins (start from target to nums[i-1]) reverse direction compare to case 1
		 for (int coin : coins)
	        for (int i = s; i >= coin; i--)
	            dp[i] += dp[i - coin];
	     return dp[s];
 *
 */
public class BackPackV {

    // i: use i items to fullfill target,
    // j: the value you want to full fill
    // induction rule: dp[i][j] = dp[i-1][j] + dp[i-1][j-A[i-1]]( add two possible situation for picking and no picking)
    // Time: O(n * m)  Space: O(n * m)
    public int boundedBackPackI(int[] A, int m){
        if(A == null || A.length == 0 || m < 0){
            return 0;
        }
        int[][] dp = new int[A.length +1][m +1];
        dp[0][0] = 1;
        for(int i = 1; i <= A.length; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] += dp[i-1][j];
                if(j > A[i - 1]){
                    dp[i][j] += dp[i - 1][j - A[i-1]];
                }
            }
        }
        return dp[A.length][m];
    }

    // rolling array
    public int boundedBackPackII(int[] A, int m){
        if(A == null || A.length == 0 || m < 0){
            return 0;
        }
        int[] dp = new int[m + 1];
        for(int i = 0; i < A.length; i++){
            for(int j = m; j >= A[i]; j--){
                dp[j] += dp[j - A[i]];
            }
        }
        return dp[m];
    }
}
