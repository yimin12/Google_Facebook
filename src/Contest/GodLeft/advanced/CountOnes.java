package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 17:52
 *   @Description :
 *
 */

/**
 * 给定一个整数 n，返回从 1 到 n 的数字中 1 出现的个数。 例如: n=5，1~n 为 1，2，3，4，5。那么 1 出现了 1 次，所以返回 1。 n=11，1~n 为 1，2，3，4，5，6，7，8，9，10，11。
 * 那么 1 出现的次数为 1(出现 1 次)， 10(出现 1 次)，11(有两个 1，所以出现了 2 次)，所以返回 4。
 */
public class CountOnes {

    // Linear traverse with O(n)
    public static int getCount(int num){
        if(num < 1) return 0;
        int res = 0;
        for(int i = 1; i <= num; i++){
            res += getOnes(i);
        }
        return res;
    }

    private static int getOnes(int num){
        int count = 0;
        while(num != 0){
            if(num % 10 == 1){
                count ++;
            }
            num /= 10;
        }
        return count;
    }

    //

    public static int getCount2(int num) {
        if (num < 1) {
            return 0;
        }
        int len = getLenOfNum(num);
        if (len == 1) {
            return 1;
        }
        int tmp1 = powerBaseOf10(len - 1);
        int first = num / tmp1;
        int firstOneNum = first == 1 ? ((num % tmp1) + 1) : tmp1;
        int otherOneNum = first * (len - 1) * (tmp1 / 10);
        return firstOneNum + otherOneNum + getCount2(num % tmp1);
    }

    public static int getLenOfNum(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public static int powerBaseOf10(int base) {
        return (int) Math.pow(10, base);
    }

    public static void main(String[] args) {
        int num = 50000000;
        long start1 = System.currentTimeMillis();
        System.out.println(getCount(num));
        long end1 = System.currentTimeMillis();
        System.out.println("cost time: " + (end1 - start1) + " ms");

        long start2 = System.currentTimeMillis();
        System.out.println(getCount2(num));
        long end2 = System.currentTimeMillis();
        System.out.println("cost time: " + (end2 - start2) + " ms");

    }
}
