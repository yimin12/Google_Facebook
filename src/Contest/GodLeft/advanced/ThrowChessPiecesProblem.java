package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 20:59
 *   @Description :
 *
 */

/**
 * 一座大楼有 0~N 层，地面算作第 0 层，最高的一层为第 N 层。已知棋子从第 0 层掉落肯定 不会摔碎，从第 i 层掉落可能会摔碎，也可能不会摔碎(1≤i≤N)。给定整数 N 作为楼层数，
 * 再给定整数 K 作为棋子数，返 回如果想找到棋子不会摔碎的最高层数，即使在最差的情况下扔 的最少次数。一次只能扔一个棋子。 【举例】 N=10，K=1。 返回 10。因为只有 1 棵棋子，
 * 所以不得不从第 1 层开始一直试到第 10 层，在最差的情况 下，即第 10 层 是不会摔坏的最高层，最少也要扔 10 次。 N=3，K=2。 返回 2。先在 2 层扔 1 棵棋子，如果碎了，试第 1 层，
 * 如果没碎，试第 3 层。 N=105，K=2 返回 14。 第一个棋子先在 14 层扔，碎了则用仅存的一个棋子试 1~13。 若没碎，第一个棋子继续在 27 层扔，碎了则 用仅存的一个棋子试 15~26。
 * 若没碎，第一个棋子继续在 39 层扔，碎了则用仅存的一个棋子试 28~38。 若 没碎，第一个棋子继续在 50 层扔，碎了则用仅存的一个棋子试 40~49。 若没碎，第一个棋子继续在 60 层扔，
 * 碎了则用仅存的一个棋子试 51~59。 若没碎，第一个棋子继续在 69 层扔，碎了则用仅存的一个棋子试 61~68。 若没碎，第一个棋子继续在 77 层扔，碎了则用仅存的一个棋子试 70~76。 若没碎，
 * 第一个棋子继续在 84 层 扔，碎了则用仅存的一个棋子试 78~83。 若没碎，第一个棋子继续在 90 层扔，碎了则用仅存的一个棋子试 85~89。 若没碎，第一个棋子继续在 95 层扔，碎了则用仅存的一个棋子试 91~94。
 * 若没碎，第一个棋子继续 在 99 层扔，碎了则用仅存的一个棋子试 96~98。 若没碎，第一个棋子继续在 102 层扔，碎了则用仅存的一 个棋子试 100、101。 若没碎，第一个棋子继续在 104 层扔，
 * 碎了则用仅存的一个棋子试 103。 若没碎，第 一个棋子继续在 105 层扔，若到这一步还没碎，那么 105 便是结果。
 */
public class ThrowChessPiecesProblem {



    public static int dpSolution(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i != dp.length; i++) {
            for (int j = 2; j != dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k != i + 1; k++) {
                    min = Math.min(min, Math.max(dp[k - 1][j - 1], dp[i - k][j]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }

    public static int dpSolutionOpt(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        int[] cands = new int[kChess + 1];
        for (int i = 1; i != dp[0].length; i++) {
            dp[1][i] = 1;
            cands[i] = 1;
        }
        for (int i = 2; i < nLevel + 1; i++) {
            for (int j = kChess; j > 1; j--) {
                int min = Integer.MAX_VALUE;
                int minEnum = cands[j];
                int maxEnum = j == kChess ? i / 2 + 1 : cands[j + 1];
                for (int k = minEnum; k < maxEnum + 1; k++) {
                    int cur = Math.max(dp[k - 1][j - 1], dp[i - k][j]);
                    if (cur <= min) {
                        min = cur;
                        cands[j] = k;
                    }
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }

    public static int dpSolutionOptAdvance(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        int bsTimes = log2N(nLevel) + 1;
        if (kChess >= bsTimes) {
            return bsTimes;
        }
        int[] dp = new int[kChess];
        int res = 0;
        while (true) {
            res++;
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= nLevel) {
                    return res;
                }
            }
        }
    }

    public static int log2N(int n) {
        int res = -1;
        while (n != 0) {
            res++;
            n >>>= 1;
        }
        return res;
    }



    public static void main(String[] args) {
//        System.out.println(solution1(105, 2));
        System.out.println(dpSolution(105, 2));
//        System.out.println(solution3(21, 2));
        System.out.println(dpSolutionOpt(105, 2));
        System.out.println(dpSolutionOptAdvance(105, 2));
//
//        System.out.println("==============");
//
//        System.out.println(solution2(105, 2));
//        System.out.println(solution3(105, 2));
//        System.out.println(solution4(105, 2));
//        System.out.println(solution5(105, 2));
//
//        System.out.println("==============");
//
//        System.out.println(solution2(3000, 10));
//        System.out.println(solution3(3000, 10));
//        System.out.println(solution4(3000, 10));
//        System.out.println(solution5(3000, 10));
//
//        System.out.println("==============");
//
//        System.out.println(solution2(6884, 5));
//        System.out.println(solution3(6884, 5));
//        System.out.println(solution4(6884, 5));
//        System.out.println(solution5(6884, 5));
//
//        System.out.println("==============");
//
//        System.out.println(solution2(6885, 5));
//        System.out.println(solution3(6885, 5));
//        System.out.println(solution4(6885, 5));
//        System.out.println(solution5(6885, 5));
//
//        System.out.println("==============");
//
//        int nLevel = 100000000;
//        int kChess = 10;
//        long start = System.currentTimeMillis();
//        System.out.println(solution5(nLevel, kChess));
//        long end = System.currentTimeMillis();
//        System.out.println("cost time: " + (end - start) + " ms");

    }
}
