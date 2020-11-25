package Algorithm.DynamicProgramming.RangeDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 23:44
 *   @Description :
 * 	    We have a list of piles of stones, each pile of stones has a certain weight, represented by an
 * 	    array of integers. In each move, we can merge two adjacent piles into one larger pile, the cost
 * 	    is the sum of the weights of the two piles. We merge the piles of stones until we have only one
 * 	    pile left. Determine the minimum total cost.
 *  Assumption:
 * 	    Stones is not null and is length of at least 1
 *  Examples:
 * 	    {4, 3, 3, 4}, the minimum cost is 28
 * 	    merge first 4 and first 3, cost 7
 * 	    merge second 3 and second 4, cost 7
 * 	    merge two 7s, cost 14
 * 	    total cost = 7 + 7 + 14 = 28
 */
public class MergeStone {

    public int minCost(int[] stones){
        //		Stones is not null, stones.length >= 1. This problem is actually the same one with Cutting
        //		Wood I. Only difference is for each partition(i,j), we need to compute the length
        if(stones == null || stones.length == 0){
            return 0;
        }
        int len = stones.length;
        int[][] cost = new int[len][len];
        int[][] subSum = new int[len][len];
        for(int i = 0; i < len; i++){
            for(int j = i; j >= 0; j--){
                if(i == j){
                    cost[j][i] = 0;
                    subSum[j][i] = stones[i];
                } else {
                    subSum[j][i] = subSum[j][i-1] + stones[i];
                    cost[j][i] = Integer.MAX_VALUE;
                    for(int k = j; k < i; k++){
                        cost[j][i] = Math.min(cost[j][i], cost[j][k] + cost[k][j]);
                    }
                }
            }
        }
        return cost[0][len - 1];
    }

    // MemorySearch
    public int minCostMS(int[] stones){
        if(stones == null || stones.length == 0){
            return 0;
        }
        int n = stones.length;
        int[][] dp = new int[n][n];
        int[] prefixSum = new int[n+1];
        for(int i = 1; i <= stones.length; i++){
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }
        return memorySearch(0, n - 1, stones, dp, prefixSum);
    }
    private int memorySearch(int left, int right, int[] stones, int[][] dp, int[] prefixSum){
        if(left >= right){
            return 0;
        }
        if(left + 1 == right){
            return stones[left] +stones[right];
        }
        int min = Integer.MAX_VALUE;
        for(int i = left; i < right; i++){
            int cost = memorySearch(left, i, stones, dp, prefixSum) + memorySearch(i + 1, right, stones, dp, prefixSum) + prefixSum[right + 1] - prefixSum[left];
            min = Math.min(min, cost);
        }
        dp[left][right] = min;
        return min;
    }

    // Follow Up, what if the given input is circular array
    public int minCostII(int[] stones){
        if(stones == null || stones.length <= 1){
            return -1;
        }
        int n = stones.length;
        int[][] dp = new int[2*n][2*n];
        int[] prefixSum = new int[2*n+1];
        for(int i= 1; i <= 2 * n; i++){
            prefixSum[i] = prefixSum[i-1] + stones[(i-1)%n];
        }
        for(int i = 0; i < 2*n; i++){
            dp[i][i] = 0; // the diagonal elements are all 0
        }
        for(int len = 2; len <= n; len++){
            for(int i = 0; i < 2 * n && i + len - 1 < 2 * n; i++){
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++){
                    if(dp[i][k] + dp[k+1][j] + prefixSum[j + 1] - prefixSum[i] < dp[i][j]){
                        dp[i][j] = dp[i][k] + dp[k + 1][j] + prefixSum[j + 1] - prefixSum[i];
                    }
                }
            }
        }
        int res =Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            if(dp[i][i + n -1] < res){
                res = dp[i][i + n - 1];
            }
        }
        return res;
    }
}
