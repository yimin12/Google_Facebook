package Algorithm.Graph.Tree.Medium;

import DataStructure.AlgoUtils.TreeNode;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 23:48
 *   @Description :
 *
 */
public class LowestCommonAncestor {

    static class TreeNodeP{
        int val;
        TreeNodeP parent;
        TreeNodeP left;
        TreeNodeP right;
        public TreeNodeP(int val) {
            this.val = val;
        }
    }

    // Follow Up 1: With Parent Pointer
    // Time: O(n)  and Space O(height) Extra space
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        int l1 = length(one);
        int l2 = length(two);
        if(l1 < l2) return mergeNode(one, two, l2 - l1);
        else return mergeNode(two, one, l1 - l2);
    }
    private TreeNodeP mergeNode(TreeNodeP shorter, TreeNodeP longer, int diff) {
        while(diff > 0) {
            longer = longer.parent;
            diff--;
        }
        while(longer != shorter) {
            longer = longer.parent;
            shorter = shorter.parent;
        }
        return longer;
    }
    private int length(TreeNodeP node) {
        int length = 0;
        while(node != null) {
            node = node.parent;
            length++;
        }
        return length;
    }

    // Follow Up 2: Common way without parent pointer, and validate whether these trees are in the same tree
    // Time: O(n)  and Space O(height) Extra space
    public TreeNode LCAI(TreeNode root, TreeNode one, TreeNode two) {
        if(root == null) return null;

        TreeNode lca = lowestCommonAncesoterNoParent(root, one, two);
        // to find out whether one node is in the other node's subtree
        if(lca == one) {
            TreeNode inATree = lowestCommonAncesoterNoParent(one, two, null);
            return inATree == null ? null : lca;
        } else if(lca == two) {
            TreeNode inATree = lowestCommonAncesoterNoParent(two, one, null);
            return inATree == null ? null : lca;
        } else {
            return lca;
        }
    }
    private TreeNode lowestCommonAncesoterNoParent(TreeNode root, TreeNode one, TreeNode two) {
        if(root == null) {
            return null;
        }
        // base case
        if(root == one || root == two) {
            return root;
        }
        // recursion rule
        TreeNode ll = lowestCommonAncesoterNoParent(root.left, one, two);
        TreeNode lr = lowestCommonAncesoterNoParent(root.right, one, two);
        if(ll != null && lr != null) {
            return root;
        }
        return ll == null ? lr : ll;
    }

    // Follow Up 3: find K nodes in binary tree
    // Time: O(n)  and Space O(height) Extra space
    public TreeNode LCAIV(TreeNode root, List<TreeNode> nodes) {
        if(root == null) return null;
        Set<TreeNode> set = new HashSet<TreeNode>(nodes);
        return helperI(root, set);
    }
    private TreeNode helperI(TreeNode root, Set<TreeNode> set) {
        if(root == null) return null;
        if(set.contains(root)) {
            return root;
        }
        TreeNode l = helperI(root.left, set);
        TreeNode r = helperI(root.right, set);
        if(l != null && r != null) {
            return root;
        }
        return l != null ? l : r;
    }

    static class TreeNodeK{
        int val;
        List<TreeNodeK> subchildre;
        public TreeNodeK(int val) {
            this.val = val;
            this.subchildre = new ArrayList<TreeNodeK>();
        }
    }

    // Follow Up 4: find two nodes in k-nary tree
    // Time: O(n)  and Space O(height) Extra space
    public TreeNodeK LCAIII(TreeNodeK root, TreeNodeK one, TreeNodeK two) {
        if(root == null) return null;
        return helperII(root, one, two);
    }
    private TreeNodeK helperII(TreeNodeK root, TreeNodeK one, TreeNodeK two) {
        if(root == null) return null;
        if(root == one || root == two) {
            return root;
        }
        int count = 0;
        TreeNodeK temp = null;
        for(TreeNodeK child:root.subchildre) {
            TreeNodeK result = helperII(child, one, two);
            if(result != null) {
                count++;
                temp = result;
            }
        }
        if(count == 2) return root;
        return temp;
    }

    // Follow Up 5: find k nodes in k-nary tree
    // Time: O(n)  and Space O(height) Extra space
    public TreeNodeK LCAV(TreeNodeK root, List<TreeNodeK> nodes) {
        if(root == null) return null;
        Set<TreeNodeK> set = new HashSet<TreeNodeK>(nodes);
        return helperIII(root, set);
    }
    private TreeNodeK helperIII(TreeNodeK root, Set<TreeNodeK> set) {
        if(root == null || set.contains(root)) {
            return root;
        }
        int count = 0;
        TreeNodeK temp = null;
        for(TreeNodeK child:root.subchildre) {
            TreeNodeK result = helperIII(child, set);
            if(result != null) {
                count++;
                if(count > 1){
                    return root;
                }
                temp = result; // handle the possible that only have one node found
            }
        }
        return temp;
    }

    // Follow Up 6: I. Lowest Common Ancestor of a Binary Search Tree:
    // DFS: Time ~ O(logN), Space ~ O(logN)
    // Key insight: The first valid nodes is the lowest common ancestor, because we traverse from root to leaf
    public TreeNode LCAVI(TreeNode root, TreeNode one, TreeNode two) {
        if(root.val < one.val && root.val < two.val) return LCAVI(root.right, one, two);
        else if (root.val > one.val && root.val > two.val) return LCAVI(root.left, one, two);
        else return root;
    }

}
