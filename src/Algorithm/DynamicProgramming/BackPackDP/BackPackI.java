package Algorithm.DynamicProgramming.BackPackDP;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 18:23
 *   @Description :
 *   	Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
 * 	    You can not divide any item into small pieces.
 * 	    If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size
 * 	    we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the
 * 	    backpack.
 * 	    You function should return the max size we can fill in the given backpack.
 *  Challenge:
 * 	    O(n x m) time and O(m) memory.
 */
public class BackPackI {

    // Key Insight: logic is similar with Coins change
    // Dynamic Programming
    // dp[i][j]: The maximum number of items we can pick from the first ith items that would be not over j
    // Induction rule: dp[i][j] = dp[i-1][j] when j < A[i-1] (will exceed if adding the newest item) must not pick the latest one
    //				   dp[i][j] = max(dp[i-1][j], dp[i-1][j-A[i-1]] + A[i-1]); (compare and find the maximum value if we pick or not)
    // Base case : dp[0][j] = 0; dp[i][0] = 0; (0 items to get j or i items to get 0)
    // Time: O(n * m) and Space: O(n * m);
    public int backPackI(int m, int[] A){
        if(A == null || A.length == 0 || m <= 0){
            return 0;
        }
        int[][] dp = new int[A.length + 1][m + 1];
        dp[0][0] = 0;
        for(int i = 1; i <= A.length; i++){
            for(int j = 1; j <= m; j++){
                if(j >= A[i - 1]){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i-1]] + A[i-1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[A.length][m];
    }

    // rolling array
    public int backPackII(int m, int[] A){
        if(A == null || A.length == 0 || m <= 0){
            return 0;
        }
        int[] dp = new int[m + 1];
        for(int i = 0; i < A.length; i++){
            for(int j = m; j > 0; j--){
                dp[j] = Math.max(dp[j], dp[j-A[i]] + A[i]);
            }
        }
        return dp[m];
    }

    // MemorySearch
    int min = Integer.MAX_VALUE;
    public int backPackIII(int m, int[] A){
        if(A == null || A.length == 0 || m <= 0){
            return 0;
        }
        Arrays.sort(A);
        boolean[] visited = new boolean[m+1];
        int[] memory = new int[m+1];
        dfs(m, A, 0, visited, memory);
        return m - min;
    }

    private int dfs(int target, int[] A, int index, boolean[] visited, int[] memory){
        if(visited[target]){
            return memory[target];
        }
        int res = target;
        for(int i = index; i < A.length; i++){
            if(target - A[i] >= 0){
                res = dfs(target - A[i], A, i + 1, visited, memory);
            }
        }
        visited[target] = true;
        memory[target] = res;
        min = Math.min(min, res);
        return res;
    }


}
