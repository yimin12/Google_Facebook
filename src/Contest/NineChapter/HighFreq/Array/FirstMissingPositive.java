package Contest.NineChapter.HighFreq.Array;

/**
 * Description
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * Example
 * Example 1:
 *
 * Input:[1,2,0]
 * Output:3
 * Example 2:
 *
 * Input:[3,4,-1,1]
 * Output:2
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] A){
        if(A == null) return 1;
        for(int i = 0; i < A.length; i++){
            while(A[i] > 0 && A[i] < A.length && A[i] != (i + 1)){
                // Swap
                int temp = A[A[i] - 1];
                if(temp == A[i]) break;
                A[A[i] - 1] = A[i];
                A[i] = temp;
            }
        }
        for(int i = 0; i < A.length; i ++){
            if(A[i] != i + 1){
                return i + 1;
            }
        }
        return A.length + 1;
    }
}
