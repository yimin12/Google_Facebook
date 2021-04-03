package Contest.Bloomberg;

import java.util.Arrays;
import java.util.TreeMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/31 14:55
 *   @Description :
 *
 */
public class MaximumProfitInJobScheduling {


    /**
     * Sort the jobs by endTime.
     *
     * dp[time] = profit means that within the first time duration,
     * we cam make at most profit money.
     * Intial dp[0] = 0, as we make profit = 0 at time = 0.
     *
     * For each job = [s, e, p], where s,e,p are its start time, end time and profit,
     * Then the logic is similar to the knapsack problem.
     * If we don't do this job, nothing will be changed.
     * If we do this job, binary search in the dp to find the largest profit we can make before start time s.
     * So we also know the maximum cuurent profit that we can make doing this job.
     *
     * Compare with last element in the dp,
     * we make more money,
     * it worth doing this job,
     * then we add the pair of [e, cur] to the back of dp.
     * Otherwise, we'd like not to do this job.
     *
     *
     * Complexity
     * Time O(NlogN) for sorting
     * Time O(NlogN) for binary search for each job
     * Space O(N)
     *
     * @param startTime
     * @param endTime
     * @param profit
     * @return
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        // sort it by its ending time first
        Arrays.sort(jobs, (a, b)->a[1] - b[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0); // key : start time, value : maxprofit
        for (int[] job : jobs) {
            // because end time dimension is sorted, we do not concern the previous job's end time is larger than current job.
            // recursion rule: dp[start_now, profit_now] = Math.max(dp[start_earlier, profit_eairler], dp[end_now, profit_now])
            // -> pick the max profit between choosing this job and give up previous job or give up this job
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            if (cur > dp.lastEntry().getValue())
                dp.put(job[1], cur);
        }
        return dp.lastEntry().getValue();
    }
}
