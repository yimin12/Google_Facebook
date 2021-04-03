package Algorithm.BasicAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/31 11:57
 *   @Description :
 *      https://leetcode.com/problems/best-meeting-point/
 */
public class BestMeetingPoint {

    public int minTotalDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        List<Integer> x = new ArrayList<>(), y = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(grid[i][j] == 1){
                    x.add(i);
                    y.add(j);
                }
            }
        }
        return getMiddle(x) + getMiddle(y);
    }

    // good trick
    private int getMiddle(List<Integer> x){
        Collections.sort(x);
        int i = 0, j = x.size() - 1;
        int res = 0;
        while(i < j){
            res += x.get(j) - x.get(i);
            j --;
            i ++;
        }
        return res;
    }
}
