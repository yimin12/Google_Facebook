package Algorithm.BitManipulation;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/4 23:25
     @Description :
     *  Given k * n + 1 numbers, every numbers occurs twice except one, find it.
        Example 1:
	        Input:  [1,2,2,3,4,4,5,3]
	        Output:  [1,5]

        Example 2:
	        Input: [1,1,2,3,4,4]
	        Output:  [2,3]
 */
public class SingleNumberII {

    // when the k is even, xor can help explode when the k is even
    public int[] singleEven(int[] A){
        if(A == null || A.length == 0){
            return new int[0];
        }
        int xor = 0;
        for(int i : A){
            xor ^= i;
        }
        int lastBit = xor - (xor & (xor - 1));
        int groupOne = 0, groupTwo = 0;
        for(int i:A){
            if((i&lastBit) == 0){
                groupOne ^= i;
            } else {
                groupTwo ^= i;
            }
        }
        return new int[]{groupOne, groupTwo};
    }

    // when the k is odd, no idea
}
