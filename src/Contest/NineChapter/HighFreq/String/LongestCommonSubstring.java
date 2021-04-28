package Contest.NineChapter.HighFreq.String;

public class LongestCommonSubstring {

    public int longestCommonSubstring(String A, String B){
        if(A == null || B == null || A.length() == 0 || B.length() == 0) return 0;
        int m = A.length(), n = B.length();
        int f[][] = new int[m + 1][n + 1];
        f[0][0] = 0;
        int max = 0;
        for(int i = 0; i <= m; i ++){
            for(int j = 0; j <= n; j ++){
                if(i == 0 || j == 0) f[i][j] = 0;
                else {
                    if (A.charAt(i - 1) == B.charAt(j - 1)) {
                        f[i][j] = f[i-1][j-1] + 1;
                        max = Math.max(max, f[i][j]);
                    } else {
                        f[i][j] = 0;
                    }
                }
            }
        }
        return max;
    }
}
