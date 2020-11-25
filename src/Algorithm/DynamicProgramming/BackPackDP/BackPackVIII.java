package Algorithm.DynamicProgramming.BackPackDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 23:04
 *   @Description :
        Given coins with different values and quantities, find out how many ways of total values in the range of [1, n] can be formed?

        Have you met this question in a real interview?
        Example
        Example 1:
            Input:
                n = 5
                value = [1,4]
                amount = [2,1]
            Output:  4

            Explanation:
            They can combine 4 numbers which are 1,2,4,5.

        Example 2:
            Input:
                n = 10
                value = [1,2,4]
                amount = [2,1,1]
            Output:  8

            Explanation:
	     They can combine 8 numbers which are 1 ~ 8.
 */
public class BackPackVIII {

    public int boundedBackPack(int n, int[] values, int[] amount){
        int len = values.length;
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        int res = 0;
        for(int i = 0; i < len; i++){
            int[] count = new int[n + 1];
            for(int j = values[i]; j <= n; j++){
                if(dp[j - values[i]] && !dp[j] && count[j - values[i]]< amount[i]){
                    count[i] += count[j - values[i]] + 1;
                    res++;
                    dp[j] = true;
                }
            }
        }
        return res;
    }
}
