package Algorithm.BitManipulation;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/4 22:59
 *   @Description :
 *      Given k * n + 1 numbers, every numbers occurs twice except one, find it.
        Input：[1,1,2,2,3,4,4]
        Output：3
        Explanation:
        Only 3 appears once
 */
public class SingleNumberI {

    // when the k is even, xor can help explode when the k is even
    public int singleEven(int k, int[] A){
        if(A == null || A.length == 0){
            return -1;
        }
        int res = 0;
        for(int i : A){
            res ^= i;
        }
        return res;
    }
    // when the k is odd, xor can not be used and we try more general way
    public int singleOdd(int k, int[] A){
        if(A == null || A.length == 0){
            return -1;
        }
        int res = 0;
        int[] bits = new int[32];
        for(int i = 0; i < 32; i++){
            for(int j : A){
                bits[i] += j >> i & 1; // bit tester
                bits[i] %= k;
            }
            res |= (bits[i] << i); // bit setter, no matter 1 or 0
        }
        return res;
    }
}
