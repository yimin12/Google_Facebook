package Algorithm.DynamicProgramming.StatefulDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 0:10
 *   @Description :
 *  	Given an input string (s) and a pattern (p), implement regular expression matching with support for'.'and'*'
 * 	    '.' Matches any single character.
 * 	    '*' Matches zero or more of the preceding element.
 *      Input: s = "mississippi"	p = "mis*is*p*." return false
 *      Input: s = "aab"  p = "c*a*b", return true
 */
public class RegularExpressionMatching {

    // Assumptions: '*' will never be the first character
    // dp[i][j] represent the first i characters in s matches first j characters in p
    // base case dp[0][0] = true; dp[0][i] = dp[0][-1], but there is a special case (a*) in p
    // 		so 	dp[0][i] = dp[0][i-2] if pattern[i-1] == '*'
    // Induction rule:
    // 	if p.charAt(j) == s.charAt(i), dp[i][j] = dp[i-1][j-1];
    // 	if p.charAt(j) == '.', dp[i][j] = dp[i-1][j-1];
    // 	if p.charAt(j) == '*', two case possible
    //		case 1: if p.charAt(j-1) != s.charAt(i), dp[i][j] = dp[i][j-2] ("*" counts as empty)
    //		case 2: if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) = '.';
    //			case 2.1 dp[i][j] = dp[i-1][j], count as multiple a
    //			case 2.2 dp[i][j] = dp[i-1][j-1], count as single a
    //			case 2.3 dp[i][j] = dp[i-1][j-2] , count as empty
    public boolean isMatch(String s, String t){
        if(s == null || t == null) return false;
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        int m = source.length, n = target.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // i: the first ith index of source, j: the first jth index of target
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                // case 1: '.' represent any character
                if(target[j-1] == '.' || target[j - 1] == source[i - 1]){
                    dp[i][j] = dp[i-1][j-1];
                } else if(target[j - 1] == '*'){
                    dp[i][j] = dp[i-1][j - 2]; // t.charAt(j-1) != s.charAt(i), dp[i][j] = dp[i][j-2] ("*" counts as empty)
                    if(target[j - 2] == '.' || target[j - 2] == source[i - 1]){
                        dp[i][j] = dp[i][j] || dp[i-1][j] || dp[i-1][j-1];
                    }
                } else{
                    return dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }

}
