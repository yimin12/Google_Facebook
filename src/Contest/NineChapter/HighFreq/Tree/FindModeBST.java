package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * If a tree has more than one mode, you can return them in any order.
 *
 * Example
 * Example 1:
 *
 * Input:
 * {1,#,2,2}
 * Output:
 * [2]
 *
 * Explanation:
 * 1
 *  \
 *   2
 *  /
 * 2
 */
public class FindModeBST {

    Map<Integer, Integer> map;
    int max = 0;
    public int[] findMode(TreeNode root){
        if(root == null) return new int[0];
        this.map = new HashMap<>();
        inOrder(root); // traverse the tree and get the frequency of each element
        List<Integer> list = new LinkedList<>();
        for(int key : map.keySet()){
            if(map.get(key) == max) list.add(key);
        }
        int[] res = new int[list.size()];
        for(int i = 0; i < res.length; i ++){
            res[i] = list.get(i);
        }
        return res;
    }

    private void inOrder(TreeNode root){
        if(root.left != null) inOrder(root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        max = Math.max(max, map.get(root.val));
        if(root.right != null) inOrder(root.right);
    }

    /**
     * Description
     * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
     *
     * You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
     *
     * Example
     * Example 1:
     *
     * Input:
     * {5,2,-3}
     * Output:
     * [-3,2,4]
     * Explanation:
     *   5
     *  /  \
     * 2   -3
     * since all the values happen only once, return all of them in any order.
     */
    private int maxFreq = 0;
    private int count = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        traverse(root, map);
        int[] res = new int[count];
        int i = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == maxFreq){
                res[i++] = entry.getKey();
            }
        }
        return res;
    }

    private int traverse(TreeNode root, Map<Integer, Integer> map){
        if(root == null) return 0;
        int left = traverse(root.left, map);
        int right = traverse(root.right, map);
        int sum = left + right + root.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        if(map.get(sum) > maxFreq){
            maxFreq = map.get(sum);
            count = 1;
        } else if(map.get(sum) == maxFreq){
            count ++;
        }
        return sum;
    }
}
