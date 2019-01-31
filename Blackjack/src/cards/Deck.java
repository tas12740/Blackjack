package cards;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
	private Card[] deck;
	private int pos;

	public Deck() {
		buildDeck();
		shuffle();
		pos = 51;
	}

	private void buildDeck() {
		deck = new Card[52];
		int count = -1;
		deck[++count] = new Card(Suit.HEARTS, Value.ACE);
		deck[++count] = new Card(Suit.HEARTS, Value.TWO);
		deck[++count] = new Card(Suit.HEARTS, Value.THREE);
		deck[++count] = new Card(Suit.HEARTS, Value.FOUR);
		deck[++count] = new Card(Suit.HEARTS, Value.FIVE);
		deck[++count] = new Card(Suit.HEARTS, Value.SIX);
		deck[++count] = new Card(Suit.HEARTS, Value.SEVEN);
		deck[++count] = new Card(Suit.HEARTS, Value.EIGHT);
		deck[++count] = new Card(Suit.HEARTS, Value.NINE);
		deck[++count] = new Card(Suit.HEARTS, Value.TEN);
		deck[++count] = new Card(Suit.HEARTS, Value.JACK);
		deck[++count] = new Card(Suit.HEARTS, Value.QUEEN);
		deck[++count] = new Card(Suit.HEARTS, Value.KING);

		deck[++count] = new Card(Suit.CLUBS, Value.ACE);
		deck[++count] = new Card(Suit.CLUBS, Value.TWO);
		deck[++count] = new Card(Suit.CLUBS, Value.THREE);
		deck[++count] = new Card(Suit.CLUBS, Value.FOUR);
		deck[++count] = new Card(Suit.CLUBS, Value.FIVE);
		deck[++count] = new Card(Suit.CLUBS, Value.SIX);
		deck[++count] = new Card(Suit.CLUBS, Value.SEVEN);
		deck[++count] = new Card(Suit.CLUBS, Value.EIGHT);
		deck[++count] = new Card(Suit.CLUBS, Value.NINE);
		deck[++count] = new Card(Suit.CLUBS, Value.TEN);
		deck[++count] = new Card(Suit.CLUBS, Value.JACK);
		deck[++count] = new Card(Suit.CLUBS, Value.QUEEN);
		deck[++count] = new Card(Suit.CLUBS, Value.KING);

		deck[++count] = new Card(Suit.DIAMONDS, Value.ACE);
		deck[++count] = new Card(Suit.DIAMONDS, Value.TWO);
		deck[++count] = new Card(Suit.DIAMONDS, Value.THREE);
		deck[++count] = new Card(Suit.DIAMONDS, Value.FOUR);
		deck[++count] = new Card(Suit.DIAMONDS, Value.FIVE);
		deck[++count] = new Card(Suit.DIAMONDS, Value.SIX);
		deck[++count] = new Card(Suit.DIAMONDS, Value.SEVEN);
		deck[++count] = new Card(Suit.DIAMONDS, Value.EIGHT);
		deck[++count] = new Card(Suit.DIAMONDS, Value.NINE);
		deck[++count] = new Card(Suit.DIAMONDS, Value.TEN);
		deck[++count] = new Card(Suit.DIAMONDS, Value.JACK);
		deck[++count] = new Card(Suit.DIAMONDS, Value.QUEEN);
		deck[++count] = new Card(Suit.DIAMONDS, Value.KING);

		deck[++count] = new Card(Suit.SPADES, Value.ACE);
		deck[++count] = new Card(Suit.SPADES, Value.TWO);
		deck[++count] = new Card(Suit.SPADES, Value.THREE);
		deck[++count] = new Card(Suit.SPADES, Value.FOUR);
		deck[++count] = new Card(Suit.SPADES, Value.FIVE);
		deck[++count] = new Card(Suit.SPADES, Value.SIX);
		deck[++count] = new Card(Suit.SPADES, Value.SEVEN);
		deck[++count] = new Card(Suit.SPADES, Value.EIGHT);
		deck[++count] = new Card(Suit.SPADES, Value.NINE);
		deck[++count] = new Card(Suit.SPADES, Value.TEN);
		deck[++count] = new Card(Suit.SPADES, Value.JACK);
		deck[++count] = new Card(Suit.SPADES, Value.QUEEN);
		deck[++count] = new Card(Suit.SPADES, Value.KING);
	}

	public void shuffle() {
		List<Card> list = Arrays.asList(deck);
		Collections.shuffle(list);
		deck = list.toArray(deck);
		pos = 51;
	}

	public Card deal() {
		return deck[pos--];
	}

	public String toString() {
		String s = "";
		for (int i = 51; i >= 0; i--) {
			s += deck[i];
			if (i != 0) {
				s += ", ";
			}
		}
		return s;
	}
}
