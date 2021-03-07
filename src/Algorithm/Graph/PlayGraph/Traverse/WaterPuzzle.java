package Algorithm.Graph.PlayGraph.Traverse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 18:33
 *   @Description :
 *      有两种水桶，一种水桶能装5L水，另外一种水桶能装3升水，怎么利用这两个水桶装4升水
 */
public class WaterPuzzle {

    private int[] pre;
    private int end = -1;

    public WaterPuzzle(int n){
        // Use bfs the traverse all states
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[100]; // a * 10 + b : a is bucket can hold 5L, and b is bucket can hold 3L, compress the state, 2 digit are enough
        pre = new int[100];
        queue.add(0); // a = 0, b = 0 -> a * 10 + b = 0
        visited[0] = true;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            int a = cur / 10, b = cur % 10;
            // declare the state
            ArrayList<Integer> nexts = new ArrayList<Integer>();
            // case 1: fills 5L to empty a
            nexts.add(5 * 10 + b);
            // case 2: fills 3L to empty b
            nexts.add(a * 10 + 3);
            // case 3: drop 5L from a
            nexts.add(0 * 10 + b);
            // case 4: drop 4L from b
            nexts.add(a * 10 + 0);
            // case 5: drop the water of a to b
            int x = Math.min(a, 3 - b); // a : water of a, b: remaining water of b
            nexts.add((a - x) * 10 + (b + x));
            // case 6: drop the water of b to a
            int y = Math.min(b, 5 - a);
            nexts.add((a + y) * 10 + (b - y));

            for(Integer next : nexts){
                if(!visited[next]){
                    queue.add(next);
                    visited[next] = true;
                    pre[next] = cur;
                    if(next / 10 == n || next % 10 == n){
                        end = next;
                        return;
                    }
                }
            }
        }
    }

    public Iterable<Integer> result(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(end == -1){
            return res;
        }
        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        for(int i = 1; i <= 8; i++){
            System.out.println((new WaterPuzzle(i)).result());
        }
    }
}
