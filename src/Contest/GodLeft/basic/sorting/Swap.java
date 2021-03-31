package Contest.GodLeft.basic.sorting;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/27 23:16
 *   @Description :
 *
 */
public class Swap {

    public static void main(String[] args) {
        int[] array = {-1, 1, 2};
        swap(array, 0, 2);
        Arrays.stream(array).forEach(System.out::println);
    }

    public static void swap(int[] array, int left, int right){
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
