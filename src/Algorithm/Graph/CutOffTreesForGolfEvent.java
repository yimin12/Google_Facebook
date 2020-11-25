package Algorithm.Graph;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/28 11:12
 *   @Description :
        You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
        0 represents the obstacle can't be reached.
        1 represents the ground can be walked through.
        The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
        You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
        You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
        You are guaranteed that no twotreeshave the same height and there is at least one tree needs to be cut off.
        Example 1:
        Input:
        [
         [1,2,3],
         [0,0,4],
         [7,6,5]
        ]
        Output:
         6
        Example 2:
        Input:

        [
         [1,2,3],
         [0,0,0],
         [7,6,5]
        ]
        Output:
         -1
        Example 3:
        Input:
        [
         [2,3,4],
         [0,0,5],
         [8,7,6]
        ]
        Output:
         6
        Explanation:
         You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
        Hint: size of the given matrix will not exceed 50x50.
 */
public class CutOffTreesForGolfEvent {

    // Insight: Find the shortest path
    // bfs1
    public int cutOffTreeBFSI(List<List<Integer>>  forest){
        List<int[]> trees = new ArrayList<>();
        int[][] map = new int[forest.size()][forest.get(0).size()];
        for(int i = 0; i < forest.size(); i++){
            for(int j = 0; j < forest.get(0).size(); j++){
                int height = forest.get(i).get(j);
                map[i][j] = height;
                if(height > 1){
                    trees.add(new int[]{height,i,j}); // create a 3 elements tuple
                }
            }
        }
        // sort the trees based on their height
        Collections.sort(trees, (t1, t2) -> Integer.compare(t1[0], t2[0]));
        int res = 0, startX = 0, startY = 0;
        for(int[] tree : trees){
            int distance = getDistance(map, startX, startY, tree[1], tree[2]);
            if(distance < 0){
                return  -1;
            }
            res += distance;
            // the completed position will become next entry position
            startX = tree[1];
            startY = tree[2];
        }
        return res;
    }
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int getDistance(int[][] map, int x, int y, int tx, int ty){
        int distance = 0;
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            while(levelSize-- > 0){
                int[] pos = queue.poll();
                if(pos[0] == tx && pos[1] == ty){
                    return distance;
                }
                for(int[] dir:dirs){
                    int neiX = pos[0] + dir[0];
                    int neiY = pos[1] + dir[1];
                    if(neiX >= 0 && neiX < map.length && neiY >= 0 && neiY < map[0].length){
                        if(map[neiX][neiX] != 0 && !visited[neiX][neiY]){
                            queue.offer(new int[]{neiX, neiY});
                            visited[neiX][neiY] = true;
                        }
                    }
                }
            }
            distance++;
        }
        return -1;
    }

    // Best first Search: BFS2 -> Dijkstra's Algorithm
    public int cutOffTree(List<List<Integer>> forest){
        List<int[]> trees = new ArrayList<>();
        int[][] map = new int[forest.size()][forest.get(0).size()];
        for(int i = 0; i < forest.size(); i++){
            for(int j = 0; j < forest.get(0).size(); j++){
                int height = forest.get(i).get(j);
                map[i][j] = height;
                if(height > 1){
                    trees.add(new int[]{height, i, j});
                }
            }
        }
        Collections.sort(trees, (t1, t2) -> Integer.compare(t1[0], t2[0]));

        int res = 0, startX = 0, startY = 0;
        for(int[] tree:trees){
            int distance = getDistanceII(map, startX, startY, tree[1], tree[2]);
            // if any node can not be completed, you can not finished your job
            if(distance < 0){
                return -1;
            }
            res += distance;
            startX = tree[1];
            startY = tree[2];
        }
        return res;
    }
    private int getDistanceII(int[][] map, int x, int y, int tx, int ty){
        int[][] distance = new int[map.length][map[0].length];
        for(int[] row:distance){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[x][y] = 0;
        boolean[][] visited = new boolean[map.length][map[0].length];
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)-> a[2]-b[2]);
        pq.offer(new int[]{x, y, 0});
        visited[x][y] = true;
        while(!pq.isEmpty()){
            int[] pos = pq.poll();
            for(int[] dir:dirs){
                int neiX = pos[0] + dir[0];
                int neiY = pos[1] + dir[1];
                if(neiX >= 0 && neiX < map.length && neiY >= 0 && neiY < map[0].length){
                    if(map[neiX][neiY] != 0 && !visited[neiX][neiY]){ // key insight of dijkstra's algorithm
                        distance[neiX][neiY] = pos[2] + 1;
                        visited[neiX][neiY] = true;
                        pq.offer(new int[]{neiX, neiY, pos[2] + 1});
                    }
                }
            }
        }
        if(distance[tx][ty] == Integer.MAX_VALUE){
            return -1;
        }
        return distance[tx][ty];
    }

    public static void main(String[] args) {
        Integer[][] test = new Integer[][]{{1,2,3},{0,0,4},{7,6,5}};
        List<List<Integer>> forest = new ArrayList<>();
        for(int i = 0; i < test.length; i++){
            List<Integer> cur = Arrays.asList(test[i]);
            forest.add(cur);
        }
        CutOffTreesForGolfEvent solution = new CutOffTreesForGolfEvent();
        int res = solution.cutOffTree(forest);
        System.out.println(res);

    }
}
