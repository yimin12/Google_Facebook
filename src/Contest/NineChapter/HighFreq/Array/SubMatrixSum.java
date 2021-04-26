package Contest.NineChapter.HighFreq.Array;

import javax.xml.transform.Result;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 * Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and right-down number.
 *
 * If there are multiple answers, you can return any of them.
 *
 * Example
 * Example 1:
 *
 * Input:
 * [
 *   [1, 5, 7],
 *   [3, 7, -8],
 *   [4, -8 ,9]
 * ]
 * Output: [[1, 1], [2, 2]]
 * Example 2:
 *
 * Input:
 * [
 *   [0, 1],
 *   [1, 0]
 * ]
 * Output: [[0, 0], [0, 0]]
 */
public class SubMatrixSum {

    class ResultType {
        boolean flag = false;
        List<Integer> indices;
        public ResultType (boolean F, List<Integer> indeces){
           this.flag = F;
           this.indices = indeces;
        }
    }

    public int[][] submatrixSum(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[][]{{-1, -1}, {-1, -1}};
        int m = matrix.length, n = matrix[0].length, i, j;
        int[][] cols_sum = new int[m + 1][n];
        for(i = 1; i <= m; i ++){
            for(j = 0; j < n; j ++){
                cols_sum[i][j] = cols_sum[i - 1][j] + matrix[i-1][j];
            }
        }
        int[] prefix = new int[n];
        for(int start = 0; start < m; start ++){
            for(int end = start; end < m; end ++){
                for(int k = 0; k < n; k ++){
                    prefix[k] = cols_sum[end+1][k] - cols_sum[start][k];
                }
                ResultType res = subArraySum(prefix);
                if(res.flag){
                    int[][] ans = {{start, res.indices.get(0)}, {end, res.indices.get(1)}};
                    return ans;
                }
            }
        }
        int[][] ans = new int[2][2];
        return ans;
    }

    public ResultType subArraySum(int[] nums){
        List<Integer> res = new ArrayList<>();
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
            if(map.containsKey(sum)){
                res.add(map.get(sum));
                res.add(i);
                return new ResultType(true, res);
            }
            map.put(sum, i + 1);
        }
        return new ResultType(false, res);
    }
}
