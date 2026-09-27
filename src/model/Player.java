package model;

import java.util.Observable;

/**
 * Abstract class representing a player in the game.
 * <p>
 * Each player has a {@link Hand}, a {@link Profile}, and a {@link GameStrategy}. 
 * The class provides methods to manipulate the hand, get the strategy, 
 * and play according to the assigned strategy.
 * </p>
 */
public abstract class Player extends Observable {
	protected Hand hand;
	protected final Profile profile;
	protected GameStrategy strategy;
	
	/**
     * Constructs a player with the given profile.
     *
     * @param profile the profile associated with this player
     */
	public Player(Profile profile) {
		this.profile = profile;
		hand = new Hand();
	}
	
	/**
     * Executes the player's strategy.
     *
     * @return whether the strategy has actually been executed.
     */
	public boolean play() {
		return strategy.play();
	}
	
	/**
     * Returns the current strategy of the player.
     *
     * @return the player's strategy
     */
	public GameStrategy getStrategy() {
		return strategy;
	}
	
	/**
     * Returns the player's hand.
     *
     * @return the hand
     */
	public Hand getHand() {
		return hand;
	}
	
	/**
     * Returns the player's avatar.
     *
     * @return the avatar from the player's profile
     */
	public Avatar getAvatar() {
		return profile.getAvatar();
	}
	
	/**
     * Returns the total value of the player's hand.
     *
     * @return the hand value
     */
	public abstract int handValue();
	
	/**
     * Adds a card to the player's hand.
     *
     * @param c the card to add
     */
	public abstract void addCard(Card c);
	
	/**
     * Resets the player for a new game or round.
     */
	public abstract void reset();
	
	/**
     * Checks if the player is busted (hand value exceeds game limit).
     *
     * @return true if busted, false otherwise
     */
	public abstract boolean isBusted();
	
	/**
	 * This method returns(getter) the player's profile
	 * 
	 * @return the profile of the player
	 */
	public Profile getProfile() {
		return profile;
	}
	
	/**
	 * Returns a string representation of the player, showing his nickname
	 * followed by his avatar.
	 *
	 * @return a string in the format "nickname avatar"
	 */
	@Override
	public String toString() {
		return String.format("%s %s\n", profile.getNickname(), profile.getAvatar());
	}
}