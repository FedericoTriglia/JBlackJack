package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import controller.DoubleDownActionListener;
import controller.SplitActionListener;
import controller.StartTurnActionListener;
import model.Game;

/**
 * Panel representing the user in the game interface.
 * <p>
 * It contains the user's cards, avatar, and action buttons
 * (Hit, Stand, Split, Double Down, Start Turn, and Bet Spinner).
 * </p>
 */
public class UserPanel extends PlayerPanel {
	private ButtonPanel buttonPanel;
	
	/**
     * Constructs a UserPanel, initializing the related card panel, button panel,
     * and avatar panel.
     */
	public UserPanel() {
		super(new CardPanel());
		buttonPanel = new ButtonPanel();
		ap = new AvatarPanel(Game.getInstance().getUser().getAvatar());
		add(buttonPanel, BorderLayout.SOUTH);
		add(cardPanel, BorderLayout.NORTH);
		add(ap, BorderLayout.CENTER);
	}
	
	/**
     * Adds a listener for the "Stand" button.
     * @param al the ActionListener to add
     */
	public void addStandButtonListener(ActionListener al) {
		buttonPanel.addStandButtonListener(al);
	}
	
	/**
     * Adds a listener for the "Hit" button.
     * @param al the ActionListener to add
     */
	public void addHitButtonListener(ActionListener al) {
		buttonPanel.addHitButtonListener(al);
	}
	
	/**
     * Enables or disables the "Hit" button.
     * @param enabled true to enable, false to disable
     */
	public void setHitButtonState(boolean enabled) {
		buttonPanel.setHitButtonState(enabled);
	}

	/**
     * Enables or disables the "Stand" button.
     * @param enabled true to enable, false to disable
     */
    public void setStandButtonState(boolean enabled) {
    	buttonPanel.setStandButtonState(enabled);
    }
    
    /**
     * Enables or disables the "Split" button.
     * @param enabled true to enable, false to disable
     */
    public void setSplitButtonState(boolean enabled) {
    	buttonPanel.setSplitButtonState(enabled);
    }
    
    /**
     * Enables or disables the "Double Down" button.
     * @param enabled true to enable, false to disable
     */
    public void setDoubleDownButtonState(boolean enabled) {
    	buttonPanel.setDoubleDownButtonState(enabled);
    }

    /**
     * Adds a listener for the "Double Down" button.
     * @param dal the DoubleDownActionListener to add
     */
	public void addDoubleDownButtonListener(DoubleDownActionListener dal) {
		buttonPanel.addDoubleDownButtonListener(dal);
	}

	/**
     * Adds a listener for the "Split" button.
     * @param spal the SplitActionListener to add
     */
	public void addSplitButtonListener(SplitActionListener spal) {
		buttonPanel.addSplitButtonListener(spal);
	}

	/**
     * Adds a listener for the "Start Turn" button.
     * @param stal the StartTurnActionListener to add
     */
	public void addStartButtonListener(StartTurnActionListener stal) {
		buttonPanel.addStartButtonListener(stal);
	}

	/**
     * Enables or disables the "Start Turn" button.
     * @param b true to enable, false to disable
     */
	public void setStartButtonState(boolean b) {
		buttonPanel.setStartButtonState(b);
	}

	/**
     * Returns the current bet value from the button panel.
     * @return the bet amount
     */
	public int getBet() {
		return buttonPanel.getBet();
	}

	/**
     * Enables or disables the bet spinner.
     * @param b true to enable, false to disable
     */
	public void setBetSpinnerState(boolean b) {
		buttonPanel.setBetSpinnerState(b);
	}
}