package Algorithm.Randomization;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 14:39
 *   @Description :
 *      In reality, if you shuffle for a combination of card, the probability should be 1/(52!). Simulate this process and assume that you have
 *      the random generator for [0,1)
 */
public class PerfectCardShuffle {

    // Method 1: recursively
    public int rand(int lower, int higher){
        return lower + (int)(Math.random() * (higher - lower + 1));
    }
    int[] perfectShuffle(int[] card, int i){
        if(i == 0){
            return card;
        }
        perfectShuffle(card, i - 1);
        int k = rand(0, i);
        int temp = card[k];
        card[k] = card[i];
        card[i] = temp;

        return card;
    }

    // Method 2: Iteratively (Fisher_Yates Algorithm)
    public int[] perfectShuffleIter(int[] card, int len){
        if (len <= 0){
            return card;
        }
        int i = len, j, temp;
        while(--i >= 0){
            j = (int) (Math.random() % (i + 1));
            temp = card[i];
            card[i] = card[j];
            card[j] = temp;
        }
        return card;
    }
}
