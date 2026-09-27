package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Game;
import model.HitStrategy;
import model.User;

/**
 * ActionListener implementation that handles the "Hit" action.
 * <p>
 * When triggered, this listener sets the current user's strategy to
 * {@link HitStrategy} and executes the user's play.
 * </p>
 */
public class HitActionListener implements ActionListener {
	
	/**
     * Invoked when an action occurs.
     * <p>
     * This method retrieves the current user from the singleton {@link Game} instance,
     * sets the user's strategy to {@link HitStrategy}, and then calls
     * {@link User#play()} to execute the move.
     * </p>
     *
     * @param e the action event that triggered this listener
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		User user = Game.getInstance().getUser();
		user.setStrategy(new HitStrategy());
		user.play();
	}
}