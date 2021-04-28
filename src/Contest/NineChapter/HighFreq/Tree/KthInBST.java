package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class KthInBST {

    /**
     * Description
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     *
     * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
     *
     * Example
     * Example 1:
     *
     * Input：{1,#,2},2
     * Output：2
     * Explanation：
     * 	1
     * 	 \
     * 	  2
     * The second smallest element is 2.
     */
    private int smallest = 0, count = 0;
    public int kthSmallest(TreeNode root, int k) {
        smallest(root, k);
        return smallest;
    }

    private void smallest(TreeNode root, int k){
        if(root == null){
            return;
        }
        smallest(root.left, k);
        if(++ count == k){
            smallest = root.val;
        }
        smallest(root.right, k);
    }

    /**
     * Most nearest value of target in BST
     * Description
     * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
     *
     * Given target value is a floating point.
     * You are guaranteed to have only one unique value in the BST that is closest to the target.
     * Example
     * Example1
     *
     * Input: root = {5,4,9,2,#,8,10} and target = 6.124780
     * Output: 5
     * Explanation：
     * Binary tree {5,4,9,2,#,8,10},  denote the following structure:
     *         5
     *        / \
     *      4    9
     *     /    / \
     *    2    8  10
     * Example2
     *
     * Input: root = {3,2,4,1} and target = 4.142857
     * Output: 4
     * Explanation：
     * Binary tree {3,2,4,1},  denote the following structure:
     *      3
     *     / \
     *   2    4
     *  /
     * 1
     */
    public int closestValue(TreeNode root, double target) {
        if(root == null) return 0;
        TreeNode upper = root;
        TreeNode lower = root;
        while(root != null){
            if(root.val > target){
                upper = root;
                root = root.left;
            } else if(root.val < target){
                lower = root;
                root = root.right;
            } else {
                return root.val;
            }
        }
        if(Math.abs(upper.val - target) < Math.abs(lower.val - target)){
            return upper.val;
        }
        return lower.val;
    }

    /**
     * K nearest value of target
     * Description
     * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
     *
     * Given target value is a floating point.
     * You may assume k is always valid, that is: k ≤ total nodes.
     * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
     * Example
     * Example 1:
     *
     * Input:
     * {1}
     * 0.000000
     * 1
     * Output:
     * [1]
     * Explanation：
     * Binary tree {1},  denote the following structure:
     *  1
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
        Deque<TreeNode> next = new LinkedList<>();
        Deque<TreeNode> prev = new LinkedList<>();
        TreeNode node = root;
        while(node != null){
            if(node.val < target){
                prev.push(node);
                node = node.right;
            } else {
                next.push(node);
                node = node.left;
            }
        }
        List<Integer> res = new LinkedList<>();
        while(res.size() < k){
            double dist_p = prev.isEmpty() ? Integer.MAX_VALUE : Math.abs(prev.peek().val - target);
            double dist_n = next.isEmpty() ? Integer.MAX_VALUE : Math.abs(next.peek().val - target);
            if(dist_p < dist_n){
                res.add(0, prev.peek().val);
                backward(prev);
            } else {
                res.add(next.peek().val);
                forward(next);
            }
        }
        return res;
    }

    private void backward(Deque<TreeNode> prev){
        TreeNode cur = prev.pop().left;
        while(cur != null){
            prev.push(cur);
            cur = cur.right;
        }
    }

    private void forward(Deque<TreeNode> next){
        TreeNode cur = next.pop().right;
        while(cur != null){
            next.push(cur);
            cur = cur.left;
        }
    }

    /**
     * Description
     * Find the node with the largest value in the binary tree and return it.
     *
     * Example
     * Example 1:
     *
     * Input:
     * {1,-5,3,1,2,-4,-5}
     * Output: 3
     * Explanation:
     * The tree look like this:
     *      1
     *    /   \
     *  -5     3
     *  / \   /  \
     * 1   2 -4  -5
     */
    public TreeNode maxNode(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode max = root;
        if(root.left != null) {
            TreeNode left = maxNode(root.left);
            max = max.val > left.val ? max : left;
        }
        if(root.right != null){
            TreeNode right = maxNode(root.right);
            max = max.val > right.val ? max : right;
        }
        return max;
    }
}
