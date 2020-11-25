package Algorithm.DynamicProgramming.RangeDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 23:30
 *  Description:
 * 	    Given a string s, find the longest palindromic substring in s.
 * 	    You may assume that the maximum length of s is 1000.
 *  Examples:
 * 	    Input : "babad"
 * 	    Output : "bab"
 * 	    Node : "aba" is also a valid answer
 *
 * 	    Input : "cbbd"
 * 	    Output : "bb"
 */
public class LongestPalindromeSubString {

    public String longestPalindrome(String s){
        if(s == null || s.length() == 0){
            return "";
        }
        int n = s.length();
        String res = null;
        int start = 0, maxLen = 0;
        boolean[][] dp = new boolean[n][n];
        for(int i = n - 1; i >= 0; i--){
            for(int j = i; j < n; j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = (j - i < 3) || dp[i + 1][j - 1];
                }
                if(dp[i][j] && (j-i + 1 > maxLen)){
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}
