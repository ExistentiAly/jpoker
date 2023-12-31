package dev.ambs.poker.game.rank;

import dev.ambs.poker.game.component.card.Card;

import java.util.List;

public class HandRank implements Comparable<HandRank> {
	public static final int HAND_SIZE = 5;

	private final HandRanking rank;
	private final List<Card> cards;

	public HandRank(final HandRanking rank, final List<Card> hand) {
		this.rank = rank;
		this.cards = hand;
	}

	public HandRanking getRank() {
		return rank;
	}

	public List<Card> getCards() {
		return cards;
	}

	@Override
	public int compareTo(final HandRank other) {
		return rank.getComparator().compare(this, other);
	}

	@Override
	public boolean equals(final Object other) {
        return other instanceof HandRank && this.compareTo((HandRank) other) == 0;
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (Card c : cards) {
			if (sb.length() != 1) {
				sb.append(' ');
			}
			sb.append(c.toString());
		}
		sb.append(']');
		sb.append(" - (");
		sb.append(rank.toString());
		sb.append(')');

		return sb.toString();
	}
}
