package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathSum {

    /**
     * Max path sum from root to leaf, might contain negative node
     */
    public int maxPathSumLeafRoot(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return root.val;
        if(root.left == null){
            return Math.max(0, maxPathSumLeafRoot(root.right)) + root.val;
        }
        if(root.right == null){
            return Math.max(0, maxPathSumLeafRoot(root.left)) + root.val;
        }
        return root.val + Math.max(0, Math.max(maxPathSumLeafRoot(root.left), maxPathSumLeafRoot(root.right)));
    }

    /**
     * Max path from any node to any node
     */
    public int maxPathSumAny(TreeNode root){
        int[] max = new int[] {Integer.MIN_VALUE};
        maxAny(root, max);
        return max[0];
    }

    private int maxAny(TreeNode root, int[] max){
        if(root == null) return 0;
        int left = maxAny(root.left, max);
        int right = maxAny(root.right, max);
        left = left < 0 ? 0 : left;
        right = right < 0 ? 0 : right;
        max[0] = Math.max(root.val + left + right, max[0]);
        return root.val + Math.max(left, right);
    }

    /**
     * Path sum equal to sum that path can only be from one node to itself or any of its descendent
     */
    public boolean pathSumToTarget(TreeNode root, int target){
        if(root == null) return false;
        Set<Integer> prefixSum = new HashSet<>();
        prefixSum.add(0);
        return pathSum(root, prefixSum, 0, target);
    }
    private boolean pathSum(TreeNode root, Set<Integer> pref, int sum, int t){
        sum += root.val;
        if(pref.contains(sum - t)){
            return true;
        }
        boolean reSet = pref.add(sum);
        if(root.left != null && pathSum(root.left, pref, sum, t)){
            return true;
        }
        if(root.right != null && pathSum(root.right, pref, sum, t)){
            return true;
        }
        if(reSet){
            // reset the state while backtracking
            pref.remove(sum);
        }
        return false;
    }

    /**
     * Path sum equal to sum that require from root to leaf
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        binaryTreePathSum(root, res, target, new ArrayList<>());
        return res;
    }

    private void binaryTreePathSum(TreeNode root, List<List<Integer>> res, int target, ArrayList<Integer> path){
        if(root == null){
            return;
        }
        path.add(root.val);
        if(root.left == null && root.right == null && target == root.val){
            res.add(new ArrayList<>(path));
        }
        binaryTreePathSum(root.left, res, target - root.val, path);
        binaryTreePathSum(root.right, res, target - root.val, path);
        path.remove(path.size() - 1);
    }

    /**
     * Path sum equal to sum that path can only be from one node to itself or any of its descendent, return the path
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            helperPathSum(root, target, new ArrayList<Integer>(), result);
        }
        return result;
    }

    private void helperPathSum(TreeNode root, int target, List<Integer> path, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        int sum = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            sum += path.get(i);
            if (sum == target) {
                result.add(new ArrayList<Integer>(path.subList(i, path.size())));
            }
        }
        helperPathSum(root.left, target, path, result);
        helperPathSum(root.right, target, path, result);
        path.remove(path.size() - 1);
    }

    /**
     * Path sum equal to sum that path can from any node to any node, return the path
     */
    class ParentTreeNode {
        public int val;
        public ParentTreeNode parent, left, right;
    }

    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        List<ParentTreeNode> nodes = new ArrayList<>();
        inOrder(root, nodes);
        for(ParentTreeNode node : nodes){
            Set<ParentTreeNode> set = new HashSet<>();
            dfs(node, target, set, new ArrayList<>(), res);
        }
        return res;
    }

    private void inOrder(ParentTreeNode root, List<ParentTreeNode> nodes){
        if(root == null){
            return;
        }
        inOrder(root.left, nodes);
        nodes.add(root);
        inOrder(root.right, nodes);
    }

    private void dfs(ParentTreeNode root, int target, Set<ParentTreeNode> set, List<Integer> path, List<List<Integer>> res){
        if(root == null || set.contains(root)) {
            return;
        }
        set.add(root);
        path.add(root.val);
        target -= root.val;
        if(target == 0) {
            List<Integer> copy = new ArrayList<>(path);
            res.add(copy);
        }
        dfs(root.left, target, set, path, res);
        dfs(root.right, target, set, path, res);
        dfs(root.parent, target, set, path, res);
        set.remove(root);
        path.remove(path.size() - 1);
    }
}
