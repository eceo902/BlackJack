import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> cards;

    public Deck()
    {
        cards = new ArrayList<Card>();
        fill();
    }

    public void fill()
    {
        for (int rank = 2; rank < 15; rank++)
        {
            for (int suit = 0; suit < 4; suit++)
            {
                cards.add(new Card(rank, suit));
            }
        }

        Collections.shuffle(cards);
    }

    public Card deal()
    {
        if(cards.size() == 0)
            fill();

        return cards.remove(0);
    }
}
