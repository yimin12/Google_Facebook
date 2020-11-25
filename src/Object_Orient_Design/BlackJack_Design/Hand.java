package Object_Orient_Design.BlackJack_Design;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 21:32
 *   @Description :
 *
 */
public class Hand {

    private List<Card> cards;
    public Hand(){
        cards = new ArrayList<>();
    }

    private List<Integer> getPossibleValues(){
        List<Integer> results= new ArrayList<>();
        int aceCount = 0;
        int resultWithoutAce = 0;
        for(Card card:cards){
            if(card.getValue() == 1){
                aceCount++;
            } else if(card.getValue() == 11 || card.getValue() == 12 || card.getValue() == 13){
                resultWithoutAce += 10;
            } else {
                resultWithoutAce += card.getValue();
            }
        }
        for(int i = 0; i <= aceCount; i++){
            int ones = i;
            int elevens = aceCount - i;
            results.add(resultWithoutAce + ones + elevens *11);
        }
        return results;
    }
    public int getBestValue(){
        List<Integer> results = getPossibleValues();
        int maxUnder = -1;
        for(int result : results){
            if(result <= 21 && result > maxUnder){
                maxUnder = result;
            }
        }
        return maxUnder;
    }

    public void insertCard(Card card){
        cards.add(card);
    }

    public String printHand() {
        String res = "Cards: ";
        for (int i = 0; i < cards.size(); i++){
            res += (cards.get(i).getValue());
            if(i != cards.size() - 1){
                res+=" , ";
            }
            else res+=';';
        }

        res += " Current best value is: " + getBestValue();
        return res;
    }
}
