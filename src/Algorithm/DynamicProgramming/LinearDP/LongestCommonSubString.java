package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 23:04
 *   @Description :
 *      Find the longest common substring of two given strings
 */
public class LongestCommonSubString {

    public String longest(String s, String t){
        if(s == null || t == null || s.length() < t.length()){
            return "";
        }
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        int start = 0;
        int longest = 0;
        int[][] dp = new int[sa.length][ta.length];
        for(int i = 0; i < sa.length; i++){
            for(int j = 0; j < ta.length; j++){
                if(sa[i] == ta[j]){
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                if(dp[i][j] > longest){
                    longest = dp[i][j];
                    start = i - longest + 1;
                }
            }
        }
        return s.substring(start, start + longest);
    }
}
