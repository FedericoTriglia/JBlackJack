package model;

/**
 * Represents the user (player) in the blackjack game.
 * <p>
 * This class is a singleton, ensuring there is only one user instance. It manages the
 * user's hands (including a possible second hand for splits), bets, and game actions
 * like hit, split, and double down.
 * <p>
 * The user class extends {@link Player} and provides game-specific logic for a human
 * player, including interaction with bets and multiple hands.
 */
public class User extends Player {
	private Hand secondHand;
	private boolean currentSecond;
	private int bet;
	private int secondBet;
	private static User instance;
	
	/**
     * Returns the singleton instance of the User.
     *
     * @return the single User instance
     */
	public static User getInstance() {
		if (instance == null)
			instance = new User();
		return instance;
	}
	
	/**
     * Private constructor to enforce singleton pattern.
     */
	private User() {
		super(Menu.getInstance().getCurrentUser());
	}
	
	/**
     * Checks if the currently active hand is busted.
     *
     * @return true if the hand value exceeds 21, false otherwise
     * @throws IllegalStateException if no hand is available
     */
	@Override
	public boolean isBusted() {
		if (currentSecond && secondHand != null)
			return secondHand.handValue() > 21;
		else if (!currentSecond && hand != null)
				return hand.handValue() > 21;
		else throw new IllegalStateException("Illegal state");
	}
	
	/**
     * Sets the bet amount for the first hand.
     *
     * @param bet the bet amount (must be >= 5 and <= the profile's score of the user)
     */
	public void setBet(int bet) {
		if (bet >= 5 && bet <= profile.getScore())
			this.bet = bet;
	}
	
	/**
	 * This method sets the value of the "secondBet" field.
	 * 
	 * @param bet the value to set
	 */
	public void setSecondBet(int bet) {
		if (secondBet >= 5 && secondBet <= profile.getScore())
			secondBet = bet;
	}
	
	/**
     * Checks if the current hand can be split into two hands.
     *
     * @return true if splitting is allowed, false otherwise
     */
	public boolean splittable() {
		return secondHand == null && hand != null && hand.splittable() && bet*2 <=profile.getScore() && bet>0;
	}
	
	/**
     * Returns the current active hand's bet.
     *
     * @return the bet of the currently active hand
     */
	public int getCurrentBet() {
		if (!currentSecond)
			return bet;
		return secondBet;
	}
	
	/**
	 * 
	 * @return the value of the "bet" field.
	 */
	public int getBet() {
		return bet;
	}
	
	/**
	 * 
	 * @return the value of the "secondBet" field.
	 */
	public int getSecondBet() {
		return secondBet;
	}
	
	/**
     * Splits the user's current hand into two hands if possible.
     *
     * @return true if split was successful, false otherwise
     */
	public boolean split() {
		if (bet*2 <= profile.getScore() && bet > 0) {
			secondHand = new Hand();
			secondHand.addCard(hand.remove(1));
			secondBet = bet;
			setChanged();
			notifyObservers(secondHand);
			return true;
		}
		return false;
	}
	
	/**
     * Checks if the user is on their last active hand (meant to be used after a split).
     *
     * @return true if it is the last hand, false otherwise
     */
	public boolean lastHand() {
		if (secondHand == null)
			return true;
		else if (currentSecond)
			return true;
		return false;
	}
	
	/**
     * Adds a card to the active hand.
     *
     * @param c the card to add
     */
	@Override
	public void addCard(Card c) {
		if (secondHand!=null && currentSecond)
			secondHand.addCard(c);
		else
			hand.addCard(c);
		setChanged();
		notifyObservers(c);
	}
	
	/**
     * Returns the value of the current active hand.
     *
     * @return the total hand value
     */
	@Override
	public int handValue() {
		if (secondHand == null)
			return hand.handValue();
		if (!currentSecond)
			return hand.handValue();
		else 
			return secondHand.handValue();
	}
	
	/**
     * Returns the second hand, if it exists.
     *
     * @return the second hand
     */
	public Hand getSecondHand() {
		return secondHand;
	}
	
	/**
     * Checks if a specific hand is busted.
     *
     * @param n 1 for the first hand, 2 for the second hand
     * @return true if the hand is busted, false otherwise
     */
	public boolean isBusted(int n) {
		if (n==1)
			return hand.handValue()>21;
		else return secondHand.handValue()>21;
	}
	
	/**
     * Checks if doubling down is allowed for the active hand.
     *
     * @return true if doubling down is possible, false otherwise
     */
	public boolean doubleDownable() {
		if (!currentSecond)
			return bet*2 < profile.getScore();
		else return secondBet*2 < profile.getScore();
	}
	
	/**
     * Updates the profile when a hand is lost.
     *
     * @param n 1 for the first hand, 2 for the second hand
     */
	public void lost(int n) {
		if (n == 1)
			profile.lost(bet);
		else if (n==2 && secondHand != null)
			profile.lost(secondBet);
		else throw new IllegalStateException("Stato lost non valido");
	}

	/**
     * Updates the profile when a hand is won.
     *
     * @param n 1 for the first hand, 2 for the second hand
     */
	public void won(int n) {
		if (n == 1)
			profile.won(bet);
		else if (n==2 && secondHand != null)
			profile.won(secondBet);
		else throw new IllegalStateException("Stato won non valido");
	}
	
	/**
     * Updates the profile when the user wins a hand with blackjack.
     *
     * @param n 1 for the first hand, 2 for the second hand
     */
	public void BJ(int n) {
		if (n == 1)
			profile.won(bet);
		else if (n==2 && secondHand != null)
			profile.won(secondBet);
		else throw new IllegalStateException("Stato BJ non valido");
	}
	
	/**
     * Updates the user-related profile when a hand results in a push.
     */
	public void push() {
		profile.push();
	}
	
	/**
     * Checks if the user is currently playing the second hand.
     *
     * @return true if playing the second hand, false otherwise
     */
	public boolean playingSecond() {
		return currentSecond;
	}
	
	/**
     * Prepares the user for the next turn and notify observers.
     */
	@Override
	public void reset() {
		bet = 0;
		secondBet = 0;
		hand.clear();
		if (secondHand != null)
			secondHand = null;
		currentSecond = false;
		strategy = null;
		setChanged();
		notifyObservers();
	}
	
	/**
     * Sets which hand is currently active.
     *
     * @param i 1 for the first hand, 2 for the second hand
     */
	public void currentHand(int i) {
		if (i <1 || i>2)
			throw new IllegalArgumentException();
		else if (i == 1)
			currentSecond = false;
		else currentSecond = true;
	}
	
	/**
     * Sets the user's strategy.
     *
     * @param us the new UserStrategy
     */
	public void setStrategy(UserStrategy us) {
		strategy = us;
	}
}