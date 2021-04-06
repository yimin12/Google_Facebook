package Contest.Bloomberg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/5 23:29
 *   @Description :
 *
 */
public class CourseSchedule {



    // Time : O(Vertex + Edge), Space: O(Vertex + Edge)
    // courseSchedule II
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // corner case
        if(numCourses < 0){
            return new int[0];
        }
        // if there is no prerequisites requirement
        if(prerequisites == null){
            int[] result = new int[numCourses];
            for(int i = 0; i < numCourses; i++){
                result[i] = i;
            }
        }
        List[] courses = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            courses[i] = new ArrayList<Integer>();
        }
        // construct the map
        for(int i= 0; i < prerequisites.length; i++){
            // inverted index
            courses[prerequisites[i][1]].add(prerequisites[i][0]);
            degree[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < degree.length; i++){
            if(degree[i] == 0){
                queue.offer(i);
            }
        }
        int count = 0;
        int[] order = new int[numCourses];
        while(!queue.isEmpty()){
            int course = (int)queue.poll();
            order[count] = course;
            count++;
            int n = courses[course].size();
            for(int i = n - 1; i >= 0; i--){
                int nextCourse = (int)courses[course].get(i);
                degree[nextCourse]--;
                if(degree[nextCourse] == 0){
                    queue.add(nextCourse);
                }
            }
        }
        if(count == numCourses){
            return order;
        }
        return new int[0];
    }

    // CourseScheduleIII, number of ways to finish all courses
    boolean[][] pre = null;
    int[] preCnt = null;
    boolean[] selected = null;
    int res = 0;
    int n;
    public int topologicalSortNumber(int nn, int[][] p) {
        n = nn;
        int i, j;
        pre = new boolean[n][n];
        preCnt = new int[n];
        selected = new boolean[n];
        for(i = 0; i < n; i++){
            preCnt[i] = 0;
            selected[i] = false;
            for(j = 0; j < n; j++){
                pre[i][j] = false;
            }
        }
        for(i = 0; i < p.length; i++){
            pre[p[i][1]][p[i][0]] = true;
            ++preCnt[p[i][0]];
        }
        dfs(0);
        return res;
    }
    private void dfs(int level){
        // base case
        if(level == n){
            res++;
            return;
        }
        for(int i = 0; i < n; i++){
            if(!selected[i] && preCnt[i] == 0){
                selected[i] = true;
                for(int j = 0; j < n; j++){
                    if(pre[i][j]){
                        preCnt[j]--;
                    }
                }
                dfs(level+1);
                // handle backtrack
                for(int j = 0; j < n; j++){
                    if(pre[i][j]){
                        preCnt[j]++;
                    }
                }
                selected[i] = false;
            }
        }

    }
}
