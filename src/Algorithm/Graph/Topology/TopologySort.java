package Algorithm.Graph.Topology;

import DataStructure.AlgoUtils.DirectedGraphNode;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/5 19:45
 *   @Description :
 *      Given an directed graph, a topological order of the graph nodes is defined as follow:
        For each directed edge A -> B in graph, A must before B in the order list.
        The first node in the order can be any node in the graph with no nodes direct to it.
        Find any topological order for the given graph
 */
public class TopologySort {

    // We should find a way to traverse the whole graph
    // DFS , Time: O(n), each node will only visit once and Space: O(n) worst case
    public List<DirectedGraphNode> topologySortDFS(ArrayList<DirectedGraphNode> graph){
        if(graph == null || graph.size() == 0){
            return new ArrayList<>();
        }
        int n = graph.size();
        List<DirectedGraphNode> result = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for(DirectedGraphNode node : graph){
            if(!visited[node.value]){
                dfs(result, visited, node);
            }
        }
        Collections.reverse(result);
        return result;
    }
    private void dfs(List<DirectedGraphNode> result, boolean[] visited, DirectedGraphNode node){
        // Mark as visited
        visited[node.value] = true;
        for(DirectedGraphNode nei : node.neighbors){
            if(!visited[nei.value]){
                dfs(result, visited, nei);
            }
        }
        result.add(node);
    }
    // BFS is also consume O(n) time and O(n) space in worst case
    public List<DirectedGraphNode> topologySortBFS(ArrayList<DirectedGraphNode> graph){
        // corner case
        if(graph == null || graph.size() == 0){
            return new ArrayList<>();
        }
        List<DirectedGraphNode> result = new ArrayList<>();
        HashMap<DirectedGraphNode, Integer> map = new HashMap<>();
        // construct the graph and find the entrance
        for(DirectedGraphNode node:graph){
            for(DirectedGraphNode nei:node.neighbors){
                map.put(nei, map.getOrDefault(nei, 0) + 1);
            }
        }
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for(DirectedGraphNode node:graph){
            if(!map.containsKey(node)){
                // find the starting point
                queue.offer(node);
                result.add(node);
            }
        }
        // start bfs
        // slightly difference between normal bfs, guarantee we generate every node once
        while(!queue.isEmpty()){
            DirectedGraphNode cur = queue.poll();
            for(DirectedGraphNode n : cur.neighbors){
                map.put(n, map.get(n) - 1);
                if(map.get(n) == 0){
                    // means that this is the last record
                    result.add(n);
                    queue.offer(n);
                }
            }
        }
        return result;

    }
}
