package Algorithm.Graph.PlayGraph.Traverse;

import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 17:27
 *   @Description :
 *
 */
public class ShortestPathBinaryMatrix {

    private int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private int cols, rows;

    public int shortestPathBinaryMatrix(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("input is invalid");
        }

        this.rows = grid.length;
        this.cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][] dis = new int[rows][cols];

        // BFS
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0); // start from [0, 0]
        visited[0][0] = true;
        dis[0][0] = 1;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            int x = cur / cols, y = cur % cols;
            for(int d = 0; d < 8; d++){
                int neiX = x + dirs[d][0];
                int neiY = y + dirs[d][1];
                if(inArea(neiX, neiY) && grid[neiX][neiY] == 0 && !visited[neiX][neiY]){
                    queue.add(neiX * cols + neiY);
                    visited[neiX][neiY] = true;
                    dis[neiX][neiY] = dis[x][y] + 1;
                    if(neiX == rows - 1 && neiY == cols - 1){
                        return dis[neiX][neiY];
                    }
                }
            }
        }
        return -1;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
