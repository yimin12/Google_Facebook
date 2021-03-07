package Algorithm.Graph.PlayGraph.Traverse.Hamilton;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 18:44
 *   @Description :
 *      Leetcode 980
 */
public class UniquePathMemorySearch {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int cols, rows;
    private int[][] grid;
    private int start, end;
    private int[][] memo;

    public int uniquePath(int[][] grid){
        this.grid = grid;
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }
        rows = grid.length;
        cols = grid[0].length;
        int left = rows * cols;
        memo = new int[1 << (rows * cols)][rows * cols];
        for(int i = 0; i < memo.length; i++){
            Arrays.fill(memo[i], -1);
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 1){
                    start = i * cols + j;
                    grid[i][j] = 0;
                } else if(grid[i][j] == 2){
                    end = i * cols + j;
                    grid[i][j] = 0;
                } else if(grid[i][j] == -1){
                    left --;
                }
            }
        }
        int visited = 0;
        return memorySearch(visited, start, left);
    }

    private int memorySearch(int visited, int v, int left){
        if(memo[visited][v] != -1) return memo[visited][v];
        visited += (1 << v);
        left --;
        if(v == end && left == 0){
            visited -= (1 << v);
            memo[visited][v] = 1;
            return 1;
        }
        int x = v / cols , y = v % cols;
        int res = 0;
        for(int d = 0; d < dirs.length; d ++){
            int neiX = x + dirs[d][0], neiY = y + dirs[d][1];
            int next = neiX * cols + neiY;
            if(inArea(neiX, neiY) && grid[neiX][neiY] == 0 && (visited & (1 << next)) == 0){
                res += memorySearch(visited, next, left);
            }
        }
        visited -= (1 << v);
        memo[visited][v] = res;
        return res;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
