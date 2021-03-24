package Contest.GodLeft.advanced;

import java.util.Arrays;
import java.util.HashMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 16:15
 *   @Description :
 */

/**
 *  给定一个数组arr，如果其中有两个集合的累加和相等，并且两个集合使用的数没有相容 的部分（也就是arr中某数不能同时进这个两个集合），那么这两个集合叫作等累加和集 合对。返回等累加和集合对中，最大的累加和。
 *  举例： arr={1,2,3,6} {1,2}和{3}，是等累加和集合对 {1,2,3}和{6}，也是等累加和集合对 返回6
 */
public class TallestBillBoard {

    public static void main(String[] args) {
        int res = tallestBillboard(new int[]{1,2,3});
        System.out.println(res);
    }

    // Assume that the given array is sorted
    public static int tallestBillboard(int[] rods) {
        HashMap<Integer, Integer> dp = new HashMap<>(), cur; // 需要两个map来回调换
        dp.put(0, 0);
        for (int x : rods) {
            if (x != 0) {
                cur = new HashMap<>(dp);
                for (int d : cur.keySet()) {
                    int diffMore = cur.get(d);
                    dp.put(d + x, Math.max(diffMore, dp.getOrDefault(x + d, 0)));
                    int diffXD = dp.getOrDefault(Math.abs(x - d), 0);
                    if (d >= x) {
                        dp.put(d - x, Math.max(diffMore + x, diffXD));
                    } else {
                        dp.put(x - d, Math.max(diffMore + d, diffXD));
                    }
                }
            }
        }
        System.out.println(dp.toString());
        return dp.get(0);
    }
}
