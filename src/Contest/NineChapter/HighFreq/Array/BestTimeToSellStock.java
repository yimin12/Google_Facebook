package Contest.NineChapter.HighFreq.Array;

import java.util.PriorityQueue;

public class BestTimeToSellStock {

    /**
     * Complete one transaction exactly
     */
    public int maxProfitI(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int res = Integer.MIN_VALUE;
        for(int price : prices){
            min = Math.min(min, price);
            res = Math.max(price - min, res);
        }
        return res;
    }

    /**
     * Complete as much as transactions as possible
     */
    public int maxProfitII(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int res = 0;
        for(int i = 0; i < prices.length - 1; i ++){
            int diff = prices[i + 1] - prices[i];
            if(diff > 0){
                res += diff;
            }
        }
        return res;
    }

    /**
     * Complete at most k transactions
     */
    public int maxProfitIII(int[] prices, int k){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int n = prices.length;
        if(k > n /2){
            // you can do as much as transactions you can
            return maxProfitII(prices);
        }
        int[][] dp = new int[k + 1][n];
        for(int i = 1; i < dp.length; i ++){
            int maxDiff = -prices[0];
            for(int j = 1; j < dp[0].length; j ++){
                maxDiff = Math.max(maxDiff, dp[i-1][j-1] - prices[j]);
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
            }
        }
        return dp[k][n - 1];
    }

    // Optimize to rolling array
    public int maxProfitIIIRolling(int K, int[] prices) {
        int n = prices.length;
        if(K > n / 2){
            int res = 0;
            for(int i = 1; i < n; i ++){
                int diff = prices[i] - prices[i - 1];
                if(diff > 0){
                    res += diff;
                }
            }
            return res;
        }
        int f[][] = new int[2][n + 1];
        for(int j = 1; j <= K; j ++){
            int maxDiff = -prices[0];
            for(int i = 1; i <= prices.length; i ++){
                f[j % 2][i] = (i == 1) ? 0 : f[j % 2][i - 1];
                f[j % 2][i] = Math.max(f[j % 2][i - 1], maxDiff + prices[i - 1]);
                maxDiff = Math.max(f[(j - 1) % 2][i] - prices[i - 1], maxDiff);
            }
        }
        return f[K % 2][prices.length];
    }

    /**
     * Given a stock n-day price, you can only trade at most once a day, you can choose to buy a stock or sell a stock or give up the trade today, output the maximum profit can be achieved.
     *
     * 1 \leq n \leq 100001≤n≤10000
     * Example
     * Example 1:
     *
     * Given `a = [1,2,10,9]`, return `16`
     * Input:
     * [1,2,10,9]
     * Output:
     * 16
     *
     * Explanation:
     * you can buy in day 1,2 and sell in 3,4.
     * profit:-1-2+10+9 = 16
     */
    // O(nlogn)
    public int maxProfitV(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int res = 0;
        for(int k : prices){
            if(queue.size() > 0 && queue.peek() < k){
                res += k - queue.poll();
                queue.offer(k); // c - b + b - a = c - a; keep the value in this process
            }
            queue.offer(k);
        }
        return res;
    }

    /**
     *  Best Time to Buy and Sell Stock with Cooldown
     *  Description
     * Suppose you have an array where the ith element represents the price of the given stock on the ith day.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
     *
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     * Example
     * Example1
     *
     * Input: [1, 2, 3, 0, 2]
     * Output: 3
     * Explanation:
     * transactions = [buy, sell, cooldown, buy, sell]
     */
    public int maxProfitCoolDown(int[] prices){
        if(prices == null || prices.length < 2) return 0;
        int n = prices.length;
        int[] sell = new int[n], buy = new int[n];
        sell[0] = 0;
        buy[0] = -prices[0];
        for(int i = 1; i < prices.length; i ++){
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            buy[i] = Math.max(buy[i - 1], (i > 1 ? sell[i-2] : 0) - prices[i]);
        }
        return sell[n - 1];
    }

    /**
     * Best Time to Buy and Sell Stock with Transaction Fee
     * Description
     * Given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee. (You need to pay fee only on selling.)
     *
     * You can complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You can not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
     *
     * Return the maximum profit you can make.
     *
     * 0 < prices.length <= 50000.
     * 0 < prices[i] < 50000.
     * 0 <= fee < 50000.
     *
     * Example
     * Example 1:
     *
     * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
     * Output: 8
     * Explanation: The maximum profit can be achieved by:
     *   Buying  at prices[0] = 1
     *   Selling at prices[3] = 8
     *   Buying  at prices[4] = 4
     *   Selling at prices[5] = 9
     *   The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     */
    public int maxProfitWithFee(int[] prices, int fee){
        if(prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[] hold = new int[n], unhold = new int[n];
        hold[0] = -prices[0];
        unhold[0] = 0;
        for(int i = 0; i < n; i ++){
            hold[i] = Math.max(hold[i - 1], unhold[i - 1] - prices[i]); // buy
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i] - fee); // sell
        }
        return unhold[n - 1];
    }
}
