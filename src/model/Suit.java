package model;

/**
 * Represents the four suits of a standard deck of playing French cards.
 * <p>
 * The suits are HEARTS, DIAMONDS, CLUBS, and SPADES.
 * </p>
 */
public enum Suit {
	HEARTS,
	DIAMONDS,
	CLUBS,
	SPADES;
	
	/**
     * Returns the name of the suit, capitalizing only the first letter.
     *
     * @return the name of the suit in Pascal case.
     */
	@Override
	public String toString() {
		return this.name().charAt(0)+this.name().substring(1).toLowerCase();
	}
}