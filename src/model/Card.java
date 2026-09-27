package model;

/**
 * Represents a playing card with a {@link Rank} and a {@link Suit}.
 * <p>
 * The class is comparable, using the card's rank for comparison.
 * </p>
 * <p>
 * The {@code isCovered} field indicates whether the card is face down
 * (e.g., the dealer's second card in the European version of blackjack,
 * which must remain hidden).
 * </p>
 */
public class Card implements Comparable<Card> {
	private final Suit suit;
	private final Rank rank;
	private boolean isCovered;
	
	 /**
     * Constructs a card with the given rank and suit.
     *
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	/**
     * Returns the suit of this card.
     *
     * @return the suit
     */
	public Suit getSuit() {
		return suit;
	}
	
	/**
     * Returns the rank of this card.
     *
     * @return the rank
     */
	public Rank getRank() {
		return rank;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 31 + rank.hashCode();
		return result * prime + suit.hashCode();
	}
	
	/**
     * Compares this card with another object for equality.
     * Two cards are equal if they have the same rank and suit.
     *
     * @param o the object to compare
     * @return {@code true} if the cards are equal, {@code false} otherwise
     */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		else if (o==null || getClass() != o.getClass())
			return false;
		Card c = (Card)o;
		return rank==c.rank && suit == c.suit;
	}
	
	/**
     * Returns a string representation of this card.
     * <p>
     * If the card is covered, returns {@code "BACK"}, otherwise returns a string representation of its value.
     * </p>
     *
     * @return a string representation of the card
     */
	@Override
	public String toString() {
		if (isCovered)
			return "BACK";
		else if (rank.toInt() < 10 && rank.toInt() > 1)
			return ""+rank.toInt()+"-"+suit.name().charAt(0);
		else if (rank.name() == "TEN")
			return "10-"+suit.name().charAt(0);
		else return rank.name().charAt(0)+"-"+suit.name().charAt(0);
	}
	
	/**
     * Compares this card to another based on rank.
     *
     * @param c the other card
     * @return a negative integer, zero, or a positive integer
     *         as this card's rank is less than, equal to,
     *         or greater than the specified card's rank
     */
	@Override
	public int compareTo(Card c) {
		return Integer.compare(rank.toInt(), c.rank.toInt());
	}
	
	/**
     * Uncovers the card if it is currently covered.
     */
	public void discover() {
		if (isCovered)
			isCovered = false;
	}
	
	/**
     * Covers the card if it is currently uncovered.
     */
	public void cover() {
		if (!isCovered)
			isCovered = true;
	}
	
	/**
     * Checks whether the card is covered.
     *
     * @return {@code true} if the card is covered, {@code false} otherwise
     */
	public boolean isCovered() {
		return isCovered;
	}
}