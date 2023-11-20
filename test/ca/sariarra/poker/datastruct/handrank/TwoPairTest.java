package ca.sariarra.poker.datastruct.handrank;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.sariarra.poker.datastruct.Card;
import ca.sariarra.poker.types.Rank;
import ca.sariarra.poker.types.Suit;

public class TwoPairTest {
	
	@Test
	public void testHighTwoPairChecskingPositiveCases() {
		boolean wantHighRank = true;
		HandRank res = TwoPair.doTheseCardsMakeThisHandRank(new Card[] {
				new Card(Rank.ACE, Suit.CLUBS),
				new Card(Rank.TWO, Suit.CLUBS),
				new Card(Rank.FIVE, Suit.CLUBS),
				new Card(Rank.TWO, Suit.DIAMONDS),
				new Card(Rank.FIVE, Suit.SPADES),
				new Card(Rank.SIX, Suit.DIAMONDS),
				new Card(Rank.THREE, Suit.HEARTS)
		}, wantHighRank);
		assertIsTwoPair(res, false, new Card[] {
				new Card(Rank.TWO, Suit.CLUBS),
				new Card(Rank.TWO, Suit.DIAMONDS),
				new Card(Rank.FIVE, Suit.CLUBS),
				new Card(Rank.FIVE, Suit.SPADES),
				new Card(Rank.ACE, Suit.CLUBS)
		});
		
		res = TwoPair.doTheseCardsMakeThisHandRank(new Card[] {
				new Card(Rank.SIX, Suit.CLUBS),
				new Card(Rank.KING, Suit.CLUBS),
				new Card(Rank.KING, Suit.HEARTS),
				new Card(Rank.TEN, Suit.CLUBS),
				new Card(Rank.SIX, Suit.SPADES),
				new Card(Rank.SEVEN, Suit.DIAMONDS),
				new Card(Rank.FIVE, Suit.CLUBS)
		}, wantHighRank);
		assertIsTwoPair(res, false, new Card[] {
				new Card(Rank.KING, Suit.CLUBS),
				new Card(Rank.KING, Suit.HEARTS),
				new Card(Rank.SIX, Suit.CLUBS),
				new Card(Rank.SIX, Suit.SPADES),
				new Card(Rank.TEN, Suit.CLUBS)
		});
	}
	
	private void assertIsTwoPair(HandRank r, boolean expectToFail, Card[] expCards) {
		if (expectToFail) {
			assertNull("Hand rank not null when it should be.", r);
		}
		else {
			assertNotNull("Hand rank null when it should not be.", r);
		}
		
		assertTrue("Hand rank not instance of expected class.", 
				r instanceof TwoPair);
		
		boolean cardFound;
		for (Card c : expCards) {
			cardFound = false;
			for (Card hc : r.getCards()) {
				if (hc.equals(c)) {
					cardFound = true;
				}
			}
			assertTrue("Did not find a card that was expected.", cardFound);
		}
	}
}
