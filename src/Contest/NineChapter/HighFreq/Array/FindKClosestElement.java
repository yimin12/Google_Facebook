package Contest.NineChapter.HighFreq.Array;

/**
 * Description
 * Given target, a non-negative integer k and an integer array A sorted in ascending order, find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. Otherwise, sorted in ascending order by number if the difference is same.
 *
 * The value k is a non-negative integer and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 10^410
 * ​4
 * ​​
 * Absolute value of elements in the array will not exceed 10^410
 * ​4
 * ​​
 * Example
 * Example 1:
 *
 * Input: A = [1, 2, 3], target = 2, k = 3
 * Output: [2, 1, 3]
 * Example 2:
 *
 * Input: A = [1, 4, 6, 8], target = 3, k = 3
 * Output: [4, 1, 6]
 */
public class FindKClosestElement {


    public int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
        if(A == null || A.length == 0 || k < 0) return new int[0];
        int[] res = new int[k];
        int left = 0, right = A.length - 1;
        while(left < right - 1 ){
            int mid = left + ((right - left) >> 1);
            if(A[mid] >= target){
                right = mid;
            } else {
                left = mid;
            }
        }
        for(int i = 0; i < k; i ++){
            if (left >= 0 && right < A.length) {
                if (Math.abs(A[left]-target) <= Math.abs(A[right]-target)) {
                    res[i] = A[left];
                    left--;
                }
                else {
                    res[i] = A[right];
                    right++;
                }
            } else if (left >= 0 && right >= A.length) {        // 如果右指针出界了那就取左指针
                res[i] = A[left];
                left--;
            }
            else if (left < 0 && right < A.length){	// 同理如果左指针出界就取右指针
                res[i] = A[right];
                right++;
            }
        }
        return res;
    }
}
