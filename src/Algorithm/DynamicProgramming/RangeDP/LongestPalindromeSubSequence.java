package Algorithm.DynamicProgramming.RangeDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/2/23 13:03
 *   @Description :
 *
 */
public class LongestPalindromeSubSequence {

    public static void main(String[] args) {
        int res = memorySearchLongest("aaabcaa");
        System.out.println(res);
    }

    static int[][] f;
    static char[] s;
    static int n = 0;
    public static int memorySearchLongest(String ss){
        s = ss.toCharArray();
        n = s.length;
        if(n == 0) return 0;
        if(n == 1) return 1;
        f = new int[n][n];
        int i, j;
        for(i = 0; i < n; i ++){
            for(j = i; j < n; j ++){
                f[i][j] = -1;
            }
        }
        search(0, n - 1);
        return f[0][n-1];
    }

    private static void search(int i, int j){
        if(f[i][j] != - 1){
            return;
        }
        if(i == j){
            f[i][j] = 1;
            return;
        }
        // recursion
        search(i+1, j);
        search(i, j - 1);
        search(i + 1, j - 1);
        // fill the result to memory
        f[i][j] = Math.max(f[i+1][j], f[i][j-1]);
        if(s[i] == s[j]){
            f[i][j] = Math.max(f[i][j], f[i+1][j-1] + 2);
        }

    }

    public static int longestPalindromeSubseq(String ss) {
        // write your code here
        if(ss == null || ss.length() == 0){
            return 0;
        }
        char[] s = ss.toCharArray();
        int n = s.length;
        if(n == 0) return 0;
        if(n == 1) return 1;

        int[][] f = new int[n][n]; // i : left border. j : right border
        int[][] print = new int[n][n];

        int i, j, len;
        // len 1;
        for(i = 0; i < n; i ++){
            f[i][i] = 1;
        }
        // len 2:
        for(i = 0; i < n - 1; i ++){
            f[i][i+1] = (s[i] == s[i+1]) ? 2:1;
        }
        for(len = 3; len <= n; len ++){
            for(i = 0; i <= n - len; i ++){
                j = len + i - 1; // convert length to index
                f[i][j] = Math.max(f[i+1][j], f[i][j-1]);

                if(f[i][j] == f[i+1][j]){
                    print[i][j] = 0; // truncate leading
                } else {
                    print[i][j] = 1; // truncate trailing
                }

                if(s[i] == s[j]){
                    f[i][j] = Math.max(f[i][j], f[i+1][j-1] + 2);
                    if(f[i][j] == f[i+1][j-1] + 2){
                        print[i][j] = 2; // keep both
                    }
                }
            }
        }

        // print the result
        char[] res = new char[f[0][n-1]];
        int p = 0, q = res.length - 1;
        i = 0;
        j = n - 1;
        while(i <= j){
            if(i == j){
                res[p] = s[i];
                break;
            }
            if(i + 1 == j){
                res[p] = s[i];
                res[q] = s[j];
                break;
            }
            if(print[i][j] == 0){
                ++i;
            } else {
                if(print[i][j] == 1){
                    --j;
                } else {
                    res[p++] = s[i];
                    res[q--] = s[j];
                    ++i;
                    --j;
                }
            }
        }

        System.out.println("Result is : ");
        for(i = 0; i < f[0][n-1]; i ++){
            System.out.print(res[i]);
        }
        System.out.println("");
        return f[0][n-1];
    }
}
