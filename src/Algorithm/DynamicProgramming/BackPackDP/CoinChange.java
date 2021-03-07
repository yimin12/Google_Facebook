package Algorithm.DynamicProgramming.BackPackDP;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/8 19:59
 *   @Description :
 *		You are given coins of different denominations and a total amount of money amount. Write a function to the number
 *		of different combinations that can sum up to that amount.
 *
 *		Example 1:
 *			coins = [1,2] , amount = 5
 *			return 3
 *		Explanation:
 *			5 = 1 + 1 + 1 + 1 + 1 = 1 + 1 + 1 + 2 = 1 + 2 + 2
 *		Note:
 *			You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {


    // Naive dp
    // n = number of types of coins, m = desired amount
    // dp[i][j] = the number of combinations to make up amount j with the first i types of coins
    // Induction rule:
    // 		dp[i][j] = sum(dp[i-1][j],dp[i-1][j-coins[i-1]*1],dp[i-1][j-coins[i-1]*2],dp[i-1][j-coins[i-1]*3]......)
    //      dp[i][j] = sum(dp[i-1][j-k*coins[i-1]) where k is 0 <= k <= j/coins[i-1]
    // Base case: dp[0][0] = 1, dp[0][1...m] = 0;
    // Time: O(n*m^2) time, Space: O(n*m) extra space
    public int naiveChange(int amount, int[] coins){
        if(coins == null || coins.length == 0 || amount < 0){
            return -1;
        }
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= coins.length; i++){
            for(int j = 0; j <= amount; j++){
                for(int k = 0; k * coins[i - 1] <= j; k++){
                    dp[i][j] = dp[i-1][j - k * coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amount];
    }
    // Method 2: faster dp with dp[i][j] has original physical meaning
    // Induction rule : dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]
    // base case : dp[0][0] = 1, d[0][1....m] = 0
    // Time: O(n*m) time, Space: O(n*m) extra space
    public int fasterChange(int amount, int[] coins){
        if(coins == null || coins.length == 0){
            return -1;
        }
        int[][] dp = new int[coins.length + 1][amount + 1];
        for(int i = 0; i <= coins.length; i++){
            for(int j = 0; i <= amount; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = 0;
                }
                dp[i][j] = dp[i - 1][j] + (coins[i-1] <= j ? dp[i][j - coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }

    // rolling array
    // Invariant: g[k] = f[i][k]   if k < j or   g[i-1][k] if k >= j, j is the current amount
    // Time: O(n*m) time, Space: O(m) extra space
    public int rollingArrayChange(int amount, int[] coins){
        if(coins == null || amount < 0){
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 1; i < coins.length; i++){
            for(int j = 0; j <= amount; j++){
                if(coins[i-1] <= j){
                    dp[j] += dp[j - coins[i-1]];
                }
            }
        }
        return dp[amount];
    }



    // ---------------------------------------------------------------- Coinchange 2

    // Assumption: you may assume that you have infinite number of each kind of coin
    // 2d dynamic program:
    // n = number of types of coins
    // m = desired amount;
    // dp[i][j] = represent min number of coins, need to make up amount j with only coins[i....n-1]
    // induction rule:
    // dp[i][j] = min(dp[i+1][j], f[i+1][j-coins[i]]+1, f[i+1][j-coins[i]*2]+2 .....) (use coins[i] k times where k is 0 to j/coins[i])
    // Generalized:
    // dp[i][j] = min(dp[i+1][j-k*coins[i]] + k), 0<=k<=j/coins[i];
    // base case:
    // dp[n][0] = 0;
    // dp[n][1...m] = positive infinity
    // Solution 1: Naive dynamic Programming  Time:O(n*m^2) and O(m*n) extra space
    public int coinChange(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount <= 0){
            return 0;
        }
        int[][] dp = new int[coins.length + 1][amount + 1];
        Arrays.fill(dp[coins.length], Integer.MAX_VALUE); // fill the last row to Integer.MAX_VALUE, you can not use zero coins for making up amount of j
        for(int i = coins.length - 1; i>= 0; i--){
            for(int j = 0; j <= amount; j++){
                dp[i][j] = dp[i + 1][j];
                int max = j / coins[i];
                for(int k = 1; k <= max; k++){
                    int prev = dp[i+1][j-k*coins[i]];
                    if(prev < Integer.MAX_VALUE){
                        dp[i][j] = Integer.min(dp[i][j],prev + k);
                    }
                }
            }
        }
        return dp[0][amount];
    }
    // Solution 2: Optimization for Time complexity when you notice
    // dp[i][j] = min(dp[i+1][j], dp[i+1][j-coins[i]]+1, dp[i+1][j-coins[i]*2]+2 .....)
    // dp[i][j-coins[i]] = min( dp[i+1][j-coins[i]], dp[i+1][j-coins[i]*2]+1 .....)+1
    // Time:O(n*m) and O(m*n) extra space
    public int coinChangeOpt(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount <= 0){
            return 0;
        }
        int[][] dp = new int[coins.length + 1][amount + 1];
        Arrays.fill(dp[coins.length], Integer.MAX_VALUE);
        dp[coins.length][0] = 0;
        for(int i = coins.length - 1; i >= 0; i--){
            for(int j = 0; j <= amount; j++){
                dp[i][j] = dp[i+1][j];
                if(j >= coins[i]){
                    int prev = dp[i][j - coins[i]];
                    if(prev < Integer.MAX_VALUE){
                        dp[i][j] = Integer.min(dp[i][j], prev +1);
                    }
                }
            }
        }
        return dp[0][amount];
    }

    // rolling array
    public int coinChangeI(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount <= 0){
            return 0;
        }
        int[] dp = new int[amount +1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = coins.length - 1; i >= 0; i--){
            for(int j = 0; j <= amount; j++){
                if(j >= coins[i]){
                    int prev = dp[j - coins[i]];
                    if(prev < Integer.MAX_VALUE){
                        dp[j] = Integer.min(dp[j], + prev + 1);
                    }
                }
            }
        }
        return dp[amount];
    }
    // dfs with pruning and greedy thought, guarantee sorted in ascending order
    public int coinChangeDFS(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount <= 0){
            return 0;
        }
        int[] res = new int[]{Integer.MAX_VALUE};
        dfs(amount, coins, 0, 0, res);
        return res[0] == Integer.MAX_VALUE ? -1 :res[0];
    }
    private void dfs(int amount, int[] coins, int index, int count, int[] res){
        if(index == coins.length - 1){
            if(amount %  coins[coins.length - 1] == 0){
                res[0] = Integer.min(res[0], count + amount/coins[coins.length - 1]);
            }
        } else {
            for(int i = amount/coins[index]; i >= 0; i--){
                dfs(amount - i * coins[index], coins, index + 1, count + i, res);
            }
        }
    }
}
