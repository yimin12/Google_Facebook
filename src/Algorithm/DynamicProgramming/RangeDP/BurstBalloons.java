package Algorithm.DynamicProgramming.RangeDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/8 0:00
 *   @Description :
        Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
        Find the maximum coins you can collect by bursting the balloons wisely.
        Note:
        You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
        0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
        Example:
        Input: [3,1,5,8]
        Output: 167
        Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
                     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {

    // dynamic programming
    public int maxCoins(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int[] nums = new int[array.length + 2]; // add the start and end positions
        int n = 1;
        for(int x : array){
            if(x > 0){
                nums[n++] = x;
            }
        }
        nums[0] = nums[n++] = 1;
        int[][] dp = new int[n][n];
        for(int i = 2; i < n; i++){
            for(int left = 0; left < n - i; left++){
                int right = left + i;
                for(int j = left + 1; j < right; j++){
                    dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        return dp[0][n - 1];
    }

    // Memory Search
    public int maxCoinsI(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int[] nums = new int[array.length + 2]; // add the start and end positions
        int n = 1;
        for(int x : array){
            if(x > 0){
                nums[n++] = x;
            }
        }
        nums[0] = nums[n++] = 1;
        int[][] memo = new int[n][n];
        return burst(memo, nums, 0, n - 1);
    }
    public int burst(int[][] memo, int[] nums, int left, int right){
        if(left + 1 == right) return 0;
        if(memo[left][right] > 0) return memo[left][right];
        int res = 0;
        for(int i = left + 1; i < right; i++){
            res = Math.max(res, nums[left] * nums[i] * nums[right] + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        }
        memo[left][right] = res;
        return res;
    }

    // Divide and Conquer with memory search
    public int maxCoinsII(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int[] nums = new int[array.length + 2]; // add the start and end positions
        int n = 1;
        for(int x : array){
            if(x > 0){
                nums[n++] = x;
            }
        }
        nums[0] = nums[n++] = 1;
        int[][] memo = new int[n][n];
        return memorySearch(1, n, array, memo);
    }

    private int memorySearch(int left, int right, int[] nums, int[][] memo){
        if(memo[left][right] != 0) return memo[left][right];
        int max = 0;
        for(int i = left; i <= right; i++){
            int cur = nums[left - 1] * nums[i] * nums[right + 1];
            int leftPart = memorySearch(left, i - 1, nums, memo);
            int rightPart = memorySearch(i + 1, right, nums, memo);
            max = Math.max(max, cur + left + right);
        }
        memo[left][right] = max;
        return max;
    }
}
