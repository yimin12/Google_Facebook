package Algorithm.DynamicProgramming.StatefulDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/12 22:51
 *   @Description :
 *		A message containing letters from A-Z is being encoded to numbers using the following mapping
 *		'A' -> 1, 'B' ->2 ..... 'Z' -> 26
 *		Given an encoded message containing digits,determine the total number of ways to decode it.
 *		Example:
 *			encode_msg = "12", it can represented as "AB" or "L", thus, number of ways to decode is 2
 *
 */
public class DecodeWays {

    public int numDecoding(String s){
        if(s == null ||s.length() == 0){
            return 0;
        }
        char[] array =  s.toCharArray();
        int[] dp = new int[array.length];
        dp[0] = 1;
        for(int i = 1; i <= array.length; i++){
            dp[i] = dp[i-1]* cnt(array[i-1]);
            if(i > 1){
                dp[i] += dp[i-2] * cnt2(array[i-2], array[i-1]);
            }
        }
        return dp[dp.length -1];
    }
    private int cnt(char c){
        return c - '0' == 0 ? 0 : 1;
    }
    private int cnt2(char t, char c){
        if(t == '1' || (t == '2' && c <= '6')) return 1;
        return 0;
    }

    /*
    If we use "*"
    Input: "*"
    Output: 9
    Explanation: You can change it to "A", "B", "C", "D", "E", "F", "G", "H", "I".
     */
    public long numDecodings(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] array = s.toCharArray();
        int n = s.length();
        long[] dp = new long[n + 1];
        for(int i = 1; i <= n; i++){
            dp[i] = dp[i-1]*cnt3(array[i-1]);
            if(i > 1){
                dp[i] += dp[i-2]*cnt4(array[i-2], array[i-1]);
            }
        }
        return dp[n];
    }
    private long cnt3(char c){
        if(c == '*') return 9;
        else if(c == '0') return 0;
        else return 1;
    }
    private long cnt4(char c, char t){
        if(c == '0') return 0;
        else if(c == '1'){
            if(c == '*') return 9;
            else {
                return 1;
            }
        } else if(c == '2'){
            if(c == '*') return 6;
            else if(t <= '6') return 1;
            else {
                return 0;
            }
        }
        if(c >= '3' && c <= '9'){
            return 0;
        }
        // if c == '*'
        if(t >= '0' && t <= '6') return 2;
        if(t >= '7' && t <= '9') return 1;
        // if c == * and t == *
        return 15;
    }
}
