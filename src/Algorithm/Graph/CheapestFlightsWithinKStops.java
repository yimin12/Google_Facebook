package Algorithm.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/28 17:22
 *   @Description :
        There arencities connected by mflights. Each fight starts from city uand arrives at vwith a pricew.
        Now given all the cities and flights, together with starting citysrcand the destination dst, your task is to find the cheapest price fromsrctodstwith up tokstops. If there is no such route, output-1.
        Example 1:
        Input:
        n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
        src = 0, dst = 2, k = 1
        Output:
         200
        Explanation:
        The graph looks like this:
             0
           /    \
         100    500
         /         \
        1 ---100--- 2
        The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
        Example 2:
        Input:
        n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
        src = 0, dst = 2, k = 0
        Output:
         500
        Explanation:
        The graph looks like this:
             0
           /    \
         100    500
         /         \
        1 ---100--- 2
        The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked red in the picture.
        Note:
        The number of nodes nwill be in range[1, 100], with nodes labeled from0ton - 1.
        The size of flights will be in range [0, n * (n - 1) / 2].
        The format of each flight will be (src, dst, price).
        The price of each flight will be in the range [1, 10000].
        k is in the range of [0, n - 1].
        There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightsWithinKStops {

    // dijkstra's Algorithm (BFS2) with priorityQueue
//    Time Complexity: O(E+nlogn), where E is the total number of flights.?
//    Space Complexity: O(n), the size of the heap.?
    class Flight{
        int start, end, price;
        Flight(int start, int end, int price){
            this.start = start;
            this.end = end;
            this.price = price;
        }
    }

    class Stop{
        int id, cost, count;
        Stop(int id, int cost, int count){
            this.id = id;
            this.cost = cost;
            this.count = count;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k){
        if(n <= 0 || flights == null || flights.length == 0 || flights[0].length == 0 || k <= 0){
            return -1;
        }
        HashMap<Integer, ArrayList<Flight>> map = new HashMap<>();
        // construct the map, key: stop, value: what flight there are
        for(int[] flight : flights){
            map.putIfAbsent(flight[0], new ArrayList<>());
            // record the from , to and cost
            map.get(flight[0]).add(new Flight(flight[0], flight[1], flight[2]));
        }
        PriorityQueue<Stop> pq = new PriorityQueue<>((a, b) -> a.cost - b.count);
        pq.offer(new Stop(src, 0, k));
        while(!pq.isEmpty()){
            Stop cur = pq.poll();
            if(cur.id == dst){
                return cur.cost;
            }
            if(cur.count >= 0){
                List<Flight> list = map.get(cur.id);
                if(list == null){
                    continue;
                }
                for(Flight f:list){
                    pq.offer(new Stop(f.end, f.price + cur.cost, cur.count - 1));
                }
            }
        }
        return -1;
    }

    // DFS with pruning
    private static int cheapest;
    private static boolean routeFound;
    public int findCheapestPriceDFS(int n, int[][] flights, int src, int dst, int k){
        cheapest = Integer.MAX_VALUE;
        routeFound = false;
        HashMap<Integer, ArrayList<Flight>> map = new HashMap<>();

        for(int[] flight:flights){
            map.putIfAbsent(flight[0], new ArrayList<>());
            map.get(flight[0]).add(new Flight(flight[0], flight[1], flight[2]));
        }
        boolean[] visited = new boolean[n];
        dfs(map, visited, src, dst, 0, k);
        if (!routeFound){
            return -1;
        }
        return cheapest;
    }
    private void dfs(HashMap<Integer, ArrayList<Flight>> map, boolean[] visited, int src, int dst, int cost, int k){
        // base case
        if(src == dst){
            routeFound = true;
            cheapest = Math.min(cost, cheapest);
            return;
        }
        // pruning
        if(k < 0){
            return;
        }
        ArrayList<Flight> flights = map.get(src);
        if(flights != null){
            for(Flight flight:flights){
                if(visited[flight.end]){
                    continue;
                }
                if(flight.price + cost > cheapest){
                    continue;
                }
                visited[flight.end] = true;
                dfs(map, visited, flight.end, dst, cost + flight.price, k-1);
                visited[flight.end] = false;
            }
        }
    }
}
