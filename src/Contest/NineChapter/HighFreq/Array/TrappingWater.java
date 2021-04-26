package Contest.NineChapter.HighFreq.Array;

import java.util.PriorityQueue;

/**
 * Description
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 * Trapping Rain Water
 *
 * Example
 * Example 1:
 *
 * Input: [0,1,0]
 * Output: 0
 * Example 2:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingWater {

    // O(n) time, O(1) space
    public int trapRainWater(int[] height){
        if(height == null || height.length == 0) return 0;
        int n = height.length, leftMax = height[0], rightMax = height[n - 1];
        int left = 0, right = n - 1, res = 0;
        while(left <= right){
            if(leftMax <= rightMax){
                res += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
            } else {
                res += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
            }
        }
        return res;
    }

    /**
     * Description
     * Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, compute how much water it is able to trap after raining.
     * Example
     * Example 1:
     *
     * Given `5*4` matrix
     * Input:
     * [[12,13,0,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]
     * Output:
     * 14
     */
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};
    public int trapRainWater(int[][] heights){
        if(heights == null || heights.length == 0 || heights[0].length == 0){
            return 0;
        }
        int m = heights.length, n = heights[0].length, i, j;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Cell> pq = new PriorityQueue<>(m * n, (a, b) -> a.height - b.height);
        for(i = 0; i < m; i ++){
            pq.offer(new Cell(i, 0, heights[i][0]));
            visited[i][0] = true;
            pq.offer(new Cell(i, n - 1, heights[i][n-1]));
            visited[i][n-1] = true;
        }
        for(j = 0; j < n; j ++){
            pq.offer(new Cell(0, j, heights[0][j]));
            visited[0][j] = true;
            pq.offer(new Cell(m - 1, j, heights[m-1][j]));
            visited[m-1][j] = true;
        }
        int res = 0;
        while(!pq.isEmpty()){
            Cell cur = pq.poll();
            for(i = 0; i < 4; i ++){
                int neiX = cur.x + dx[i];
                int neiY = cur.y + dy[i];
                if(neiX < 0 || neiY < 0 || neiX >= m || neiY >= n || visited[neiX][neiY]){
                    continue;
                }
                visited[neiX][neiY] = true;
                pq.offer(new Cell(neiX, neiY, Math.max(heights[neiX][neiY], cur.height)));
                res += Math.max(0, cur.height - heights[neiX][neiY]);
            }
        }
        return res;

    }

    class Cell{
        public int x,  y, height;
        public Cell(int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height =height;
        }

    }


}
