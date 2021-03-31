package Contest.GodLeft.basic.sorting;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/28 17:52
 *   @Description :
 *
 */
public class MergeSort {

    public static void mergeSort(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        int[] helper = new int[array.length];
        mergeSort(array, 0, array.length - 1, helper);
    }

    public static void mergeSort(int[] array, int left, int right, int[] helper){
        if(left == right) return;
        int mid = left + ((right - left) >> 1);
        mergeSort(array, left, mid, helper);
        mergeSort(array, mid + 1, right, helper);
        merge(array, left, mid, right,helper);
    }

    public static void merge(int[] array, int left, int mid, int right, int[] helper){
        int i = left;
        int leftBound = left;
        int rightBound = mid + 1;
        while(leftBound <= mid && rightBound <= right){
            helper[i ++ ] = array[leftBound] < array[rightBound] ? array[leftBound ++] : array[rightBound ++];
        }
        // post processing
        while(leftBound <= mid){
            helper[i ++] = array[leftBound ++];
        }
        while(rightBound <= right){
            helper[i ++] = array[rightBound ++];
        }
        for(i = left; i <= right; i ++){
            array[i] = helper[i];
        }
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        mergeSort(arr);
        printArray(arr);

    }
}
