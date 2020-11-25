package Algorithm.Graph.Tree.Basic;

import DataStructure.AlgoUtils.TreeNode;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/18 23:07
 *   @Description :
 *
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
The first data is the root node, followed by the value of the left and right son nodes, and "#" indicates that there is no child node.
The number of nodes does not exceed 20.
Example 1:

Input：{1,2,3}
Output：[[1],[2,3]]
Explanation：
  1
 / \
2   3
it will be serialized {1,2,3}
level order traversal
Example 2:

Input：{1,#,2,3}
Output：[[1],[2],[3]]
Explanation：
1
 \
  2
 /
3
it will be serialized {1,#,2,3}
level order traversal
 */
public class LevelOrder {

    // BFS
    public List<List<Integer>> levelOrderI(TreeNode root) {
        List result = new ArrayList();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            result.add(level);
        }
        return result;
    }
    // DFS
    public List<List<Integer>> levelOrderDFS(TreeNode root){
        List<List<Integer>> results = new ArrayList<>();
        if(root == null){
            return results;
        }
        int maxLevel = 0;
        while(true){
            List<Integer> level = new ArrayList<Integer>();
            dfs(root, level, 0, maxLevel);
            if(level.size() == 0) break;
            results.add(level);
            maxLevel++;
        }
        return results;
    }
    private void dfs(TreeNode root, List<Integer> level, int cur, int max){
        // base case
        if(root == null || cur > max){
            return;
        }
        if(cur == max){
            level.add(root.val);
            return;
        }
        dfs(root.left, level, cur + 1, max);
        dfs(root.right, level, cur + 1, max);
    }


    // From upside down
    public List<List<Integer>> levelOrderUpDown(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> curList = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                curList.add(cur.val);
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            res.add(curList);
        }
        return res;
    }
    // Follow Up: From down to Up
    public List<List<Integer>> levelOrderDownUp(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> curList = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                curList.add(cur.val);
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            res.add(curList);
        }
        Collections.reverse(res);
        return res;
    }
}
