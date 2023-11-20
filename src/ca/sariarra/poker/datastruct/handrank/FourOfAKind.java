package ca.sariarra.poker.datastruct.handrank;

import static ca.sariarra.util.ParamUtil.ensureNotNull;

import java.util.Arrays;

import ca.sariarra.poker.datastruct.Card;
import ca.sariarra.poker.logic.AceHighComparator;

public class FourOfAKind extends HandRank {

	private FourOfAKind(boolean isHigh, Card[] hand) {
		super(isHigh, hand);
	}
	
	public static HandRank doTheseCardsMakeThisHandRank(Card[] hand, boolean isHighRank) {
		ensureNotNull("Hand", hand);
		if (hand.length < 4) {
			return null;
		}
		
		Arrays.sort(hand, new AceHighComparator());
		
		int streak = 1;
		int i;
		for (i = hand.length - 2; i >= 0; i--) {
			if (hand[i].getRank() == hand[i + 1].getRank()) {
				streak++;
				if (streak == 4) {
					break;
				}
			}
			else {
				streak = 1;
			}
		}
		
		if (streak == 4) {
			Card[] retHand = new Card[5];
			retHand[0] = hand[i];
			retHand[1] = hand[i + 1];
			retHand[2] = hand[i + 2];
			retHand[3] = hand[i + 3];
			int rem = 4;
			
			for (int j = hand.length - 1; j >= 0; j--) {
				if (j >= i && j <= i + 3) {
					continue;
				}
				
				retHand[rem] = hand[j];
				rem++;
				if (rem > 4) {
					break;
				}
			}
			
			return new FourOfAKind(true, retHand);
		}

		return null;
	}
	
	@Override
	public int compareTo(HandRank other) {
		// TODO Auto-generated method stub
		return 0;
	}

}