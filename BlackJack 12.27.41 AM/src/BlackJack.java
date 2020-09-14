import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack
{
    private double bet;
    private Deck deck;
    private Player dealer;
    private ArrayList<Player> players;

    public BlackJack(double bet)
    {
        this.bet = bet;
        deck = new Deck();
        dealer = new Player("Dealer", 0);
        players = new ArrayList<Player>();
        players.add(dealer);

    }

    public void addPlayer(Player player)
    {
        players.add(0, player);
    }

    public void playRound()
    {
        System.out.println("\n ~~ New Round! ~~");

        // take bets
        for(Player player : players)
        {
            if(player != dealer)
                player.setCash(player.getCash() - bet);
        }

        // deal cards
        for (int i = 0; i < 2; i++)
        {
            for (Player player : players)
                player.hit(deck.deal());
        }

        for(Player player : players)
        {
            if(player != dealer)
                System.out.println(player);
        }

        System.out.println("Dealer: " + dealer.getHands().get(0).getCards());

        for(Player player : players)
            if(player != dealer)
                for (int i = 0; i < player.getHands().size(); i++)
                    playerTurn(player, player.getHands().get(i));

        // dealer turn
        Hand dealerHand = dealer.getHands().get(0);

        System.out.println("\n-- Dealer's Turn! --");
        System.out.println();

        while(dealerHand.getValue() < 17)
        {
            dealerHand.hit(deck.deal());
            System.out.println(dealerHand);
        }

        if(dealerHand.isBusted())
            System.out.println("Dealer BUSTED!");
        else if(dealerHand.has21())
            System.out.println("Dealer has 21!");

        System.out.println();
        for(Player player : players)
            if(player != dealer)
                for(Hand hand : player.getHands())
                {
                    double winnings = 0;

                    if(!hand.isBusted())
                    {
                        if(hand.hasBlackjack())
                            winnings = bet * 1.5;
                        else if(hand.getValue() > dealerHand.getValue() || dealerHand.isBusted())
                            winnings = bet * 2;
                        else if(hand.getValue() == dealerHand.getValue())
                            winnings= bet;
                    }

                    if(hand.isDoubleDown())
                        winnings += 2;

                    player.setCash(player.getCash() + winnings);
                    System.out.println(player.getName() + " won $" + winnings + "! ):(");
                }
    }

    private void playerTurn(Player player, Hand hand)
    {
        String name = player.getName();

        System.out.println("\n-- " + name + "'s Turn! --");
        System.out.println(hand);

        Scanner input = new Scanner(System.in);

        boolean done = false;

        if(hand.hasBlackjack())
        {
            System.out.println(name + " has BLACKJACK");
            done = true;
        }

        while(!done)
        {
            System.out.println("Choose one:");
            System.out.println("1) Hit");
            System.out.println("2) Stand");

            if(hand.canDoubleDown())
                System.out.println("3) Double Down");

            if(hand.canSplit())
                System.out.println("4) Split");

            int choice = input.nextInt();

            if(choice == 1)
            {
                hand.hit(deck.deal());
            }
            else if(choice == 2)
            {
                done = true;
            }
            else if(choice == 3 && hand.canDoubleDown() && player.getCash() >= bet)
            {
                hand.setDoubleDown(true);
                player.setCash(player.getCash() - bet);
                hand.hit(deck.deal());
                done = true;
            }
            else if(choice == 4 && hand.canSplit() && player.canSplit() && player.getCash() >= bet)
            {
                player.setCash(player.getCash() - bet);
                player.split();
            }
            else
            {
                System.out.println("Not a valid input, reenter.");
            }

            System.out.println(hand);

            if(hand.isBusted())
            {
                System.out.println(name + " BUSTED!");
                done = true;
            }
            else if(hand.has21())
            {
                System.out.println(name + " has 21!");
                done = true;
            }
        }
    }
}
