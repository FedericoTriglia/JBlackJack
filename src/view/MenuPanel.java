package view;

import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import model.Profile;

/**
 * A container panel that organizes the main menu of the application.
 * <p>
 * This panel holds three sub-panels:
 * <ul>
 *     <li>{@link StartingPanel} at the top (NORTH)</li>
 *     <li>{@link ProfilesPanel} at the center (CENTER)</li>
 *     <li>{@link SettingsPanel} at the left (WEST)</li>
 * </ul>
 * It provides accessors and helper methods to interact with its sub-panels and
 * to update data based on changes in the model.
 * </p>
 */
public class MenuPanel extends JPanel {
	private StartingPanel sp;
	private ProfilesPanel pp;
	private SettingsPanel settingsPanel;
	
	/**
     * Constructs the MenuPanel and initializes its layout and sub-panels.
     */
	public MenuPanel() {
		setLayout(new BorderLayout());
		sp = new StartingPanel();
		add(sp, BorderLayout.NORTH);
		pp = new ProfilesPanel();
		add(pp, BorderLayout.CENTER);
		settingsPanel = new SettingsPanel();
		add(settingsPanel, BorderLayout.WEST);
	}
	
	/**
     * Returns the nickname entered for a new user in the SettingsPanel.
     *
     * @return the new user's nickname
     */
	public String getNewNick() {
		return settingsPanel.getNewNick();
	}
	
	/**
     * Returns the currently selected user nickname in the SettingsPanel.
     *
     * @return the current user's nickname
     */
	public String getCurrentNick() {
		return (String)settingsPanel.getCurrentNick();
	}
	
	/**
     * Returns the number of decks selected in the SettingsPanel.
     *
     * @return the number of decks
     */
	public int getDecks() {
		return settingsPanel.getDecks();
	}
	
	/**
     * Returns the ButtonGroup representing user selection radio buttons in the SettingsPanel.
     *
     * @return the ButtonGroup of radio buttons
     */
	public ButtonGroup getRadio() {
		return settingsPanel.getRadio();
	}
	
	/**
     * Refreshes the profiles data displayed in the ProfilesPanel.
     */
	public void setData() {
		pp.reload();
	}
	
	/**
     * Returns the SettingsPanel contained in this MenuPanel.
     *
     * @return the SettingsPanel
     */
	public SettingsPanel getSettingsPanel() {
		return settingsPanel;
	}
	
	/**
     * Returns the StartingPanel contained in this MenuPanel.
     *
     * @return the StartingPanel
     */
	public StartingPanel getStartingPanel() {
		return sp;
	}
	
	/**
     * Returns the ProfilesPanel contained in this MenuPanel.
     *
     * @return the ProfilesPanel
     */
	public ProfilesPanel getProfilesPanel() {
		return pp;
	}

	/**
     * Updates the MenuPanel based on changes in the model.
     * <p>
     * If the argument is a {@link model.Profile}, updates the profile data in SettingsPanel.
     * If the argument is a {@link java.lang.String}, removes the profile with that nickname from SettingsPanel.
     * Refreshes the ProfilesPanel in all cases.
     * </p>
     *
     * @param arg the object representing the change (Profile or String)
     */
	public void reload(Object arg) {
		if (arg instanceof Profile)
			settingsPanel.reload((Profile)arg);
		else if (arg instanceof String)
			settingsPanel.removeProfile((String)arg);
		pp.reload();
	}
	
	/**
     * Returns the nickname of the user selected for deletion in the SettingsPanel.
     *
     * @return the nickname to delete
     */
	public String getToDeleteNick() {
		return settingsPanel.getToDeleteNick();
	}
	
	/**
     * Returns the string representing the selected avatar in the SettingsPanel.
     *
     * @return the avatar string
     */
	public String getAvatarString() {
		return settingsPanel.getAvatarString();
	}
}