package model;

/**
 * Implements the strategy for splitting a player's hand in Blackjack.
 * <p>
 * If the player has a pair and is allowed to split, this strategy will
 * split the hand into two separate hands and deal one card to each new hand.
 * </p>
 */
public class SplitStrategy implements UserStrategy {
	
	/**
     * Executes the split action if possible.
     *
     * @return {@code true} if the hand was successfully split and cards were dealt;
     *         {@code false} if the hand cannot be split
     */
	@Override
	public boolean play() {
		Game game = Game.getInstance();
		User user = game.getUser();
		if (user.splittable()) {
			user.split();
			if (user.getSecondHand() != null && user.getSecondHand().size() == 1) {
				game.getDealer().giveCard(user);
				user.currentHand(2);
				game.getDealer().giveCard(user);
				user.currentHand(1);
				return true;
			}
		}
		return false;
	}
}