package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/4 0:36
 *   @Description :
 * 	    Find the length of longest common subsequence of two given strings.
 *  Assumptions:
 *  	The two given strings are not null
 *  Examples:
 *  	S = ��abcde��, T = ��cbabdfe��, the longest common subsequence of s
 *  	and t is {��a��, ��b��, ��d��, ��e��}, the length is 4.
 */
public class LongestCommonSubsequence {

    public int longest(String s, String t){
        if(s == null || t == null || s.length() == 0 || t.length() == 0){
            return 0;
        }
        int[][] dp = new int[s.length()][t.length()];
        for(int i  = 1; i < s.length(); i++){
            for(int j = 1; j < t.length(); j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}
