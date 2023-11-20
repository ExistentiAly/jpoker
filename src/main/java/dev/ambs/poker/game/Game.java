package dev.ambs.poker.game;

import dev.ambs.poker.game.action.HandAction;
import dev.ambs.poker.game.component.betting.BlindLevel;
import dev.ambs.poker.game.component.card.Card;
import dev.ambs.poker.game.component.hand.HandActionLog;
import dev.ambs.poker.game.component.hand.HandOfPlay;
import dev.ambs.poker.game.component.hand.HandSeat;
import dev.ambs.poker.game.rank.HandRank;
import dev.ambs.poker.table.Seat;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public abstract class Game {

    public static NoLimitTexasHoldem noLimitTexasHoldem() {
        return new NoLimitTexasHoldem();
    }

    public HandOfPlay createHand(final List<Seat> seats, final BlindLevel level, final int handNum, final HandActionLog actionLog) {
        return new HandOfPlay(seats, level, handNum, actionLog, this);
    }

    public abstract HandAction[] handActions();

    public abstract HandRank rankHand(final List<Card> holeCards, final List<Card> communityCards);

    public HandRank rankHand(final List<Card> holeCards) {
        return rankHand(holeCards, Collections.emptyList());
    }

    public abstract Function<List<HandSeat>, List<HandSeat>> rankerFunction();
}
