package model;

/**
 * Implementation of {@link GameStrategy} for the dealer.
 * <p>
 * Encapsulates the dealer's behavior according to standard game rules:
 * the dealer must draw cards until their hand value reaches at least 17.
 * </p>
 */
public class DealerStrategy implements GameStrategy {
	
	/**
     * Executes the dealer's move according to the strategy.
     * <p>
     * If the dealer's hand value is less than 17, a card is drawn.
     * </p>
     *
     * @return always returns true, indicating the strategy has been applied
     */
	@Override
	public boolean play() {
		Dealer d = Game.getInstance().getDealer();
		Hand h = d.getHand();
		if (h.handValue() < 17)
			d.giveCard(d);
		return true;
	}
}