package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 22:42
 *   @Description :
 * 	    Given a matrix that contains only 1s and 0s, find the largest X shape
 * 	    which contains only 1s, with the same arm lengths and the four arms joining at
 * 	    the central point.
 * 	    Return the arm length of the largest X shape
 *      Assumption:
 * 	    The given matrix is not null, has size of N * M, N >= 0 and M >= 0;
 *      Examples;
 *	        { {0, 0, 0, 0}, the largest X of 1s has arm length 2
	        {1, 1, 1, 1},
	        {0, 1, 1, 1},
	        {1, 0, 1, 1} }
 */
public class LargestXOfOnes {

    public int largestCross(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int M = matrix.length, N = matrix[0].length;
        int[][] leftUp = leftUpI(matrix, M, N);
        int[][] rightDown =rightDownI(matrix, M, N);
        return merge(leftUp, rightDown, M, N);
    }

    // Follow Up 1:
    public int largestX(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int M = matrix.length, N = matrix[0].length;
        int[][] leftUp = leftUp(matrix, M, N);
        int[][] rightDown = rightDown(matrix, M, N);
        return merge(leftUp, rightDown, M, N);
    }

    public int[][] leftUpI(int[][] matrix, int M, int N){
        int[][] left = new int[M][N];
        int[][] up = new int[M][N];
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j] == 1){
                    left[i][j] = getNumber(left, i, j - 1, M, N) + 1;
                    up[i][j] = getNumber(up, i - 1, j, M, N) + 1;
                }
            }
        }
        merge(left, up, M, N);
        return left;
    }

    public int[][] rightDownI(int[][] matrix, int M, int N){
        int[][] right = new int[M][N];
        int[][] down = new int[M][N];
        for(int i = M - 1; i >= 0; i--){
            for(int j = N - 1; j >= 0; j--){
                right[i][j] = getNumber(right, i, j + 1, M, N) + 1;
                down[i][j] = getNumber(down, i + 1, j, M, N) + 1;
            }
        }
        merge(right, down, M, N);
        return right;
    }
    private int merge(int[][] leftUp, int[][] rightDown, int M, int N){
        int res = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
                res = Math.min(res, leftUp[i][j]);
            }
        }
        return res;
    }
    private int[][] leftUp(int[][] matrix, int M, int N){
        int[][] left = new int[M][N];
        int[][] up = new int[M][N];
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j] == 1){
                    left[i][j] = getNumber(left, i - 1, j - 1, M, N) + 1;
                    up[i][j] = getNumber(up, i - 1, j - 1, M, N) + 1;
                }
            }
        }
        merge(left, up, M, N);
        return left;
    }
    private int[][] rightDown(int[][] matrix, int M, int N){
        int[][] right = new int[M][N];
        int[][] down = new int[M][N];
        for(int i = M - 1; i >= 0; i--){
            for(int j = N - 1; j >= 0; j--){
                if(matrix[i][j] == 1){
                    right[i][j] = getNumber(right, i + 1, j, M, N);
                    down[i][j] = getNumber(down, i, j + 1, M, N);
                }
            }
        }
        merge(right, down, M, N);
        return right;
    }
    private int getNumber(int[][] number, int x, int y, int M, int N){
        if(x < 0 || x >= N || y < 0 || y >= M){
            return 0;
        }
        return number[x][y];
    }
}
