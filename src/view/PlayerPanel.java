package view;

import java.awt.BorderLayout;
import java.util.Observable;

import javax.swing.JPanel;

/**
 * Abstract panel representing a player in the game (user or dealer).
 * <p>
 * Each PlayerPanel contains a {@link CardPanel} to display the player's cards.
 * </p>
 */
public abstract class PlayerPanel extends JPanel{
	protected CardPanel cardPanel;
	protected AvatarPanel ap;
	
	/**
     * Constructs a PlayerPanel with the given CardPanel.
     *
     * @param cp the CardPanel to display the player's cards
     */
	public PlayerPanel(CardPanel cp) {
		cardPanel = cp;
		setLayout(new BorderLayout());
	}
	
	/**
     * Updates the PlayerPanel based on changes in the model.
     *
     * @param o   the Observable object being observed
     * @param arg an argument passed by the Observable
     */
	public void reload(Observable o, Object arg) {
		cardPanel.reload(o, arg);
	}
}