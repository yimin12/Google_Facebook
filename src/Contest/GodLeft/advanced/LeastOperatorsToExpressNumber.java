package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 1:01
 *   @Description :
 *
 */

/**
 *  给定两个正数x和target，target一定不小于x。你可以使用任意个x并可以用+、-、*、/ 操作符随意连接，但是不可以使用括号。返回如果想得到target，最少需要几个操作符。
 */
public class LeastOperatorsToExpressNumber {

    public static int leastOpsExpressTarget(int x, int target){
        int pos = 0, neg = 0, k = 0;
        while(target > 0){
            int cur = target % x;
            if(k > 0){
                int pos2 = Math.min(cur * k + pos, (cur + 1) * k + neg);
                int neg2 = Math.min((x - cur) * k + pos, (x - cur - 1) * k + neg);
                pos = pos2;
                neg = neg2;
            } else {
                pos = cur * 2;
                neg = (x - cur) * 2;
            }
            target /= x;
            k++;
        }
        return Math.min(pos, k + neg) -1;
    }

    public static void main(String[] args) {
        int res = leastOpsExpressTarget(2, 13);
        System.out.println(res);
    }
}
