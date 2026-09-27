package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of playing cards held by a player or dealer in the game.
 * <p>
 * Provides methods to manipulate the hand, calculate its total value according to Blackjack rules,
 * check if it can be split, and iterate over the cards.
 * </p>
 */
public class Hand implements Iterable<Card>, Comparable<Hand> {
	private List<Card> cards;
	
	/**
     * Creates an empty hand.
     */
	public Hand() {
		cards = new ArrayList<>();
	}
	
	/**
     * Returns the number of cards in the hand.
     *
     * @return the number of cards
     */
	public int size() {
		return cards.size();
	}
	
	 /**
     * Adds a card to the hand.
     *
     * @param c the card to add
     */
	public void addCard(Card c) {
		cards.add(c);
	}
	
	/**
     * Returns the card at the specified index.
     *
     * @param index the position of the card
     * @return the card at the specified index
     */
	public Card get(int index) {
		return cards.get(index);
	}
	
	/**
     * Removes and returns the card at the specified index.
     *
     * @param index the position of the card to remove
     * @return the removed card
     */
	public Card remove(int index) {
		return cards.remove(index);
	}
	
	/**
     * Removes all cards from the hand.
     */
	public void clear() {
		cards.clear();
	}
	
	/**
     * Calculates the total value of the hand according to Blackjack rules.
     * <p>
     * Aces are counted as 11 unless that would cause the hand to bust, in which case they count as 1.
     * </p>
     *
     * @return the total value of the hand
     */
	public int handValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : cards) {
        	Rank rank = card.getRank();
        	value += rank.toInt();
        	if (rank == Rank.ACE)
        		aceCount++;
        }
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
        return value;
    }
	
	/**
     * Checks if the hand can be split into two separate hands.
     * <p>
     * A hand can be split if it contains exactly two cards of the same rank.
     * </p>
     *
     * @return true if the hand can be split, false otherwise
     */
	public boolean splittable() {
		return cards.size() == 2 && cards.get(0).getRank().toInt() == cards.get(1).getRank().toInt();
	}
	
	/**
     * Returns a string representation of the hand, listing each card.
     *
     * @return a string with all cards in the hand
     */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card c : cards)
			sb.append(c.toString()+"\n");
		return sb.toString().trim();
	}
	
	/**
     * Returns an iterator over the cards in the hand.
     *
     * @return an iterator for the hand
     */
	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
	
	/**
     * Compares this hand with another hand according to Blackjack rules.
     * <p>
     * Hands with Blackjack (21 points with two cards) are ranked higher than other hands.
     * Otherwise, hands are compared by their total value.
     * </p>
     *
     * @param h the hand to compare with
     * @return a negative integer, zero, or a positive integer if this hand is less than, equal to,
     * or greater than the specified hand
     */
	@Override
	public int compareTo(Hand h) {
		if (handValue() == 21) {
			if (size() == 2 && h.size() > 2)
				return 1;
			else if (h.size() == 2 && 2 < size())
				return -1;
			else return 0;
		}
		else return Integer.compare(handValue(), h.handValue());
	}
}