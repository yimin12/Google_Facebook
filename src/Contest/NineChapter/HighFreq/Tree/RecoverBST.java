package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.Stack;

/**
 * Description
 * In a binary search tree, (Only) two nodes are swapped. Find out these nodes and swap them. If there no node swapped, return original root of tree.
 *
 * Example
 * Example1
 *
 * Input: {4,5,2,1,3}
 * Output: {4,2,5,1,3}
 * Explanation:
 * Given a binary search tree:
 *     4
 *    / \
 *   5   2
 *  / \
 * 1   3
 * return
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 */
public class RecoverBST {

    private TreeNode one = null, two = null, last = new TreeNode(Integer.MIN_VALUE);
    public TreeNode bstSwappedNode(TreeNode root) {
        dfs(root);
        int temp = one.val;
        one.val = two.val;
        two.val = temp;
        return root;
    }

    private void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.left);
        if(one == null && root.val < last.val){
            one = last;
        }
        if(one != null && root.val < last.val){
            two = root;
        }
        last = root;
        dfs(root.right);
    }

    public TreeNode bstSwappedNodeIterative(TreeNode root) {
        // write your code here
        if (root == null) {
            return null;
        }

        TreeNode swapNode1 = null, swapNode2 = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root, last = null;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            // swap judge
            if (last != null && p.val < last.val) {
                if (swapNode1 == null && swapNode2 == null) {
                    // 第一次触法exchange judge，last是swapNode
                    // 此外，中序相邻点交换会使得 exchange judge 只触法一次
                    swapNode1 = last;
                    swapNode2 = p;  // 避免额外判定
                } else {
                    // 第二次触法exchange judge，p是swapNode
                    swapNode2 = p;
                    break;
                }
            }
            last = p;
            p = p.right;
        }

        // swap
        if (swapNode1 != null && swapNode2 != null) {
            int tmp = swapNode1.val;
            swapNode1.val = swapNode2.val;
            swapNode2.val = tmp;
        }

        return root;
    }
}
