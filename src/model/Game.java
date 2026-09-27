package model;

import java.util.Observable;

/**
 * Represents the singleton blackjack game controller.
 * <p>
 * This class manages the state of a blackjack game, including the dealer, the user,
 * game rules, and the result of the last hand. It also provides methods for game actions
 * such as dealing cards, checking if hands are splittable, doubling down, and setting
 * hand results.
 * </p>
 * <p>
 * The class extends {@link Observable} to notify observers whenever the game state changes.
 * </p>
 */
public class Game extends Observable {
	private Result result;
	private static Game instance;
	private final boolean americanRules;
	private Dealer dealer;
	private User user;
	
	/**
     * Returns the singleton instance of the Game.
     *
     * @return the single Game instance
     */
	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	/**
     * Private constructor to enforce singleton pattern.
     */
	private Game() {
		americanRules = Menu.getInstance().getRules();
		dealer = Dealer.getInstance();
		user = User.getInstance();
	}
	
	/**
     * Returns whether the game is using American blackjack rules.
     *
     * @return true if American rules are used, false otherwise
     */
	public boolean getRules() {
		return americanRules;
	}
	
	/**
     * Deals initial cards to the dealer and the user until the dealer has two cards.
     */
	public void giveCards() {
		while(Dealer.getInstance().getHand().size() < 2) {
			dealer.giveCard(user);
			dealer.giveCard(dealer);
		}
	}
	
	/**
	 * The dealer shuffles the deck
	 */
	public void shuffle() {
		dealer.shuffle();
	}
	
	/**
     * Checks if the user can split his hand.
     *
     * @return true if the user can split, false otherwise
     */
	public boolean splittable() {
		return user.splittable();
	}
	
	/**
     * Returns the dealer.
     *
     * @return the Dealer instance
     */
	public Dealer getDealer() {
		return dealer;
	}
	
	/**
     * Returns the user.
     *
     * @return the User instance
     */
	public User getUser() {
		return user;
	}
	
	/**
     * Returns the result of the current turn.
     *
     * @return the Result of the current turn, or null if not yet determined
     */
	public Result getResult() {
		return result;
	}

	/**
    * Checks if the user can double down for the current hand.
    *
    * @return true if doubling down is possible, false otherwise
    */
	public boolean doubleDownable() {
		return user.doubleDownable();
	}
	
	/**
     * Forces the dealer to reveal his second card.
     */
	public void discoverSecond() {
		dealer.discoverSecond();
	}
	
	/**
     * Checks if the game instance has been initialized.
     *
     * @return true if the Game singleton exists, false otherwise
     */
	public static boolean initialized() {
		return instance != null;
	}
	
	/**
     * Returns the bet of the user's current hand.
     *
     * @return the current user bet
     */
	public int getUserBet() {
		return user.getCurrentBet();
	}
	
	/**
     * Sets the result for a specific hand.
     *
     * @param n 1 for the first hand, 2 for the second hand
     * @throws IllegalArgumentException if the hand number is invalid
     */
	public void setResultHand(int n) {
		if (n == 1)
			setResult(1);
		else if (n == 2 && user.getSecondHand()!=null)
			setResult(2);
		else throw new IllegalArgumentException("Invalid hand number");
	}
	
	/**
     * Determines the result of a hand against the dealer and updates the user's profile.
     *
     * @param n 1 for the first hand, 2 for the second hand
     */
	private void setResult(int n) {
		if (user.isBusted(n)) {
			result = Result.YOU_LOST;
			user.lost(n);
		}
		else {
			Hand h = n == 2 ? user.getSecondHand() : user.getHand();
			if (dealer.isBusted()) {
				if (h.size() == 2 && h.handValue() == 21) {
					result = Result.BJ;
					user.BJ(n);
				}
				else {
					result = Result.YOU_WON;
					user.won(n);
				}
			}
			else if (h.handValue() > dealer.getHand().handValue()) {
				if (h.size() == 2 && h.handValue() == 21) {
					result = Result.BJ;
					user.BJ(n);
				}
				else {
					result = Result.YOU_WON;
					user.won(n);
				}
			}
			else if (h.handValue() < dealer.getHand().handValue()) {
				result = Result.YOU_LOST;
				user.lost(n);
			}
			else {
				if (h.handValue() != 21) {
					result = Result.PUSH;
					user.push();
				}
				else if (h.size() == 2) {
					if (dealer.getHand().size() == 2) {
						result = Result.PUSH;
						user.push();
					}
					else {
						result = Result.BJ;
						user.won(n);
					}
				}
				else {
					if (dealer.getHand().size() == 2) {
						result = Result.YOU_LOST;
						user.lost(n);
					}
					else {
						result = Result.PUSH;
						user.push();
					}
				}
			}
		}
		setChanged();
		notifyObservers(result);
	}
	
	/**
     * Removes the result of the last hand and notifies observers.
     */
	public void removeResult() {
		result = null;
		setChanged();
		notifyObservers();
	}
	
	/**
     * Resets the game state in order to start another turn, and notifies observers.
     */
	public void reset() {
		result = null;
		dealer.reset();
		user.reset();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Override of the "toString()" method
	 */
	@Override
	public String toString() {
		String player = String.format("%s: %d", user.getProfile().getNickname(), user.getHand().handValue());
		if (user.getSecondHand() != null)
			player += String.format(", %d", user.getSecondHand().handValue());
		return player + String.format("Dealer: %d\n", dealer.handValue());
	}
}