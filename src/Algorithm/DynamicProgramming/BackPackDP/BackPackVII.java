package Algorithm.DynamicProgramming.BackPackDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 22:11
 *   @Description :
 *      Assume that you have n yuan. There are many kinds of rice in the supermarket. Each kind of rice is bagged and must be purchased i
 *      n the whole bag. Given the weight, price and quantity of each type of rice, find the maximum weight of rice that you can purchase.
 *      Input:  n = 8, prices = [3,2], weights = [300,160], amounts = [1,6]
        Output:  640
        Explanation:  Buy the second rice(price = 2) use all 8 money.
        Input:  n = 8, prices  = [2,4], weight = [100,100], amounts = [4,2 ]
        Output:  400
        Explanation:  Buy the first rice(price = 2) use all 8 money.
        There are three restrictions
 */
public class BackPackVII {

    // dp[i][j] represent take first i types of items, the maximum value we can get by not exceeding j
    // dp[i][j] = max(dp[i - 1][j - k * price[i - 1]] + k * weight[i - 1]), k: 0 ~ amounts if j - k * price[i - 1]
    // base case: dp[0][j] = 0; dp[i][0] = 0;
    // Time: O(m * n * k) where m is total value, n is the number of options, and k is amount of each option
    // Space: O(n * m);
    public int boundedBackPack(int[] prices, int[] weight, int[] amounts, int m){
        if(prices == null || weight == null || amounts == null || prices.length * weight.length * amounts.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[len + 1][m + 1];
        for(int i = 1; i <= len; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = dp[i-1][j];
                for(int k = 1; k <= amounts[i-1]; k++){
                    if(j >= k * prices[i-1]){
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j - k*prices[i-1]] + k*weight[i-1]);
                    }
                }
            }
        }
        return dp[len][m];
    }
    // for for all possible situations, and rolling array
    public int backPackVII(int n, int[] prices, int[] weight, int[] amounts) {
        // write your code here
        int m = prices.length;
        int[] f = new int[n + 1];
        for(int i = 0;i < m;i++){
            for(int j = 1;j <= amounts[i];j++){
                for(int k = n;k >= prices[i];k--){
                    f[k] = Math.max(f[k], f[k - prices[i]] + weight[i]);
                }
            }
        }
        return f[n];
    }
}
