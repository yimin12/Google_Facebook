package Algorithm.DynamicProgramming.LinearDP;

import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/4 22:26
 *   @Description :
 *  	Given a non-negative integer array representing the heights of a list of adjacent bars.
 * 	    Suppose each bar has a width of 1. Find the largest amount of water that can be trapped
 * 	    in the histogram.
 *  Assumptions:
 *  	The given array is not null
 *  Examples:
 *  	{ 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2
 * 	    (at index 1, 1 unit of water can be trapped and index 3, 1 unit of water can be trapped)
 */
public class MaxWaterTrapped {

    public int maxTrapped(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int left = 0, leftMax = 0;
        int right = 0, rightMax = 0;
        int waterTrapped = 0;
        while(left < right){
            if(array[left] < array[right]){
                waterTrapped = Math.max(0, leftMax - array[left]);
                leftMax = Math.max(array[left], leftMax);
                left++;
            } else {
                waterTrapped = Math.max(0, rightMax - array[right]);
                rightMax = Math.max(rightMax, array[right]);
                right--;
            }
        }
        return waterTrapped;
    }
    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, 1, 0, -1};
    public int maxTrappedII(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        if(rows < 3 || cols < 3){
            return 0;
        }
        // int[x, y, height]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[rows][cols];
        // add all border
        for(int i = 0; i < rows; i++){
            pq.add(new int[]{i, 0, matrix[i][0]});
            pq.add(new int[]{i, cols-1, matrix[i][cols-1]});
            visited[i][0] = visited[i][cols-1] = true;
        }
        for(int i = 0; i < cols; i++){
            pq.add(new int[]{0, i, matrix[0][i]});
            pq.add(new int[]{rows-1, i, matrix[rows-1][i]});
            visited[0][i] = visited[rows-1][i] = true;
        }
        int waterTrapped = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            for(int i = 0; i < 4; i++){
                int neiX = cur[0] + dx[i];
                int neiY = cur[1] + dy[i];
                if(neiX >= 0 && neiX < rows && neiY >= 0 && neiY < cols && visited[neiX][neiY]){
                    waterTrapped = Math.max(0, cur[3] - matrix[neiX][neiY]);
                    pq.offer(new int[]{neiX, neiY, Math.max(cur[3], matrix[neiX][neiY])});
                }
            }
        }
        return waterTrapped;
    }
}
