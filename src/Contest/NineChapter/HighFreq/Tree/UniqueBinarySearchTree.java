package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Given n, how many structurally unique BSTs (binary search trees) that store values 1...n?
 *
 * Example
 * Example 1:
 *
 * Input:n = 3,
 * Output: 5
 * Explanation:there are a total of 5 unique BST's.
 * 1           3    3       2      1
 *  \         /    /       / \      \
 *   3      2     1       1   3      2
 *  /      /       \                  \
 * 2     1          2                  3
 */
public class UniqueBinarySearchTree {

    /**
     * f[i] represent i nodes in the bst
     * base case: f[0] = f[1] = 1, 0 node or 1 node should be counted as 1 bst
     * induction rule : f[2] = f[0]*f[1] + f[1][f0] (f[j] = sum(f[k] * f[l]) where k + l + 1 = j and k -> [0, j))
     * O(n) time and O(n) space for f table
     * @param n
     * @return
     */
    public int numTree(int n){
        if(n <= 1 ) return 1;
        int f[] = new int[n + 1];
        f[0] = f[1] = 1;
        for(int i = 2; i <= n; i ++){
            for(int j = 0; j < i; j ++){
                f[i] += f[j] * f[i - j - 1];
            }
        }
        return f[n];
    }

    /**
     * Generate all uniques bst
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if(n <= 0) {
            res.add(null);
            return res;
        }
        return generate(1, n);
    }

    private List<TreeNode> generate(int left, int right){
        List<TreeNode> cur = new ArrayList<>();
        if(left > right){
            cur.add(null);
            return cur;
        }

        for(int i = left; i <= right; i ++){
            // divide
            List<TreeNode> leftCur = generate(left, i - 1);
            List<TreeNode> rightCur = generate(i + 1, right);
            // conquer
            for(TreeNode l : leftCur){
                for(TreeNode r : rightCur){
                    TreeNode c = new TreeNode(i);
                    c.left = l;
                    c.right = r;
                    cur.add(c); // concat to be new sub trees for upper level
                }
            }
        }
        return cur;
    }
}
