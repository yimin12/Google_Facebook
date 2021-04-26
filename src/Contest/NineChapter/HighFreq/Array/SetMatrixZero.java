package Contest.NineChapter.HighFreq.Array;

/**
 * Description
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 *
 * Example
 * Example 1:
 *
 * Input:[[1,2],[0,3]]
 * Output:[[0,2],[0,0]]
 * Example 2:
 *
 * Input:[[1,2,3],[4,0,6],[7,8,9]]
 * Output:[[1,0,3],[0,0,0],[7,0,9]]
 */

public class SetMatrixZero {

    /**
     * Do it in-place
     * @param matrix
     */
    public void setZeros(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        int m = matrix.length, n = matrix[0].length, i, j;
        boolean empty_row = false, empty_col = false;
        for(j = 0; j < n; j ++){
            if(matrix[0][j] == 0){
                empty_row = true;
                break;
            }
        }
        for(i = 0; i < m; i ++){
            if(matrix[i][0] == 0){
                empty_col = true;
                break;
            }
        }
        // tag
        for(i = 1; i < m; i ++){
            for(j = 1; j < n; j ++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // remark
        for(i = 1; i < m; i ++){
            for(j = 1; j < n; j ++){
                if(matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        if(empty_col){
            for(i = 0; i < m; i ++){
                matrix[i][0] = 0;
            }
        }
        if(empty_row){
            for(i = 0; i < n; i ++){
                matrix[0][i] = 0;
            }
        }
    }
}
