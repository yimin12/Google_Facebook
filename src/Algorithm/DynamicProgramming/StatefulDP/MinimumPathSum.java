package Algorithm.DynamicProgramming.StatefulDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 0:40
 *   @Description :
 *    	Given a _m_x_n _grid filled with non-negative numbers, find a path from top left to bottom right which_minimizes_the sum of all numbers along its path.
 * 	    You can only move either down or right at any point in time.
 * 	    Input:				Output: 7
	    [
	     [1,3,1],
	     [1,5,1],
	     [4,2,1]
	    ]  1->3->1->1->1
 */
public class MinimumPathSum {
    // Idea is similar with Unique Path, but it involves weight
    // dp[i][j] = represent the min Path Sum from start i, j
    // Induction rule: dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j] (dp[i][j-1] comes from left, dp[i-1][j] comes from up)
    // base case: dp[0][0] = grid[0][0]
    // Time: O(m*n) Space: O(m*n)
    public int minpathSum(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) dp[i][j] = 0;
                else if(i == 0 || j == 0){
                    dp[i][j] = i == 0 ? dp[i][j - 1] + matrix[i][j] : dp[i-1][j] + matrix[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + matrix[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
