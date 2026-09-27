package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Game;
import model.SplitStrategy;
import model.User;

/**
 * ActionListener implementation that handles the "Split" action.
 * <p>
 * When triggered, this listener checks if the current user can perform a split.
 * If the user is eligible, it sets the user's strategy to {@link SplitStrategy}
 * and executes the user's play.
 * </p>
 */
public class SplitActionListener implements ActionListener {

	/**
     * Invoked when the "Split" action occurs.
     * <p>
     * This method retrieves the current user from the singleton {@link Game} instance,
     * verifies if the user can split their hand using {@link User#splittable()}, 
     * and if so, sets the user's strategy to {@link SplitStrategy} and calls {@link User#play()}.
     * </p>
     *
     * @param e the action event that triggered this listener
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		User u = Game.getInstance().getUser();
		if (u.splittable()) {
			u.setStrategy(new SplitStrategy());
			u.play();
		}
	}
}