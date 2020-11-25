package Algorithm.BasicAlgorithm.Sorting;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 22:48
 *   @Description :
 *
 */
public abstract class InplaceSort {

    public abstract void sort(int[] values);

    public static void swap(int[] values, int i, int j){
        int tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
}
