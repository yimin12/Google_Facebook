package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Given a binary search tree and a range [k1, k2], return node values within a given range in ascending order.
 *
 * Example
 * Example 1:
 *
 * Input:
 *
 * tree = {5}
 * k1 = 6
 * k2 = 10
 * Output:
 *
 * []
 * Explanation:
 *
 * No number between 6 and 10
 */
public class RangeInBST {


    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        dfs(root, k1, k2, res);
        return res;
    }

    private void dfs(TreeNode root, int k1, int k2, List<Integer> res){
        if(root == null) return;
        if(root.val < k1){
            dfs(root.right, k1, k2, res);
        } else if(root.val > k2){
            dfs(root.left, k1, k2, res);
        } else {
            dfs(root.left, k1, k2, res);
            res.add(root.val);
            dfs(root.right, k1, k2, res);
        }
    }
}
