package Contest.Bloomberg;

import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/3 1:11
 *   @Description :
 *
 */
public class NumberOfIslands {

    // define traverse direction
    public static int[][] dirs = {{-1, 0},{1, 0},{0, 1},{0, -1}};

    // Method 1: using dfs, Time: O(m*n) worst case Space:(m*n) by call stack
    public int numIslandsDFS(char[][] grid) {
        // sanity check
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        final int rows = grid.length;
        final int cols = grid[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j, rows, cols);
                }
            }
        }
        return count;
    }
    // if we do not need to return the original grid, so mark 1 as 0, because 0 is meaningless for us
    private void dfs(char[][] grid, int x, int y, int rows, int cols) {
        if(x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        for(int[] dir:dirs) {
            int neiX = x + dir[0];
            int neiY = y + dir[1];
            dfs(grid, neiX, neiY, rows, cols);
        }
    }
    // Method 2: same just the regular dfs
    public int numIslandsDFSII(boolean[][] grid) {
        // Assumption: the given the input is valid
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int nums = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == false) {
                    continue;
                }
                nums++;
                dfsII(grid, i, j);
            }
        }
        return nums;
    }
    private void dfsII(boolean[][] grid, int row, int col) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return;
        }
        if(grid[row][col] == true) {
            grid[row][col] = false;
            dfsII(grid, row-1, col);
            dfsII(grid, row+1, col);
            dfsII(grid, row, col-1);
            dfsII(grid, row, col+1);
        }
    }
    // Method 3: BFS Solution
    static class Point{
        int row, col;
        Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public int numIslandBFS(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int nums = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    nums++;
                    grid[i][j] = '0';
                    Queue<Point> q = new LinkedList<Point>();
                    q.offer(new Point(i, j));
                    while(!q.isEmpty()) {
                        Point point = q.poll();
                        for(int k = 0; k < 4; k++) {
                            int nextX = point.row + dirs[k][0];
                            int nextY = point.col + dirs[k][1];
                            if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == '1') {
                                grid[nextX][nextY] = '0';
                                q.offer(new Point(nextX, nextY));
                            }
                        }
                    }
                }
            }
        }
        return nums;
    }
    // Method 4: Another version of BFS by not implementing encapsulation class
    private int m,n;
    public int numsIslandBFSII(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    bfs(grid, visited, i, j);
                }
            }
        }
        return count;
    }
    private void bfs(char[][] grid, boolean[][] visited, int x, int y) {
        int[] dx = {1, 0 , -1, 0};
        int[] dy = {0, 1 , 0, -1};
        Queue<Integer> moveX = new LinkedList<Integer>();
        Queue<Integer> moveY = new LinkedList<Integer>();
        moveX.offer(x);
        moveY.offer(y);
        visited[x][y] = true;
        while(!moveX.isEmpty() && !moveY.isEmpty()) {
            int curX = moveX.poll();
            int curY = moveY.poll();
            for(int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if(nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < n && !visited[x][y] && grid[x][y] == '1') {
                    visited[nextX][nextY] = true;
                    moveX.offer(nextX);
                    moveY.offer(nextY);
                }
            }
        }
    }

    class UnionFind{
        int[] father = null;
        int count;
        private int find(int x){
            if(father[x] == x){
                return x;
            }
            return father[x] = find(father[x]);
        }
        public UnionFind(int n){
            father = new int[n];
            for(int i = 0; i < n; i++){
                father[i] = i;
            }
        }
        private void connect(int a, int b){
            int root_a = find(a);
            int root_b = find(b);
            if(root_b != root_a){
                father[root_a] = root_b;
                count--;
            }
        }
        public int query(){
            return this.count;
        }
        public void set_count(int total){
            count = total;
        }

    }
    public int numIslands(boolean[][] grid) {
        int count = 0;
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(n*m);
        int total = 0;
        for(int i = 0;i < grid.length; ++i)
            for(int j = 0;j < grid[0].length; ++j)
                if (grid[i][j])
                    total ++;
        uf.set_count(total);
        for(int i = 0;i < grid.length; ++i)
            for(int j = 0;j < grid[0].length; ++j)
                if (grid[i][j]) {
                    if (i > 0 && grid[i - 1][j]) {
                        uf.connect(i * m + j, (i - 1) * m + j);
                    }
                    if (i <  n - 1 && grid[i + 1][j]) {
                        uf.connect(i * m + j, (i + 1) * m + j);
                    }
                    if (j > 0 && grid[i][j - 1]) {
                        uf.connect(i * m + j, i * m + j - 1);
                    }
                    if (j < m - 1 && grid[i][j + 1]) {
                        uf.connect(i * m + j, i * m + j + 1);
                    }
                }
        return uf.query();
    }
}
