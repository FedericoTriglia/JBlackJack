package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.NewGameActionListener;

/**
 * Panel containing the starting controls of the game.
 */
public class StartingPanel extends JPanel {
	/** Button used to start a new game. */
	private JButton playButton;
	
	/**
     * Constructs the StartingPanel and initializes the "PLAY" button.
     */
	public StartingPanel() {
		playButton = new JButton("PLAY");
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(playButton);
		playButton.setFocusPainted(false);
	}
	
	/**
     * Adds a listener to the "PLAY" button to start a new game.
     * 
     * @param ngal the NewGameActionListener to add
     */
	public void addNewGameListener(NewGameActionListener ngal) {
		playButton.addActionListener(ngal);
	}
}