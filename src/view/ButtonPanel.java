package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;

import controller.DoubleDownActionListener;
import controller.SplitActionListener;
import controller.StartTurnActionListener;

/**
 * A panel containing all buttons and the bet spinner used in the game interface.
 * <p>
 * This panel provides buttons for "STAND", "HIT", "SPLIT", "DOUBLEDOWN", and "START",
 * as well as a JSpinner for selecting the player's bet. The panel handles layout,
 * appearance, and allows adding specific listeners to each button.
 * </p>
 */
public class ButtonPanel extends JPanel {
	private JButton standButton;
	private JButton hitButton;
	private JButton doubleDownButton;
	private JButton splitButton;
	private JButton startButton;
	private JSpinner betSpinner;
	
	/**
     * Constructs a ButtonPanel with all game buttons and a bet spinner.
     * <p>
     * The layout is a FlowLayout with a green background. Buttons are added in the
     * following order: START, bet spinner, spacer, HIT, STAND, SPLIT, DOUBLEDOWN.
     * Button focus painting is disabled for better visual appearance.
     * </p>
     */
	public ButtonPanel() {
		setLayout(new FlowLayout());
		setBackground(Color.GREEN);
		standButton = new JButton("STAND");
		hitButton = new JButton("HIT");
		splitButton = new JButton("SPLIT");
		doubleDownButton = new JButton("DOUBLEDOWN");
		startButton = new JButton("START");
		betSpinner = new JSpinner();
		SpinnerNumberModel snm = new SpinnerNumberModel(5, 5, Integer.MAX_VALUE, 5);
		betSpinner.setModel(snm);
		betSpinner.setPreferredSize(new Dimension(50, betSpinner.getPreferredSize().height));
		DefaultEditor df = (DefaultEditor)betSpinner.getEditor();
		df.getTextField().setEditable(false);
		add(startButton);
		add(betSpinner);
		add(Box.createHorizontalStrut(80));
		add(hitButton);
		add(standButton);
		add(splitButton);
		add(doubleDownButton);
		
		hitButton.setFocusPainted(false);
		standButton.setFocusPainted(false);
		splitButton.setFocusPainted(false);
		doubleDownButton.setFocusPainted(false);
		startButton.setFocusPainted(false);
	}
	
	/** Adds an ActionListener to the STAND button. */
	public void addStandButtonListener(ActionListener al) {
		standButton.addActionListener(al);
	}
	
	/** Adds an ActionListener to the HIT button. */
	public void addHitButtonListener(ActionListener al) {
		hitButton.addActionListener(al);
	}
	
	/** Enables or disables the HIT button. */
	public void setHitButtonState(boolean state) {
		hitButton.setEnabled(state);
	}
	
	/** Enables or disables the STAND button. */
    public void setStandButtonState(boolean state) {
        standButton.setEnabled(state);
    }
    
    /** Enables or disables the SPLIT button. */
    public void setSplitButtonState(boolean state) {
    	splitButton.setEnabled(state);
    }
    
    /** Enables or disables the DOUBLEDOWN button. */
    public void setDoubleDownButtonState(boolean state) {
    	doubleDownButton.setEnabled(state);
    }

    /** Adds a DoubleDownActionListener to the DOUBLEDOWN button. */
	public void addDoubleDownButtonListener(DoubleDownActionListener dal) {
		doubleDownButton.addActionListener(dal);
	}
	
	/** Adds a SplitActionListener to the SPLIT button. */
	public void addSplitButtonListener(SplitActionListener spal) {
		splitButton.addActionListener(spal);
	}
	
	/** Enables or disables the START button. */
	public void setStartButtonState(boolean state) {
		startButton.setEnabled(state);
    }
	
	/** Adds a StartTurnActionListener to the START button. */
	public void addStartButtonListener(StartTurnActionListener stal) {
		startButton.addActionListener(stal);
	}
	
	/** Returns the current value of the bet spinner. */
	public int getBet() {
		return (int)betSpinner.getValue();
	}
	
	/** Enables or disables the bet spinner. */
	public void setBetSpinnerState(boolean b) {
		betSpinner.setEnabled(b);
	}
}