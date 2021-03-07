package Algorithm.Graph.PlayGraph.Traverse.Hamilton;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/10 17:51
 *   @Description :
 *      Leetcode 980
 */
public class UniquePath {

    private int[][] dirs =  {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int rows, cols;
    private int[][] grid;
    private int start, end;

    public int uniquePath(int[][] grid){
        this.grid = grid;
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }
        rows = grid.length;
        cols = grid[0].length;
        int steps = rows * cols;
        for(int i = 0 ; i < rows; i ++){
            for(int j = 0; j < cols; j ++){
                if(grid[i][j] == 1){
                    start = i * cols + j;
                    grid[i][j] = 0; // once you have visited, erase it by marking 0
                } else if(grid[i][j] == 2){
                    end = i * cols + j;
                    grid[i][j] = 0;
                } else if(grid[i][j] == -1){
                    steps--;
                }
            }
        }
        int visited = 0;
        return dfs(visited, start, steps);
    }

    private int dfs(int visited, int v, int steps){
        int x = v / cols, y = v % cols;
        visited += (1 << v);
        steps--;
        if(v == end && steps == 0){
            visited -= (1 << v);
            return 1; // base case used for backtracking
        }
        int res = 0;
        for(int d = 0; d < 4; d++){
            int neiX = x + dirs[d][0], neiY = y + dirs[d][1];
            int next = neiX * cols + neiY;
            if(inArea(neiX, neiY) && grid[neiX][neiY] == 0 && (visited & (1 << next)) == 0){
                res += dfs(visited, next, steps);
            }
        }
        visited -= (1 << v);
        return res;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

}
