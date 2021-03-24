package Contest.GodLeft.advanced;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 1:11
 *   @Description :
 *
 */
public class DistinctSubSequence {

    // distinct subsequence:

    /**
     * Key insight:
     * f[i]中i是在前i个字符中，取以i下标结尾的唯一字符序列有多少个。假设最新进来的char为s.charAt(i)，那么新增的不同字符串一定是以非s.charAt(i)这个字符串结尾的个数之和。下面例子就是按照以某个字符结尾不同而归类
     * 举例子：
     *      // 空集下distinct subsequence个数为1， res = 1
     *      1. 插入a字符，{a} -> {}, {a},   add = res; res += add; 所以res = 2。本质上就是原结果集中，只有以空结尾的一个集合，无其他。
     *      2. 再插入b字符 {a, b} -> {} -> {} 、 {b}
     *                             {a} -> {a} 、 {a,b} , add = res, res += add, so res = 4;
     *      3. 再插入一个字符a {a, b, a} -> 以空结尾 {} -> {} , {a}
     *                                -> 以a结尾 {a} -> {a} , {a,a}
     *                                -> 以b结尾 {b} 、 {a,b}  -> {b, a} , {a,b,a} 从结果可以看出{a}这个元素被计算过两次，多出来的这个个数刚好就是以插入字符结尾的subsequence。
     *                                因此， add = res - count(subsequence(a)) -> add = 4 - 1, res += add -> res = 4 + 3 = 7
     *      如果你不进行归堆的话，以i为结果的subsequence会分布再前i项以s.charAt(i)结尾的subsequence中，所以我们要累加起来去除，才能得到正确答案 : res += dp[i],由此我们也看到了归堆对结果的正确性没有影响，找到了优化时间的办法
     *      下面这个解法时间复杂度是O(n^2)的，因为还包括了前i个字符这个额外信息，我们完全不需要。进一步优化也从这里入手
     * @param s
     * @return
     */
    public static int distinctSubSequence_1(String s){
        char[] str = s.toCharArray();
        int res = 0;
        int f[] = new int[str.length];
        Arrays.fill(f, 1);
        for(int i = 0; i < str.length; i ++){
            for(int j = 0; j < i; j ++){
                if(str[i] != str[j]){
                    f[i] += f[j];
                }
            }
            res += f[i];
        }
        return res;
    }

    public static int distinctSubSequence_2(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        Arrays.fill(dp, 1);
        int[] count = new int[26]; // 在此处进行归堆,假设都是小写字母，如果有其他字符，扩大长度即可
        int res = 0;
        for(int i = 0; i < str.length; i ++){
            int index = str[i] - 'a';
            dp[i] += res - count[index]; // res - count(subsequence(a)), count['a'] 代表以a结尾序列的个数
            res += dp[i]; // res += add;
            count[index] = count[index] + dp[i]; // 更新以str[i]结尾的个数，正是dp[i]
        }
        return res;
    }

    // 由于dp[i] 至于count有关系，与dp前面的数据无关，所以可以继续抛弃dp转而用一个变量存。这里并非是滚动数组优化
    public static int distinctSubSequence_3(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[26];
        int res = 0;
        for(int i = 0; i < s.length(); i ++){
            int index = s.charAt(i) - 'a';
            int pre = res - count[index] + 1; // 加1是整个字串都取上的结果，对应前面的Arrays.fill(dp, 1);
            count[index] += pre;
            res += pre;
        }
        return res;
    }

    public static String random(int len, int variable){
        int size = (int)(Math.random() * len) + 1;
        char[] str = new char[size];
        for(int i = 0; i < size; i ++){
            str[i] = (char)((int)(Math.random() * variable) + 'a');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int len = 10;
        int variable = 5;
        for (int i = 0; i < 1000000; i++) {
            String test = random(len, variable);
            if (distinctSubSequence_1(test) != distinctSubSequence_2(test) || distinctSubSequence_2(test) != distinctSubSequence_3(test)) {
                System.out.println("fuck");
            }
        }
    }
}
