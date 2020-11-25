package Algorithm.Graph.Tree.Medium;

import java.util.LinkedList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 23:48
 *   @Description :
 *
 */
public class LowestCommonAncestor {

    private TreeNodeP lca = null;
    private TreeNodeP root;

    public LowestCommonAncestor(TreeNodeP root){
        this.root = root;
    }

    public TreeNodeP lca(int id1, int id2){
        lca = null;
        helper(root, id1, id2);
        return lca;
    }

    private boolean helper(TreeNodeP node, int a, int b){
        if(node == null) return false;
        int count = 0;
        // check the parent node
        if(node.id() == a){
            count++;
        }
        if(node.id() == b){
            count++;
        }
        for(TreeNodeP child : node.children()){
            if(helper(child, a, b)){
                count++;
            }
        }
        if(count == 2){
            lca = node;
        }
        return count > 0;
    }

    private static class TreeNodeP{
        // number of nodes in the subtree. Computed when tree is built
        private int n;
        private int id;
        private TreeNodeP parent; // Contains parent node
        private List<TreeNodeP> children; // n-ary tree

        public TreeNodeP(int id){
            this(id, null);
        }

        public TreeNodeP(int id, TreeNodeP node){
            this.id = id;
            this.parent = node;
            children = new LinkedList<>();
        }

        public void addChildren(TreeNodeP... nodes){
            for(TreeNodeP node : nodes){
                this.children.add(node);
            }
        }

        public void setSize(int n) {
            this.n = n;
        }

        // Number of nodes in the subtree (including the node itself)
        public int size() {
            return n;
        }

        public int id() {
            return id;
        }

        public TreeNodeP parent() {
            return parent;
        }

        public List<TreeNodeP> children() {
            return children;
        }

        public static TreeNodeP rootTree(List<List<Integer>> graph, int rootId) {
            TreeNodeP root = new TreeNodeP(rootId);
            return buildTree(graph, root);
        }

        // Do dfs to construct rooted tree.
        private static TreeNodeP buildTree(List<List<Integer>> graph, TreeNodeP node) {
            int subtreeNodeCount = 1;
            for (int neighbor : graph.get(node.id())) {
                // Ignore adding an edge pointing back to parent.
                if (node.parent() != null && neighbor == node.parent().id()) {
                    continue;
                }

                TreeNodeP child = new TreeNodeP(neighbor, node);
                node.addChildren(child);

                buildTree(graph, child);
                subtreeNodeCount += child.size();
            }
            node.setSize(subtreeNodeCount);
            return node;
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }

}
