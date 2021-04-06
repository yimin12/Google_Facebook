package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/5 21:22
 *   @Description :
 *
 */
public class MedianOfTwoSortedList {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) return 0.0;
        int n = nums1.length + nums2.length;
        if(n % 2 == 0){
            return (findMedian(nums1, 0, nums2, 0, n/2) + findMedian(nums1, 0, nums2, 0, n/2 + 1))/2.0;
        } else {
            return findMedian(nums1, 0, nums2, 0, n/2 + 1);
        }
    }
    private double findMedian(int[] A, int a, int[] B, int b, int k){
        if(a >= A.length){
            return B[b + k - 1];
        }
        if(b >= B.length){
            return A[a + k - 1];
        }
        if(k == 1){
            return Math.min(A[a], B[b]);
        }
        int left = (a + k/2 - 1) < A.length ? A[a + k/2 - 1] : Integer.MAX_VALUE;
        int right = (b + k/2 -1) < B.length ? B[b + k/2 - 1] : Integer.MAX_VALUE;
        // eliminate the smaller half, it is impossible to be answer
        if(left < right){
            return findMedian(A, a + k/2, B, b, k - k/2);
        } else{
            return findMedian(A, a, B, b + k/2, k - k/2);
        }
    }
}
