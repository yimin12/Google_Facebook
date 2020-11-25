package Algorithm.Graph.Tree.Basic;

import java.util.Arrays;
import java.util.HashMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/2 19:21
 *   @Description :
 *      Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 *      Below is one possible representation of s1 = "great":
 *   Below is one possible representation of s1 = "great":

            great
           /    \
          gr    eat
         / \    /  \
        g   r  e   at
                   / \
                  a   t
        To scramble the string, we may choose any non-leaf node and swap its two children.

        For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

            rgeat
           /    \
          rg    eat
         / \    /  \
        r   g  e   at
                   / \
                  a   t
        We say that "rgeat" is a scrambled string of "great".

        Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

            rgtae
           /    \
          rg    tae
         / \    /  \
        r   g  ta  e
               / \
              t   a
        We say that "rgtae" is a scrambled string of "great".

        Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
public class ScrambleTreeString {

    // Method 3: Dynamic Programming
    public boolean isScramble(String s1, String s2){
        // Save the sanity check
        int m = s1.length(), n = s2.length();
        if(m != n){
            return false;
        }
        // i : start from ith characters, j : start from j characters, k: length of current substring
        boolean[][][] dp = new boolean[n][n][n + 1];
        int i, j, k, len;
        for(i = 0; i < n; i++){
            for(j = 0; j < n; j++){
                dp[i][j][1] = (s1.charAt(i) == s2.charAt(j));
            }
        }
        for(len = 2; len <= n; len++){
            for(i = 0; i <= n - len; i++){
                for(j = 0; j <= n - len; j++){
                    // initialize
                    dp[i][j] [len] = false;
                    for(k = 1; k < len; k++){
                        // no swap
                        if(dp[i][j][k] && dp[i + k][j + k][len - k]){
                            dp[i][j][len] = true;
                            break;
                        }
                        // swap
                        if(dp[i][j + len - k][k] && dp[i + k][j][len - k]){
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }


    // Method 2: Memory Search (use hashmap or array)
    HashMap<String, Boolean> record = new HashMap<String, Boolean>(); // hashmap version
    public boolean isScrambleMS(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        if(record.containsKey(s1 + "#" + s2)) return record.get(s1 + "#" + s2); // memorization
        int n = s1.length();
        // base case
        if(n == 1){
            return s1.charAt(0) == s2.charAt(0);
        }
        for(int k = 1; k < n; k++){
            if(isScrambleMS(s1.substring(0, k), s2.substring(0, k)) && isScrambleMS(s1.substring(k), s2.substring(k)) ||
                    isScrambleMS(s1.substring(0, k), s2.substring(n - k)) && isScrambleMS(s1.substring(n-k, n), s2.substring(0, n))){
                record.put(s1 + "#" + s2, true);
                return true;
            }
        }
        record.put(s1 + "#" + s2, false);
        return false;
    }

    // Method 1: pure dfs
    public boolean isScrambleDFS(String s1, String s2){
        // base case
        if(s1.length() != s2.length()){
            return false;
        }
        if(s1.length() == 0 || s1.equals(s2)){
            return true;
        }
        // base case check
        if(!isValid(s1,s2)){
            return false;
        }
        // divide
        for(int i = 1; i < s1.length(); i++){
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, s1.length());
            // not swap
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, s2.length());
            // swap
            String s23 = s2.substring(0, s2.length() - i);
            String s24 = s2.substring(s2.length() - i);

            if((isScrambleDFS(s11, s21) && isScrambleDFS(s12, s22)) || (isScrambleDFS(s11, s24) && isScrambleDFS(s12, s23))) return true;
        }
        return false;
    }
    private boolean isValid(String s1, String s2){
        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        if(!(new String(array1)).equals(new String(array2))){
           return false;
        }
        return true;
    }
}
