package Algorithm.Graph.Topology;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/26 10:37
 *   @Description :
        Given a list of Connections, which is the Connection class (the city name at both ends of the edge and a cost between them), find edges that can connect all the cities and spend the least amount.
        Return the connects if can connect all the cities, otherwise return empty list.
         Input:
        ["Acity","Bcity",1]
        ["Acity","Ccity",2]
        ["Bcity","Ccity",3]
        Output:
        ["Acity","Bcity",1]
        ["Acity","Ccity",2]
        Example 2:

        Input:
        ["Acity","Bcity",2]
        ["Bcity","Dcity",5]
        ["Acity","Dcity",4]
        ["Ccity","Ecity",1]
        Output:
        []
        Explanation:
        No way
 */
public class MinimumSpanningTree {

    class Connection{
        public String city1, city2;
        public int cost;
        public Connection(String city1, String city2, int cost){
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }
    }

    // Use UnionFind to detect cycle
    public List<Connection> lowestCost(List<Connection> connections){
        if(connections == null || connections.size() == 0){
            return new ArrayList<>();
        }
        // we want to find the minimum path, we should sort connections by cost
        Collections.sort(connections, (a, b) -> a.cost != b.cost ? a.cost - b.cost : a.city1.equals(b.city1) ? a.city2.compareTo(b.city2) : a.city1.compareTo(b.city1));
        Map<String, Integer> graph = new HashMap<>();
        int n = 0;
        // construct graph
        for(Connection connection:connections){
            if(!graph.containsKey(connection.city1)){
                graph.put(connection.city1, ++n);
            }
            if(!graph.containsKey(connection.city2)){
                graph.put(connection.city2, ++n);
            }
        }
        // Union Find
        int[] parent = new int[n + 1];
        List<Connection> res = new ArrayList<>();
        for(Connection connection:connections){
            int num1 = graph.get(connection.city1);
            int num2 = graph.get(connection.city2);
            int root1 = find(num1, parent);
            int root2 = find(num2, parent);
            if(root1 != root2){
                parent[root1] = root2;
                res.add(connection);
            }
        }
        // if there exist isolated island
        if(res.size() != n - 1){
            return new ArrayList<>();
        }
        return res;
    }
    private int find(int num, int[] parent){
        if(parent[num] == 0){
            return num;
        }
        return parent[num] = find(parent[num], parent);
    }
}
