package Algorithm.Graph.PlayGraph.Traverse;

import java.util.HashSet;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 16:36
 *   @Description :
 *      LeetCode 695
 */
public class MaxAreaOfIsland {

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int rows, cols;
    private int[][] grid;

    private HashSet<Integer>[] graph;
    private boolean[] visited;

    public MaxAreaOfIsland(int[][] grid){
        this.grid = grid;
    }

    public int maxArea(){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        graph = constructGraph();
        int res = 0;
        visited = new boolean[graph.length];
        for(int v = 0; v < graph.length; v++){
            int x = v / cols, y = v % cols;
            if(grid[x][y] == 1 && !visited[v]){
                res = Math.max(res, dfs(v));
            }
        }
        return res;
    }

    private int dfs(int v){
        visited[v] = true;
        int res = 1;
        for(int nei:graph[v]){
            if(!visited[nei]){
                res += dfs(nei);
            }
        }
        return res;
    }

    private HashSet<Integer>[] constructGraph(){
        HashSet<Integer>[] g = new HashSet[rows * cols];
        for(int i = 0; i < g.length; i++){
            g[i] = new HashSet<>();
        }
        for(int v = 0; v < g.length; v++){
            int x = v / cols, y = v % cols;
            if(grid[x][y] == 1){
                for(int i = 0; i < 4; i++){
                    int neiX = x + dirs[i][0];
                    int neiY = y + dirs[i][1];
                    if(inArea(neiX, neiY) && grid[neiX][neiY] == 1){
                        int nei = neiX * cols + neiY;
                        g[v].add(nei);
                        g[nei].add(v); // undirected graph, add twice
                    }
                }
            }
        }
        return g;
    }

    // solution without constructing the graph
    public int maxAreaII(){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows * cols];
        int res = 0;
        for(int i = 0 ; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 1 && !visited[i * cols + j]){
                    res = Math.max(res, dfsI(i, j));
                }
            }
        }
        return res;
    }

    private int dfsI(int x, int y){
        visited[x * cols + y] = true;
        int res = 1;
        for(int d = 0; d < 4; d++){
            int neiX = x + dirs[d][0], neiY = y + dirs[d][1];
            if(inArea(neiX, neiY) && grid[neiX][neiY] == 1 && !visited[neiX * cols + neiY]){
                res += dfsI(neiX, neiY);
            }
        }
        return res;
    }


    private boolean inArea(int x, int y){
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1}};
        MaxAreaOfIsland s = new MaxAreaOfIsland(grid);
        System.out.println(s.maxAreaII());
    }
}
