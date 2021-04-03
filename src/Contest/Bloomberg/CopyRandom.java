package Contest.Bloomberg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 15:23
 *   @Description :
 *
 */
public class CopyRandom {


    // Copy the graph
    public GraphNode cloneGraph(GraphNode node) {
        if(node == null) return null;
        Map<GraphNode, GraphNode> map = new HashMap<>();
        map.put(node, new GraphNode(node.val));
        dfs(node, map);
        return map.get(node);
    }

    private void dfs(GraphNode node, Map<GraphNode, GraphNode> map){
        GraphNode copy = map.get(node);
        for(GraphNode nei : node.neighbors){
            if(!map.containsKey(nei)){
                map.put(nei, new GraphNode(nei.val));
                dfs(nei, map);
            }
            // add the neighbors while backtracking
            copy.neighbors.add(map.get(nei));
        }
    }

    // Copy random LinkedList
    public ListNode copyRandomList(ListNode head) {
        if(head == null) return head;
        // key : original , value = copied node
        Map<ListNode, ListNode> map = new HashMap<>();
        ListNode dummy = new ListNode(-1), cur = dummy;
        while(head != null){
            if(!map.containsKey(head)){
                map.put(head, new ListNode(head.val));
            }
            cur.next = map.get(head); // connect by dummy list;
            if(head.random != null){
                map.putIfAbsent(head.random, new ListNode(head.random.val));
                cur.next.random = map.get(head.random);
            }
            head = head.next;
            cur = cur.next;
        }
        return dummy.next;

    }

    // Copy random binary tree
    public Node copyRandomBinaryTree(Node root){
        Map<Node, Node> map = new HashMap<>();
        return dfsTree(root, map);
    }

    private Node dfsTree(Node root, Map<Node, Node> map){
        if(root == null) return null;
        if(map.containsKey(root)){
            return map.get(root);
        }
        Node copy = new Node(root.val);
        map.put(root, copy);

        copy.left = dfsTree(root.left, map);
        copy.right = dfsTree(root.right, map);
        copy.random = dfsTree(root.random, map);
        return copy;
    }

    class Node {
        int val;
        public Node left;
        public Node right;
        public Node random;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node left, Node right, Node random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode random;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class GraphNode {
        public int val;
        public List<GraphNode> neighbors;
        public GraphNode() {
            val = 0;
            neighbors = new ArrayList<GraphNode>();
        }
        public GraphNode(int _val) {
            val = _val;
            neighbors = new ArrayList<GraphNode>();
        }
        public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
