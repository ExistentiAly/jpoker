package ca.sariarra.poker.logic;

import ca.sariarra.poker.datastruct.Table;

public abstract class Game {
	
	public abstract void dealCards();
	
	public abstract boolean bettingRound(int roundNum);
	
	public abstract void runHand(Table table);
	
	public abstract void rankHands();

}
