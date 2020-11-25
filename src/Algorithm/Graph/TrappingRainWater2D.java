package Algorithm.Graph;

import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/28 20:26
 *   @Description :
 *
 */
public class TrappingRainWater2D {

    class Cell{
        public int x, y, height;
        public Cell(int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heights){
        if(heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }
        // use minHeap
        int m = heights.length, n = heights[0].length;
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>((c1, c2)-> c1.height == c2.height ? 0 : c1.height - c2.height);
        int[][] visited = new int[m][n];
        for(int i = 0; i < m; i++){
            pq.offer(new Cell(i, 0, heights[i][0]));
            pq.offer(new Cell(i, n-1, heights[i][n-1]));
            visited[i][0] = 1;
            visited[i][n-1] = 1;
        }
        for(int i = 0; i < n; i++){
            pq.offer(new Cell(0, i, heights[0][i]));
            pq.offer(new Cell(m-1, i, heights[m-1][i]));
            visited[0][i] = 1;
            visited[m-1][i] = 1;
        }
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};
        int waterTrap = 0;
        while(!pq.isEmpty()){
            Cell cur = pq.poll();
            for(int k = 0; k < dx.length; k++){
                int neiX = cur.x + dx[k];
                int neiY = cur.y + dy[k];
                if(neiX < m && neiX >= 0 && neiY < n && neiY >= 0 && visited[neiX][neiY] != 1){
                    pq.offer(new Cell(neiX, neiY, Math.max(cur.height, heights[neiX][neiY])));
                    visited[neiX][neiY] = 1;
                    waterTrap += Math.max(0, cur.height - heights[neiX][neiY]);
                }
            }
        }
        return waterTrap;
    }
}
