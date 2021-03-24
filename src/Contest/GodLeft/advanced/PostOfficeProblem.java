package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 23:42
 *   @Description :
 *
 */

import java.util.Arrays;

/**
 * 一条直线上有居民点，邮局只能建在居民点上。给定一个有序正数数组arr，每个值表示 居民点的一维坐标，再给定一个正数 num，表示邮局数量。选择num个居民点建立num个 邮局，使所有的居民点到最近邮局的总距离最短，返回最短的总距离
 */
public class PostOfficeProblem {

    public static int minDistances1(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int[][] w = new int[arr.length + 1][arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(i + j) / 2];
            }
        }
        int[][] dp = new int[num][arr.length];
        for (int j = 0; j != arr.length; j++) {
            dp[0][j] = w[0][j];
        }
        for (int i = 1; i < num; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + w[k + 1][j]);
                }
            }
        }
        return dp[num - 1][arr.length - 1];
    }

    public static int minDistances2(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int[][] w = new int[arr.length + 1][arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(i + j) / 2];
            }
        }
        int[][] dp = new int[num][arr.length];
        int[][] s = new int[num][arr.length];
        for (int j = 0; j != arr.length; j++) {
            dp[0][j] = w[0][j];
            s[0][j] = 0;
        }
        int minK = 0;
        int maxK = 0;
        int cur = 0;
        for (int i = 1; i < num; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                minK = s[i - 1][j];
                maxK = j == arr.length - 1 ? arr.length - 1 : s[i][j + 1];
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = minK; k <= maxK; k++) {
                    cur = dp[i - 1][k] + w[k + 1][j];
                    if (cur <= dp[i][j]) {
                        dp[i][j] = cur;
                        s[i][j] = k;
                    }
                }
            }
        }
        return dp[num - 1][arr.length - 1];
    }

    // for test
    public static int[] getSortedArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i != len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int[] arr = { -2, -1, 0, 1, 2, 1000 };
        int num = 2;
        System.out.println(minDistances1(arr, num));
        System.out.println(minDistances2(arr, num));

        int times = 100; // test time
        int len = 1000; // test array length
        int range = 2000; // every number in [0,range)
        int p = 50; // post office number
        long time1 = 0; // method1 all run time
        long time2 = 0;// method2 all run time
        long start = 0;
        long end = 0;
        int res1 = 0;
        int res2 = 0;
        for (int i = 0; i != times; i++) {
            arr = getSortedArray(len, range);
            start = System.currentTimeMillis();
            res1 = minDistances1(arr, p);
            end = System.currentTimeMillis();
            time1 += end - start;
            start = System.currentTimeMillis();
            res2 = minDistances2(arr, p);
            end = System.currentTimeMillis();
            time2 += end - start;
            if (res1 != res2) {
                printArray(arr);
                break;
            }
            if (i % 10 == 0) {
                System.out.print(". ");
            }
        }
        System.out.println();
        System.out.println("method1 all run time(ms): " + time1);
        System.out.println("method2 all run time(ms): " + time2);

    }
}
