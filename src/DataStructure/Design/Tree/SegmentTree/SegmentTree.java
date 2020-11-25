package DataStructure.Design.Tree.SegmentTree;

import java.util.function.BinaryOperator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/12 13:33
 *   @Description :
 *
 */
public class SegmentTree {

    // Constructor
    public SegmentTree(long[] values, SegmentCombinationFn segmentCombinationFn, RangeUpdateFunction rangeUpdateFunction){
        if(values == null){
            throw new IllegalArgumentException("Segment tree values can not be null");
        }
        if(segmentCombinationFn == null || rangeUpdateFunction == null){
            throw new IllegalArgumentException("Please specify a valid segment combination function or range update function");
        }
        n = values.length;
        // Consider the deepest level of segment tree, may be there are empty hold in the array, but we still create it
        int N = 4 * n;
        st = new Segment[N];

        // Select the specified combination function
        if (segmentCombinationFn == SegmentCombinationFn.SUM) {
            combinationFn = sumCombinationFn;
            if (rangeUpdateFunction == RangeUpdateFunction.ADDITION) {
                ruf = sumQuerySumUpdate;
                lruf = lsumQuerySumUpdate;
            } else if (rangeUpdateFunction == RangeUpdateFunction.ASSIGN) {
                ruf = sumQueryAssignUpdate;
                lruf = lsumQueryAssignUpdate;
            } else if (rangeUpdateFunction == RangeUpdateFunction.MULTIPLICATION) {
                ruf = sumQueryMulUpdate;
                lruf = lsumQueryMulUpdate;
            }
        } else if (segmentCombinationFn == SegmentCombinationFn.MIN) {
            combinationFn = minCombinationFn;
            if (rangeUpdateFunction == RangeUpdateFunction.ADDITION) {
                ruf = minQuerySumUpdate;
                lruf = lminQuerySumUpdate;
            } else if (rangeUpdateFunction == RangeUpdateFunction.ASSIGN) {
                ruf = minQueryAssignUpdate;
                lruf = lminQueryAssignUpdate;
            } else if (rangeUpdateFunction == RangeUpdateFunction.MULTIPLICATION) {
                ruf = minQueryMulUpdate;
                lruf = lminQueryMulUpdate;
            }
        } else if (segmentCombinationFn == SegmentCombinationFn.MAX) {
            combinationFn = maxCombinationFn;
            if (rangeUpdateFunction == RangeUpdateFunction.ADDITION) {
                ruf = maxQuerySumUpdate;
                lruf = lmaxQuerySumUpdate;
            } else if (rangeUpdateFunction == RangeUpdateFunction.ASSIGN) {
                ruf = maxQueryAssignUpdate;
                lruf = lmaxQueryAssignUpdate;
            } else if (rangeUpdateFunction == RangeUpdateFunction.MULTIPLICATION) {
                ruf = maxQueryMulUpdate;
                lruf = lmaxQueryMulUpdate;
            }
        } else {
            throw new UnsupportedOperationException(
                    "Combination function not supported: " + segmentCombinationFn);
        }
        // start to build tree
        buildSegmentTree(0, 0, n - 1, values);
    }

    /**
     * Builds a segment tree by starting with the leaf nodes and combining segment values on callback. Recursive Build up
     *
     * @param i the index of the segment in the segment tree
     * @param left the left index (inclusive) of the segment range
     * @param right the right index (inclusive) of the segment range
     * @param values the initial values array
     */
    private void buildSegmentTree(int i, int left, int right, long[] values){
        // base case: create new node for leaf
        if(left == right){
            st[i] = new Segment(i, values[left], values[right], values[right], left, right);
            return;
        }
        // Divide
        int mid = left + (right - left)/2;
        buildSegmentTree(2*i+1, left, mid, values);
        buildSegmentTree(2*i+2, mid + 1, right, values);
        // Conquer
        Long segmentValue = combinationFn.apply(st[2*i+1].value, st[2*i+2].value);
        Long minValue = Math.min(st[2*i+1].value, st[2*i+2].value);
        Long maxValue = Math.max(st[2*i+1].value, st[2*i+2].value);
        // Update the path's nodes
        st[i] = new Segment(i, segmentValue, minValue, maxValue, left, right);
    }

    /**
     * Returns the query of the range [left, right] on the original 'value' array
     */
    public Long rangeQueryI(int left, int right){
        return rangeQueryI(0, 0, n - 1, left, right);
    }

    private Long rangeQueryI(int i, int left, int right, int leftRange, int rightRange){
        if(left > right){
            return null;
        }
        propagateI(i, left ,right);
        if(left == leftRange && right ==rightRange){
            return st[i].value;
        }
        int mid = left + (right - left) >> 1;
        return combinationFn.apply(rangeQueryI(2*i + 1, left, mid, leftRange, Math.min(right, rightRange)),
                rangeQueryI(2*i + 2, mid + 1, right, Math.max(left, leftRange), rightRange));
    }

    // Apply the delta value to the current node and push it to the child segments
    private void propagateI(int i, int left, int right){
        if (st[i].lazy != null) {
            // Only used for min/max mul queries
            st[i].min = st[i].min * st[i].lazy;
            st[i].max = st[i].max * st[i].lazy;

            // Apply the delta to the current segment.
            st[i].value = ruf.apply(st[i], st[i].lazy);
            // Push the delta to left/right segments for non-leaf nodes
            propagateLazy1(i, left, right, st[i].lazy);
            st[i].lazy = null;
        }
    }

    private void propagateLazy1(int i, int left, int tr, long delta) {
        // Ignore leaf segments
        if (left == tr) return;
        st[2 * i + 1].lazy = lruf.apply(st[2 * i + 1], delta);
        st[2 * i + 2].lazy = lruf.apply(st[2 * i + 2], delta);
    }

    public void rangeUpdate1(int l, int r, long x) {
        rangeUpdate1(0, 0, n - 1, l, r, x);
    }

    private void rangeUpdate1(int i, int left, int right, int l, int r, long x) {
        propagateI(i, left, right);
        if (l > r) {
            return;
        }

        if (left == l && right == r) {
            // Only used for min/max mul queries
            st[i].min = st[i].min * x;
            st[i].max = st[i].max * x;

            st[i].value = ruf.apply(st[i], x);
            propagateLazy1(i, left, right, x);
        } else {
            int tm = (left + right) / 2;
            // Instead of checking if [left, tm] overlaps [l, r] and [tm+1, right] overlaps
            // [l, r], simply recurse on both segments and let the base case disregard
            // invalid intervals.
            rangeUpdate1(2 * i + 1, left, tm, l, Math.min(tm, r), x);
            rangeUpdate1(2 * i + 2, tm + 1, right, Math.max(l, tm + 1), r, x);

            st[i].value = combinationFn.apply(st[2 * i + 1].value, st[2 * i + 2].value);
            st[i].max = Math.max(st[2 * i + 1].max, st[2 * i + 2].max);
            st[i].min = Math.min(st[2 * i + 1].min, st[2 * i + 2].min);
        }
    }

    // The number of elements in the original input values array
    private int n;

    // The segment tree represented as a binary tree of ranges where st[0] is the root node and the left and right children of node i are i*2+1 and i*2+2 (The same as heap)
    private Segment[] st;

    // Choose range combination function
    private BinaryOperator<Long> combinationFn;

    // The Range Update Function (RUF) that choose how a lazy delta value is applied to a segment
    private Ruf ruf;

    // The Lazy Range Update Function (LRUF) associated with the RUF. How to propagate the lazy delta values is sometimes different than how we apply them to current segment
    private Ruf lruf;

    // Define the Binary Operator
    private BinaryOperator<Long> sumCombinationFn = (a, b) -> safeSum(a, b);
    private BinaryOperator<Long> minCombinationFn = (a, b) -> safeMin(a, b);
    private BinaryOperator<Long> maxCombinationFn = (a, b) -> safeMax(a, b);

    // Range update functions (for non and non lazy version)
    private Ruf minQuerySumUpdate = (s, x) -> safeSum(s.value, x);
    private Ruf lminQuerySumUpdate = (s, x) -> safeSum(s.lazy, x);

    // Multiplication update
    private Ruf minQueryMulUpdate = (s, x) -> {
        if(x == 0) {
            return 0L;
        } else if(x < 0){
            // s.min was already calculated
            if(safeMul(s.value, x) == s.min){
                return s.max;
            } else {
                return s.min;
            }
        } else {
            return safeMul(s.value, x);
        }
    };

    private Ruf lminQueryMulUpdate = (s, x) -> safeMul(s.lazy, x);

    private Ruf minQueryAssignUpdate = (s, x) -> x;
    private Ruf lminQueryAssignUpdate = (s, x) -> x;

    private Ruf maxQuerySumUpdate = (s, x) -> safeSum(s.value, x);
    private Ruf lmaxQuerySumUpdate = (s, x) -> safeSum(s.lazy, x);

    private Ruf maxQueryMulUpdate =
            (s, x) -> {
                if (x == 0) {
                    return 0L;
                } else if (x < 0) {
                    if (safeMul(s.value, x) == s.min) {
                        return s.max;
                    } else {
                        return s.min;
                    }
                } else {
                    return safeMul(s.value, x);
                }
            };
    private Ruf lmaxQueryMulUpdate = (s, x) -> safeMul(s.lazy, x);

    private Ruf maxQueryAssignUpdate = (s, x) -> x;
    private Ruf lmaxQueryAssignUpdate = (s, x) -> x;

    private Ruf sumQuerySumUpdate = (s, x) -> s.value + (s.right - s.left + 1) * x;
    private Ruf lsumQuerySumUpdate = (s, x) -> safeSum(s.lazy, x);

    private Ruf sumQueryMulUpdate = (s, x) -> safeMul(s.value, x);
    private Ruf lsumQueryMulUpdate = (s, x) -> safeMul(s.lazy, x);

    private Ruf sumQueryAssignUpdate = (s, x) -> (s.right - s.left + 1) * x;
    private Ruf lsumQueryAssignUpdate = (s, x) -> x;

    private long safeSum(Long a, Long b){
        if(a == null) a = 0L;
        if(b == null) b = 0L;
        return a + b;
    }

    private Long safeMul(Long a, Long b){
        if(a == null) a = 1L;
        if(b == null) b = 1L;
        return a * b;
    }

    private Long safeMin(Long a, Long b){
        if(a == null) return b;
        if(b == null) return a;
        return Math.min(a, b);
    }

    private Long safeMax(Long a, Long b){
        if(a == null) return b;
        if(b == null) return a;
        return Math.max(a, b);
    }

    // The type of segment combination function to use
    private static enum SegmentCombinationFn{
        SUM, MIN, MAX;
    }

    // Updating the value of specific index position, or a range of values, modify the affected values using the following function:
    private static enum RangeUpdateFunction{
        ASSIGN, // Assign all teh values in the range[l, r] to be 'x'
        ADDITION, // Add the value of 'x' to all the elements in the range of [l, r]
        MULTIPLICATION // multiply all elements in the range [l, r] by value of 'x'
    }

    // Use for Lazy init
    private interface Ruf{
        Long apply(Segment segment, Long delta);
    }

    private static class Segment{
        int i;
        Long value;
        Long lazy;

        // Used only for Min/Max multiply queries
        Long min, max;

        // The range of segment
        int left, right;

        public Segment(int i, Long value, Long min, Long max, int left, int right){
            this.i = i;
            this.value = value;
            this.min = min;
            this.max = max;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d], value = %d, lazy = %d", left, right, value, lazy);
        }

    }
}
