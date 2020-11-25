package Algorithm.DynamicProgramming.RangeDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/9 18:11
 *   @Description :
 *      Given a string s, cut s into some substrings such that every substring is a palindrome.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Input: "a"
 * Output: 0
 * Explanation: "a" is already a palindrome, no need to split.
 */
public class PalindromePartitioning {

    // Method 1 : O(n^2)
    public int minCut(String s){
        if(s == null || s.length() <= 1){
            return 0;
        }
        int n = s.length();
        char[] array = s.toCharArray();
        boolean[][] isPalin = calPalin(array);
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j = 0; j < i; j++){
                if(isPalin[j][i-1]){
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        // dp[n] is the total number of palindromes and dp[n]-1 is the number of cut required, e.g. 4 pieces need 3 cuts
        return dp[n] - 1;
    }
    private boolean[][] calPalin(char[] c){
        // already check the input
        int n = c.length;
        boolean[][] isPalin = new boolean[n][n];
        // single word should be palindrome (# of length is odd)
        for(int i = 0; i < n; i++){
            isPalin[i][i] = true;
        }
        // (# of length is even)
        for(int i = 0; i < n - 1; i++){
            isPalin[i][i+1] = (c[i] == c[i+1]);
        }
        // range dp
        for(int len = 2; len < n; len++){
            for(int start = 0; start + len < n; start++){
                isPalin[start][start+len] = isPalin[start + 1][start + len - 1] && c[start] == c[start + len];
            }
        }
        return isPalin;
    }

    // Method 2: Contributed by hou sir
    public int minCutI(String s){
        if(s == null || s.length() <= 1){
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        int[] print = new int[n + 1];
        int i, j, p;
        char[] c = s.toCharArray();
        boolean[][] isPalin = calcPalin(c, n);
        dp[0] = 0;
        for(i = 1; i <= n; i++){
            dp[i] = Integer.MAX_VALUE;
            for(j = 0; j < i; j++){
                if(isPalin[j][i-1] && dp[j] != Integer.MAX_VALUE && dp[j] + 1 < dp[i]){
                    dp[i] = dp[j]+1;
                    print[i] = j; // current palindrome starts from j to i
                }

            }
        }
        // find the solution path (print it from the end to the start)
        while(i != 0){
            for(j = print[i]; j < i; j++){
                System.out.print(c[j]);
            }
            System.out.println("");
            i = print[i];
        }
        return dp[n] - 1;
    }
    // Extended the center to check the palindrome(O(n^2) time and space)
    private boolean[][] calcPalin(char[] c, int n){
        boolean[][] isPalin = new boolean[n][n];
        int i, j, p;
        for(i = 0; i < n; i++){
            for(j = 0; j < n; j++){
                isPalin[i][j] = false;
            }
        }
        // check for odd length
        for(p = 0; p < n; p++){
            i = j = p; // reset it to the center
            while(i >= 0 && j < n && c[i] == c[j]){
                isPalin[i][j] = true;
                i--;
                j--;
            }
            // else is false
        }
        // check for even length
        for(p = 0; p < n - 1; p++){
            i = p;
            j = p + 1;
            while(i >= 0 && j < n && c[i] == c[j]){
                isPalin[i][j] = true;
                i--;
                j++;
            }
        }
        return isPalin;
    }

    // Follow Up 1:
    /**
     * Given a string s. Partition s such that every substring in the partition is a palindrome
     * Return all possible palindrome partitioning of s.
     * Input: "aab"
     * Output: [["aa", "b"], ["a", "a", "b"]]
     * Explanation: There are 2 ways to split "aab".
     *     1. Split "aab" into "aa" and "b", both palindrome.
     *     2. Split "aab" into "a", "a", and "b", all palindrome.
     */
    // basic dfs + backtracking + dp(isPalin), we do not need to check whether it is palindrome repeatedly
    public List<List<String>> partition(String s){
        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length() == 0){
            return res;
        }
        StringBuilder sb = new StringBuilder();
        char[] c = s.toCharArray();
        boolean[][] isPalin = calPalin(c);
        dfs(c, 0, res, isPalin, new ArrayList<>());
        return res;
    }
    private void dfs(char[] c, int startIndex, List<List<String>> res, boolean[][] isPalin, List<Integer> cur){
        // base case
        if(startIndex == c.length){
            addResult(c, cur, res);
            return;
        }
        // recursion rule (add each character uniquely)
        for(int i = startIndex; i < c.length; i++){
            // case 1: not palindrome, skip
            if(!isPalin[startIndex][i]){
                continue;
            }
            // case 2:
            cur.add(i);
            dfs(c, i + 1, res, isPalin, cur);
            cur.remove(cur.size() - 1);
        }
    }
    private void addResult(char[] c, List<Integer> cur, List<List<String>> res){
        List<String> r = new ArrayList<>();
        int startIndex = 0;
        for(int i = 0; i < cur.size(); i++){
            r.add(new String(c, startIndex, cur.get(i) + 1));
            startIndex = cur.get(i) + 1;
        }
        res.add(r);
    }

    public static void main(String[] args) {
        PalindromePartitioning solution = new PalindromePartitioning();
        List<List<String>> res = solution.partition("a");
        for(List<String> cur:res){
            System.out.println(cur.toString());
        }

    }
}
