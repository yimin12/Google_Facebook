package Object_Orient_Design.BlackJack_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 21:33
 *   @Description :
 *
 */
public class Dealer {

    private BlackJack game;
    private Hand hand;
    private int bets;

    public Dealer() {
        hand = new Hand();
        bets = 10000;
    }

    public void insertCard(Card card) {
        hand.insertCard(card);
    }

    public boolean largerThan(NormalPlayer p) {
        return hand.getBestValue() >= p.getBestValue();
    }

    public void updateBets(int amount) {
        bets += amount;
    }

    public void setGame(BlackJack game) {
        this.game = game;
    }

    public void dealNextCard() {
        insertCard(game.dealNextCard());
    }

    public String printDealer() {
        return "Dealer " + hand.printHand() + ", total bets: " + bets + "\n";
    }
}
