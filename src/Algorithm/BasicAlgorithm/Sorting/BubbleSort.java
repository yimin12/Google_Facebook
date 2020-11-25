package Algorithm.BasicAlgorithm.Sorting;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 22:56
 *   @Description :
 *
 */
public class BubbleSort extends InplaceSort {

    private static void bubbleSort(int[] array){
        if(array == null){
            return;
        }
        boolean sorted;
        do{
            sorted = true;
            for(int i = 1; i < array.length; i++){
                if(array[i] < array[i-1]){
                    swap(array, i, i-1);
                    sorted = false;
                }
            }
        } while (!sorted);
    }

    @Override
    public void sort(int[] values) {
        BubbleSort.bubbleSort(values);
    }
}
