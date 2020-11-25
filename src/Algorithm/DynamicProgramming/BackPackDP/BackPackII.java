package Algorithm.DynamicProgramming.BackPackDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 18:46
 *   @Description :
 *  	Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?
 *  	You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
 *  Example:
 *  	Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.
 */
public class BackPackII {

    // dp[i][j] the maximum value by picking first ith items which is not exceed j weight
    // induction rule: dp[i][j]= Max(dp[i-1][j], j >= A[i-1] ? dp[i-1][j-A[i-1]] + V[i-1] : 0)
    // base case: dp[0][0] = 0;
    // Time: O(n*m) Space : O(n*m)
    public int backPackI(int[] A, int[] V, int m){
        if(A == null || V == null || A.length != V.length || m < 0){
            return 0;
        }
        int[][] dp = new int[A.length + 1][m+1];
        dp[0][0] = 0;
        for(int i = 1; i <= A.length; i++){
            for(int j = 1; j <= m; j++){
                if(j >= A[i-1]){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][i-A[i-1]] + V[i-1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[A.length][m];
    }

    // rolling array,
    public int backPackII(int[] A, int[] V, int m){
        if(A == null || V == null || A.length != V.length || m < 0){
            return 0;
        }
        int[] dp = new int[m + 1];
        // the rolling array should start i from 0
        for(int i = 0; i < A.length; i++){
            for(int j = m; j >= A[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);
            }
        }
        return dp[m];
    }
}
