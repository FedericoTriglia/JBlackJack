package model;

/**
 * Represents the outcome of a Blackjack hand.
 */
public enum Result {
	
	/** The player wins the hand. */
	YOU_WON,
	
	/** The hand is a tie between player and dealer. */
	PUSH,
	
	/** The player loses the hand. */
	YOU_LOST,
	
	/** The player has a Blackjack (Ace + 10-value card). */
	BJ;
	
	/**
     * Returns a string representing the result.
     *
     * @return a formatted string describing the outcome
     */
	@Override
	public String toString() {
		switch(this.name()) {
			case "BJ": return "BLACK JACK";
			case "YOU_WON": return "YOU WON";
			case "YOU_LOST": return "YOU LOST";
			default: return this.name();
		}
	}
}