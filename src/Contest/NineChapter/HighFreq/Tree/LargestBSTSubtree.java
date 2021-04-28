package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

/**
 * Description
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 *
 * The subtree which you find must be a binary search tree(BST).
 * The subtree which you find must be a full binary tree.
 * A binary tree is full if each node is either a leaf or possesses exactly two childnodes.
 *
 * Example
 * Example 1:
 *
 * Input:
 * {10,5,15,1,8,#,7}
 * Output：
 * 3
 *
 * Explanation:
 *     10
 *     / \
 *    5  15
 *   / \   \
 *  1   8   7
 * The Largest BST Subtree in this case is :
 *    5
 *   / \
 *  1   8.
 * The return value is the subtree's size, which is 3.
 */
public class LargestBSTSubtree {

    class Node{
        int res, small, large;
        boolean isBST;
        public Node(){
            res = 0;
            isBST = true;
            small = Integer.MAX_VALUE;
            large = Integer.MIN_VALUE;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        return dfs(root).res;
    }

    private Node dfs(TreeNode root){
        if(root == null){
            return new Node();
        }
        Node cur = new Node();
        Node left = dfs(root.left);
        Node right = dfs(root.right);
        cur.small = Math.min(left.small, root.val);
        cur.large = Math.max(right.large, root.val);
        if(left.isBST && right.isBST && left.large <= root.val && right.small >= root.val){
            cur.res = left.res + right.res + 1;
            cur.isBST = true;
        } else {
            cur.res = Math.max(left.res, right.res);
            cur.isBST = false;
        }
        return cur;
    }

}
