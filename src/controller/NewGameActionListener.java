package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Avatar;
import model.Database;
import model.Game;
import model.Menu;
import model.Profile;
import view.AudioManager;
import view.MainView;

/**
 * ActionListener implementation that handles the "New Game" action.
 * <p>
 * When triggered, this listener performs several tasks:
 * <ul>
 *   <li>Retrieves the number of decks from the {@link MainView} and sets it in the {@link Menu}.</li>
 *   <li>Checks if the current user has a profile, and if so, updates the user's avatar if it has changed.</li>
 *   <li>Saves the updated user information to the {@link Database}.</li>
 *   <li>Initializes the {@link Game} instance.</li>
 * </ul>
 * </p>
 */
public class NewGameActionListener implements ActionListener {

	/**
     * Invoked when the "New Game" action occurs.
     * <p>
     * This method retrieves the current user's profile and checks if the avatar needs to be updated.
     * If the avatar has changed, it updates the profile and saves it on the database.
     * Then it initializes the game.
     * </p>
     *
     * @param e the action event that triggered this listener
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		MainView view = MainView.getInstance();
		Menu menu = Menu.getInstance();
		int nDecks = view.getDecks();
		menu.setDecksNumber(nDecks);
		Profile p = menu.getCurrentUser();
		if (p != null) {
			Avatar a1 = p.getAvatar();
			String avatarS = view.getAvatarString();
			Avatar a2 = Avatar.fromString(avatarS);
			if (a1 != a2) {
				p.setAvatar(a2);
				try {
					Database.getInstance().save();
				} catch(FileNotFoundException exc) {
					System.err.println("Directory not found");
				} catch (IOException exception) {
					System.err.println("Invalid format");
				}
			}
			Game.getInstance();
			AudioManager.getInstance().play("Audio.wav");
		}
	}
}