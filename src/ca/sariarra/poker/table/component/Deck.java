package ca.sariarra.poker.table.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.sariarra.poker.card.Card;
import ca.sariarra.poker.card.Rank;
import ca.sariarra.poker.card.Suit;

public class Deck {
	private final List<Card> deck;
	private List<Card> discards;

	public Deck() {
		Card[] rawCards = new Card[] {
				new Card(Rank.ACE, Suit.HEARTS, this), new Card(Rank.ACE, Suit.DIAMONDS, this), new Card(Rank.ACE, Suit.CLUBS, this), new Card(Rank.ACE, Suit.SPADES, this),
				new Card(Rank.KING, Suit.HEARTS, this), new Card(Rank.KING, Suit.DIAMONDS, this), new Card(Rank.KING, Suit.CLUBS, this), new Card(Rank.KING, Suit.SPADES, this),
				new Card(Rank.QUEEN, Suit.HEARTS, this), new Card(Rank.QUEEN, Suit.DIAMONDS, this), new Card(Rank.QUEEN, Suit.CLUBS, this), new Card(Rank.QUEEN, Suit.SPADES, this),
				new Card(Rank.JACK, Suit.HEARTS, this), new Card(Rank.JACK, Suit.DIAMONDS, this), new Card(Rank.JACK, Suit.CLUBS, this), new Card(Rank.JACK, Suit.SPADES, this),
				new Card(Rank.TEN, Suit.HEARTS, this), new Card(Rank.TEN, Suit.DIAMONDS, this), new Card(Rank.TEN, Suit.CLUBS, this), new Card(Rank.TEN, Suit.SPADES, this),
				new Card(Rank.NINE, Suit.HEARTS, this), new Card(Rank.NINE, Suit.DIAMONDS, this), new Card(Rank.NINE, Suit.CLUBS, this), new Card(Rank.NINE, Suit.SPADES, this),
				new Card(Rank.EIGHT, Suit.HEARTS, this), new Card(Rank.EIGHT, Suit.DIAMONDS, this), new Card(Rank.EIGHT, Suit.CLUBS, this), new Card(Rank.EIGHT, Suit.SPADES, this),
				new Card(Rank.SEVEN, Suit.HEARTS, this), new Card(Rank.SEVEN, Suit.DIAMONDS, this), new Card(Rank.SEVEN, Suit.CLUBS, this), new Card(Rank.SEVEN, Suit.SPADES, this),
				new Card(Rank.SIX, Suit.HEARTS, this), new Card(Rank.SIX, Suit.DIAMONDS, this), new Card(Rank.SIX, Suit.CLUBS, this), new Card(Rank.SIX, Suit.SPADES, this),
				new Card(Rank.FIVE, Suit.HEARTS, this), new Card(Rank.FIVE, Suit.DIAMONDS, this), new Card(Rank.FIVE, Suit.CLUBS, this), new Card(Rank.FIVE, Suit.SPADES, this),
				new Card(Rank.FOUR, Suit.HEARTS, this), new Card(Rank.FOUR, Suit.DIAMONDS, this), new Card(Rank.FOUR, Suit.CLUBS, this), new Card(Rank.FOUR, Suit.SPADES, this),
				new Card(Rank.THREE, Suit.HEARTS, this), new Card(Rank.THREE, Suit.DIAMONDS, this), new Card(Rank.THREE, Suit.CLUBS, this), new Card(Rank.THREE, Suit.SPADES, this),
				new Card(Rank.TWO, Suit.HEARTS, this), new Card(Rank.TWO, Suit.DIAMONDS, this), new Card(Rank.TWO, Suit.CLUBS, this), new Card(Rank.TWO, Suit.SPADES, this),
		};

		deck = new ArrayList<Card>(rawCards.length);
		for (Card c : rawCards) {
			deck.add(c);
		}

		discards = new ArrayList<Card>();

		shuffle();
	}

	public Card deal() {
		if (deck.isEmpty()) {
			reshuffle();
		}

		if (deck.size() == 0) {
			throw new RuntimeException("The deck is out of cards.");
		}

		return ((ArrayList<Card>) deck).remove(deck.size() - 1);
	}

	public void discard(final Card c) {
		discards.add(c);
	}

	public void shuffle() {
		if (deck.size() == 0) {
			return;
		}

		Random rand = new Random();

		// Run through the deck three times, randomizing the order of the cards.
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < deck.size(); i++) {
				swapCards(i, rand.nextInt(deck.size()));
			}
		}
	}

	private void swapCards(final int startIndex, final int swapIndex) {
		Card temp = deck.get(swapIndex);
		deck.set(swapIndex, deck.get(startIndex));
		deck.set(startIndex, temp);
	}

	public void reshuffle() {
		deck.addAll(discards);
		discards = new ArrayList<Card>();
		shuffle();
	}

	public int cardsLeftInDeck() {
		return deck.size();
	}

	public int cardsInDiscards() {
		return discards.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card c : deck) {
			sb.append(c.toString());
			sb.append(' ');
		}

		return sb.toString();
	}

	public boolean isEmpty() {
		return deck.isEmpty() && discards.isEmpty();
	}

	public int size() {
		return deck.size();
	}
}
