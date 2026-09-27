package model;

/**
 * Implements the strategy for standing in Blackjack.
 * <p>
 * The player takes no further action on the current hand. If the player
 * has a second hand, this strategy will only indicate standing if the
 * second hand is currently being played.
 * </p>
 */
public class StandStrategy implements UserStrategy {
	
	/**
     * Executes the stand action.
     *
     * @return {@code true} if the player stands and ends his turn;
     *         {@code false} if the player should keep playing with another hand
     */
	@Override
	public boolean play() {
		User u = Game.getInstance().getUser();
		if (u.getSecondHand() == null || u.playingSecond())
			return true;
		else
			return false;
	}
}