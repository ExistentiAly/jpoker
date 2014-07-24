package ca.sariarra.poker.table.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PotManager {
	private Map<Seat, Long> wagers;
	private Seat highestBettor;

	public PotManager(final Seat[] seatsForHand) {
		reset(seatsForHand);
	}

	public void reset(final Seat[] seatsForHand) {
		wagers = new HashMap<Seat, Long>(10);
		for (Seat s : seatsForHand) {
			wagers.put(s, 0l);
		}

		highestBettor = null;
	}

	public void add(final Seat seat, final long amount) {
		if (wagers.containsKey(seat)) {
			wagers.put(seat, wagers.get(seat) + amount);
		}
		else {
			wagers.put(seat, amount);
		}

		// Adjust the highest bettor.
		highestBettor = null;
		long highestWager = 0;
		for (Entry<Seat, Long> wager : wagers.entrySet()) {
			if (wager.getKey().isFolded()) {
				continue;
			}

			if (wager.getValue() > highestWager) {
				highestWager = wager.getValue();
				highestBettor = wager.getKey();
			}
		}
	}

	public boolean hasUncalledBet() {
		if (highestBettor == null) {
			return false;
		}

		long highestWager = wagers.get(highestBettor);
		for (Entry<Seat, Long> wager : wagers.entrySet()) {
			if (wager.getKey().isAllIn()) {
				continue;
			}
			if (wager.getKey().isFolded()) {
				continue;
			}

			if (wager.getValue() < highestWager) {
				return true;
			}
		}
		return false;
	}

	public Seat uncalledBettor() {
		return highestBettor;
	}

	public long getUncalledBet(final Seat seat) {
		if (highestBettor == null) {
			return 0;
		}

		long highWager = wagers.get(highestBettor);
		if (!wagers.containsKey(seat)) {
			return highWager;
		}

		return highWager - wagers.get(seat);
	}

	public Long getSize() {
		long size = 0;
		for (Entry<Seat, Long> wager : wagers.entrySet()) {
			size += wager.getValue();
		}

		return size;
	}

	public void returnUncalledBet() {
		if (!hasUncalledBet()) {
			return;
		}

		long highWager = wagers.get(highestBettor);
		long secondHighest = 0;
		for (Entry<Seat, Long> wager : wagers.entrySet()) {
			if (wager.getKey() == highestBettor) {
				continue;
			}

			if (wager.getValue() > secondHighest) {
				secondHighest = wager.getValue();
			}
		}

		if (highWager - secondHighest > 0) {
			highestBettor.addChips(highWager - secondHighest);
		}

		highestBettor = null;
	}

	public List<Pot> groupPotsByContestors() {
		Map<Seat, Long> wagersCopy = new HashMap<Seat, Long>(wagers);
		List<Pot> pots = new ArrayList<Pot>();

		Long lowestTotalWager;
		List<Seat> contestors;
		List<Seat> toRemove;
		while (!wagersCopy.isEmpty()) {
			lowestTotalWager = null;
			contestors = new ArrayList<Seat>();
			toRemove = new ArrayList<Seat>();

			for (Entry<Seat, Long> wager : wagersCopy.entrySet()) {
				if (wager.getKey().isFolded() || wager.getValue() == 0) {
					toRemove.add(wager.getKey());
					continue;
				}

				if (lowestTotalWager == null || lowestTotalWager > wager.getValue()) {
					lowestTotalWager = wager.getValue();
					contestors.add(wager.getKey());
				}
				else if (lowestTotalWager != null && lowestTotalWager <= wager.getValue()) {
					contestors.add(wager.getKey());
				}
			}

			if (lowestTotalWager == null) {
				break;
			}

			for (Seat rem : toRemove) {
				wagersCopy.remove(rem);
			}

			pots.add(new Pot(lowestTotalWager * contestors.size(), contestors));
			for (Entry<Seat, Long> wager : wagersCopy.entrySet()) {
				wagersCopy.put(wager.getKey(), wager.getValue() - lowestTotalWager);
			}
		}

		return pots;
	}

}
