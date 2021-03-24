package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 16:45
 *   @Description :
 *
 */

import Object_Orient_Design.Design_Pattern.Factory.FactoryMethod.Phone;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定数组 arr 和整数 num，共返回有多少个子数组满足如下情况: max(arr[i..j]) - min(arr[i..j]) <= num max(arr[i..j])表示子数组 arr[i..j]中的最大值，min(arr[i..j])表示子数组 arr[i..j]中的最小值。
 * 【要求】 如果数组长度为 N，请实现时间复杂度为 O(N)的解法。
 */
public class AllLessNumSubArray {

    public static int getCount(int[] array, int num){
        if(array == null || array.length == 0){
            return 0;
        }
        Deque<Integer> minStack = new LinkedList<Integer>();
        Deque<Integer> maxStack = new LinkedList<Integer>();
        int i = 0, j = 0, res = 0;
        // sliding window, and j is the right bounder and i is the left bounder
        while(i < array.length){
            while( j < array.length ) {
                // case 1: maintain min stack
                while(!minStack.isEmpty() && array[minStack.peekLast()] >= array[j]){
                    minStack.pollLast();
                }
                minStack.offerLast(j);
                // case 2: maintain max stack
                while(!maxStack.isEmpty() && array[maxStack.peekLast()] <= array[j]){
                    maxStack.pollLast();
                }
                maxStack.offerLast(j);
                if(array[maxStack.peekFirst()] - array[minStack.peekFirst()] > num){
                    break;
                }
                j ++;
            }
            if(minStack.peekFirst() == i){
                minStack.pollFirst();
            }
            if(maxStack.peekFirst() == i){
                maxStack.pollFirst();
            }
            res += j - i;
            i ++;
        }
        return res;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(getCount(arr, num));

    }
}
