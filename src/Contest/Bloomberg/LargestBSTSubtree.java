package Contest.Bloomberg;

import DataStructure.AlgoUtils.TreeNode;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 12:13
 *   @Description :
 *
 */
public class LargestBSTSubtree {

    class Message{
        int size, upper, lower;
        public Message(int size, int lower, int upper) {
            this.size = size;
            this.lower = lower;
            this.upper = upper;
        }
    }

    int max = 0;
    public int largestBSTSubtree(TreeNode root){
        if(root == null) return 0;
        traverse(root);
        return max;
    }

    private Message traverse(TreeNode root){
        if(root == null) return new Message(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        Message left = traverse(root.left);
        Message right = traverse(root.right);
        // valid while back tracking
        if(left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower){
            return new Message(-1, 0, 0);
        }
        int size = left.size + right.size + 1;
        max = Math.max(size, max);
        return new Message(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
}
