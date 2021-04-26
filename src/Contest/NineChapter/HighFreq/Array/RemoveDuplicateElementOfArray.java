package Contest.NineChapter.HighFreq.Array;

import java.util.Arrays;

public class RemoveDuplicateElementOfArray {

    /**
     * sorted array, duplicate element only return one
     */
    public int[] dedup(int[] array){
        if(array == null || array.length == 0) return array;
        int end = 0;
        for(int i = 1; i < array.length; i ++){
            if(array[i] != array[end]){
                array[++end] = array[i];
            }
        }
        return Arrays.copyOf(array, end + 1);
    }

    /**
     * sorted array, duplicate element only retain two
     */
    public int[] dedupII(int[] array){
        if(array.length <= 2){
            return array;
        }
        int end = 1;
        for(int i = 2; i < array.length; i ++){
            if(array[i] != array[end - 1]){
                array[++end] = array[i];
            }
        }
        return Arrays.copyOf(array, end + 1);
    }

    /**
     * sorted array, duplicate element not retain any
     */
    public int[] dedupIII(int[] array){
        if(array == null || array.length <= 1){
            return array;
        }
        int end = 0;
        boolean flag = false;
        for(int i = 1; i < array.length; i ++){
            if(array[i] == array[end]){
                flag = true;
            } else if(flag == true){
                array[end] = array[i];
                flag = false;
            } else {
                array[++end] = array[i];
            }
        }
        return Arrays.copyOf(array, flag ? end : end + 1);
    }

    /**
     * Unsorted array, repeatedly deduplication
     */
    public int[] dedupIV(int[] array){
        int end = -1;
        for(int i = 0; i < array.length; i ++){
            if(end == -1 || array[end] != array[i]){
                array[++end] = array[i];
            } else {
                while(i + 1 < array.length && array[i + 1] == array[end]){
                    i ++;
                }
                end --;
            }
        }
        return Arrays.copyOf(array, end + 1);
    }
}
