package Algorithm.BasicAlgorithm.Sorting;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/14 13:54
 *   @Description :
 *      <p>See https://en.wikipedia.org/wiki/Radix_sort for details on runtime and complexity Radix sorts
 *      operates in O(nw) time, where n is the number of keys, and w is the key length where w is
 *      constant on primitive types like Integer which gives it a better performance than other
 *      compare-based sort algorithms, like i.e. QuickSort
 *      <p>Time Complexity: O(nw)
 */
public class RadixSort extends InplaceSort{

    static int getMax(int[] array){
        int max = array[0];
        for(int i : array){
            if(i > max){
                max = i;
            }
        }
        return max;
    }

    static int calculateNumOfDigits(int num){
        return (int) Math.log10(num) + 1;
    }

    // Requires all number to be greater than 1
    public static void radixSort(int[] numbers){
        if(numbers == null || numbers.length <= 1){
            return;
        }
        int max = getMax(numbers);
        int numOfDigits = calculateNumOfDigits(max);
        int placeValue = 1;
        while(numOfDigits-- > 0){
            countSort(numbers, placeValue);
            placeValue *= 10;
        }
    }

    private static void countSort(int[] array, int placeValue){
        int range = 10;
        int[] frequency = new int[range];
        int[] sorted = new int[array.length];

        for(int i = 0; i < array.length; i++){
            int digit = (array[i]/placeValue) % range;
            frequency[digit]++;
        }

        for(int i = 1; i < range; i++){
            frequency[i] += frequency[i-1];
        }

        for(int i = array.length - 1; i >= 0; i--){
            int digit = (array[i] / placeValue) % range;
            sorted[frequency[digit] - 1] = array[i];
            frequency[digit]--;
        }
        System.arraycopy(sorted, 0, array, 0, array.length);
    }

    @Override
    public void sort(int[] values) {
        RadixSort.radixSort(values);
    }

    public static void main(String[] args) {
        InplaceSort sorter = new RadixSort();
        int[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7, 890, 1, 587};
        sorter.sort(numbers);
        // Prints:
        // [1, 7, 37, 68, 123, 134, 221, 387, 468, 587, 769, 890]
        System.out.println(java.util.Arrays.toString(numbers));
    }
}
