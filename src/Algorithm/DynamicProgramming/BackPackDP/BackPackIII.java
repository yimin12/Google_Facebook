package Algorithm.DynamicProgramming.BackPackDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 18:55
 *   Description:
 * 	    Given n_kind of items with size Ai and value Vi(each item has an infinite number available) and a backpack with size_m. What's the maximum value can you put into the backpack?
 * 	    You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
 * 	    Given 4 items with size[2, 3, 5, 7]and value[1, 5, 2, 4], and a backpack with size 10. The maximum value is 15.
 */
public class BackPackIII {

    // not limited resource
    //  Key insight: logic is similar with coin change
    //  i, number of types items
    //  j, required amount of value you can not exceed
    // dp[i][j] represent the max value we can get by picking n types of items that not exceed the required amount
    // induction rule:
    // dp[i][j] = max {dp[i - 1][j - k*A[i-1]] + k*V[i-1]}, k >= 0, k <= j / A[i-1](enumerate all possible times of item i we can pick and calculate the max)
    // base case: dp[i][0] = 0, dp[0][j] = 0
    // time : O(m*n*k) worst case, Space: O(n*m)
    public int unboundedBackPackI(int[] A, int[] V, int m){
        if(A == null || V == null || A.length != V.length || m < 0){
            return 0;
        }
        int[][] dp = new int[A.length + 1][m + 1];
        dp[0][0] = 0;
        for(int i = 1; i <= A.length; i++){
            for(int j = 1; j <= m; j++){
                for(int k = 0; k <= j/A[i-1]; k++){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - A[i-1]] + k * V[i-1]);
                }
            }
        }
        return dp[A.length][m];
    }

    public int unboundBackPackII(int[] A, int[] V, int m){
        if(A == null || V == null || A.length != V.length || m < 0){
            return 0;
        }
        int[][] dp = new int[A.length + 1][m + 1];
        for(int i = 1; i <= A.length; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = dp[i-1][j];
                if(j >= A[i - 1]){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j - A[i-1]] + V[i-1]);
                }
            }
        }
        return dp[A.length][m];
    }

    // Rolling array
    public int unboundedBackPackIII(int[] A, int[] V, int m){
        if(A == null || V == null || A.length != V.length || m < 0){
            return 0;
        }
        int[] dp = new int[m + 1];
        dp[0] = 0;
        for(int i = 0; i <= A.length; i++){
            for(int j = 1; j <= m; j++){
                dp[j] = Math.max(dp[j], j >= A[i] ? dp[j - A[i]] + V[i] : 0);
            }
        }
        return dp[m];
    }
}
