package automation;

import players.*;
import cards.*;
import tree.*;

public class BlackjackAutomated {
	private Deck deck;
	private PlayerPersonality player;
	private int trials;
	private int sum;

	public BlackjackAutomated(int limit, double percent, int trials) {
		deck = new Deck();
		player = new PlayerPersonality(limit, percent);
		this.trials = trials;
		sum = 0;
	}

	public static void main(String[] args) {
		BetTree bt = BlackjackAutomated.buildBetTree();
		// LimitTree lt = BlackjackAutomated.buildLimitTree();

		System.out.println("Max: " + bt.maxPercent());
		System.out.println("Trials: " + bt.maxTrials());
		System.out.println("Min: " + bt.minPercent());
		System.out.println("Trials: " + bt.minTrials());
	}

	public Player getPlayer() {
		return player;
	}

	public int getTrials() {
		return trials;
	}

	public double getAverage() {
		return ((double) sum) / trials;
	}

	public static LimitTree buildLimitTree() {
		LimitTree lt = new LimitTree();
		for (int l = 1; l <= 21; l++) {
			BlackjackAutomated bja = new BlackjackAutomated(l, .01, 1000);
			bja.play();
			lt.add(bja.getAverage(), l);
		}
		return lt;
	}

	public static BetTree buildBetTree() {
		BetTree bt = new BetTree();
		for (double b = .01; b < 1; b += .01) {
			BlackjackAutomated bja = new BlackjackAutomated(14, b, 1000);
			bja.play();
			bt.add(bja.getAverage(), b);
		}
		return bt;
	}

	public void play() {
		Player dealer = new Player();
		for (int k = 0; k < trials; k++) {
			player.setMoney(1000);
			for (int i = 0; i < 100000; i++) {
				player.reset();
				dealer.reset();
				deck.shuffle();

				if (player.getMoney() < 5) {
					sum += i;
					break;
				} else if (i == 1000000 - 1) {
					sum += 1000000;
				}

				// Set-up
				dealer.hit(deck.deal());
				player.hit(deck.deal());
				dealer.hit(deck.deal());
				player.hit(deck.deal());

				// TODO: implement double and surrender logic
				while (player.getMaxScore() < player.getLimit()) {
					player.hit(deck.deal());
				}

				if (player.getSum()[0] > 21) {
					player.subtractMoney((int) Math.ceil(player.getMoney() * player.getPercent()));
				} else {
					while (dealer.getSum()[0] < 17) {
						dealer.hit(deck.deal());
					}
					if (dealer.getSum()[0] > 21) {
						player.addMoney((int) Math.ceil(player.getMoney() * player.getPercent()));
					} else {
						if (player.getMaxScore() > dealer.getMaxScore()) {
							player.addMoney((int) Math.ceil(player.getMoney() * player.getPercent()));
						} else {
							player.subtractMoney((int) Math.ceil(player.getMoney() * player.getPercent()));
						}
					}
				}
			}
		}

	}
}
