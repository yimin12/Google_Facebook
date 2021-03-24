package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 18:10
 *   @Description :
 *
 */
public class FactorialProblem {

    /**
     * 最终尾随零的数量之和质因子中2和5的数量有关，很容易想到质因子5的数量一定会比2少，所以只需要算出n!的质因子5的数量即可
     * 所以我们考虑1~n！之间5的倍数，25的倍数，125的倍数，625的倍数......的数量，即可算出答案
     * @param n
     * @return
     */
    public static int factorialTrailingZero(int n) {
        if(n >= 5){
            return n / 5 + factorialTrailingZero(n / 5);
        } else {
            return 0;
        }
    }
    public static int factorialTrailingZero2(int num) {
        if (num < 0) {
            return 0;
        }
        int res = 0;
        while (num != 0) {
            res += num / 5;
            num /= 5;
        }
        return res;
    }

    /**
     * 给定一个非负整数 N，如果用二进制数表达 N!的结果，返回最低位的 1 在哪个 位置上，认为最右的位置为位置 0。 例如:1!=1，最低位的 1 在 0 位置上。2!=2，最低位的 1 在 1 位置上。1000000000!，
     * 最低位的 1 在 999999987 位置上。
     *
     * 这道题属于上面那道题目的拓展，但是知识要分成好几块
     * 前置知识点：
     *      1。最低位是1是从右往左数第一个1的下标，设最右位下标为0.
     *      2. 最低为是1的下标换一种方式问就是将连乘结果转化成2进制有多少个trailingZeros，有三个trailingZeros说明最低为3，例子: 1000,该二进制数最低位是1的下标为3
     *      3. 决定有多少个trailingZeros的实际上是看算数左移了几次，而决定算术左移几次本质是看有几个2的因子:
     *            我们在计算机算数左移(n << 2),就是在最低位补个0，效果就是n整体乘2。那么相对应，(假设我们处理的数大于0),算数右移就是舍掉最低位，并在最高位补0。这也是计算机计算时向下取整与丢失精度的原因。
     *            因此，我们要看n!二进制结果中最低为1的下标 -> n!二进制结果有多少个trailingZeros -> n!中乘数贡献了多少个2的因子(每个2的因子，提供一次算数左移，也就提供一个trailingZeros)
     *            例子: 8!结果的二进制最低为1下标 -> 。。。 -> 8！结果中2的因子有多少个 -> 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 -> (8提供3个2的因子，4提供两个2的因子，6，2各提供1个二的因子) [8提供3个是因为2*2*2]
     *            那么这个练成的问题就转化成了累加的问题，与上面那题类似。
     *      4. 为何N！中2因子的个数是 N - countOnes(binary(N)) 的个数呢？ 如 8!中，有 8 - countOnes(1000) = 8 - 1 = 7个二的因子。与第三点计算结果相同
     *          换个例子，假设寻找17！中2因子的个数：我们正着推导一遍
     *          17！说明在连乘等式中1到17都用到了，带入到上到题的想法中，2因子的个数就是 17/2 + 17/4 + 17/8 + 17/16 = 15 (计算机向下取整)
     *          先将17转换成2进制， 17 = 0b10001，设两个二进制数 m1 = 10000, m2=1，则 m1 + m2 = 0b10001 = 17
     *          m1提供的二的因字数为 m1/2 + m1/4 + ... + 1 = m1 - 1（等比数列求和), m2能提供的二的因子数为m2/2 + m2/4 + m2/8 ... + 1 = m2 - 1(依旧是等比数列求和)
     *          假设N！中有k个1，那么我们只需要拆出m1,m2...mk进行等比就求和，就能得出2的因子数是 (m1-1) + (m2-1) + ... + (mk - 1) = m1 + m2 + m3 + ... + mk - k (k为二进制数中1的个数)
     *          而m1 + m2 + .... mk = N
     *          因此得证：N!的2因子个数为 -> N/2 + N/4 + ... + 1 = N - countOnes(binary(N)),利用这个公式，直接得出下面代码的结论即可。
     * @param num
     * @return
     */
    public static int lowestOnes(int num) {
        // num - countOnes(num) = res
        if (num < 1) {
            return -1;
        }
        int ones = 0;
        int tmp = num;
        while (tmp != 0) {
            ones += (tmp & 1) != 0 ? 1 : 0;
            tmp >>>= 1;
        }
        return num - ones;
    }



    public static void main(String[] args) {
        System.out.println(factorialTrailingZero(25));

        System.out.println(lowestOnes(5));
    }
}
