package Contest.NineChapter.HighFreq.Array;

public class SlidingWindowMatrixMaximum {

    public int maxSlidingMatrix(int[][] matrix, int k){
        if(matrix == null || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        if(m < k || n < k) return 0;
        int[][] sum = new int[m + 1][n + 1];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + matrix[i][j];
            }
        }
        int res = sum[k][k]; // up-left 0, 0 to down-right k, k
        for(int i = 1; i + k - 1 <= m; i ++){
            for(int j = 1; j + k - 1 <= n; j ++){
                res = Math.max(res, sum[i + k - 1][j + k - 1] - sum[i-1][j + k - 1] - sum[i + k - 1][j - 1] + sum[i - 1][j - 1]);
            }
        }
        return res;

    }
}
