package Algorithm.Graph.Search.Basic;

import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/10/30 21:53
 *   @Description :
 *      Given a knight in a chessboard n * m (a binary matrix with 0 as empty and 1 as barrier). the knight initialze position is (0, 0) and he wants to reach position
 *      (n - 1, m - 1), Knight can only be from left to right. Find the shortest path to the destination position, return the length of the route. Return -1 if knight can
 *      not reached.
 */
public class KnightShortestPath {

    int[][] dirs = {{1, 2},{-1, 2},{2, 1},{-2, 1}};

    /**
     * @param grid: a chessboard included 0 and 1
     * @return: the shortest path
     */
    // Method 5: Bi-direction BFS
    int[][] FORWARD_DIRECTIONS = {
            {1,2},{-1,2},{2,1},{-2,1}
    };
    int[][] BACKWARD_DIRCTIONS = {
            {-1,-2},{1,-2},{-2,-1},{2,-1}
    };

    public int shortestPathBIBFS(boolean[][] grid){
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0){
            return -1;
        }
        int n = grid.length, m = grid[0].length;
        if(grid[n - 1][m - 1]){
            return -1;
        }
        if(n * m == 1){
            return 0;
        }

        Queue<Point> forward = new LinkedList<>();
        Queue<Point> backward = new LinkedList<>();
        boolean[][] forwardSet = new boolean[n][m];
        boolean[][] backwardSet = new boolean[n][m];
        forward.offer(new Point(0, 0));
        backward.offer(new Point(n - 1, m - 1));
        forwardSet[0][0] = true;
        backwardSet[n-1][m-1] = true;
        int distance = 0;
        while(!forward.isEmpty() && !backward.isEmpty()){
            distance++;
            if(extendQueue(forward, FORWARD_DIRECTIONS, forwardSet, backwardSet, grid)){
                return distance;
            }
            distance++;
            if(extendQueue(backward, BACKWARD_DIRCTIONS, backwardSet, forwardSet, grid)){
                return distance;
            }
        }
        return -1;
    }
    private boolean extendQueue(Queue<Point> queue, int[][] directions, boolean[][] visited, boolean[][] oppositevisited, boolean[][] grid){
        int queueLength = queue.size();
        for(int i = 0; i < queueLength; i++){
            Point cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            for(int[] dir : directions){
                int neiX = x + dir[0];
                int neiY = y + dir[1];
                if(isValid(neiX, neiY, grid, visited)){
                    continue;
                }
                if(oppositevisited[neiX][neiY] == true){
                    return true;
                }
                queue.offer(new Point(neiX, neiY));
                visited[neiX][neiY] = true;
            }
        }
        return false;
    }
    private boolean isValid(int x, int y, boolean[][] grid, boolean[][] visited){
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !grid[x][y] && !visited[x][y];
    }

    // Method 4: Dynamic Programming
    public int shortestPathDP2(boolean [][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for(int i = 0; i < n; i++){
            for(int j = 1; j < m; j++){
                if(!grid[i][j]){
                    for(int[] dir : dirs){
                        if(i >= 1 && j >= 2 && dp[i - 1][j - 2] != Integer.MAX_VALUE){
                            dp[i][j] = Math.min(dp[i][j], dp[i-1][j-2] + 1);
                        }
                        if(i + 1 < n && j - 2 >= 0 && dp[i+1][j-2] != Integer.MAX_VALUE){
                            dp[i][j] = Math.min(dp[i][j], dp[i+1][j-2] + 1);
                        }
                        if(i >= 2 && j >= 1 && dp[i-2][j-1] != Integer.MAX_VALUE){
                            dp[i][j] = Math.min(dp[i][j], dp[i-2][j-1] + 1);
                        }
                        if(i + 2 < n && j >= 1 && dp[i+2][j-1] != Integer.MAX_VALUE){
                            dp[i][j] = Math.min(dp[i][j], dp[i+2][j-1] + 1);
                        }
                    }
                }
            }
        }
        return dp[n-1][m-1] == Integer.MAX_VALUE ? -1 : dp[n-1][m-1];
    }


    // Method 3: Memory Search
    public int shortestPathms2(boolean[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }
        int n = grid.length, m = grid[0].length;
        int[][] memory = new int[n][m];
        memorySearch(grid, memory, n, m, 0, 0);
        return memory[0][0] == Integer.MAX_VALUE ? -1 : memory[0][0];
    }

    private int memorySearch(boolean[][] grid, int[][] memory, int rows, int cols, int x, int y){
        if(x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y]){
            return Integer.MAX_VALUE;
        } else if(x == rows - 1 && y == cols - 1){
            return 0;
        } else if(memory[x][y] > 0){
            return memory[x][y];
        } else {
            int min = Integer.MAX_VALUE;
            for(int[] dir : dirs){
                int neiX = x + dir[0];
                int neiY = y + dir[1];
                int count = memorySearch(grid, memory, rows, cols, neiX, neiY);
                if(count == Integer.MAX_VALUE){
                    continue;
                }
                min = Math.min(min, count + 1);
            };
            memory[x][y] = min;
            return memory[x][y];
        }
    }

    // Method 2: Use pure dfs; Upper case: Time O(4 ^(m*n)) Space: O(m * n)
    public int shortestPathdfs2(boolean[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }
        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        // int[] records its coordinate
        int count = dfs(grid, visited, 0, 0, n, m);
        return count == -2 ? -1 : count;
    }
    private int dfs(boolean[][] grid, boolean[][] visited, int x, int y, int rows, int cols){
        if(x < 0 || x >= rows || y < 0 || y >= cols || visited[x][y] || grid[x][y]){
            return Integer.MAX_VALUE;
        } else if(x == rows - 1 && y == cols - 1){
            return 0;
        }
        visited[x][y] = true;
        int min = Integer.MAX_VALUE;
        for(int[] dir : dirs){
            int neiX = x + dir[0];
            int neiY = y + dir[1];
            int count = dfs(grid, visited, neiX, neiY, rows, cols);
            if(count == Integer.MAX_VALUE){
                continue;
            }
            min = Math.min(min, count + 1);
        }
        visited[x][y] = false;
        return min;
    }

    private boolean valid(int curX, int curY, int rows, int cols, boolean[][] visited, boolean[][] grid){
        return curX >= 0 && curX < rows && curY >= 0 && curY < cols && !visited[curX][curY] && grid[curX][curY] == false;
    }

    // Method 1: Use bfs
    public int shortestPathbfs2(boolean[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }
        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        // int[] records its coordinate
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.offer(new int[]{0, 0});
        int count = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                if(cur[0] == n - 1 && cur[1] == m - 1){
                    return count;
                }
                for(int[] dir : dirs){
                    int neiX = cur[0] + dir[0];
                    int neiY = cur[1] + dir[1];
                    if(valid(neiX, neiY, n, m, visited, grid)){
                        queue.offer(new int[]{neiX, neiY});
                        visited[neiX][neiY] = true;
                    }
                }
            }
            count++;
        }
        return -1;
    }
}

class Point{
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}