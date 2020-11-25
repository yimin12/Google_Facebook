package Algorithm.BasicAlgorithm.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 13:24
 *   @Description :
 *      Bucket represent a small range of total range, choose suitable bucket for current value. If each values distributes uniformly, it will consume linear time
 */
public class BucketSort extends InplaceSort {

    // Performs a bucket sort of an array in which all the elements are bounded in the range [minValue, maxValue].
    // For bucket sort linear performance the elements need to uniformly distributed
    private static void bucketSort(int[] array, int min, int max){
        if(array == null || array.length == 0 || min == max) return;
        // N is number elements and M is the range of values
        final int N = array.length, M = max - min + 1, numBuckets = M/N + 1;
        List<List<Integer>> buckets = new ArrayList<>(numBuckets);
        // init
        for(int i = 0; i < numBuckets; i++){
            buckets.add(new ArrayList<>());
        }
        // Place each element into bucket
        for(int i = 0; i < N; i++){
            int index = (array[i] - min)/M;
            buckets.get(index).add(array[i]);
        }
        // Sort buckets and switch these answer to gether
        for(int i = 0, j = 0; i < numBuckets; i++){
            List<Integer> bucket = buckets.get(i);
            if(bucket.size() != 0){
                Collections.sort(bucket);
                for(int k = 0; k < bucket.size(); k++){
                    array[j++] = bucket.get(k);
                }
            }
        }
    }
    @Override
    public void sort(int[] values) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < values.length; i++){
            // narrow the bounder to find the range
            if(values[i] < min) min = values[i];
            if(values[i] > max) max = values[i];
        }
        BucketSort.bucketSort(values, min, max);
    }

    public static void main(String[] args) {
        BucketSort sorter = new BucketSort();

        int[] array = {10, 4, 6, 8, 13, 2, 3};
        sorter.sort(array);
        // Prints:
        // [2, 3, 4, 6, 8, 10, 13]
        System.out.println(java.util.Arrays.toString(array));

        array = new int[] {10, 10, 10, 10, 10};
        sorter.sort(array);
        // Prints:
        // [10, 10, 10, 10, 10]
        System.out.println(java.util.Arrays.toString(array));
    }
}
