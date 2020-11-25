package Algorithm.Graph.Topology;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/5 19:58
 *   @Description :
 *      There are a total of n courses you have to take, labeled from 0 to n - 1.
        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
        Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
        * Input: n = 2, prerequisites = [[1,0],[0,1]]
          Output: false
          Input: n = 2, prerequisites = [[1,0]]
          Output: true
 */
public class CourseSchedule {

    // BFS for topological sort: use indegree to decide what object should be offered into queue(indegree == 0), then the order will be sorted
    // DFS for topological sort: we need another state to record the diving and backtracking


    // We should construct graph when you try to solve topology problem
    // The solving solution could be DFS or BFS or UnionFind, whatever you like
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0 || prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0){
            return true;
        }
        int[] degree = new int[numCourses];
        List[] courses = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++) {
            courses[i] = new ArrayList<Integer>(); //为每一门课创建一个list表示之后可以学的
        }
        // construct the map, inverted mark the prerequisites and the courses
        for(int i=0; i<prerequisites.length; i++){ //学了pre 之后能学的课程
            courses[prerequisites[i][1]].add(prerequisites[i][0]);
            degree[prerequisites[i][0]]++;
        }
        // we use degree to help bfs work
        Queue<Integer> queue = new LinkedList<>();
        // put all course that are degree of 0
        for(int i = 0; i < numCourses; i++){
            if(degree[i] == 0){
                queue.offer(i); // i is the label of the course, it might have multiple entrance point
            }
        }
        int result = 0;
        while(!queue.isEmpty()) {
            int preCourse = (int)queue.poll(); //前置课程
            result++;
            // courses[preCourse] 前置课程的后面课程的list
            int size = courses[preCourse].size();
            for(int i=0; i<size; i++) {
                int nextCourse = (int)courses[preCourse].get(i);
                degree[nextCourse]--;
                if(degree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        return result == numCourses;
    }

    // DFS version with OOD style
    class Course{
        boolean visited = false; // being visited;
        boolean tested = false; // tested if it is cyclic
        List<Course> pre = new ArrayList<>();
        public void add(Course c){
            pre.add(c);
        }
    }
    public boolean canFinishDFS(int numCourses, int[][] prerequisites){
        Course[] courses = new Course[numCourses];
        for(int i = 0; i < numCourses; i++){
            courses[i] = new Course();
        }
        for(int i = 0; i < prerequisites.length; i++){
            courses[prerequisites[i][0]].add(courses[prerequisites[i][1]]);
        }
        // try to get all entry
        for(int i = 0; i < numCourses; i++){
            if(isCyclic(courses[i])) return false; // if any cycle detected, return false;
        }
        return true;
    }
    private boolean isCyclic(Course c){
        if(c.tested) return false; // already tested before, handle the process of backtracking
        if(c.visited) return true; // already visited before, handle the process of diving
        // if it neither visited nor tested, means the process is diving
        c.visited = true;
        for(Course course : c.pre){
            if(isCyclic(course)){
                return true;
            }
        }
        // backtracking part
        c.tested = true;
        return false; // means no cycle
    }


    // Follow up: What if I want the order to learn all courses
    // The same as Topology Sort, BFS method, if you want to implement dfs, the same as well
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

    // Follow Up 2: Return how many ways you can complement all the course if applicable
    // if we need to return one valid solution of all possible answers, use dfs rather than bfs
    int n;
    boolean[][] pre;
    boolean[] done;
    int[] preCnt;//preCnt[i]: how many of i's prerequisite has not been taken
    int res = 0;

    void dfs(int level){
        if(level == n){
            res++;
            return;
        }

        int i, j;
        //try to select course i
        for(i = 0; i < n; i++){
            if(!done[i] && preCnt[i] == 0){
                done[i] = true;
                //當選了i，哪些課程的沒修的先修課程沒變
                for(j = 0; j < n; j++){
                    if(pre[i][j]){
                        preCnt[j] --;
                    }
                }

                dfs(level + 1);
                done[i] = false;
                //反過來撤銷
                for(j = 0; j < n; j++){
                    if(pre[i][j]){
                        preCnt[j] ++;
                    }
                }
            }
        }

    }

    public int topologicalSortNumber(int nn, int[][] p) {
        // Write your code here
        n = nn;
        int i, j;
        //p[i] : {x, y}: to take x, you must first take y
        pre = new boolean[n][n];
        for(i = 0; i < n; i++){
            for(j = 0; j < n; j++){
                pre[i][j] = false;
            }
        }

        preCnt = new int[n];
        done = new boolean[n];

        for(i = 0; i < n; i++){
            preCnt[i] = 0;
            done[i] = false;
        }


        //pre[i][j] = true if i needs to be taken before j
        for(i = 0; i < p.length; i++){
            pre[p[i][1]][p[i][0]] = true;
            preCnt[p[i][0]] ++;
        }

        dfs(0);
        return res;
    }
}
