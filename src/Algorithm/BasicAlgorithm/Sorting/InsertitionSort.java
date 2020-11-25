package Algorithm.BasicAlgorithm.Sorting;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 22:49
 *   @Description :
 *
 */
public class InsertitionSort extends InplaceSort {

    // Inplace sorting with O(n^2) time
    private static void insertionSort(int[] array){
        if(array == null) return;
        for(int i = 1; i < array.length; i++){
            for(int j = i; j > 0 && array[j] < array[j-1]; j--){
                swap(array, j-1, j);
            }
        }
    }

    @Override
    public void sort(int[] values) {
        InsertitionSort.insertionSort(values);
    }

}
