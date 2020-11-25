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
public class BlackJack {
    private List<NormalPlayer> players;
    private Dealer dealer;

    private List<Card> cards;

    public BlackJack() {
        players = new ArrayList<>();
        dealer = new Dealer();
    }

    public void initCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addPlayer(NormalPlayer p) {
        players.add(p);
    }


    public void dealInitialCards() {
        for (NormalPlayer player : players) {
            player.insertCard(dealNextCard());
        }

        dealer.insertCard(dealNextCard());

        for (NormalPlayer player : players) {
            player.insertCard(dealNextCard());
        }

        dealer.insertCard(dealNextCard());
    }

    public Card dealNextCard() {
        Card card = cards.remove(0);
        return card;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void compareResult() {
        for (NormalPlayer p : players) {
            if (dealer.largerThan(p)) {
                dealer.updateBets(p.getCurrentBets());
                p.lose();
            } else {
                dealer.updateBets(-p.getCurrentBets());
                p.win();
            }
        }
    }

    public String print() {
        String s = "";
        for (NormalPlayer player : players) {
            s += "playerid: " + (player.getId() + 1) + " ;" + player.printPlayer();
        }
        return s;
    }
}
