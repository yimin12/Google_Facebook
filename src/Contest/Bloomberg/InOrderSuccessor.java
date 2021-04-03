package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/2 23:49
 *   @Description :
 *
 */
public class InOrderSuccessor {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node inorderSuccessor(Node node) {
        if(node == null) return null;
        if(node.right != null){
            node = node.right;
            while(node != null && node.left != null){
                node = node.left;
            }
            return node;
        }
        while(node != null){
            if(node.parent == null) return null;
            if(node.parent.left == node){
                return node.parent;
            } else {
                node = node.parent;
            }
        }
        return node;
    }
}
