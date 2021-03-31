package Contest.GodLeft.basic;

import Contest.GodLeft.basic.sorting.Swap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/28 18:47
 *   @Description :
 *
 */
public class NetherlandsFlag {

    public static int[] sort(int[] array, int num){
        if(array == null || array.length == 0){
            return array;
        }
        return partition(array, 0, array.length - 1, num);
    }

    public static int[] partition(int[] array, int left, int right, int num){
        int less = left - 1;
        int more = right + 1;
        while(left < more){
            if(array[left] < num){
                Swap.swap(array, ++less, left++);
            } else if(array[left] > num){
                Swap.swap(array, left, --more);
            } else{
                // in the right position already
                left++;
            }
        }
        return new int[] {less + 1, more - 1};
    }

    // for test
    public static int[] generateArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 3);
        }
        return arr;
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

    public static void main(String[] args) {
        int[] test = generateArray();

        printArray(test);
        int[] res = partition(test, 0, test.length - 1, 1);
        printArray(test);
        System.out.println(res[0]);
        System.out.println(res[1]);

    }
}
