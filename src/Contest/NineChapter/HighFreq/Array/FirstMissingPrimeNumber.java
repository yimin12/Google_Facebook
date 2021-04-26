package Contest.NineChapter.HighFreq.Array;

/**
 * Description
 * Given a list of prime numbers and find the smallest prime number that doesn't appear in this list.
 *
 * Example
 * Example1
 *
 * Input: [3,5,7]
 * Output: 2
 * Example2
 *
 * Input: [2,3,5,7,11,13,17,23,29]
 * Output: 19
 */
public class FirstMissingPrimeNumber {

    public int firstMissingPrime(int[] num){
        int size = num.length;
        if(size == 0) return 2;
        int last = num[size - 1], max = last;
        boolean[] isPrime = new boolean[max];
        for(int i = 0; i < max; i ++){
            isPrime[i] = true;
        }
        int count = 0;
        int i = 2;
        for(; i < max; i ++){
            if(isPrime[i]){
                if(count < size && i == num[count]){
                    count ++;
                } else {
                    if(count < size){
                        return i;
                    } else {
                        break;
                    }
                }
            }
            for(int j = 2; (i * j) < max; j ++){
                isPrime[j * i] = false;
            }
        }
        i ++;
        while(!isPrime(i)){
            i ++;
        }
        return i;
    }

    private boolean isPrime(int num){
        for(int i = 2; i <= Math.sqrt(num); i ++){
            if(num % i == 0) return false;
        }
        return true;
    }
}
