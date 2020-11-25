package Algorithm.DynamicProgramming.RangeDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/9 21:21
 *   @Description :
 *      Given n books and the i-th book has pages[i] pages. There are k persons to copy these books.
 *      These books list in a row and each person can claim a continous range of books. For example, one copier can copy
 *      the books from i-th to j-th continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).
 *      They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign
 *      books so that the slowest copier can finish at earliest time?
 */

/**
 * Input: pages = [3, 2, 4], k = 2
 * Output: 5
 * Explanation:
 *     First person spends 5 minutes to copy book 1 and book 2.
 *     Second person spends 4 minutes to copy book 3.
 */
public class CopyBook {

    // Method 0: Binary Search
    public int copyBooks(int[] pages, int k){
        if (pages == null || pages.length == 0) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }

        int l = 0, r = getSum(pages);
        while(l + 1 < r){
            int mid = l + (r-l)/2;
            if(getCopiers(pages, mid) <= k){
                r = mid;
            } else {
                l = mid;
            }
        }
        if(getCopiers(pages, l) <= k){
            return l;
        }
        return r;
    }
    private int getCopiers(int[] pages, int limit){
        int copiers = 0;
        int lastCopied = limit; // init from 0 to 1 worker
        for(int page:pages){
            // work overload
            if(page > limit){
                return Integer.MAX_VALUE;
            }
            // need new workers
            if(lastCopied + page > limit){
                copiers++;
                lastCopied = 0;
            }
            lastCopied += page;
        }
        return copiers;
    }
    private int getSum(int[] pages){
        int res = 0;
        for(int page : pages){
            res += page;
        }
        return res;
    }
    // All following method are : time(O(n^2)) and space(O(n^2))
    // Method 1:
    public int copyBooksV(int[] pages, int k){
       if(pages == null || pages.length == 0 || k <= 0){
           return 0;
       }
       int n = pages.length;
       int res = Integer.MIN_VALUE;
       if(k > n){
           for(int page : pages){
               res = Math.min(page,res);
           }
           return res;
       }
       int[] prefix = new int[pages.length];
       for(int i = 0; i < pages.length; i++){
           if(i == 0){
               prefix[i] = pages[0];
           } else{
               prefix[i] = prefix[i-1] + pages[i];
           }
       }
       int[][] dp = new int[n][k];
       // base case
        for(int i = 0; i < n; i++){
            dp[i][0] = prefix[i]; // all work will completed by one worker
        }
        for(int j = 1; j < k; j++){
            int p = 0;
            dp[0][j] = pages[0];
            // case 1: when # of workers is larger than books'
            for(int i = 1; i < j; i++){
                dp[i][j] = Math.max(dp[i-1][j], pages[i]);
            }
            // case 2: more books
            for(int i = j; i < n; i++){
                while(p < i && dp[p][j-1] < prefix[i] - prefix[p]){
                    p++;
                }
                dp[i][j] = Math.max(dp[p][j-1], prefix[i] - prefix[p]);
                if(p > 0){
                    p--;
                }
                dp[i][j] = Math.min(dp[i][j], Math.max(dp[p][j-1], prefix[i] - prefix[p]));
            }
        }
        return dp[n-1][k-1];
    }
    // Handle it in sequence type dp
    // Method 2:
    public int copyBookI(int[] pages, int k){
        if(pages == null || pages.length == 0 || k <= 0){
            return 0;
        }
        int n = pages.length;
        // where there is more workers
        if(k > n){
            k = n;
        }
        // sequence type
        int[][] dp = new int[k+1][n+1];
        // base case
        dp[0][0] = 0;
        for(int i=1; i <= n; i++){
            dp[0][i] = Integer.MAX_VALUE;
        }
        // induction rule: start from 1 worker
        for(int i = 1; i <= k; i++){
            dp[i][0] = 0; // no time cost for no book
            for(int j = 1; j <= n; j++){
                dp[i][j] = Integer.MAX_VALUE;
                int sum = 0;
                for(int p = j; p >= 0; p--){
                    // minimize the max value
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][j], sum));
                    if(p > 0){
                        sum += pages[p-1];
                    }
                }
            }
        }
        return dp[k][n];
    }
    // Optimize space by using rolling array
    public int copyBookII(int[] pages, int K){
        if(pages == null || pages.length == 0){
            return 0;
        }
        int i, j, k, sum, n = pages.length;
        if(pages.length < K){
            n = K;
        }
        int[][] dp = new int[2][n+1];
        int old, cur = 0;
        for(i = 0; i <= n; i++){
            dp[cur][i] = Integer.MAX_VALUE;
        }
        dp[cur][0] = 0;
        for(i = 1; i <= K; i++){
            old = cur;
            cur = 1 - cur;
            for(j = 0; j <= n; j++){
                dp[cur][j] = Integer.MAX_VALUE;
                sum = 0;
                for(k = j; k >= 0; k--){
                    if(dp[old][k] < Integer.MAX_VALUE){ // start rolling
                        dp[cur][j] = Math.min(dp[cur][j], Math.max(dp[old][k], sum));
                    }
                    if(k > 0){
                        sum += pages[k - 1];
                    }
                }
            }
        }
        return dp[cur][n];
    }

    // Method 4: Memory Search
    int[][] dp;
    int[] A;
    int n, k;
    public int copyBookIV(int[] pages, int K){
        if(pages == null || pages.length == 0){
            return 0;
        }
        A = pages;
        k = K;
        n = A.length;
        if(k > n){
            k = n;
        }
        dp = new int[k+1][n+1];
        for(int i = 0; i <= k; i++){
            for(int j = 0; j <= n; j++){
                dp[i][j] = -1;
            }
        }
        memorySearch(k, n);
        return dp[k][n];
    }
    private void memorySearch(int k, int i){
        // pruning
        if(dp[k][i] != -1){
            return;
        }
        // base case1: 0 workers
        if(k == 0){
            dp[k][i] = i == 0 ? 0 : Integer.MAX_VALUE;
            return;
        }
        // base case2: 0 books
        if(i == 0){
            dp[k][i] = 0;
            return;
        }
        dp[k][i] = Integer.MAX_VALUE;
        int s = 0;
        for(int j = i; j >= 0; j--){
            memorySearch(k - 1, j);
            dp[k][i] = Math.min(dp[k][i], Math.max(s, dp[k-1][j]));
            if(j > 0){
                s += A[j-1];
            }
        }
    }


}
