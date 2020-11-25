package DataStructure.Design.Tree;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/12 11:28
 *   @Description :
 *      Binary Indexed Tree
 *      Fenwick tree or binary indexed tree is a data structure that can efficiently update elements and calculate prefix sums in a table of numbers.
 */
public class FenwickTree {

    // The size fo the array holding the Fenwick tree's value
    final int N;

    // Array contains Fenwick Tree's elements
    private long[] tree;

    public FenwickTree(int size){
        tree = new long[(N = size + 1)];
    }

    // Construct a fenwick tree with an initial set of values. The values array MUST BE ONE BASED meaning values[0] does not get used. O(n) construction
    public FenwickTree(long[] values){
        if(values == null) throw new IllegalArgumentException("Values array can not be null");
        N = values.length;
        values[0] = 0L;
        // Do not change the input
        tree = values.clone();
        for(int i = 1; i < N; i++){
            int parent = i + lsb(i);
            if(parent < N) tree[parent] += tree[i];
        }
    }

    // Returns the value of the least significant bit (LSB)
    // lsb(108) = lsb(0b1101100) =     0b100 = 4
    // lsb(104) = lsb(0b1101000) =    0b1000 = 8
    // lsb(96)  = lsb(0b1100000) =  0b100000 = 32
    // lsb(64)  = lsb(0b1000000) = 0b1000000 = 64
    private int lsb(int i){
        // Isolates the lowest one bit value
        return i & (-i);
    }

    // Compute the prefix sum from [1,i] in O(logn) time
    private long prefixSum(int index){
        long sum = 0L;
        while(index != 0){
            sum += tree[index];
            index -= lsb(index); // or i &= ~lsb(i)
        }
        return sum;
    }

    // Return the range sum of the interval [left, right] in O(logn) time
    public long sum(int left, int right){
        if(right < left) throw new IllegalArgumentException("Make sure right >= left");
        return prefixSum(right) - prefixSum(left);
    }

    // Get the value at index i
    public long get(int index){
        return sum(index, index);
    }

    // Add 'v' to index 'i' in O(logn) time. Update operations
    public void add(int i, long v){
        while(i < N){
            tree[i] += v;
            i += lsb(i);
        }
    }

    // Set index i to be equal to v, O(logn) time
    public void set(int index, long v){
        add(index, v - sum(index, index));
    }

    @Override
    public String toString() {
        return Arrays.toString(tree);
    }

    /**
     * 对于创建数组的时候
     * 可以发现，在这棵抽象的树种向上移动的过程其实就是不断将当前数字的最后一个1翻转为0的过程。基于这一事实，实现在Binary Indexed Tree中向上（在数组中向前）寻找母结点的代码就非常容易了。
     * 例如给定一个int x = 13，这个过程可以用如下运算实现：
     *
     * x = 13 = 0b00001101
     * -x = -13 = 0b11110011
     * x & (-x) = 0b00000001
     * x - (x & (-x)) = 0b00001100
     */

    /**
     * 对于更新数组元素的时候
     * 从5开始，应当被更新的位置的坐标为原坐标加上原坐标二进制表示中最后一个1所代表的数字。这一过程和上面求和的过程刚好相反。以int x = 5为例，我们可以用如下运算实现：
     *
     * x = 5 = 0b00000101
     * -x = -5 = 0b11111011
     * x & (-x) = 0b00000001
     * x + (x & (-x)) = 0b00000110
     */
}