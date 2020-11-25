package Algorithm.BasicAlgorithm.Sorting;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 14:05
 *   @Description :
 *      Heapsort is a comparison-based sorting algorithm that uses a binary heap data structure. Like mergesort, heapsort has a running time of O ( n log n ) ,
 *       O(n\log n), O(nlogn), and like insertion sort, heapsort sorts in-place, so no extra space is needed during the sort
 */
public class HeapSort extends InplaceSort{

    private static void heapSort(int[] array){
        if(array == null) return;
        int n = array.length;

        // Heapify, converts array into binary heap O(n)
        // Percolate down from the end will be more efficient than we do it in reversed way
        for(int i = Math.max(0, (n>>1)-1); i >= 0; i--){
            percolateDown(array, n, i);
        }

        // Sorting bit O(n*logn)
        for(int i = n - 1; i >= 0; i--){
            swap(array, 0, i);
            percolateDown(array, i, 0);
        }
    }

    private static void percolateDown(int[] array, int n, int i){
        while(true){
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;
            if(right < n && array[right] > array[largest]) largest = right;
            if(left < n && array[left] > array[largest]) largest = left;
            if(largest != i){
                swap(array, i, largest);
                i = largest;
            } else break;
        }
    }

    @Override
    public void sort(int[] values) {
        HeapSort.heapSort(values);
    }

    public static void main(String[] args) {
        InplaceSort sorter = new HeapSort();
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        sorter.sort(array);
        // Prints:
        // [-13, 2, 3, 4, 4, 6, 8, 10]
        System.out.println(java.util.Arrays.toString(array));
    }
}
