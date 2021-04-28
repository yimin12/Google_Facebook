package Contest.NineChapter.HighFreq.String;

/**
 * Description
 * Check a positive number is a palindrome or not.
 *
 * A palindrome number is that if you reverse the whole number you will get exactly the same number.
 *
 * It's guaranteed the input number is a 32-bit integer, but after reversion, the number may exceed the 32-bit integer.
 *
 * Example
 * Example 1:
 *
 * Input:11
 * Output:true
 *
 * Example 2:
 *
 * Input:1232
 * Output:false
 * Explanation:
 * 1232!=2321
 */
public class PalindromeNumber {

    public boolean isPalindrome(int num){
        if(num <= 0) return false;
        int temp = 0, cur = num;
        while(cur > 0){
            temp = temp * 10 + cur % 10;
            cur /= 10;
        }
        return temp == num;
    }
}
