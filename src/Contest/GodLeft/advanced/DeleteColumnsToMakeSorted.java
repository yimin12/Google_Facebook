package Contest.GodLeft.advanced;

import java.util.ArrayList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 0:21
 *   @Description :
 *
 */

/**
 * 给定一个字符串类型的数组strs，其中每个字符串的长度都一样。你的目标是让每一个 字符串都变成从左往右字符不降序的样子。你可以删掉所有字符串中i位置上的字符，
 * 但是一旦决定执行这个删除将对所有字符串生效。请返回操作的最少执行次数。
 */
public class DeleteColumnsToMakeSorted {

    public static int minDeletionSize(String[] strs){
        if(strs == null || strs.length == 0 || strs[0] == null || strs[0].length() == 0){
            return 0;
        }
        int num = strs.length;
        int len = strs[0].length();
        char[][] board = new char[len][num];
        for(int i = 0; i < num; i ++){
            char[] str = strs[i].toCharArray();
            for(int j = 0; j  < len; j ++){
                board[j][i] = str[j];
            }
        }
        int[] dp = new int[len];
        dp[0] = 1;
        ArrayList<ArrayList<char[]>> ends = new ArrayList<>();
        for(int i = 0; i < len; i ++){
            ends.add(new ArrayList<>());
        }
        ends.get(0).add(board[0]);
        int right = 0;
        int res = len - 1;
        for(int j = 1; j < len; j ++){
            int l = 0, r = right;
            while(l <= r){
                int m = l + (r - l)/2;
                if(noMore(ends.get(m), board[j])){
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends.get(l).add(board[j]);
            dp[j] = l + 1;
            res = Math.min(res, len - dp[j]);
        }
        return res;
    }

    public static boolean noMore(ArrayList<char[]> pres, char[] cur){
        for(char[] pre:pres){
            int i = 0;
            for(; i < pre.length; i ++){
                if(pre[i] > cur[i]){
                    break;
                }
            }
            if(i == pre.length){
                return true;
            }
        }
        return false;
    }


    public static String random(int len, int varible) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * varible) + 'a');
        }
        return String.valueOf(str);
    }

    public static String[] randoms(int num, int maxLen, int varible) {
        int size = (int) (Math.random() * num) + 1;
        int len = (int) (Math.random() * maxLen) + 1;
        String[] strs = new String[size];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = random(len, varible);
        }
        return strs;
    }

    public static void printStringArray(String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
    }
    public static void main(String[] args) {
        String[] test = randoms(20000, 20000, 5);
        long start;
        long end;

        start = System.nanoTime();
        minDeletionSize(test);
        end = System.nanoTime();
        System.out.println((end - start) + " nano time");

    }
}
