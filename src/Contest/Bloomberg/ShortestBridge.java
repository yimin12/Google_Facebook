package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 16:49
 *   @Description :
 *      https://leetcode.com/problems/shortest-bridge/
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Share
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 *
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 *
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 */
public class ShortestBridge {

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    public int shortestBridge(int[][] A) {
        if(A == null || A.length == 0 || A[0].length == 0) return 0;
        int m = A.length, n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false; // find one island first, and let the other one to be unvisited, then use bfs to find the min distance
        for(int i = 0; i < m; i ++){
            if(found){
                break;
            }
            for(int j = 0; j < n; j ++){
                if(A[i][j] == 1){
                    dfs(A, visited, queue, i, j); // add all the coordinates of island to queue and prepare it for bfs.
                    found = true;
                    break;
                }
            }
        }
        int step = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size -- > 0){
                int[] cur = queue.poll();
                for(int i = 0; i < 4; i ++){
                    int neiX = cur[0] + dx[i];
                    int neiY = cur[1] + dy[i];
                    if(neiX >= 0 && neiY >= 0 && neiX < m && neiY < n && !visited[neiX][neiY]){
                        if(A[neiX][neiY] == 1){
                            return step;
                        }
                        queue.offer(new int[]{neiX, neiY});
                        visited[neiX][neiY] = true;
                    }
                }
            }
            step ++;
        }
        return -1;
    }

    private void dfs(int[][] A, boolean[][] visited, Queue<int[]> q, int x, int y){
        if(x < 0 || x >= A.length || y < 0 || y >= A[0].length || visited[x][y] || A[x][y] == 0){
            return;
        }
        visited[x][y] = true;
        q.offer(new int[]{x, y});
        for(int i = 0; i < 4; i ++){
            dfs(A, visited, q, x + dx[i], y + dy[i]);
        }
    }
}
