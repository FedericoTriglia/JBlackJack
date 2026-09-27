package model;

/**
 * Singleton class representing the dealer in the game.
 * <p>
 * Extends {@link Player} and manages the deck, dealing cards,
 * applying rules (American or European), and interacting with players' hands.
 * </p>
 */
public class Dealer extends Player {
	private static Dealer instance;
	private Deck deck;
	private final boolean americanRules;
	
	/**
     * Returns the single instance of the Dealer.
     * If it does not exist, a new instance is created.
     *
     * @return the Dealer singleton instance
     */
	public static Dealer getInstance() {
		if (instance == null)
			instance = new Dealer();
		return instance;
	}
	
	/**
     * Private constructor to enforce the singleton pattern.
     * <p>
     * Initializes the dealer's hand, deck, strategy, and rule set
     * based on {@link Menu} settings.
     * </p>
     */
	private Dealer() {
		super(Menu.getInstance().getDealerProfile());
		this.deck = new Deck(Menu.getInstance().getDecksNumber());
		strategy = new DealerStrategy();
		americanRules = Menu.getInstance().getRules();
	}
	
	/**
     * Adds a card to the dealer's hand.
     * <p>
     * If American rules are active and this is the second card,
     * it will be covered.
     * </p>
     *
     * @param c the card to add
     */
	@Override
	public void addCard(Card c) {
		if (hand.size() == 1 && americanRules)
			c.cover();
		hand.addCard(c);
		setChanged();
		notifyObservers(c);
	}
	
	/**
     * Shuffles the deck.
     */
	public void shuffle() {
		deck.shuffle();
	}
	
	/**
     * Gives a card to a specific player.
     *
     * @param p the player to receive the card
     */
	public void giveCard(Player p) {
		p.addCard(deck.drawCard());
	}
	
	/**
     * Checks if the dealer has busted (hand value > 21).
     *
     * @return true if dealer's hand value is greater than 21, false otherwise
     */
	@Override
	public boolean isBusted() {
		return hand.handValue() > 21;
	}
	
	/**
     * Returns the current hand value of the dealer.
     *
     * @return dealer's hand value
     */
	@Override
	public int handValue() {
		return hand.handValue();
	}
	
	/**
     * Preparing the dealer for a new round.
     * <p>
     * Clears the hand and initializes a new deck.
     * Notifies observers of the change.
     * </p>
     */
	@Override
	public void reset() {
		hand.clear();
		deck = new Deck(Menu.getInstance().getDecksNumber());
		setChanged();
		notifyObservers();
	}
	
	/**
     * Flips the dealer's second card if it is covered.
     */
	public void discoverSecond() {
		if (hand.size() == 2 && hand.get(1).isCovered()) {
			hand.get(1).discover();
			setChanged();
			notifyObservers(true);
		}
	}
	
	/**
     * Determines whether a given hand can still play against the dealer.
     *
     * @param h the hand to check
     * @return true if the hand can play, false otherwise
     */
	private boolean toPlayAgainst(Hand h) {
		if (h.handValue() > 21)
			return false;
		else if (h.size() == 2 && h.handValue() == 21)
			return false;
		else return true;
	}
	
	/**
     * Determines if the dealer should continue playing according to game rules.
     *
     * @return true if the dealer can play, false otherwise
     */
	public boolean canPlay(User u) {
		boolean b = hand.handValue()<17;
		boolean h1 = toPlayAgainst(u.getHand());
		boolean h2;
		if (u.getSecondHand()!=null)
			h2 = toPlayAgainst(u.getSecondHand());
		else h2 = false;
		return (b && (h1 || h2));
	}
}