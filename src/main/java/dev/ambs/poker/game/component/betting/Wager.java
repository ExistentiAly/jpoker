package dev.ambs.poker.game.component.betting;

import dev.ambs.poker.game.component.hand.HandSeat;
import dev.ambs.poker.table.ChipStack;

public class Wager {

    private final ChipStack amount;
    private final HandSeat seat;
    private final boolean allIn;

    private Wager(final ChipStack amount, final HandSeat seat) {
        this(amount, seat, false);
    }

    private Wager(final ChipStack amount, final HandSeat seat, final boolean allIn) {
        this.amount = amount;
        this.seat = seat;
        this.allIn = allIn;
    }

    public static Wager create(final ChipStack amount, final HandSeat seat) {
        return new Wager(amount, seat);
    }

    public ChipStack getAmount() {
        return amount;
    }

    public HandSeat getSeat() {
        return seat;
    }

    public boolean isAllIn() {
        return allIn;
    }

}
