package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * Abstract panel for displaying the result of a game turn.
 * <p>
 * This class is abstract in order to apply the decorator pattern to display the result.
 * </p>
 */
public abstract class ResultPanel extends JPanel {
	
	/**
     * Constructs a new ResultPanel.
     * <p>
     * Sets the background color to green and the layout to {@link BorderLayout}.
     * </p>
     */
	public ResultPanel() {
		setBackground(Color.GREEN);
		setLayout(new BorderLayout());
	}
	
	/**
     * Updates the panel.
     *
     * @param arg the object containing the data to update
     */
	public abstract void reload(Object arg);
	
	/**
     * Sets the panel's text color.
     * 
     * @param c the color to set
     */
	public abstract void setColor(Color c);
	
	/**
     * Displays a textual result on the panel.
     *
     * @param s the result string to display
     */
	public abstract void showResult(String s);
	
	/**
     * Hides the displayed result.
     * <p>
     * Useful for resetting the panel before a new turn or update.
     * </p>
     */
    public abstract void hideResult();
}