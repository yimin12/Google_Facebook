package Contest.NineChapter.HighFreq.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Given two Sparse Matrix A and B, return the result of AB.
 *
 * You may assume that A's column number is equal to B's row number.
 *
 * Example
 * Example1
 *
 * Input:
 * [[1,0,0],[-1,0,3]]
 * [[7,0,0],[0,0,0],[0,0,1]]
 * Output:
 * [[7,0,0],[-7,0,3]]
 * Explanation:
 * A = [
 *   [ 1, 0, 0],
 *   [-1, 0, 3]
 * ]
 *
 * B = [
 *   [ 7, 0, 0 ],
 *   [ 0, 0, 0 ],
 *   [ 0, 0, 1 ]
 * ]
 *
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 */
public class SparseMatrixMultiplication {


    // common ways to do, Time (n^2 * (1 + n)) = O(n^2 + n^3) Space O(1)
    public int[][] multiply(int[][] A, int[][] B){
        int n = A.length, t = A[0].length, m = B[0].length;
        int[][] res = new int[n][m];
        for(int i = 0; i < n ; i ++){
            for(int j = 0; j < m; j ++){
                int sum = 0;
                for(int k = 0; k < t; k ++){
                    sum += A[i][k] * B[k][j];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }

    // 将全部元素都是0的矩阵变成0举证，省去对应位置相乘
    public int[][] multiplyOpt(int[][] A, int[][] B) {
        // write your code here
        // A(n, t) * B(t, m) = C(n, m)
        int n = A.length;
        int t = A[0].length;
        int m = B[0].length;
        int[][] C = new int[n][m];

        List<Point> A_Points = getNonZeroPoints(A);
        List<Point> B_Points = getNonZeroPoints(B);

        for (Point pA : A_Points) {
            for (Point pB : B_Points) {
                if (pA.j == pB.i) {
                    C[pA.i][pB.j] += A[pA.i][pA.j] * B[pB.i][pB.j];
                }
            }
        }

        return C;
    }


    private List<Point> getNonZeroPoints(int[][] matrix) {
        List<Point> nonZeroPoints = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    nonZeroPoints.add(new Point(i, j));
                }
            }
        }
        return nonZeroPoints;
    }

    class Point {
        int i, j;
        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

}
