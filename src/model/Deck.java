package model;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the collection of cards used in the game.
 * <p>
 * This class does not represent a single deck of 52 cards, but a collection of multiple decks.
 * In Blackjack, the game is played with at least 2 decks and no more than 6. 
 * The number of decks is specified in the constructor, which creates the appropriate number of cards (52 * number of decks).
 * </p>
 */
public class Deck implements Iterable<Card> {
	private List<Card> deck;
	
	/**
     * Constructs a deck containing multiple standard 52-card decks.
     *
     * @param n the number of decks to include; must be between 2 and 6
     * @throws IllegalArgumentException if n is less than 2 or greater than 6
     */
	public Deck(int n) {
		if (n<2 || n>6)
			throw new IllegalArgumentException("To play this game, you need at least 2 decks, and no more than 6 decks");
		deck = new LinkedList<>();
		for (Suit s : Suit.values())
			for (Rank r : Rank.values())
				for (int i = 0; i<n; i++)
					deck.add(new Card(r, s));
	}
	
	/**
     * Draws (removes and returns) the top card from the deck.
     * <p>
     * This method removes the card at index 0.
     * </p>
     *
     * @return the drawn {@link Card}
     */
	public Card drawCard() {
		return deck.remove(0);
	}
	
	/**
     * Shuffles the cards in the deck.
     * <p>
     * Intentionally shuffles six times to maximize randomness, simulating the method used by casino dealers.
     * </p>
     */
	public void shuffle() {
		for (int i = 0; i<6; i++)
			Collections.shuffle(deck);
	}
	
	/**
     * Returns an iterator over the cards in the deck.
     *
     * @return an {@link Iterator} over {@link Card} objects
     */
	@Override
	public Iterator<Card> iterator() {
		return deck.iterator();
	}
	
	/**
     * Returns a string representation of all the cards in the deck, one card per line.
     *
     * @return a {@link String} listing all cards in the deck
     */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card c : deck)
			sb.append(c.toString()+"\n");
		return sb.toString().trim();
	}
}