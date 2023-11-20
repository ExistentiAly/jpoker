package ca.sariarra.poker.datastruct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.sariarra.poker.logic.PokerGame;

public class TournamentTable extends Table {
	private final int tourneyTableNum;

	public TournamentTable(final int numSeats, final boolean pIsCashTable, final PokerGame pGame,
			final long pTableNum, final int pTourneyTableNum) {
		super(numSeats, pIsCashTable, pGame, pTableNum);

		tourneyTableNum = pTourneyTableNum;
	}

	@Override
	protected boolean dealNextHand() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void setSeatsForHand() {
		List<Seat> seatsInHand = new ArrayList<Seat>(10);
		for (int i = 1; i <= seats.length; i++) {
			if (seats[button + i % seats.length] == null) {
				continue;
			}

			if (seats[button + i % seats.length].getPlayer() == null) {
				seats[button + i % seats.length] = null;
				continue;
			}

			seatsInHand.add(seats[button + i % seats.length]);
		}

		seatsForHand = seatsInHand.toArray(new Seat[seatsInHand.size()]);
	}

	@Override
	protected BlindLevel getBlindLevel(final Date time) {
		// TODO Auto-generated method stub
		return null;
	}
}
