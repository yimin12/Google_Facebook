package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/21 23:53
 *   @Description :
* 	    Given two nodes in a binary tree, find their lowest common ancestor.
        Assumptions:
	    There is no parent pointer for the nodes in the binary tree
	    The given two nodes are guaranteed to be in the binary tree
        Examples:
            5				The lowest common ancestor of 2 and 14 is 5
          /   \
         9    12			The lowest common ancestor of 2 and 9 is 9
        /  \    \
       2    3   14
 */
public class LowestCommonAncestor {

    static class TreeNodeP{
        int val;
        TreeNodeP parent, left, right;
        public TreeNodeP(int val){
            this.val = val;
        }
    }

    // Question 1: With Parent Pointer
    // Time: O(n) and Space: O(height)
    public TreeNodeP lowestCommonAncestorI(TreeNodeP one, TreeNodeP two){
        int l1 = getHeight(one);
        int l2 = getHeight(two);
        if(l1 < l2){
            return mergeNode(one, two, l2 - l1);
        }
        return mergeNode(two, one, l1 - l2);
    }

    private int getHeight(TreeNodeP node){
        int length = 0;
        while(node != null){
            node = node.parent;
            length++;
        }
        return length;
    }

    private TreeNodeP mergeNode(TreeNodeP shorter, TreeNodeP longer, int diff){
        while(diff > 0){
            longer = longer.parent;
            diff--;
        }
        while(longer != shorter){
            longer = longer.parent;
            shorter = shorter.parent;
        }
        return longer;
    }

    // Question 2: LCA without parent pointer
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode one, TreeNode two){
        if(root == null) return null;
        TreeNode lca = LCAI(root, one, two);
        // find out whether one node and two node are in same tree
        if(lca == one){
            TreeNode inATree = LCAI(one, two, null);
            return inATree == null ? null : lca;
        } else if(lca == two){
            TreeNode inATree = LCAI(two, one, null);
            return inATree == null ? null : lca;
        } else {
            return lca;
        }
    }

    private TreeNode LCAI(TreeNode root, TreeNode one, TreeNode two){
        if(root  == null){
            return null;
        }
        if(root == one || root == two){
            return root;
        }
        TreeNode ll = LCAI(root.left, one, two);
        TreeNode lr = LCAI(root.right, one, two);
        if(ll != null && lr != null){
            return root;
        }
        return ll == null ? lr : ll;
    }
}
