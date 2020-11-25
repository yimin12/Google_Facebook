package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/7 0:12
 *   @Description :
 *      Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
 *      The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
 *      Input:
        grid =[
             "0E00",
             "E0WE",
             "0E00"
        ]
        Output: 3
        Explanation:
        Placing a bomb at (1,1) kills 3 enemies
 */
public class BombEnemy {

    public int bombEmeny(char[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int n = grid.length, m = grid[0].length;
        int[][] up = new int[n][m];
        int[][] down = new int[n][m];
        int[][] left = new int[n][m];
        int[][] right = new int[n][m];
        int i, j;
        // left-up
        for(i = 0; i < n; i++){
            for(j = 0; j < m; j++){
                if(grid[i][j] == 'E'){
                    up[i][j] = i == 0 ? 1 : up[i-1][j] + 1;
                    left[i][j] = j == 0 ? 1 : left[i][j-1] + 1;
                } else if(grid[i][j] == '0'){
                    up[i][j] = i == 0 ? 0 : up[i-1][j];
                    left[i][j] = j == 0 ? 0 : left[i][j-1];
                } else {
                    up[i][j] = 0;
                    left[i][j] = 0;
                }
            }
        }
        // right-down
        for(i = n-1; i >= 0; i--){
            for(j = m-1; j >= 0; j--){
                if(grid[i][j] == 'E'){
                    down[i][j] = i == n-1 ? 1 : down[i+1][j] + 1;
                    right[i][j] = j == m-1 ? 1 : right[i][j+1] + 1;
                } else if(grid[i][j] == '0'){
                    down[i][j] = i == n-1 ? 0 : down[i+1][j];
                    right[i][j] = j == m-1 ? 0 : right[i][j+1];
                } else {
                    down[i][j] = 0;
                    right[i][j] = 0;
                }
            }
        }
        // find the result
        int res = Integer.MIN_VALUE;
        for(i = 0; i < n; i++){
            for(j = 0; j < m; j++){
                if(grid[i][j] == '0'){
                    res = Math.max(res, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
                }
            }
        }
        return res;
    }
}
