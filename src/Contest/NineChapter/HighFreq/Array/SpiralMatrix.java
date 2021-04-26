package Contest.NineChapter.HighFreq.Array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    /**
     * Description
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     *
     * Example
     * Example 1:
     *
     * Input:	[[ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ]]
     * Output: [1,2,3,6,9,8,7,4,5]
     * Example 2
     *
     * Input:	[[ 6,4,1 ], [ 7,8,9 ]]
     * Output: [6,4,1,9,8,7]
     */
    public List<Integer> sprialOrder(int[][] matrix){
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int i = 0, j = 0, m = matrix.length, n = matrix[0].length;
        int left = 0, right = n - 1, up = 0, down = m - 1;
        while(left <= right && up <= down){
            for(j = left; j <= right && up <= down; j ++){
                res.add(matrix[up][j]);
            }
            up ++;
            for(i = up; i <= down && left <= right; i ++){
                res.add(matrix[i][right]);
            }
            right --;
            for(j = right; j >= left && up <= down; j --){
                res.add(matrix[down][j]);
            }
            down --;
            for(i = down; i >= up && left <= right; i --){
                res.add(matrix[i][left]);
            }
            left ++;
        }
        return res;
    }

    /**
     * Description
     * Given an integer n, generate a square matrix filled with elements from 1 to n^2n
     * ​2
     * ​​  in spiral order.
     *
     * (The spiral rotates clockwise from the outside to the inside, referring to examples)
     *
     * Example
     * Example 1:
     *
     * input: 2
     * output:
     * [
     *   [1, 2],
     *   [4, 3]
     * ]
     * Example 2:
     *
     * input: 3
     * output:
     * [
     *   [ 1, 2, 3 ],
     *   [ 8, 9, 4 ],
     *   [ 7, 6, 5 ]
     * ]
     */
    // Good trick for simulating the walking
    public int[][] generateMatrix(int n){
        if(n <= 0) return new int[0][0];
        int[][] matrix = new int[n][n];

        int[][] dirs = { { 0,  1}, { 1,  0}, { 0, -1}, {-1,  0} };
        int[] steps = {n, n-1, n-1, n-2};

        int idx = 0;
        int num = 1;
        int x = 0, y = -1;
        while (true) {
            if (steps[idx] <= 0) break;
            for (int i = 0; i < steps[idx]; i++) {
                x += dirs[idx][0];
                y += dirs[idx][1];
                matrix[x][y] = num ++;
            }
            steps[idx] -= 2;
            idx ++;
            idx = idx & 3;
        }

        return matrix;
    }

    /**
     * https://www.lintcode.com/problem/1597/, Follow up 3
     */
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0){
        if(!valid(R, C, r0, c0)) return new int[0][0];
        int size = R * C, x = r0, y = c0, z = 1, step = 1, index = 0;
        int[][] res = new int[size][2];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        res[0][0] = x;
        res[0][1] = y;
        while(z < size){
            for(int i = 0; i < step; i ++){
                x += dx[index];
                y += dy[index];
                if(x >= 0 && x < R && y >= 0 && y < C){
                    res[z][0] = x;
                    res[z][1] = y;
                    z++;
                }
            }
            index = (index + 1) % 4;
            if(index % 2 == 0){
                step ++;
            }
        }
        return res;
    }

    private boolean valid(int R, int C, int r0, int c0){
        if(r0 >= R || r0 < 0 || c0 < 0 || c0 >= C){
            return false;
        }
        return true;
    }



}
