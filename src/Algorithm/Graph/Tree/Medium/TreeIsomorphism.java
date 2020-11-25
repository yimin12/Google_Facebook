package Algorithm.Graph.Tree.Medium;

import java.lang.reflect.Array;
import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 22:45
 *   @Description :
 *      The graph isomorphism problem for general graphs can be quite difficult, however there exists an
 *      elegant solution to uniquely encode a graph if it is a tree. Here is a brilliant explanation with
 *      animations:
 *
 *      <p>http://webhome.cs.uvic.ca/~wendym/courses/582/16/notes/582_12_tree_can_form.pdf
 *
 *      <p>This implementation uses a breadth first search on an undirected graph to generate the tree's
 *      canonical encoding.
 *
 *      <p>Tested code against: https://uva.onlinejudge.org/external/124/p12489.pdf
 */
public class TreeIsomorphism {

    public List<List<Integer>> createEmptyTree(int n){
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i < n; i++){
            tree.add(new ArrayList<>());
        }
        return tree;
    }

    public void addUndirectedEdge(List<List<Integer>> tree, int from, int to){
        tree.get(from).add(to);
        tree.get(to).add(from);
    }

    private List<Integer> findTreeCenters(List<List<Integer>> tree){
        final int n = tree.size();
        int[] degrees = new int[n];
        // Find all leaf nodes
        List<Integer> leaves = new ArrayList<>();
        for(int i = 0;i < n; i++){
            List<Integer> edges = tree.get(i);
            degrees[i] = edges.size();
            if(degrees[i] <= 1) leaves.add(i);
        }
        int processedLeafs = leaves.size();

        /// Remove leaf nodes and decrease the degree of each node adding new leaf nodes progressively util only the center remain
        while(processedLeafs < n){
            List<Integer> newLeaves = new ArrayList<>();
            for(int node : leaves){
                for(int nei:tree.get(node)) if(--degrees[nei] == 1) newLeaves.add(nei);
            }
            processedLeafs += newLeaves.size();
            leaves = newLeaves;
        }
        return leaves;
    }

    // Encodes a tree as a string such that isomorphic tree also has same encoding
    public String encodeTree(List<List<Integer>> tree){
        if(tree == null || tree.size() == 0) return "";
        if(tree.size() == 1) return "()";
        final int n = tree.size();
        int root = findTreeCenters(tree).get(0);
        int[] degree = new int[n];
        int[] parent = new int[n];
        boolean[] visited = new boolean[n];
        List<Integer> leaves = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        visited[root] = true;
        parent[root] = -1; // unused
        queue.offer(root);

        // BFS to find all the nodes
        while(!queue.isEmpty()){
            int cur = queue.poll();
            List<Integer> edges = tree.get(cur);
            degree[cur] = edges.size();
            for(int next : edges){
                if(!visited[next]){
                    visited[next] = true;
                    parent[next] = cur;
                    queue.offer(next);
                }
            }
            if(degree[cur] == 1) leaves.add(cur);
        }

        List<Integer> newLeafs = new ArrayList<>();
        String[] map = new String[n];
        for(int i = 0; i < n; i++){
            visited[i] = false;
            map[i] = "()";
        }

        int treeSize = n;
        while(treeSize > 2){
            for(int leaf:leaves){
                visited[leaf] = true;
                int p = parent[leaf];
                if( --degree[p] == 1) newLeafs.add(p);
                treeSize--;
            }
            // Update parent labels
            for (int p : newLeafs) {

                List<String> labels = new ArrayList<>();
                for (int child : tree.get(p))
                    // Recall edges are bidirectional so we don't want to
                    // access the parent's parent here.
                    if (visited[child]) labels.add(map[child]);

                String parentInnerParentheses = map[p].substring(1, map[p].length() - 1);
                labels.add(parentInnerParentheses);

                Collections.sort(labels);
                map[p] = "(".concat(String.join("", labels)).concat(")");
            }

            leaves.clear();
            leaves.addAll(newLeafs);
            newLeafs.clear();
        }

        // Only one node remains and it holds the canonical form
        String l1 = map[leaves.get(0)];
        if (treeSize == 1) return l1;

        // Two nodes remain and we need to combine their labels
        String l2 = map[leaves.get(1)];
        return ((l1.compareTo(l2) < 0) ? (l1 + l2) : (l2 + l1));
    }

    public boolean treesAreIsomorphic(List<List<Integer>> tree1, List<List<Integer>> tree2) {
        return encodeTree(tree1).equals(encodeTree(tree2));
    }

}
