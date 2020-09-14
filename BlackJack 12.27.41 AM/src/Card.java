public class Card
{
    private int rank;
    private int suit;

    private static final String[] ranks = {"", "", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private static final String[] suits = {"♥", "♦", "♣", "♠"};

    /**
     * Precondition: 2 <= rank <= 14
     *               0 <= suit <= 3
     *
     * @param rank
     * @param suit
     */
    public Card(int rank, int suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank()
    {
        return rank;
    }

    /**
     * @return a String representation of this Card in
     *          the format ranksuit.
     *          Examples: J
     *                    A
     */
    public String toString()
    {
        return ranks[rank] + suits[suit];
    }

    public int getSuit()
    {
        return suit;
    }

    /**
     * @return the BlackJack value of this card
     *          [2, 10] -> [2, 10]
     *          [J, K] -> 10
     *          A -> 11
     */
    public int getValue()
    {
        if(rank <= 10)
            return rank;
        if(rank == 14)
            return 11;
        else
            return 10;
    }


}
