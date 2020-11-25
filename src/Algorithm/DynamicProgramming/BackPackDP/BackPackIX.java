package Algorithm.DynamicProgramming.BackPackDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/9 23:52
 *   @Description :
 *      You have a total of n thousand yuan, hoping to apply for a university abroad. The application is required to pay a certain fee.
 *       Give the cost of each university application and the probability of getting the University's offer, and the number of university is
 *      m. If the economy allows, you can apply for multiple universities. Find the highest probability of receiving at least one offer.
 *      Example 1:
	    Input:
                n = 10
                prices = [4,4,5]
                probability = [0.1,0.2,0.3]
            Output:  0.440

            Explanation：
            select the second and the third school.


        Example 2:
            Input:
                n = 10
                prices = [4,5,6]
                probability = [0.1,0.2,0.3]
            Output:  0.370

            Explanation:
            select the first and the third school.
 */
public class BackPackIX {

    public double backPack(int[] prices, double[] probabilities, int n){
        double[] dp = new double[10010];
        // get the probabilities of not getting this offer
        for(int i = 0; i < probabilities.length; i++){
            dp[i] = 1.0;
        }
        for(int i = 0; i < probabilities.length; i++){
            probabilities[i] = 1 - probabilities[i];
        }
        for(int i = 0; i < probabilities.length; i++){
            for(int j = n; j >= prices[i]; j--){
                dp[j] = Math.min(dp[j], dp[j - prices[i]] * probabilities[i]);
            }
        }
        return 1.0 - dp[n];
    }
}
