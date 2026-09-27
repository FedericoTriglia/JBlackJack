package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DoubleDownStrategy;
import model.Game;
import model.User;

/**
 * ActionListener implementation that handles the "Double Down" action.
 * <p>
 * When triggered, this listener sets the current user's strategy to
 * {@link DoubleDownStrategy} and executes the method play
 * </p>
 */
public class DoubleDownActionListener implements ActionListener {

	/**
     * Invoked when an action occurs.
     * <p>
     * This method retrieves the current user from the singleton {@link Game} instance,
     * sets the user's strategy to {@link DoubleDownStrategy}, and then calls
     * {@link User#play()} to execute the move.
     * </p>
     *
     * @param e the action event that triggered this listener
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		User u = Game.getInstance().getUser();
		u.setStrategy(new DoubleDownStrategy());
		u.play();
	}
	
}