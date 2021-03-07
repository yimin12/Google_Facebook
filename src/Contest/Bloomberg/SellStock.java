package Contest.Bloomberg;

import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/3 0:52
 *   @Description :
 *
 */
public class SellStock {
    // Q1: warm up
    public int bestSellI(int[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }
        int min = input[0];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < input.length; i++) {
            if (input[i] < min) {
                min = input[i];
            } else {
                res = Math.max(res, input[i] - min);
            }
        }
        return res;
    }

    // Q2: follow up1: greedy thought

    /**
     * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
     * However, you may not engage in multiple transactions at the same time (ie, if you already have the stock,
     * you must sell it before you buy again).
     */
    public int bestSellII(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit = 0;
        for (int i = 0; i < prices.length- 1; i++) {
            int cur = prices[i + 1] - prices[i];
            if (cur > 0) {
                profit += cur;
            }
        }
        return profit;
    }

    // Q3: follow up2:

    /**
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     */
    // Method 1:
    // Let dp(i) be the max profit when 1st transaction is in [0, i] and 2nd transaction is in [i, N - 1].
    // dp(i) = f(i) + g(i) where f(i) = max{f(i - 1), p[i] - p_min} and g(i) = max{g(i + 1), p_max - p[i]}
    public int bestSellIII(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[] f = new int[n]; // first transaction
        int[] g = new int[n];
        // forward
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(prices[i], min);
            // sell or not sell
            f[i] = Math.max(f[i - 1], prices[i] - min);
        }
        // backward
        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);
            g[i] = Math.max(g[i + 1], max - prices[i]);
        }
        int profit = 0;
        for (int i = 0; i < n; i++) {
            profit = Math.max(g[i] + f[i], profit);
        }
        return profit;
    }

    // Method2: Totally 2 transactions. Contains 5 phases: before first purchase, on-hold, after sell and before second purchase, on-hold, after second sell
    public int bestSellIV(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n + 1][5 + 1]; // col: 2 * (times + 1)
        // base case
        for (int i = 0; i <= 5; i++) {
            if (i == 0) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = Integer.MIN_VALUE;
            }
        }
        // induction rule
        for (int i = 1; i <= n; i++) {
            // for phase 1.3.5 f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}
            for (int j = 1; j <= 5; j += 2) {
                // yesterday has became phase j or phase j - 1 (no stock phase)
                if (j > 1 && i > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            // for phase 2.4 f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1]} (have stock)
            for (int j = 2; j <= 5; j += 2) {
                if (i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
            }
        }
        int res = 0;
        for (int j = 1; j <= 5; j += 2) {
            res = Math.max(res, dp[n][j]);
        }
        return res;
    }

    // Q4: follow 3:

    /**
     * You may complete at most k transactions. What's the maximum profit?
     */
    // Logic is the same as the 2nd method of Q3
    public int bestSellV(int[] prices, int K) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        int i, j, k;
        if (K > n / 2) {
            // best time to buy and sell stock ii
            int tmp = 0;
            for (i = 0; i < n - 1; ++i) {
                tmp += Math.max(0, prices[i + 1] - prices[i]);
            }

            return tmp;
        }

        int[][] f = new int[n + 1][2 * K + 1 + 1];
        for (k = 1; k <= 2 * K + 1; ++k) {
            f[0][k] = Integer.MIN_VALUE; // impossible
        }

        f[0][1] = 0;
        for (i = 1; i <= n; ++i) {
            // 阶段1, 3, .. 2 * K + 1: f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}
            for (j = 1; j <= 2 * K + 1; j += 2) {
                f[i][j] = f[i - 1][j];
                if (j > 1 && i > 1 && f[i - 1][j - 1] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }

            // 阶段2, 4.., 2K: f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1]}
            for (j = 2; j <= 2 * K + 1; j += 2) {
                f[i][j] = f[i - 1][j - 1];
                if (i > 1 && f[i - 1][j] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
            }
        }

        int res = 0;
        for (j = 1; j <= 2 * K + 1; j += 2) {
            res = Math.max(res, f[n][j]);
        }

        return res;
    }

    // Method 2: https://www.jiuzhang.com/solutions/best-time-to-buy-and-sell-stock-iv/
    // i: have made i transactions, j : using j days
    // we record two states : keep the stack and sell the sock
    //      by enumerating all days to find what day is the best day to buy stock
    // dp[i][j] = Math.max(dp[i][j-1] keep, price[j] - price[k] + dp[i-1][k] where k : 1,2,...j-1) O(n^2*k)
    // optimize the time complexity by recording the maxDiff to O(n*k), Space: O(n*k)
    public int maxProfit(int K, int[] prices) {
        int n = prices.length;
        // corner case
        if (n == 0 || K == 0) {
            return 0;
        }
        // corner case: equal to infinity times of transaction
        if (K >= n / 2) {
            int res = 0;
            for (int i = 1; i < n; i++) {
                res += Math.max(0, prices[i] - prices[i - 1]);
            }
            return res;
        }
        // main part
        int[][] dp = new int[K + 1][n];
        for (int i = 1; i < dp.length; i++) {
            int maxDiff = -prices[0]; // 0 - price[0] : one purchase with no sell
            for (int j = 1; j < dp[0].length; j++) {
                maxDiff = Math.max(maxDiff, dp[i - 1][j - 1] - prices[j]);
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
            }
        }
        return dp[K][n - 1];
    }

    // Optimize to rolling array
    public int maxProfitOpt(int K, int[] prices) {
        if (K == 0 || prices.length == 0){
            return 0;
        }
        // this is necessary to pass test case35%.
        // the maximum number of transactions would be # of days / 2
        // if k >= upper limit, this is the same situation as unlimited transaction.
        // so apply Greedy Choice Property and you don't need to go through the dp.
        int profit = 0;
        if(K >= prices.length / 2) {
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
        /*
        mod by 2 for space optimization
        */
        int[][] dp = new int[2][prices.length + 1];
        for (int j = 1; j <= K; j++){
            int maxDiff = -prices[0];
            for (int i = 1; i <= prices.length; i++){
                dp[j % 2][i] = (i == 1) ? 0 : dp[j % 2][i - 1];
                dp[j % 2][i] = Math.max(dp[j % 2][i - 1], maxDiff + prices[i - 1]);
                maxDiff = Math.max(dp[(j - 1) % 2][i] - prices[i - 1], maxDiff);
            }
        }
        return dp[K % 2][prices.length];
    }

    // Q5: follow 4:

    /**
     * Given a stock n-day price, you can only trade at most once a day, you can choose to buy a stock or sell a stock or give up the trade today, output the maximum profit can reach.
     */
    public int bestSellVI(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[] dp = new int[len + 1];
        dp[1] = -prices[0];
        for (int i = 1; i < len; i++) {
            int[] tmp = new int[len + 1];
            tmp[0] = Math.max(dp[0], dp[1] + prices[i]);
            tmp[i] = Math.max(dp[i], dp[i - 1] - prices[i]);
            tmp[i + 1] = dp[i] - prices[i];
            for (int j = 1; j < i; j++) {
                tmp[j] = Math.max(dp[j],
                        Math.max(dp[j - 1] - prices[i], dp[j + 1] + prices[i]));
            }
            dp = tmp;
        }
        return dp[0];
    }
    // Mehtod 2: Using priorityQueue
    public int bestSellIIX(int[] a) {
        // write your code here
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int total = 0;
        for (int price : a) {
            if (!minHeap.isEmpty() && minHeap.peek() < price) {
                int buyPrice = minHeap.poll();
                total += price - buyPrice;
                minHeap.offer(price);
            }
            minHeap.offer(price);
        }
        return total;
    }

    // Follow Up 5: if there are some restriction for the transaction
    /**
     * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions
     * 1. You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * 2. After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     */
    // hold[i] = the greatest profit that holding the stock in the ith day
    // unhold[i] = the greatest profit that unholding the stock in the ith day
    // Target = unhold[n-1], meaning that you sell all stock at the last day
    // Base case:
    // hold[0] = -price[0]
    // hold[1] = max(-price[1],-price[0])
    // unhold[0] = 0;
    // Induction rule:
    // Case 1.0 for hold[i] in the ith day
    // 		1.1 you buy stock in the ith day.  unhold[i-2] - price[i]
    //  	1.2 you do not buy stock in the ith day.	hold[i-1]
    // Case 2.0 for unhold[i] in the ith day
    // 		2.1 you sale stock in the ith day	hold[i-1] + price[i]
    //		2.2 you do not sale stock in the ith day. unhold[i-1]
    public int maxProfitIII(int[] prices) {
        // sanity check
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[] hold = new int[n];
        int[] unhold = new int[n];
        // base case
        hold[0] = -prices[0];
        unhold[0] = 0;
        // induction rule
        for(int i = 1; i < n; i++) {
            if(i == 1) {
                hold[i] = Math.max(hold[i-1],-prices[i]);
            } else {
                hold[i] = Math.max(unhold[i-2] - prices[i], hold[i-1]);
            }
            unhold[i] = Math.max(hold[i-1] + prices[i], unhold[i-1]);
        }
        // greedy thought: you must not hold the stock for have greatest profit
        return unhold[n-1];
    }
    // Method 3: Space optimization, logic is exactly the same as method 2
    // Time: O(n), Space: O(n) can optimize to O(1)
    public int maxProfitOptimize(int[] prices) {
        // sanity check
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        // base case
        int hold = -prices[0];
        int unholdTwoDaysBefore = 0;
        int unhold = 0;
        int temp;
        // induction rule
        for(int i = 1; i < n; i++) {
            if(i == 1) {
                // temp = hold[i], hold = hold[i-1]
                temp = Math.max(hold, -prices[i]);
            } else {
                temp = Math.max(unholdTwoDaysBefore - prices[i], hold);
                // update unhold[i-2] = unhold[i-1]
                unholdTwoDaysBefore = unhold;
            }
            unhold = Math.max(hold + prices[i], unhold);
            hold = temp;
        }
        // greedy thought: you must not hold the stock for have greatest profit
        return unhold;
    }
}
