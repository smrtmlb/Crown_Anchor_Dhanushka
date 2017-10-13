
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

public class TestDiceValuesSame {

	@Test
	public void payLevelTest() throws IOException {

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		Dice d1 = new Dice();
		Dice d2 = new Dice();
		Dice d3 = new Dice();

		Player player = new Player("Fred", 100);
		Game game = new Game(d1, d2, d3);
		List<DiceValue> cdv;
		List<DiceValue> cdv2;
		boolean differentValues = true;

		while (true) {

			for (int i = 0; i < 100; i++) {
				String name = "Fred";
				int balance = 100;
				int limit = 0;
				player = new Player(name, balance);
				player.setLimit(limit);
				int bet = 5;

				int turn = 0;
				while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200) {
					turn++;
					DiceValue pick = DiceValue.getRandom();

					System.out.printf("Turn %d: %s bet %d on %s\n", turn, player.getName(), bet, pick);

					int winnings = game.playRound(player, pick, bet);
					cdv = game.getDiceValues();
					cdv2 = game.getDiceValues();

					System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));

					System.out.printf("Rolled Again %s, %s, %s\n", cdv2.get(0), cdv2.get(1), cdv2.get(2));

					// Compare values in each time.
					if (cdv2.get(0).equals(cdv.get(0)) && cdv2.get(1).equals(cdv.get(1))
							&& cdv2.get(2).equals(cdv.get(2))) {

						differentValues = false;
					}

					if (winnings > 0) {
						System.out.printf("%s won %d, balance now %d\n\n", player.getName(), winnings,
								player.getBalance());

					} else {
						System.out.printf("%s lost, balance now %d\n\n", player.getName(), player.getBalance());
					}

				} // while

			} // for

			assertTrue("Similar Dice values return every time", differentValues);
			System.out.println("Similar values return....");

			String ans = console.readLine();
			if (ans.equals("q"))
				break;
		}

		// while true

	}

}
