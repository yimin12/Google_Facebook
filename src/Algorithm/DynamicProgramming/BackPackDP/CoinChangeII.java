package Algorithm.DynamicProgramming.BackPackDP;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 17:18
 *   @Description :
 *		You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest
 *		number of coins that you need to make up that amount. If that amount of money can not be made up by
 *		combination of the coins, return -1;
 *	    Example:
 *		coins = [1,2,5], amount = 11; 	return 3 (11 = 5+5+1)
 *		coins = [2], amount = 3; return -1'
 */
public class CoinChangeII {

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
