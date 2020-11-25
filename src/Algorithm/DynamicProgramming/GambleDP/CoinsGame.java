package Algorithm.DynamicProgramming.GambleDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/12 19:45
 *   @Description :
 *  	    There are n coins in a line. Two players take turns to take one or two coins from right side until there are no
 * 	        more coins left. The player who take the last coin wins.
 * 	        Could you please decide the first play will win or lose?
 * 	    Example
		    n = 1, return true.
		    n = 2, return true.
		    n = 3, return false.
		    n = 4, return true.
		    n = 5, return true
 */
public class CoinsGame {

    // It is also called Coins In Line
    public boolean firstWillWinI(int n){
        int[] memory = new int[n + 1];
        return memorySearchI(memory, n);
    }
    private boolean memorySearchI(int[] mem, int n){
        // purning with memory search
        if(mem[n] != 0){
            if(mem[n] == 1){
                return false;
            } else {
                return true;
            }
        }
        // base case
        if(n <= 0){
            mem[n] = 1;
        } else if(n <= 2 && n >= 1){
            mem[n] = 2; // you left one or two to the opposite
        } else {
            if((memorySearchI(mem, n - 2) && memorySearchI(mem, n - 1))){
                mem[n] = 2;
            } else {
                mem[n] = 1;
            }
        }
        return mem[n] == 2;
    }

    /*
     * 	There are n coins with different value in a line. Two players take turns to take one or two coins from left side
     * 	until there are no more coins left. The player who take the coins with the most value wins.
     * Could you please decide the first player will win or lose?
     * Example:
     * 	Given values array A = [1,2,2], return true.
     * 	Given A = [1,2,4], return false.
     */
    // Key insight: left as less as possible to your opposite

    // State: dp[i] the maximum value we can get if there are i coins left
    // Function: i : how many types of coins we have
    //		     sum[i] : sum of last i coins
    // Induction rule: dp[i] = sum[i] - min(dp[i-1],dp[i-2])
    // Base case: dp[0] = 0, dp[1] = coins[n-1], dp[2] = coins[n-1] + coins[n-2]
    public boolean canFirstWinII(int[] values){
        if(values == null || values.length == 0){
            return true;
        }
        int len = values.length;
        int[] prefixSum = new int[len + 1];
        int[] dp = new int[len + 1];
        for(int i = 1; i <= len; i++){
            prefixSum[i] = prefixSum[i - 1] + values[len - i];
        }
        dp[0] = 0;
        dp[1] = values[len - 1]; // traverse in reversed direction
        for(int i = 2; i <= len; i++){
            dp[i] = prefixSum[i] - Math.min(dp[i - 1], dp[i - 2]);
        }
        return dp[len] > prefixSum[len]/2;
    }

    // Follow Up 3:
    /*
     * 	There are n coins in a line, and value of i-th coin is values[i]
     * 	Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.
     * 	Could you please decide the first player will win or lose?
     * 	Input: [3, 2, 2]	Output: true	Explanation: The first player takes 3 at first. Then they both take 2.
     */
    // Key Insight: RangeDP
    public boolean canFirstWinIII(int[] values){
        if(values == null || values.length == 0){
            return true;
        }
        int n = values.length;
        int[] prefixSum = new int[n + 1];
        for(int i = 1; i <= n; i++){
            prefixSum[i] =prefixSum[i - 1] + values[i - 1];
        }
        int[][] dp = new int[n][n];
        // base case: means only one coins left
        for(int i = 0; i < n; i++){
            dp[i][i] = values[i];
        }
        for(int len = 2; len <= n ; len++){
            for(int left = 0; left < n; left++){
                int right = left + len - 1;
                if(right >= n){
                    continue;
                }
                int subTotal = prefixSum[right + 1] - prefixSum[left];
                dp[left][right] = Math.max(subTotal - dp[left + 1][right], subTotal - dp[left][right - 1]);
            }
        }
        return dp[0][n - 1] > prefixSum[n]/2;
    }
    // memorySearch
    public boolean canFirstWinMSII(int[] values){
        if(values == null || values.length == 0){
            return true;
        }
        int n = values.length;
        int[][] dp = new int[n + 1][n + 1];
        boolean[][] flag = new boolean[n + 1][n + 1];
        int sum = 0;
        for(int val : values){
            sum += val;
        }
        return memorySearchII(0, n - 1, dp, flag, values) > sum / 2;
    }
    private int memorySearchII(int left, int right, int[][] dp, boolean[][] flag, int[] values){
        if(flag[left][right]){
            return dp[left][right];
        }
        flag[left][right] = true;
        if(left > right){
            return 0;
        } else if(left == right){
            return values[left];
        } else if(left == right - 1){
            return Math.max(values[left], values[right]);
        } else {
            int leftEnd = memorySearchII(left + 1, right, dp, flag, values);
            int rightEnd = memorySearchII(left, right - 1, dp, flag, values);
            dp[left][right] = Math.max(leftEnd, rightEnd);
        }
        return dp[left][right];
    }
}
