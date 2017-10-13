import org.junit.Test;
 
import static org.junit.Assert.*;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
import java.util.List;
 
public class TestBettingLimit {
 
    @Test
    public void testBetLimit() throws IOException {
 
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
 
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();
 
        Player player = new Player("Fred", 100);
        Game game = new Game(d1, d2, d3);
        List<DiceValue> cdv = game.getDiceValues();
 
        while (true) {
 
            for (int i = 0; i < 100; i++) {
                String name = "Fred";
                int balance = 100;
                int limit = 0;
                player = new Player(name, balance);
                player.setLimit(limit);
                int bet = 5;
 
                int turn = 0;
                while (player.balanceExceedsLimitBy(bet) && player.getBalance() <200) {
                    turn++;
                    DiceValue pick = DiceValue.getRandom();
 
                    System.out.printf("Turn %d: %s bet %d on %s\n", turn,player.getName(), bet, pick);
 
                    int winnings = game.playRound(player, pick, bet);
                    cdv = game.getDiceValues();
 
                    System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1),cdv.get(2));
 
                    if (winnings > 0) {
                        System.out.printf("%s won %d, balance now %d\n\n",player.getName(), winnings,
                                player.getBalance());
 
                    } else {
                        System.out.printf("%s lost, balance now %d\n\n",player.getName(), player.getBalance());
                    }
 
                } // while
                assertEquals(player.getBalance(), 0); // check the final balance is zero.
 
            } // for
 
            String ans = console.readLine();
            if (ans.equals("q"))
                break;
        } // while true
 
    }
 
}

