package Algorithm.BasicAlgorithm.Sorting;

import java.util.Random;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 12:52
 *   @Description :
 *      QuickSort3 or Dutch National Flag algorithm is similar to the QuickSort algorithm but has an
 *      improved partitioning algorithm. QuickSort is quite slow in the case where very few unique
 *      elements exist in the array so the QuickSort3 algorithm is used at that time.
 */
public class QuickSortII extends InplaceSort{

    private static Random random = new Random();

    public static void quickSort3(int[] values){
        if(values == null) return;
        quickSort3(values, 0, values.length - 1); // randomize quick sort
    }

    private static void quickSort3(int[] values, int left, int right){
        if(left >= right){
            return;
        }
        int pivotIndex = random.nextInt(right - left + 1) + left; // choose pivot in domain of [left, right)
        swap(values, pivotIndex, left); // swap the chosen pivot to the left
        int[] m = partition3(values, left, right); // pre order sorting
        // divide into sub case, handle the duplicate pivot problem
        quickSort3(values, left, m[0]);
        quickSort3(values, m[1], right);
    }

    // Partition array in such way that all the elements whose value is equal to pivot are grouped together
    // Note: Java can not return tuple like python, elements between j and k are the same elements as the pivot.
    // If all elements are unique, no need to use array as return type
    private static int[] partition3(int[] values, int left, int right){
        int j, k;
        // base case
        if(right - left <= 1){
            if(values[right] < values[left]){
                swap(values, left, right);
            }
            j = left;
            k = right;
            int[] m = {j, k};
            return m;
        }
        int cursor = left+1;
        int pivot = values[left];
        // Same as rainbow sort
        while(cursor <= right){
            if(values[cursor] < pivot){
                swap(values, cursor, left);
                left++;
                cursor++;
            } else if (values[cursor] == pivot){
                cursor++;
            } else {
                swap(values, cursor, right);
                right--;
            }
        }
        j = left - 1;
        k = cursor;
        int[] m = {j, k};
        return m;
    }

    @Override
    public void sort(int[] values) {
        QuickSortII.quickSort3(values);
    }

    public static void main(String[] args) {
        InplaceSort sorter = new QuickSortII();
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3, 3};
        sorter.sort(array);
        // Prints:
        // [-13, 2, 3, 4, 4, 6, 8, 10]
        System.out.println(java.util.Arrays.toString(array));
    }
}
