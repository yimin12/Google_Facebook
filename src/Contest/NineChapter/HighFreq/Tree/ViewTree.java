package Contest.NineChapter.HighFreq.Tree;

import DataStructure.AlgoUtils.TreeNode;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/14 15:59
 *   @Description :
    *	Examples:  	the right view =  [1, 3, 7, 8, 11]
                   1
                /    \
               2      3
              / \    /  \
             4   5   6  7
            /            \
            9             8
          /  \
         10  11
 */
public class ViewTree {

    // Right view with dfs
    public List<Integer> rightViewDFS(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return  res;
        }
        dfsRight(root, res, 0);
        return res;
    }
    private void dfsRight(TreeNode root, List<Integer> res, int level){
        if(root == null){
            return;
        }
        if(level == res.size()){
            res.add(root.val);
        }
        dfsRight(root.right, res, level + 1);
        dfsRight(root.left, res, level + 1);
    }
    // Right view with bfs
    public List<Integer> leftViewBFS(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(i == size - 1){
                    res.add(cur.val);
                }
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }

    // Symmetric problem, left view with bfs and dfs

    // Follow Up: Pre Order , In Order , Post Order, Level Order, ZigZag Order, All situation should be Time: O(n) and O(n) for storing result
    // PreOrder Iterative traversal
    public List<Integer> preOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollFirst();
            if(cur.right != null){
                stack.offerFirst(cur.right);
            }
            if(cur.left != null){
                stack.offerFirst(cur.left);
            }
            res.add(cur.val);
        }
        return res;
    }

    // Use Stack to do inorder traverse, iterative traverse
    public List<Integer> inOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = stack.pollFirst();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    // Post Order Iterative traversal,
    // Method 1: post order is the reverse of pre-order with traversing right-subtree before traversing left tree
    public List<Integer> postOrder(TreeNode root){
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.offer(root);
        // step 1: pre order traverse and start with right subtree
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            if(cur.left != null) {
                stack.offerFirst(cur.left);
            }
            if(cur.right != null) {
                stack.offerFirst(cur.right);
            }
            res.add(cur.val);
        }
        Collections.reverse(res);
        // step 2: reverse the result
        return res;
    }

    // Method 2: PostOrder
    public List<Integer> postOrderII(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        TreeNode prev = null;
        while(!stack.isEmpty()){
            TreeNode cur = stack.peekFirst();
            if(prev == null || cur == prev.left || cur == prev.right){
                if(cur.left != null){
                    stack.offerFirst(cur.left);
                } else if(cur.right != null){
                    stack.offerFirst(cur.right);
                } else {
                    stack.pollFirst();
                    result.add(cur.val);
                }
            } else if(prev == cur.left && cur.right == null){
                stack.pollFirst();
                result.add(cur.val);
            } else {
                stack.offerFirst(cur.right);
            }
        }
        return result;
    }
    // Level Order : Looking from top to bottom,bfs
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offerFirst(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curRes = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.pollLast();
                if(cur.left != null) {
                    queue.offerFirst(cur.left);
                }
                if(cur.right != null) {
                    queue.offerFirst(cur.right);
                }
                curRes.add(cur.val);
            }
            res.add(curRes);
        }
        return res;
    }
    // Level Order: dfs Time: O(n) Extra Space: O(height)
    public List<List<Integer>> levelOrderII(TreeNode root){
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelOrderDFS(root, 0, res);
        return res;
    }
    private void levelOrderDFS(TreeNode root, int depth, List<List<Integer>> res) {
        if(root == null) return;
        if(depth == res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(depth).add(root.val);
        levelOrderDFS(root.left,depth+1,res);
        levelOrderDFS(root.right, depth+1, res);
    }

    // ZigZag : Assume that we print it from left to right at odd level and print it from right to left in
    public List<List<Integer>> zigZagOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            flag = !flag;
            List<Integer> cur = new ArrayList<>();
            for(int i = 0; i < size; i ++){
                TreeNode node;
                if(flag){
                    node = queue.pollFirst();
                }	else {
                    node = queue.pollLast();
                }
                cur.add(node.val);
                if(flag){
                    if(node.left != null){
                        queue.offerLast(node.left);
                    }
                    if(node.right != null){
                        queue.offerLast(node.right);
                    }
                } else {
                    if(node.right != null){
                        queue.offerFirst(node.right);
                    }
                    if(node.left != null){
                        queue.offerFirst(node.left);
                    }
                }
            }
            res.add(cur);
        }
        return res;
    }
    // Vertical View : Given a binary tree, return the vertical order traversal of its nodes' values.
    // (ie, from top to bottom, column by column). If two nodes are in the same row and column, the order should be from left to right.
    //	Input:  [3,9,20,null,null,15,7]
//			   3
//			  /\
//			 /  \
//			 9  20
//			    /\
//			   /  \
//			  15   7
//
//
//			Output:
//
//
//			[
//			  [9],
//			  [3,15],
//			  [20],
//			  [7]
//			]
    // Key Insight: The structure should be similar with Pascal Triangle
    // Time: O(n)  Space : Extra Space for (3*n)
    public List<List<Integer>> verticalOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        queue.add(root);
        cols.add(0);
        int min = 0, max = 0;
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            int col = cols.poll();
            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(cur.val);
            if(cur.left != null){
                queue.offer(cur.left);
                cols.offer(col - 1);
                min = Math.min(min, col - 1);
            }
            if(cur.right != null){
                queue.offer(cur.right);
                cols.offer(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        for(int i = min; i <= max; i++){
            res.add(map.get(i));
        }
        return res;
    }

    /**
     * Boundary View
     */
    List<Integer> res = new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if(root == null) return res;
        res.add(root.val);
        dfs(root.left, true, false);
        dfs(root.right, false, true);
        return res;
    }

    private void dfs(TreeNode root, boolean leftBoundary, boolean rightBoundary){
        if(root == null) return;
        // leaf node should be boundary
        if(root.left == null && root.right == null){
            res.add(root.val);
            return;
        }
        if(leftBoundary) {
            res.add(root.val);
        }
        dfs(root.left, leftBoundary, rightBoundary && root.right == null);
        dfs(root.right, leftBoundary && root.left == null, rightBoundary);
        if(rightBoundary){
            res.add(root.val);
        }
    }

    public static void main(String[] args) {

    }
}
