package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Menu;
import view.MainView;

/**
 * ActionListener implementation that handles the selection of a user profile.
 * <p>
 * When triggered, this listener retrieves the currently selected nickname 
 * from {@link MainView} and sets it as the current user in {@link Menu}.
 * </p>
 */
public class UserSelectionActionListener implements ActionListener {

	/**
     * Invoked when a user selection occurs.
     * <p>
     * This method gets the currently selected nickname from the main view
     * and updates the {@link Menu} with the selected user.
     * </p>
     *
     * @param e the action event that triggered this listener
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		String currentNick = MainView.getInstance().getCurrentNick();
		Menu.getInstance().setCurrentUser(currentNick);
	}

}