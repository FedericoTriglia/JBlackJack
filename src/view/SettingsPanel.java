package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import controller.DeleteActionListener;
import controller.RegistrationActionListener;
import controller.RulesActionListener;
import controller.UserSelectionActionListener;
import model.Avatar;
import model.Menu;
import model.Profile;

/**
 * Panel for configuring game settings and managing user profiles.
 * <p>
 * Allows the user to:
 * <ul>
 *   <li>Select game rules (EU or USA)</li>
 *   <li>Select the number of decks to use</li>
 *   <li>Select the active player</li>
 *   <li>Choose an avatar for the player</li>
 *   <li>Create new user accounts</li>
 *   <li>Delete existing user accounts</li>
 * </ul>
 * </p>
 */
public class SettingsPanel extends JPanel {
	//componenti per registrazione nuovo utente
	private JLabel labelNewAccount;
	private JTextField registrationField;
	private JButton registrationButton;
	//componenti per cancellazione utente già esistente
	private JLabel labelDeleteAccount;
	private JTextField deleteField;
	private JButton deleteButton;
	//componenti per selezione regole
	private JLabel rulesLabel;
	private JRadioButton buttonEu;
	private JRadioButton buttonUsa;
	private ButtonGroup rulesRadioGroup;
	//componenti per selezione giocatore
	private JLabel playerLabel;
	private JComboBox<String> menuPlayers;
	//componenti per selezionare avatar
	private JLabel avatarLabel;
	private JComboBox<String> menuAvatars;
	//componenti per selezione numero mazzi
	private JLabel decksLabel;
	private JSpinner decksSpinner;
	
	/**
     * Constructs a SettingsPanel with all components (spinner, combo box, labels, buttons, ecc.) initialized.
     */
	public SettingsPanel() {
		setPreferredSize(new Dimension(500, 10));
		setLayout(new GridBagLayout());
		
		Border internalBorder = BorderFactory.createTitledBorder("Settings");
		Border externalBorder = BorderFactory.createEmptyBorder(0, 5, 5, 5);
		Border finalBorder = BorderFactory.createCompoundBorder(externalBorder, internalBorder);
		setBorder(finalBorder);
		
		//Decks:
		decksLabel = new JLabel("Decks: ");
		decksSpinner = new JSpinner();
		SpinnerNumberModel snm = new SpinnerNumberModel(2, 2, 6, 1);
		decksSpinner.setModel(snm);
		DefaultEditor df = (DefaultEditor)decksSpinner.getEditor();
		df.getTextField().setEditable(false);
		//Players:
		playerLabel = new JLabel("Select player: ");
		menuPlayers = new JComboBox<>(Menu.getInstance().getNicks());
		//Avatars:
		avatarLabel = new JLabel("Choose avatar: ");
		menuAvatars = new JComboBox<>(Avatar.getAnimals());
		menuAvatars.setPreferredSize(new Dimension(150, menuAvatars.getPreferredSize().height));
		
		if (Menu.getInstance().getCurrentUser() != null) {
			menuPlayers.setSelectedItem(Menu.getInstance().getCurrentUser().getNickname());
			menuAvatars.setSelectedItem(Menu.getInstance().getCurrentUser().getAvatar().name());
		}
		else if (Avatar.getAnimals() != null && Avatar.getAnimals().length>0)
			menuAvatars.setSelectedIndex(0);
		
		//Rules:
		rulesLabel = new JLabel("Rules: ");
		buttonEu = new JRadioButton("EU", true);
		buttonUsa = new JRadioButton("USA", false);
		buttonEu.setFocusPainted(false);
		buttonUsa.setFocusPainted(false);
		buttonEu.setActionCommand("EU");
		buttonUsa.setActionCommand("USA");
		
		rulesRadioGroup = new ButtonGroup();
		rulesRadioGroup.add(buttonEu);
		rulesRadioGroup.add(buttonUsa);
		//registration field:
		labelNewAccount = new JLabel("New Account: ");
		registrationField = new JTextField("Your nickname", 20);
		registrationButton = new JButton("Create Account");
		registrationButton.setFocusPainted(false);
		//delete account field:
		labelDeleteAccount = new JLabel("Delete Account: ");
		deleteField = new JTextField("Your nickname", 20);
		deleteButton = new JButton("Delete Account");
		deleteButton.setFocusPainted(false);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//1ST-2ND RAW: RULES RADIO-BUTTONS
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(rulesLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(buttonEu, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(buttonUsa, gbc);
		
		//3RD RAW: SPINNER DECKS
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		gbc.insets = new Insets(10, 0, 15, 5);
		
		add(decksLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		gbc.insets = new Insets(10, 0, 15, 5);
		
		add(decksSpinner, gbc);
		
		//4TH RAW: SELECT USER
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		gbc.insets = new Insets(10, 0, 15, 5);
		
		add(playerLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(menuPlayers, gbc);
		
		//5TH RAW: AVATAR
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(avatarLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.insets = new Insets(0, 0, 0, 4);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(menuAvatars, gbc);
		
		//6TH RAW: NEW USER NICK + create account
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.insets = new Insets(10, 0, 15, 5);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(labelNewAccount, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.insets = new Insets(0, 0, 0, 4);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(registrationField, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 5;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.insets = new Insets(0, 0, 0, 0);
		
		gbc.ipadx = 10;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(registrationButton, gbc);
		
		//7TH RAW: DELETE USER
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.insets = new Insets(10, 0, 15, 5);
		
		gbc.ipadx = 0;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(labelDeleteAccount, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.insets = new Insets(0, 0, 0, 4);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(deleteField, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 6;
		
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		gbc.insets = new Insets(0, 0, 0, 0);
		
		gbc.ipadx = 13;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(deleteButton, gbc);
		
	}
	
	/**
     * Returns the nickname entered in the JTextField to create a new account.
     *
     * @return the new nickname as a String
     */
	public String getNewNick() {
		return registrationField.getText();
	}
	
	/**
     * Returns the ButtonGroup for rule selection.
     *
     * @return the rules radio button group
     */
	public ButtonGroup getRadio() {
		return rulesRadioGroup;
	}
	
	/**
     * Returns the currently selected player nickname.
     *
     * @return the selected nickname object
     */
	public Object getCurrentNick() {
		return menuPlayers.getSelectedItem();
	}
	
	/**
     * Returns the number of decks selected using the JSpinner.
     *
     * @return number of decks
     */
	public int getDecks() {
		return (int) decksSpinner.getValue();
	}

	/**
     * Updates the player list with a new profile.
     *
     * @param arg the Profile to add
     */
	public void reload(Profile arg) {
		boolean b = true;
		for (int i = 0; i < menuPlayers.getItemCount(); i++)
	        if (menuPlayers.getItemAt(i).equals(arg.getNickname()))
	            b = false;
		if (b) {
			menuPlayers.addItem(arg.getNickname());
			menuPlayers.setSelectedItem(arg.getNickname());
		}
	}

	/**
     * Adds a listener for creating new accounts.
     *
     * @param real the RegistrationActionListener to add
     */
	public void addRegistrationListener(RegistrationActionListener real) {
		registrationButton.addActionListener(real);
	}

	/**
     * Adds listeners for rule selection changes.
     *
     * @param rual the RulesActionListener to add
     */
	public void addRulesListener(RulesActionListener rual) {
		buttonEu.addActionListener(rual);
		buttonUsa.addActionListener(rual);
		
	}

	/**
     * Adds a listener for selecting a profile from the ComboBox.
     *
     * @param ssal the UserSelectionActionListener to add
     */
	public void addUserSelectionListener(UserSelectionActionListener ssal) {
		menuPlayers.addActionListener(ssal);
	}

	/**
     * Returns the nickname entered for deletion.
     *
     * @return nickname to delete as a String
     */
	public String getToDeleteNick() {
		return deleteField.getText();
	}

	/**
     * Removes a profile from the player ComboBox.
     *
     * @param arg the nickname to remove
     */
	public void removeProfile(String arg) {
		Menu m = Menu.getInstance();
		for (int i = 0; i < menuPlayers.getItemCount(); i++)
	        if (menuPlayers.getItemAt(i).equals(arg)) {
	        	menuPlayers.removeItem(arg);
	        }
		if (m.getCurrentUser() != null)
			menuPlayers.setSelectedItem(m.getCurrentUser().getNickname());
	}

	/**
     * Adds a listener for deleting accounts.
     *
     * @param delAl the DeleteActionListener to add
     */
	public void addDeleteActionListener(DeleteActionListener delAl) {
		deleteButton.addActionListener(delAl);
	}

	/**
     * Returns the currently selected avatar as a String.
     *
     * @return selected avatar
     */
	public String getAvatarString() {
		return (String) menuAvatars.getSelectedItem();
	}
}