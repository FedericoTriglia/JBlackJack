package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Game;
import view.MainView;

/**
 * ActionListener implementation that handles the start of a game turn.
 * <p>
 * When triggered, this listener retrieves the bet amount from {@link MainView} 
 * and sets it for the current user in the {@link Game} instance.
 * </p>
 */
public class StartTurnActionListener implements ActionListener {

	/**
     * Invoked when a player's turn starts.
     * <p>
     * This method retrieves the current user's bet from the main view and sets it 
     * in the singleton {@link Game} instance for the current user.
     * </p>
     *
     * @param e the action event that triggered this listener
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		Game.getInstance().getUser().setBet(MainView.getInstance().getBet());
	}
}