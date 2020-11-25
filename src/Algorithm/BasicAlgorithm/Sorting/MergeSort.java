package Algorithm.BasicAlgorithm.Sorting;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 23:05
 *   @Description :
 *
 */
public class MergeSort extends InplaceSort {

    public static int[] mergeSort(int[] array){
        if(array == null || array.length < 0) return array;
        int n = array.length;
        int[] left = mergeSort(Arrays.copyOfRange(array, 0, n >> 1));
        int[] right = mergeSort(Arrays.copyOfRange(array, n >> 1, n));
        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right){
        int leftBound = left.length, rightBound = right.length;
        int l = 0, r = 0;
        int n = leftBound + rightBound;
        // need O(n) space here
        int[] ar = new int[n];
        for(int i = 0; i < n; i++){
            if(l == leftBound){
                ar[i] = right[r++];
            } else if(r == rightBound){
                ar[i] = left[l++];
            } else {
                ar[i] = left[l] <= right[r] ? left[l++] : right[r++];
            }
        }
        return ar;
    }

    @Override
    public void sort(int[] values) {
        MergeSort.mergeSort(values);
    }
}
