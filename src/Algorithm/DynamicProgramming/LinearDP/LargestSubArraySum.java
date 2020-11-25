package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 21:58
 *  	Given a matrix that contains integers, find the submatrix with the largest sum.
 *  	Return the sum of the sub matrix
 *      Assumption:
 * 	    The given matrix is not null and has size of M * N, where M >= 1 and N >=1
 *      Examples:
 *          {{1, -2, -1, 4},		the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
  	        {1, -1,  1, 1},
            {0, -1, -1, 1},
  	        {0,  0,  1, 1} }
 */
public class LargestSubArraySum {

    // O(n);
    public int largest(int[] array){
        int res = array[0];
        int cur = array[0];
        for(int i = 0; i < array.length; i++){
            cur = Math.max(cur + array[i], array[i]);
            res = Math.max(res, cur);
        }
        return res;
    }
    // O(n*m)
    public int largestMartrixSum(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int M = matrix.length, N = matrix[0].length;
        int res = 0;
        for(int i = 0; i < M; i++){
            int[] cur = new int[N];
            for(int j = 0; j < M; j++){
                add(cur, matrix[i]);
                res = Math.max(res, largest(cur));
            }
        }
        return res;
    }
    private void add(int[] cur, int[] add){
        for(int i = 0; i < cur.length; i++){
            cur[i] += add[i];
        }
    }
}
