package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import model.Result;

/**
 * Concrete implementation of {@link ResultPanel} for displaying a simple textual result.
 * <p>
 * Shows a large, centered label with the result text and allows changing its color.
 * </p>
 */
public class ResultBasePanel extends ResultPanel {
	
	/** Label used to display the result text. */
	private JLabel resultLabel;
	
	/**
     * Constructs a ResultBasePanel.
     * <p>
     * The result label is centered, and uses a bold Arial font at size 50.
     * </p>
     */
	public ResultBasePanel() {
		resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 50));
        add(resultLabel);
        setVisible(false);
	}
	
	/**
     * Displays the given result string on the panel.
     *
     * @param s the result text to show
     */
	@Override
	public void showResult(String s) {
		resultLabel.setText(s);
        setVisible(true);
	}
	
	/**
     * Hides the displayed result and clears the text.
     */
	@Override
	public void hideResult() {
		resultLabel.setText("");
        setVisible(false);
    }
	
	/**
     * Updates the panel.
     */
	public void reload(Object arg) {
		Result r = (Result)arg;
		showResult(r.toString() + "!");
	}
    
	/**
     * Sets the text color of the result label.
     *
     * @param c the color to apply to the result text
     */
    @Override
    public void setColor(Color c) {
        resultLabel.setForeground(c);
    }
}