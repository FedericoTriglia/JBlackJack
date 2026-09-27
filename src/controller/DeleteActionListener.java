package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.Database;
import model.Menu;
import model.Profile;
import view.MainView;

/**
 * An {@link ActionListener} that handles the deletion of a user profile.
 * <p>
 * When triggered, this listener retrieves the nickname of the profile to delete
 * from the {@link MainView}, checks if the profile exists in the {@link Menu},
 * deletes it from the {@link Menu} and {@link Database}, and then saves the
 * database.
 * </p>
 */
public class DeleteActionListener implements ActionListener {

	/**
     * Invoked when the delete action is performed.
     * <p>
     * This method performs the following steps:
     * <ol>
     *     <li>Gets the nickname of the profile to delete from {@link MainView}.</li>
     *     <li>Checks if the profile exists in the {@link Menu}.</li>
     *     <li>If the profile exists:
     *         <ul>
     *             <li>Deletes the profile from the {@link Menu}.</li>
     *             <li>Removes the profile from the {@link Database}.</li>
     *             <li>Saves the database, catching any {@link FileNotFoundException} or {@link IOException}.</li>
     *         </ul>
     *     </li>
     * </ol>
     *
     * @param e the {@link ActionEvent} triggered by the user interface
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = MainView.getInstance().getToDeleteNick();
		Menu m = Menu.getInstance();
		Profile p = m.hasProfile(s);
		if (p != null) {
			m.deleteProfile(p);
			Database.getInstance().removeProfile(p);
			try {
				Database.getInstance().save();
			} catch(FileNotFoundException exc) {
			} catch (IOException exception) {
				System.err.println("Invalid format");
			}
		}
	}
}