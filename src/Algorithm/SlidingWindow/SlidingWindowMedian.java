package Algorithm.SlidingWindow;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/2 14:51
 *   @Description :
 *      Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
        Examples:
        [2,3,4], the median is3
        [2,3], the median is(2 + 3) / 2 = 2.5
        Given an arraynums, there is a sliding window of sizekwhich is moving from the very left of the array to the very right. You can only see theknumbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
        For example,
        Givennums=[1,3,-1,-3,5,3,6,7], andk= 3.
        Window position                Median
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       1
         1 [3  -1  -3] 5  3  6  7       -1
         1  3 [-1  -3  5] 3  6  7       -1
         1  3  -1 [-3  5  3] 6  7       3
         1  3  -1  -3 [5  3  6] 7       5
         1  3  -1  -3  5 [3  6  7]      6
        Therefore, return the median sliding window as[1,-1,-1,3,5,6].
 */
public class SlidingWindowMedian {

    // Assume that k is always valid and k is always smaller than input array's size for non-empty
    /*
    Implementation - Two priority queues:
        A max-heap to store the smaller half of the numbers
        A min-heap to store the larger half of the numbers
        If k = 2*n + 1 (∀n∈Z), then max-heap is allowed to hold n+1 elements, while min-heap can hold n elements.
        If k = 2*n (∀n∈Z), then both heaps are balanced and hold nn elements each.
        Complexity
        Time:
        add() - O(logk)
        remove() - O(logk + k) ~ O(k). Due to remove(Object o) is O(k)
        total - O(n * k)
        Space: O(k)
     */
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    public double[] medianSlidingWindow(int[] nums, int k){
        int len = nums.length - k + 1;
        if(len <= 0){
            return new double[0];
        }
        double[] result = new double[len];
        for(int i = 0; i < nums.length; i++){
            if(i >= k){
                result[i - k] = getMedian();
                remove(nums[i - k]);
            }
            if(i < nums.length){
                add(nums[i]);
            }
        }
        return result;
    }
    private void add(int num){
        if(num > getMedian()){
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }
        rebalance();
    }
    // it will take O(n) to remove one element
    private void remove(int num){
        if(num > getMedian()){
            minHeap.remove(num);
        } else {
            maxHeap.remove(num);
        }
        rebalance();
    }
    private double getMedian(){
        if(minHeap.isEmpty() && maxHeap.isEmpty()){
            return 0;
        }
        // case 1: even number of elements
        if(minHeap.size() == maxHeap.size()){
            return ((double)minHeap.peek() + (double)maxHeap.peek())/2.0;
        }
        // case 2: odd number of elements
        return (double)maxHeap.peek();
    }
    private void rebalance(){
        if(minHeap.size() > maxHeap.size()){
            maxHeap.add(minHeap.poll());
        } else if(maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
    }
    // 更优化的方案是使用Hash-Heap，也就是优化remove(Object o)这个O(n)的操作. 但是Java没有内置Hash-Heap，可以用TreeMap或者TreeSet来实现
    // Hash-Heap for remove is O(1), and TreeMap for remove is O(logn), both are better.
    // Version 2: TreeMap
    public double[] medianSlidingWindowI(int[] nums, int k){
        if(nums == null || nums.length == 0 || k <= 0){
            return new double[0];
        }
        double[] res = new double[nums.length - k + 1];
        int index = 0;
        boolean useBoth = k % 2 == 0;
        // maxTreeMap
        TreeMap<Integer, Integer> small = new TreeMap<>((a, b) -> b - a);
        int smallSize = 0;
        // MinTreeMap
        TreeMap<Integer, Integer> big = new TreeMap<>();
        int bigSize = 0;
        for(int i = 0; i < nums.length; i++){
            // pop the very first element of the window
            if(smallSize + bigSize == k){
                if(nums[i - k] <= small.firstKey()){
                    remove(small, nums[i-k]);
                    smallSize--;
                } else {
                    remove(big, nums[i-k]);
                    bigSize--;
                }
            }
            // add the current element to the window
            if(smallSize <= bigSize){
                add(small, nums[i]);
                smallSize++;
            } else {
                add(big, nums[i]);
                bigSize++;
            }

            // rebalance
            if(bigSize > 0){
                while (small.firstKey() > big.firstKey()){
                    // swap their top element if disorder
                    add(big, remove(small ,small.firstKey()));
                    add(small, remove(big, big.firstKey()));
                }
            }
            // add the result
            if(smallSize + bigSize == k){
                if(useBoth) res[index++] = ((double)small.firstKey() + big.firstKey())/2.0;
                else res[index++] = (double)small.firstKey();
            }
        }
        return res;
    }
    private int remove(TreeMap<Integer, Integer> map, int i){
        map.put(i, map.get(i) - 1);
        if(map.get(i) == 0) map.remove(i);
        return i;
    }
    private void add(TreeMap<Integer, Integer> map, int i){
        if(!map.containsKey(i)) map.put(i, 1);
        else map.put(i, map.get(i) + 1);
    }
}
