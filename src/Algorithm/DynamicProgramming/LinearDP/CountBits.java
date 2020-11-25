package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/7 0:38
 *   @Description :
 *      Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation
 *      and return them as an array.
 *      Input: 5
        Output: [0,1,1,2,1,2]
        Explanation:
        The binary representation of 0~5 is:
        000
        001
        010
        011
        100
        101
        the count of "1" in each number is: 0,1,1,2,1,2
 */
public class CountBits {

    public int countOnes(int n){
        // Assumption: n >= 0
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 0; i <= n; i++){
            dp[i] = dp[i >> 1] + (i % 2);
        }
        return dp[n];
    }
}
