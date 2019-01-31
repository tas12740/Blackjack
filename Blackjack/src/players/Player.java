package players;

import java.util.ArrayList;

import cards.*;

public class Player {
	private ArrayList<Card> cards;
	private int numAces;
	private int money;

	public Player() {
		cards = new ArrayList<Card>();
		numAces = 0;
		money = 100;
	}

	public void reset() {
		cards.clear();
		numAces = 0;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void hit(Card c) {
		cards.add(c);
		if (c.getValue() == Value.ACE) {
			numAces++;
		}
	}

	public int[] getSum() {
		// calculate the sum of cards other than aces
		int[] results = new int[(numAces > 0) ? numAces + 1 : 1];

		int sum = 0;
		for (Card c : cards) {
			if (c.getValue() != Value.ACE) {
				sum += c.getValue().getValue();
			}
		}

		if (numAces == 0) {
			// there is only one result
			results[0] = sum;
		} else {
			switch (numAces) {
			case 1:
				results[0] = sum + 1;
				results[1] = sum + 11;
				break;
			case 2:
				results[0] = sum + 2;
				results[2] = sum + 22;
				results[1] = sum + 12;
				break;
			case 3:
				results[0] = sum + 3;
				results[3] = sum + 33;
				results[2] = sum + 23;
				results[1] = sum + 13;
				break;
			case 4:
				results[0] = sum + 4;
				results[4] = sum + 44;
				results[3] = sum + 34;
				results[2] = sum + 24;
				results[1] = sum + 14;
				break;
			}
		}
		return results;
	}

	public String display() {
		String s = "";
		int[] results = getSum();
		int count = 0;
		s += results[count];
		while (++count < results.length && results[count] <= 21) {
			s += " or " + results[count];
		}
		return s;
	}

	public int getMaxScore() {
		int[] results = getSum();
		int max = results[0];
		for (int i = 1; i < results.length; i++) {
			if (results[i] > max && results[i] <= 21) {
				max = results[i];
			}
		}
		return max;
	}

	public boolean has21() {
		int[] results = getSum();
		for (int i = 0; i < results.length; i++) {
			if (results[i] == 21) {
				return true;
			}
		}
		return false;
	}

	public void addMoney(int m) {
		money += m;
	}

	public void subtractMoney(int m) {
		money -= m;
	}

	public void setMoney(int m) {
		this.money = m;
	}

	public int getMoney() {
		return money;
	}
}
