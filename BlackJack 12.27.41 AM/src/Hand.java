import java.util.ArrayList;

public class Hand
{
    private ArrayList<Card> cards;
    private boolean doubleDown;


    public Hand()
    {
        cards = new ArrayList<>();
    }

    public void hit(Card card)
    {
        cards.add(card);
    }

    /**
     * @return the BlackJack value of his hand.
     */
    public int getValue()
    {
        int value = 0, aces = 0;

        for(Card card : cards)
        {
            value += card.getValue();

            if(card.getRank() == 14)                        // card is an ace
                aces++;
        }

        while(value > 21 && aces > 0)
        {
            value -= 10;
            aces--;
        }

        return value;
    }

    public void setDoubleDown(boolean doubleDown)
    {
        this.doubleDown = doubleDown;
    }

    public boolean isDoubleDown()
    {
        return doubleDown;
    }

    public boolean isBusted()
    {
        return getValue() > 21;
    }

    public boolean has21()
    {
        return getValue() == 21;
    }

    public boolean hasBlackjack()
    {
        return has21() && cards.size() == 2;
    }

    public boolean canDoubleDown()
    {
        return cards.size() == 2;
    }

    public boolean canSplit()
    {
        return canDoubleDown() && cards.get(0).getRank() == cards.get(1).getRank();
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public String toString()
    {
        return cards.toString();
    }
}
