package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/6 22:06
 *   @Description :
 *      There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different.
 *      You have to paint all the houses such that no two adjacent houses have the same color, and you need to cost the least. Return the minimum cost.
 *      The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2]
 *      is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 *      Input: [[14,2,11],[11,14,5],[14,3,10]]
 *      Output: 10
 *      Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. Minimum cost: 2 + 5 + 3 = 10.
 */
public class PaintHouse {


    public int paintHouse(int[][] cost){
        if(cost == null || cost.length == 0 || cost[0].length == 0){
            return 0;
        }
        int n = cost.length, m = cost[0].length;
        int[][] dp = new int[n + 1][m]; // sequence type dp
        // base case
        for(int i = 0; i < m; i++){
            dp[0][i] = 0;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < m; j++){
                int cur = Integer.MAX_VALUE;
                for(int k = 1; k < m; k++){
                    cur = Math.min(dp[i-1][(j+k)%m] + cost[i-1][(j+k)%m],cur);
                }
                dp[i][j] = cur;
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++){
            res = Math.min(dp[n][i],res);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // Only care the minimum and the second minimum
    public int paintHouseOpt(int[][] cost){
        if(cost == null || cost.length == 0 || cost[0].length == 0){
            return 0;
        }
        int n = cost.length, m = cost[0].length;
        int[][] dp = new int[n + 1][m]; // sequence type dp
        // base case
        int i, j, min1, min2, index1, index2;
        for(i = 0; i < m; i++){
            dp[0][i] = 0;
        }
        // induction rule
        for(i = 1; i <= n; i++){
            // step 1: find the smallest value and the second smallest value
            min1 = min2 = Integer.MAX_VALUE;
            index1 = index2 = 0;
            for(j = 0; j < m; j++){
                // record the index rather than value
                if(dp[i-1][j] < min1){
                    min2 = min1;
                    index2 = index1;
                    min1 = dp[i-1][j];
                    index1 = j;
                } else if(dp[i-1][j] < min2){
                    min2 = dp[i-1][j];
                }
            }
            for(j = 0; j < m; j++){
                dp[i][j] = cost[i-1][j];
                if(j != index1){
                    dp[i][j] += min1;
                } else {
                    dp[i][j] += min2;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(i = 0; i < m; i++){
            res = Math.min(dp[n][i], res);
        }
        return res;
    }

}
