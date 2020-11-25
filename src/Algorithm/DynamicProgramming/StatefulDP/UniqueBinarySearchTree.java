package Algorithm.DynamicProgramming.StatefulDP;

import DataStructure.AlgoUtils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 14:08
 *   @Description :
  *		Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *		For example,
 *		Given n = 3, there are a total of 5 unique BST's
 * 		1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
 */
public class UniqueBinarySearchTree {

    // Solution 1: dynamic programming, logic is similar with cutting rope
    // dp[i] represent that there three nodes in the subtree.
    // base case : dp[0] = 1, dp[1] = 1, dp[2] = dp[0]dp[1] + dp[1]dp[0], dp[3] = dp[0]dp[2] + dp[1]dp[1] + dp[2]dp[0]
    // induction rule: dp[i] = sum(dp[k]dp[i-k-1]) where k = 0 to (i-1)
    // The essential process is: to build a tree, we need to pick a root node, then we need to know how many possible
    // left sub trees and right sub trees can be held under that node, finally multiply them.
    // To build a tree contains {1,2,3,4,5}. First we pick 1 as root, for the left sub tree, there are none; for the
    // right sub tree, we need count how many possible trees are there constructed from {2,3,4,5}, apparently it's the same
    // number as {1,2,3,4}. So the total number of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0] =1).
    // Similarly, root 2 has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has dp[0]*dp[4] = 14.
    // Finally sum the up and it's done.
    // 1-d DP: Time ~ O(N^2), Space ~ O(N)

    public int numsOfTree(int n){
        if( n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    // deserialize the number to BST
    public List<TreeNode> deserializedTrees(int n){
        return dfs(1, n);
    }
    private List<TreeNode> dfs(int left, int right){
        List<TreeNode> subs = new ArrayList<>();
        if(left > right){
            return subs;
        }
        if(left == right){
            subs.add(new TreeNode(left));
        }
        for(int k = left; k <= right; k++){
            List<TreeNode> leftSubs = dfs(left, k - 1);
            List<TreeNode> rightSubs = dfs(k + 1, right);
            for(TreeNode l : leftSubs){
                for(TreeNode r : rightSubs){
                    TreeNode cur = new TreeNode(k);
                    cur.left = l;
                    cur.right = r;
                    subs.add(cur);
                }
            }
        }
        return subs;
    }
}
