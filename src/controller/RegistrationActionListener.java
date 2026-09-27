package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Avatar;
import model.Database;
import model.Menu;
import model.Profile;
import view.MainView;

/**
 * ActionListener implementation that handles the "User Registration" action.
 * <p>
 * When triggered, this listener performs the following tasks:
 * <ul>
 *   <li>Retrieves the new nickname and avatar from {@link MainView}.</li>
 *   <li>Creates a new {@link Profile} with the provided nickname and avatar.</li>
 *   <li>Adds the new profile to the {@link Menu} and {@link Database}.</li>
 *   <li>Saves the database, handling potential {@link FileNotFoundException} and {@link IOException}.</li>
 * </ul>
 * </p>
 */
public class RegistrationActionListener implements ActionListener {

	/**
     * Invoked when the "Register" action occurs.
     * <p>
     * This method retrieves the new nickname and avatar from the main view, 
     * creates a new profile, adds it to the menu and database, and then attempts 
     * to save the database.
     * </p>
     *
     * @param e the action event that triggered this listener
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = MainView.getInstance().getNewNick();
		String avatarS = MainView.getInstance().getAvatarString();
		Avatar av = Avatar.fromString(avatarS);
		if (s != null && av != null) {
			Profile pp = new Profile(s, av);
			Menu.getInstance().addProfile(pp);
			Database.getInstance().addProfile(pp);
			try {
				Database.getInstance().save();
			} catch(FileNotFoundException exc) {
				System.err.println("Directory not found");
			} catch (IOException exception) {
				System.err.println("Invalid format");
			}
		}
	}
}