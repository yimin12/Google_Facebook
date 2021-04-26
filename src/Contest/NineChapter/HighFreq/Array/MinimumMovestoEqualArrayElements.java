package Contest.NineChapter.HighFreq.Array;

/**
 * Description
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
 *
 * Have you met this question in a real interview?
 * Example
 * Input:
 * [1,2,3]
 *
 * Output:
 * 3
 *
 * Explanation:
 * Only three moves are needed (remember each move increments two elements):
 *
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */

public class MinimumMovestoEqualArrayElements {

    /**
     * 对于长度n的数组中，将n-1个元素的值加一，其实可以等价于将某个元素减一，所以需要的步数就等于$sum(1,n) - minNum * length$，其中$sum(1,n)$为数组所有元素的和，$minNum$为数组中最小的元素，$length$为数组长度。
     * @param nums  找平均数
     * @return
     */
    public int minMoves(int[] nums){
        int sum = 0;
        int minNum = Integer.MAX_VALUE;
        for(int num : nums){
            sum += num;
            minNum = Math.min(minNum, num);
        }
        return sum - minNum * nums.length;
    }

    /**
     * Description
     * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
     *
     * The array can contain up to 10000 elements.
     *
     * Example
     * Example 1:
     *
     * Input:
     * [1,2,3]
     *
     * Output:
     * 2
     *
     * Explanation:
     * Only two moves are needed (remember each move increments or decrements one element):
     *
     * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
     */
    /**
     * 变成了找中位数，能够双向移动就没必要去找平均数了
     */
    public int minMoves2(int[] nums) {

        int median = quickselect(nums, 0, nums.length-1, (nums.length + 1) / 2);
        int minmoves = 0;

        for(int i = 0; i < nums.length; ++ i){
            minmoves += Math.abs( median - nums[i] );
        }
        return minmoves;
    }

    private int quickselect(int[] nums, int start, int end, int size) {
        int mid = (start + end) / 2;
        int pivot = nums[mid];
        int i = start - 1, j = end + 1, temp;
        for (int k = start; k < j; k++) {
            if (nums[k] < pivot) {
                i++;
                temp = nums[i] ^ nums[k];
                nums[i] ^= temp;
                nums[k] ^= temp;
            } else if (nums[k] > pivot) {
                j--;
                temp = nums[k] ^ nums[j];
                nums[j] ^= temp;
                nums[k] ^= temp;
                k--;
            }
        }
        if (i - start + 1 >= size) {
            return quickselect(nums, start, i, size);
        } else if (j - start >= size) {
            return nums[j-1];
        } else {
            return quickselect(nums, j, end, size - (j - start));
        }
    }
}
