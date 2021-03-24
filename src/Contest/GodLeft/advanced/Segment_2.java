package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/24 0:46
 *   @Description :
 *
 */
public class Segment_2 {

    public static class SegmentTree {
        private int[] sum;
        private int[] tag;
        private int leaf;
        private int unleaf;

        public SegmentTree(int size) {
            init(size);
        }

        public SegmentTree(int[] nums) {
            init(nums.length);
            for (int i = 1; i <= leaf; i++) {
                sum[unleaf + i] = nums[i - 1];
            }
            for (int i = unleaf - 1; i > 0; i--) {
                sum[i] = sum[i << 1] + sum[i << 1 | 1];
            }
        }

        private void init(int size) {
            unleaf = 1;
            while (unleaf < size + 2) {
                unleaf <<= 1;
            }
            leaf = size;
            sum = new int[unleaf << 1];
            tag = new int[unleaf << 1];
        }

        public void add(int l, int r, int v) {
            int s = unleaf + l;
            int t = unleaf + r + 2;
            int ln = 0;
            int rn = 0;
            int x = 1;
            for (; (s ^ t ^ 1) != 0; s >>= 1, t >>= 1, x <<= 1) {
                sum[s] += v * ln;
                sum[t] += v * rn;
                if ((~s & 1) != 0) {
                    tag[s ^ 1] += v;
                    sum[s ^ 1] += v * x;
                    ln += x;
                }
                if ((t & 1) != 0) {
                    tag[t ^ 1] += v;
                    sum[t ^ 1] += v * x;
                    rn += x;
                }
            }
            for (; s != 0; s >>= 1, t >>= 1) {
                sum[s] += v * ln;
                sum[t] += v * rn;
            }
        }

        public int query(int l, int r) {
            int s = unleaf + l;
            int t = unleaf + r + 2;
            int ln = 0;
            int rn = 0;
            int x = 1;
            int ans = 0;
            for (; (s ^ t ^ 1) != 0; s >>= 1, t >>= 1, x <<= 1) {
                ans += tag[s] * ln;
                ans += tag[t] * rn;
                if ((~s & 1) != 0) {
                    ans += sum[s ^ 1];
                    ln += x;
                }
                if ((t & 1) != 0) {
                    ans += sum[t ^ 1];
                    rn += x;
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        int[] test = { 1, 1, 1, 1, 1, 1 };
        SegmentTree seg = new SegmentTree(test);
        seg.add(0, 2, 4);
        System.out.println(seg.query(2, 4));

    }

}
