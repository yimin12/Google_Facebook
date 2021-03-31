package Algorithm.Graph.Tree.Basic;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/29 20:44
 *   @Description :
 *
 */
public class PopulatingRightPointersInTrees {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if(root == null) return root;
        helper(root);
        return root;
    }

    public void helper(Node root){
        if(root == null) return; // if node is null just return
        if(root.left != null){ // if left is not null then make left point to the right child
            root.left.next = root.right;
        }
        Node next = root.next;
        while(next != null && next.left == null && next.right == null){ //find proper next node with at least one child
            next = next.next;
        }
        if(next != null){ // if proper next node, start assigning next pointers to childs
            if(root.right != null){ // if right child is not null then assign next pointer to it
                if(next.left != null){
                    root.right.next = next.left;
                } else {
                    root.right.next = next.right;
                }
            } else if(root.left != null){ // if right is null then assign next pointer to the left child
                if(next.left != null){
                    root.left.next = next.left;
                } else {
                    root.left.next = next.right;
                }
            } else { // if both childs are null then just return
                return;
            }
        }
        helper(root.right); // recur to the right child first, because children of left child are going to point to the children of the right one
        helper(root.left); // finally recur to the left child
    }
}
