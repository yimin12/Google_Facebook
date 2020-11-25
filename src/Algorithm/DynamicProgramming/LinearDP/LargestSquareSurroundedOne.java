package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 21:42
 *   @Description :
 * 	    Determine the largest square of 1s in a binary matrix
 * 	    (a binary matrix only contains 0 and 1), return the length of the largest square.
 *   Assumption:
 * 	    The given matrix is not null and guaranteed to be of size N * N and N >= 0
 *   ExamplesL:
 * 	    {0, 0, 0, 0},  the largest square of 1s has length of 2
  	    {1, 1, 1, 1},
  	    {0, 1, 1, 1},
  	    {1, 0, 1, 1}}
 */
public class LargestSquareSurroundedOne {

    public int largest(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int M = matrix.length, N = matrix[0].length;
        int res = 0;
        int[][] left = new int[M+1][N+1];
        int[][] up = new int[M+1][N+1];
        for(int i = 0; i < M; i++){
            for(int j = 0; i < N; j++){
                if(matrix[i][j] == 1){
                    left[i+1][j+1] = left[i+1][j] + 1;
                    up[i+1][j+1] = up[i][j+1] + 1;
                    for(int maxLen = Math.min(left[i+1][j+1], up[i+1][j+1]); maxLen > 0; maxLen--){
                        if(left[i+2-maxLen][j+1] >= maxLen && up[i+1][j+2-maxLen] >= maxLen){
                            res = Math.max(res, maxLen);
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
}
