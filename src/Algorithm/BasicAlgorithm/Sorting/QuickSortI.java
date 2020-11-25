package Algorithm.BasicAlgorithm.Sorting;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 12:45
 *   @Description :
 *
 */
public class QuickSortI extends InplaceSort {

    public static void quicksort(int[] array){
        if(array == null) return;
        quicksort(array, 0, array.length - 1);
    }

    private static void quicksort(int[] array, int left, int right){
        if(left < right){
            int pivot = partition(array, left, right);
            quicksort(array, left, pivot);
            quicksort(array, pivot + 1, right);
        }
    }

    private static int partition(int[] array, int left, int right){
        int pivot = array[0]; // or use random generator
        int i = left - 1, j = right + 1; // start from -1 and array.length
        while(true){
            do{
                i++;
            } while(array[i] < pivot);
            do{
                j--;
            } while(array[j] > pivot);
            if(i < j) swap(array, i, j);
            else return j;
        }
    }

    @Override
    public void sort(int[] values) {
        QuickSortI.quicksort(values);
    }
}
