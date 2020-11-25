package Algorithm.Graph;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/28 18:30
 *   @Description :
 *      Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
        The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
        Have you met this question in a real interview?
        Example
        Given a / b = 2.0, b / c = 3.0.
        queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
        return [6.0, 0.5, -1.0, 1.0, -1.0 ].

        The input is: vector<pair<string, string>> equations,
        vector<double>& values,
        vector<pair<string, string>> queries ,
        where equations.size() == values.size(), and the values are positive.
        This represents the equations. Return vector<double>.

        According to the example above:

        equations = [ ["a", "b"], ["b", "c"] ],
        values = [2.0, 3.0],
        queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 */
public class EvaluateDivision {

    class Edge{
        String from, to;
        double value;
        public Edge(String from, String to, double value){
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
    public double[] calEquation(String[][] equations, double[] values, String[][] queries){
        if(equations == null || values == null || queries == null || equations.length != values.length){
            return new double[0];
        }
        HashMap<String, List<Edge>> graph = buildGraph(equations, values);
        double[] result = new double[queries.length];
        int index = 0;
        for(String[] query : queries){
            if(!graph.containsKey(query[0])){
                result[index] = -1;
            } else {
                String nominator = query[0];
                String denominator = query[1];
                HashSet<String> visited = new HashSet<>();
                double val = dfs(graph, visited, 1.0, nominator, denominator);
                result[index] = val;
            }
            index++;
        }
        return result;
    }
    private HashMap<String, List<Edge>> buildGraph(String[][] equations, double[] values){
        HashMap<String, List<Edge>> graph = new HashMap<>();
        for(int i = 0; i < equations.length; i++){
            graph.putIfAbsent(equations[i][0], new ArrayList<>());
            graph.get(equations[i][0]).add(new Edge(equations[i][0], equations[i][1], values[i]));
            // undirected weighted graph
            graph.putIfAbsent(equations[i][1], new ArrayList<>());
            graph.get(equations[i][1]).add(new Edge(equations[i][1], equations[i][0], 1.0/values[i]));
        }
        return graph;
    }
    private double dfs(HashMap<String, List<Edge>> graph, HashSet<String> visited, double valuePath, String from, String to){
        // base case
        if(from == to){
            return valuePath;
        }
        visited.add(from);
        List<Edge> edges = graph.get(from);
        if(edges != null){
            for(Edge e : edges){
                if(visited.contains(e)){
                    continue;
                }
                visited.add(to);
                double value = dfs(graph, visited, valuePath * e.value, e.to, to);
                // pruning
                if(value != -1.0){
                    return value;
                }
            }
        }
        return -1.0;
    }

    // Method 2: BFS
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries){
        if(equations == null || values == null || queries == null || equations.size() != values.length) return new double[0];
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for(int i = 0; i < equations.size(); i++){
            List<String> equation = equations.get(i);
            add(graph, equation.get(0), equation.get(1), values[i]);
            add(graph, equation.get(1), equation.get(0), 1.0/values[i]);
        }
        double[] res = new double[queries.size()];
        for(int i = 0; i < res.length; i++){
            List<String> query = queries.get(i);
            res[i] = getValue(graph, query.get(0), query.get(1));
        }
        return res;
    }
    private void add(Map<String, Map<String, Double>> graph, String from ,String to, double value){
        graph.putIfAbsent(from, new HashMap<>());
        graph.get(from).put(to, value);
    }
    private double getValue(Map<String, Map<String, Double>> graph, String from, String to){
        if(graph.get(from) == null || graph.get(to) == null){
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        Map<String, Double> value = new HashMap<>();
        Set<String> visited = new HashSet<>();
        // init
        queue.offer(from);
        visited.add(from);
        value.put(from, 1.0); // record the current path value
        String cur,next;
        while(!queue.isEmpty()){
            cur = queue.poll();
            for(Map.Entry<String,Double> entry:graph.get(cur).entrySet()){
                next = entry.getKey();
                value.put(next, value.get(cur)*entry.getValue());
                if(next.equals(to)){
                    return value.get(to);
                } else if(visited.add(next)){
                    queue.offer(next);
                }
            }
        }
        return -1.0;
    }
}
