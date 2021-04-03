package Algorithm.DynamicProgramming.StatefulDP;

import DataStructure.AlgoUtils.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/12 20:58
 *   @Description :
 *      You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint
 *		stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police
 *		if two adjacent houses were broken into on the same night
 *		Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob
 *		tonight without alerting the police.
 *
 *		Input: [1,2,3,1]
 *		Output: 4
 *		Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).  Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobber {

    public int robI(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        if(array.length == 1) return array[0];
        int[] dp = new int[array.length];
        dp[0] = array[0];
        dp[1] = Math.max(array[0], array[1]);
        for(int i = 2; i <= array.length; i++){
            dp[i] = Math.max(dp[i-1], dp[i - 2] + array[i]);
        }
        return dp[array.length - 1];
    }
    // rolling array
    public int robIrolling(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int[] dp = new int[4];
        // base case
        dp[0] = 0;
        dp[1] = array[0];
        dp[2] = Math.max(dp[0] + array[1], dp[1]);
        for(int i = 3; i <= array.length; i++){
            dp[i % 3] = Math.max(dp[(i-2)%3] + array[i - 1], dp[(i-1)%3]);
        }
        return dp[array.length];
    }
    // Follow Up 2: Houses arranged in a Circle
    // After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not
    // get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of
    // the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
    // 1-d DP: Time ~ O(2N), Space ~ O(N)
    // If s[0] is robbed, then s[1] and s[N - 1] cannot be robbed, so the max1 = s[0] + max of s[2 .. N - 2]
    // If s[0] is not robbed, then s[1] and s[N - 1] can be robbed, so the max2 = max of s[1 .. N - 1].
    public int robII(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int n = array.length;
        if(n == 1){
            return array[0];
        } else if(n == 2){
            return Math.max(array[0], array[1]);
        } else if(n == 3){
            return Math.max(array[1], array[0] + array[2]);
        }
        // case1 : rob the first house
        int first = robI(Arrays.copyOfRange(array,0, array.length - 1));
        int second = robI(Arrays.copyOfRange(array, 1, array.length));
        return Math.max(first, second);
    }
    // Follow Up 3: Houses arranged in Tree
    // The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root."
    // Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in
    // this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
    public int robIII(TreeNode root){
        if(root == null){
            return 0;
        }
        int val = 0;
        // if rob the root house
        if(root.left != null){
            val += robIII(root.left.left) + robIII(root.left.right);
        }
        if(root.right != null){
            val += robIII(root.right.left) + robIII(root.right.right);
        }
        return Math.max(root.val + val, robIII(root.left) + robIII(root.right));
    }

    // Memory Search
    public int robIV(TreeNode root){
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }
    private int[] dfs(TreeNode root){
        if(root == null){
            return new int[2];
        }
        // create two pair of tuple
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
    // Method 2: Think one step further, by pruning the identical subproblems
    public int robOpt(TreeNode root) {
        return robOpt(root, new HashMap<>());
    }
    private int robOpt(TreeNode root, HashMap<TreeNode, Integer> map) {
        // base case :
        if(root == null) return 0;
        if(map.containsKey(root)) return map.get(root);
        int val = 0;
        if(root.left != null) {
            val += robOpt(root.left.left, map) + robOpt(root.left.right, map);
        }
        if(root.right != null) {
            val += robOpt(root.right.left, map) + robOpt(root.right.right, map);
        }
        val = Math.max(val + root.val, robOpt(root.left, map) + robOpt(root.right, map));
        map.put(root, val);
        return val;
    }
}
