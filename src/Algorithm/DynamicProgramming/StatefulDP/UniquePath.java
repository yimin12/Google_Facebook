package Algorithm.DynamicProgramming.StatefulDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 14:29
 *   @Description :
 *	    A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *		The robot can only move either down or right at any point in time.
 *		The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *		How many possible unique paths are there?
 */
public class UniquePath {

    // Method 1: dfs, each level have two choice, so total movement is constant and assume it is m
    // Time: O(2^(m+n-2))  extra space: O(max(m,n)) for call stack
    public int uniquePathI(int m, int n){
        if(m <= 0 || n <= 0){
            return 0;
        }
        return dfsI(m - 1, n - 1);
    }
    private int dfsI(int m, int n){
        if(m < 0 || n < 0) return 0;
        else if(m == 0 && n == 0) return 1;
        return dfsI(m-1, n) + dfsI(m, n - 1);
    }

    // Method 2: memory Search
    public int uniquePathII(int m, int n){
        if(m <= 0 || n <= 0) return 0;
        return memorySearch(m - 1, n - 1, new int[n][m]);
    }
    private int memorySearch(int m, int n, int[][] mem){
        if(m < 0 || n < 0) return 0;
        else if(m == 0 && n == 0) return 1;
        else if(mem[m][n] > 0){
            return mem[m][n];
        } else {
            mem[m][n] = memorySearch(m - 1, n, mem) + memorySearch(m, n - 1, mem);
            return mem[m][n];
        }
    }

    // Method 3: dynamic programming
    // dp[i][j] represent the number of ways from [0][0] move to [i][j].
    // Base case :
    //  	dp[0][0] = 1;
    // Induction rule:
    // 		dp[i][j] = dp[i-1][j] + dp[i][j-1] care about the out of boundary
    // Time ~ O(M*N), Space ~ O(M*N)
    public int uniquePathIII(int m, int n){
        if(m <= 0 || n <= 0) return 0;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    // Rolling array
    public int uniquePathIV(int m, int n){
        if(m <= 0 || n <= 0) return 0;
        int min = Math.min(m, n), max = Math.max(m, n);
        int[] dp = new int[min];
        for(int i = 0; i < max; i++){
            for(int j = 0; j < min; j++){
                if(i == 0 && j == 0) dp[j] = 1;
                else {
                    dp[j] = ((i > 0) ? dp[j] : 0) + ((j > 0) ? dp[j - 1] : 0);
                }
            }
        }
        return dp[min - 1];
    }

    // Follow Up 2: Now consider if some obstacles are added to the grids. How many unique paths would there be?
    // An obstacle and empty space is marked as 1 and 0 respectively in the grid.
    // Method 1: dfs
    // Time: O(2^(m+n-2))  extra space: O(max(m,n)) for call stack
    public int uniquePathV(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] count = new int[1];
        count[0] = memorySearchII(0, m - 1, 0, n - 1, grid, count);
        return count[0];
    }
    private int memorySearchII(int curX, int rows, int curY, int cols, int[][] grid, int[] count){
        if(curX == rows && curY == cols){
            count[0]++;
            return count[0];
        }
        if(curX < rows && grid[curX +1][curY] == 0) memorySearchII(curX + 1, rows, curY, cols, grid, count);
        if(curY < cols && grid[curX][curY + 1] == 0) memorySearchII(curX, rows, curY + 1, curY, grid, count);
        return count[0];
    }
    // Version 2 of memorySearch
    public int uniquePathVI(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] mem = new int[m][n];
        return memorySearchIII(0, 0, m-1, n-1, mem, grid);
    }
    private int memorySearchIII(int x, int y, int rows, int cols, int[][] mem, int[][] grid){
        if(x > rows || y > cols){
            return 0;
        } else if(x == rows && cols == 1){
            return 1;
        } else if(mem[x][y] > 0){
            return mem[x][y];
        }
        if(x < rows && grid[x + 1][y] == 0){
            mem[x+1][y] = memorySearchIII(x + 1, y, rows, cols, mem, grid);
        }
        if(y < cols && grid[x][y + 1] == 0){
            mem[x][y + 1] = memorySearchIII(x, y + 1, rows, cols, mem, grid);
        }
        mem[x][y] = mem[x + 1][y] + mem[x][y + 1];
        return mem[x][y];
    }
    // DP Version
    public int uniquePathVIII(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = grid[i][j] == 1 ? 0 : 1;
                } else {
                    dp[i][j] = (grid[i - 1][j] == 1 ? 0 : dp[i - 1][j]) + (grid[i][j - 1] == 1 ? 0 : dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    // rolling array
    public int uniquePathIX(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int min = Math.max(m, n), max = Math.max(m, n);
        int[] dp = new int[min];
        int value;
        for(int i = 0; i < max; i++){
            for(int j = 0; j < min; j++){
                if(i == 0 || j == 0){
                    dp[j] = grid[i][j] == 1 ? 0 : 1;
                } else {
                    dp[j] = grid[i][j] == 1 ? 0 : ((i > 0) ? dp[j] : 0) + ((j > 0) ? dp[j - 1] : 0);
                }
            }
        }
        return dp[min - 1];
    }

    // Follow Up 3: On a 2-dimensional grid, there are 4 types of squares:
    // 1 represents the starting square.  There is exactly one starting square.
    // 2 represents the ending square.  There is exactly one ending square
    // 0 represents empty squares we can walk over.
    // -1 represents obstacles that we cannot walk over
    // Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
    // Method 1: DFS with back tracking
    // Time: O(4^(m*n)) Space:(m*n)
    public int uniquePathX(int[][] grid){
        int startX = 0, startY = 0, walk = 0;
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    walk++;
                }
                if(grid[i][j] == 1){
                    startX = i;
                    startY = j;
                }
            }
        }
        return dfsII(startX, startY, m, n, - 1, walk, grid);
    }
    private int dfsII(int x, int y, int rows, int cols, int index, int walk, int[][] grid){
        if(x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == -1){
            return 0;
        }
        if(grid[x][y] == 2){
            if(index == walk){
                return 1;
            } else return 0;
        }
        grid[x][y] = -1;
        int total = dfsII(x + 1, y, rows, cols, index + 1, walk, grid) + dfsII(x - 1, y, rows, cols, index + 1, walk , grid) +
                dfsII(x, y + 1, rows, cols, index +1, walk, grid) + dfsII(x, y - 1, rows, cols, index + 1, walk, grid);
        // handle the backtracking
        grid[x][y] = 0;
        return total;
    }

    public static void main(String[] args) {
        UniquePath solution = new UniquePath();
        int[][] test = new int[][] {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        int res = solution.uniquePathX(test);
        System.out.println(res);
    }
}
