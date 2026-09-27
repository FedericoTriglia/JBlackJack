package model;

/**
 * Implementation of {@link GameStrategy} representing the "double down" action.
 * <p>
 * Encapsulates the logic for the user to perform a double down move in the game.
 * </p>
 */
public class DoubleDownStrategy implements UserStrategy {

	/**
     * Executes the "double down" action for the user.
     * <p>
     * Delegates the action to the user's {@link User#doubleDown()} method.
     * </p>
     *
     * @return true if the action was successfully performed, false otherwise
     */
	@Override
	public boolean play() {
		User u = Game.getInstance().getUser();
		Dealer d = Game.getInstance().getDealer();
		if (u.getSecondHand() == null || !u.playingSecond()) {
			if (u.getCurrentBet()*2 + u.getSecondBet() <= u.getProfile().getScore() && u.getCurrentBet() > 0) {
				u.setBet(u.getCurrentBet());
				d.giveCard(u);
				return true;
			}
		}
		else 
			if (u.getBet() + 2*u.getSecondBet() <= u.getProfile().getScore() && u.getSecondBet() > 0) {
				u.setSecondBet(u.getSecondBet()*2);
				d.giveCard(u);
				return true;
			}
		return false;
	}
}