package Contest.NineChapter.HighFreq.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Prime factorize a given integer.
 *
 * You should sort the factors in ascending order.
 *
 * Example
 * Example 1:
 *
 * Input: 10
 * Output: [2, 5]
 * Example 2:
 *
 * Input: 660
 * Output: [2, 2, 3, 5, 11]
 */
public class PrimeFactorization {

    public List<Integer> primeFactorization(int num) {
        List<Integer> factors = new ArrayList<>();
        for(int i = 2; i * i <= num; i ++){
            while(num % i == 0){
                num = num / i;
                factors.add(i);
            }
        }
        if(num != 1){
            factors.add(num);
        }
        return factors;
    }
}
