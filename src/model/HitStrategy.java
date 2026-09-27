package model;

/**
 * Implements the "hit" strategy for a player in the game.
 * <p>
 * When executed, this strategy draws one card for the user and always returns true.
 * </p>
 */
public class HitStrategy implements UserStrategy {
	
	/**
     * Executes the hit action for the current user.
     *
     * @return true after the card is drawn
     */
	@Override
	public boolean play() {
		User u = Game.getInstance().getUser();
		Dealer d = Game.getInstance().getDealer();
		if (u.getCurrentBet() > 0)
			d.giveCard(u);
		return true;
	}
}