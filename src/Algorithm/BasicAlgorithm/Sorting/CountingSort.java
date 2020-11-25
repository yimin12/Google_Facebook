package Algorithm.BasicAlgorithm.Sorting;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 13:46
 *   @Description :
 *      Idea is similar with bucket sort
 */
public class CountingSort extends InplaceSort{

    // Sorts values in the range of [minVal, maxVal] in O(n+maxVal-maxVal)
    // It is not recommended when the range is large
    private static void countingSort(int[] array, int min, int max){
        // size may be extremely large
        int size = max - min + 1;
        int[] b = new int[size];
        for(int i = 0; i < array.length; i++){
            b[array[i] - min]++;
        }
        for(int i = 0, k = 0; i < b.length; i++){
            while(b[i]-- > 0) array[k++] = i + min;
        }
    }

    @Override
    public void sort(int[] values) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < values.length; i++){
            if(values[i] < min) min = values[i];
            if(values[i] > max) max = values[i];
        }
        CountingSort.countingSort(values, min, max);
    }

    public static void main(String[] args) {
        CountingSort sorter = new CountingSort();
        int[] nums = {+4, -10, +0, +6, +1, -5, -5, +1, +1, -2, 0, +6, +8, -7, +10};
        sorter.sort(nums);

        // Prints:
        // [-10, -7, -5, -5, -2, 0, 0, 1, 1, 1, 4, 6, 6, 8, 10]
        System.out.println(java.util.Arrays.toString(nums));
    }
}
