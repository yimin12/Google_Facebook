package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class VerifyTree {


    /**
     * Is Binary Search tree
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isBST(TreeNode node, long min, long max){
        if(node == null) return true;
        if(node.val <= min || node.val >= max){
            return false;
        }
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    /**
     * Is Complete binary tree
     */
    public boolean isCompleteTree(TreeNode root){
        if(root == null) return true;
        boolean flag = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur == null){
                flag = true;
            }
            if(cur != null){
                if(flag){
                    return false;
                }
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return true;
    }

    /**
     * Description
     * Given a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     *
     * Example
     * Example 1:
     *
     * Input: {1,2,2,3,4,4,3}
     * Output: true
     * Explanation:
     *          1
     *         / \
     *        2   2
     *       / \ / \
     *       3 4 4 3
     *
     * is a symmetric binary tree.
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root){
        if(root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode one, TreeNode two){
        if(one == null && two == null) return true;
        if(one == null || two == null) return false;
        if(one.val != two.val) return false;
        return isSymmetric(one.left, two.right) && isSymmetric(one.right, two.left);
    }

    /**
     * Description
     * Check if two binary trees are identical. Identical means the two binary trees have the same structure and every identical position has the same value.
     *
     * Example
     * Example 1:
     *
     * Input:{1,2,2,4},{1,2,2,4}
     * Output:true
     * Explanation:
     *         1                   1
     *        / \                 / \
     *       2   2   and         2   2
     *      /                   /
     *     4                   4
     *
     * are identical.
     * Example 2:
     *
     * Input:{1,2,3,4},{1,2,3,#,4}
     * Output:false
     * Explanation:
     *
     *         1                  1
     *        / \                / \
     *       2   3   and        2   3
     *      /                        \
     *     4                          4
     *
     * are not identical.
     */
    public boolean isSameTree(TreeNode one, TreeNode two){
        if(one == null && two == null) return true;
        if(one == null || two == null) return false;
        if(one.val != two.val) return false;
        return isSameTree(one.left, two.left) && isSameTree(one.right, two.right);
    }

    /**
     * Check if a given binary tree is balanced. A balanced binary tree is one in which the depths of every node’s left and right subtree differ by at most 1.
     * Examples
     *         5
     *       /    \
     *     3        8
     *   /   \        \
     * 1      4        11
     *
     * is balanced binary tree,
     *
     *         5
     *       /
     *     3
     *   /   \
     * 1      4
     *
     * is not balanced binary tree.
     */
    public boolean isBalanced(TreeNode root){
        if(root == null) return true;
        return balanced(root) != -1;
    }

    private int balanced(TreeNode root){
        if(root == null) return 0;
        int leftH = balanced(root.left);
        if(leftH == -1) return -1;
        int rightH = balanced(root.right);
        if(rightH == -1) return -1;
        if(Math.abs(leftH - rightH) > 1){
            return -1;
        }
        return Math.max(leftH, rightH) + 1;
    }

    /**
     * Determine whether two given binary trees are identical assuming any number of ‘tweak’s are allowed. A tweak is defined as a swap of the children of one node in the tree.
     * Examples
     *         5
     *       /    \
     *     3        8
     *   /   \
     * 1      4
     * and
     *         5
     *       /    \
     *     8        3
     *            /   \
     *           1     4
     *
     * the two binary trees are tweaked identical.
     */
    public boolean isTweeakedIdentical(TreeNode one, TreeNode two){
        if(one == null && two == null) return true;
        if(one == null || two == null) return false;
        if(one.val != two.val) return false;
        return (isTweeakedIdentical(one.left, two.left) && isTweeakedIdentical(one.right, two.right))
                || (isTweeakedIdentical(one.left, two.right) && isTweeakedIdentical(one.right, two.left));
    }

}
