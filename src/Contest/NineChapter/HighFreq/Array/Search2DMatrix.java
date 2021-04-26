package Contest.NineChapter.HighFreq.Array;

/**
 * Description
 * Write an efficient algorithm that searches for a value in an m x n matrix, return The number of occurrence of it.
 *
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * Integers in each column are sorted from up to bottom.
 * No duplicate integers in each row or column.
 * Example
 * Example 1:
 *
 * Input:
 *
 * matrix = [[3,4]]
 * target = 3
 * Output:
 *
 * 1
 * Explanation:
 *
 * There is only one 3 in the matrix.
 *
 * Example 2:
 *
 * Input:
 *
 * matrix = [
 *       [1, 3, 5, 7],
 *       [2, 4, 7, 8],
 *       [3, 5, 9, 10]
 *     ]
 * target = 3
 * Output:
 *
 * 2
 */
public class Search2DMatrix {

    public int searchMatrix(int[][] matrix, int target){
        if(matrix == null || matrix.length == 0 || matrix[0] == null ||matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int x = m - 1, y = 0, count = 0;
        while(x >= 0 && y < n){
            if(matrix[x][y] < target){
                y ++;
            } else if(matrix[x][y] > target){
                x --;
            } else {
                count ++;
                x --;
                y ++;
            }
        }
        return count;
    }

}
