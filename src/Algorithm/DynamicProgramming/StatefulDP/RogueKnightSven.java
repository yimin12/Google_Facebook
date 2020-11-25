package Algorithm.DynamicProgramming.StatefulDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/10 19:46
 *   @Description :
 * 	    In material plane "reality", there are n + 1 planets, namely planet 0, planet 1, ..., planet n.
 * 	    Each planet has a portal through which we can reach the target planet directly without passing through other planets.
 * 	    But portal has two shortcomings.
 * 	    First, planet i can only reach the planet whose number is greater than i, and the difference between i can not exceed the limit.
 * 	    Second, it takes cost[j] gold coins to reach the planet j through the portal.
 * 	    Now, Rogue Knight Sven arrives at the planet 0 with m gold coins, how many ways does he reach the planet n through the portal?
 *      Example 1:
 * 	    Input:
	    n = 1
	    m = 1
	    limit = 1
	    cost = [0, 1]
	    Output:
	    1
	    Explanation:
	       Plan 1: planet 0 → planet 1
 */
public class RogueKnightSven {

    public long getNumberOfWays(int n, int m, int limit, int[] cost){
        long[][] dp = new long[n + 1][m + 1];
        long[][] sum = new long[n + 1][m + 1];
        int i, j, k;
        for(i = 0; i < m; i++){
            dp[0][i] = 0;
            sum[0][i] = 0;
        }
        dp[0][m] = sum[0][m] = 1;
        for(i = 0; i <= n; i++){
            for(j = 0; j <= m; j++){
                dp[i][j] = 0;
                sum[i][j] = sum[i-1][j];
                if(j + cost[i] > m){
                    continue;
                }
                k = i - limit;
                if(k < 0){
                    k = 0;
                }
                if(k == 0){
                    dp[i][j] += sum[i-1][j + cost[i]];
                } else {
                    dp[i][j] += sum[i - 1][j + cost[i]] - sum[k - 1][j + cost[i]];
                }
                sum[i][j] += dp[i][j];
            }
        }
        int res = 0;
        for(i = 0; i <= m; i++){
            res += dp[n][i];
        }
        return res;
    }

    // memorySearch
    int n, m, limit;
    int[] cost;
    long[][] memory;
    public long getNumberOfWaysI(int nn, int mm, int limit, int[] cost){
        n = nn;
        m = mm;
        this.limit = limit;
        this.cost = cost;
        memory = new long[n + 1][m + 1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= m; j++){
                memory[i][j] = -1;
            }
        }
        long res = 0;
        for(int i = 0; i <= m; i++){
            cal(n, i);
            res += memory[n][i];
        }
        return res;
    }
    private void cal(int i, int j){
        if(memory[i][j] != -1){
            return;
        }
        if(i == 0){
            if(j == m){
                memory[i][j] = 1;
            } else {
                memory[i][j] = 0;
            }
        }
        memory[i][j] = 0;
        if(j + cost[i] > i){
            return;
        }
        for(int k = i - limit; k < i; k++){
            if(k < 0){
                continue;
            }
            cal(k, j + cost[i]);
            memory[i][j] += memory[k][j + cost[i]];
        }
    }

}
