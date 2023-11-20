package dev.ambs.poker.game.action;

import dev.ambs.poker.game.component.hand.HandOfPlay;

public interface HandActionExecutor {

	/**
	 * Executes the hand action on the table.
	 *
	 * @param handOfPlay The poker table.
     * @return True if the hand is over at this point.
     */
    boolean execute(HandOfPlay handOfPlay);
}
