package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import model.Game;
import model.StandStrategy;
import model.User;

/**
 * ActionListener implementation that handles the "Stand" action.
 * <p>
 * When triggered, this listener sets the current user's strategy to {@link StandStrategy}
 * and executes the user's play.
 * </p>
 */
public class StandActionListener implements ActionListener {
	
	/**
     * Invoked when the "Stand" action occurs.
     * <p>
     * This method retrieves the current user from the singleton {@link Game} instance,
     * sets the user's strategy to {@link StandStrategy}, and then calls {@link User#play()}
     * to execute the move.
     * </p>
     *
     * @param e the action event that triggered this listener
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		User u = Game.getInstance().getUser();
		u.setStrategy(new StandStrategy());
		u.play();
	}
}