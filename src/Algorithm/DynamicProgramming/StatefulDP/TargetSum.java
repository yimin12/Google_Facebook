package Algorithm.DynamicProgramming.StatefulDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/10 15:56
 *   @Description :
* 	    You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -.
*       For each integer, you should choose one from + and - as its new symbol.
*       Find out how many ways to assign symbols to make sum of integers equal to target S.
*       Example:
* 	    Input: nums is [1, 1, 1, 1, 1], S is 3. 	 Output: 5
* 	    Explanation:
* 	    -1+1+1+1+1 = 3
	    +1-1+1+1+1 = 3
	    +1+1-1+1+1 = 3
	    +1+1+1-1+1 = 3
	    +1+1+1+1-1 = 3
	    There are 5 ways to assign symbols to make the sum of nums be target 3.
 */
public class TargetSum {

    //	Let P be the positive subset and N be the negative subset
    //	For example:
    //	Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
    //	Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]
    //
    //	Then let's see how this can be converted to a subset sum problem:
    //
    //	                  sum(P) - sum(N) = target
    //	sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
    //	                       2 * sum(P) = target + sum(nums)
    //	So the original problem has been converted to a subset sum problem as follows:
    //	Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
    //
    //	Note that the above formula has proved that target + sum(nums) must be even
    //	We can use that fact to quickly identify inputs that do not have a solution (Thanks to @BrunoDeNadaiSarnaglia for the suggestion)
    public int findTargetSumWays(int[] nums, int target){
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum < target || (sum + target) % 2 == 1){
            return 0;
        }
        return subDP(nums, (sum + target)/2);
    }
    private int subDP(int[] nums, int target){
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++){
            for(int j = target; j >= nums[i]; j--){
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    // Follow Up: Coins change, Two possible situation
    /**
     * @return number of ways to make sum s using repeated coins
     */
    public int coinresp(int[] coins, int target){
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int coin : coins){
            for(int j =  coin; j <= target; j++){
                dp[j] += dp[j - coin];
            }
        }
        return dp[target];
    }

    /**
     * @return number of ways to make sum s using non-repeated coins
     */
    public int coinnonrep(int[] coins, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int coin : coins){
            for(int j = target; j >= coin; j--){
                dp[j] = dp[j - coin];
            }
        }
        return dp[target];
    }
}
