import java.util.ArrayList;

public class Player
{
    private String name;
    private double cash;
    private ArrayList<Hand> hands;

    public Player(String name, double cash)
    {
        this.name = name;
        this.cash = cash;
        hands = new ArrayList<Hand>();
        hands.add(new Hand());
    }

    public void reset()
    {
        hands = new ArrayList<Hand>();
    }

    public void hit(Card card)
    {
        hit(0, card);
    }

    public void hit(int i, Card card)
    {
        hands.get(i).hit(card);
    }

    public void split()
    {
        Hand one = hands.get(0);
        Hand two = new Hand();

        two.hit(one.getCards().remove(1));

        hands.add(two);
    }

    public boolean canSplit()
    {
        return hands.size() == 1 && hands.get(0).canSplit();
    }

    public String toString()
    {
        return name + ": " + hands.toString().substring(1, hands.toString().length() - 1) + " $" + cash;
    }

    public double getCash()
    {
        return cash;
    }

    public void setCash(double cash)
    {
        this.cash = cash;
    }

    public ArrayList<Hand> getHands()
    {
        return hands;
    }

    public String getName()
    {
        return name;
    }
}
