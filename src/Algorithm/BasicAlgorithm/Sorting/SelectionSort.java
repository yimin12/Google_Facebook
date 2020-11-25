package Algorithm.BasicAlgorithm.Sorting;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 23:03
 *   @Description :
 *
 */
public class SelectionSort extends InplaceSort {

    private static void selectionSort(int[] array){
        if(array == null) return;
        for(int i = 0; i < array.length; i++){
            int swapIdx = i;
            for(int j = i + 1; j < array.length; j++){
                if(array[j] < array[swapIdx]){
                    swapIdx = j;
                }
            }
            swap(array, swapIdx, i);
        }
    }

    @Override
    public void sort(int[] values) {
        SelectionSort.selectionSort(values);
    }
}
