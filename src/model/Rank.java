package model;

/**
 * Represents the rank of a playing card.
 */
public enum Rank {
	ACE(11),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10);
	
	private int value;
	
	/**
     * Constructs a Rank with the given integer value.
     *
     * @param value the numerical value of the rank
     */
	Rank(int value) {
		this.value = value;
	}
	
	/**
     * Returns the integer value associated with this rank.
     *
     * @return the numerical value of the card rank
     */
	public int toInt() {
		return value;
	}
	
	/**
     * Returns a string representation of the rank.
     * Numbers are returned as digits, instead face cards are capitalized words.
     *
     * @return a string representing the rank
     */
	@Override
	public String toString() {
		if (this == Rank.TEN || value<10)
			return ""+value;
		return this.name().charAt(0)+this.name().substring(1).toLowerCase();
	}
}