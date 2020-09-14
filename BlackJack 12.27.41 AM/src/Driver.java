public class Driver
{
    public static void main(String[] args)
    {
        BlackJack game = new BlackJack(5);

        game.addPlayer(new Player("Cat", 500));
        game.addPlayer(new Player("Jigglypuff", 300));
        game.addPlayer(new Player("defly.io", 100));

        game.playRound();
    }
}
