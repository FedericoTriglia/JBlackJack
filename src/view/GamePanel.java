package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

import model.*;
import javax.swing.*;

import controller.DoubleDownActionListener;
import controller.SplitActionListener;
import controller.StartTurnActionListener;

/**
 * Panel representing the main game interface.
 * <p>
 * Contains the dealer panel at the top, the user panel at the bottom, 
 * and a result panel in the center. Provides methods to update UI elements
 * and handle user interactions such as buttons and bets.
 * </p>
 */
public class GamePanel extends JPanel {
	private DealerPanel dealerPanel;
	private UserPanel userPanel;
	private ResultPanel resultPanel;
	
	/**
     * Constructs the main GamePanel with dealer, user, and result panels.
     */
	public GamePanel() {
		setBackground(Color.GREEN);
		setLayout(new BorderLayout());
		dealerPanel = new DealerPanel();
		userPanel = new UserPanel();
		add(dealerPanel, BorderLayout.NORTH);
		add(userPanel, BorderLayout.SOUTH);
		ResultPanel base = new ResultBasePanel();
		resultPanel = new ResultTypingDecorator(
						new ResultColorDecorator(
							new ResultFadeDecorator(base)
						)
					);
		add(resultPanel, BorderLayout.CENTER);
	}
	
	/**
     * Adds an ActionListener for the user's stand button.
     * @param al the ActionListener to add
     */
	public void addStandButtonListener(ActionListener al) {
		userPanel.addStandButtonListener(al);
	}
	
	/**
     * Adds an ActionListener for the user's hit button.
     * @param al the ActionListener to add
     */
	public void addHitButtonListener(ActionListener al) {
		userPanel.addHitButtonListener(al);
	}
	
	/**
     * Enables or disables the hit button.
     * @param enabled true to enable, false to disable
     */
	public void setHitButtonState(boolean enabled) {
		userPanel.setHitButtonState(enabled);
	}

	/**
     * Enables or disables the stand button.
     * @param enabled true to enable, false to disable
     */
    public void setStandButtonState(boolean enabled) {
    	userPanel.setStandButtonState(enabled);
    }

    /**
     * Shows the result text on the result panel.
     * @param s the result text
     */
    public void showResult(String s) {
		resultPanel.showResult(s);
	}

    /**
     * Hides the result panel.
     */
    public void hideResult() {
        resultPanel.hideResult();
    }
    
    /**
     * Enables or disables the split button.
     * @param enabled true to enable, false to disable
     */
    public void setSplitButtonState(boolean enabled) {
    	userPanel.setSplitButtonState(enabled);
    }
    
    /**
     * Enables or disables the double down button.
     * @param enabled true to enable, false to disable
     */
    public void setDoubleDownButtonState(boolean enabled) {
    	userPanel.setDoubleDownButtonState(enabled);
    }

    /**
     * Adds an ActionListener for the double down button.
     * @param dal the DoubleDownActionListener to add
     */
	public void addDoubleDownButtonListener(DoubleDownActionListener dal) {
		userPanel.addDoubleDownButtonListener(dal);
	}
	
	/**
     * Adds an ActionListener for the split button.
     * @param spal the SplitActionListener to add
     */
	public void addSplitButtonListener(SplitActionListener spal) {
		userPanel.addSplitButtonListener(spal);
	}

	/**
     * Adds an ActionListener for the start turn button.
     * @param stal the StartTurnActionListener to add
     */
	public void addStartButtonListener(StartTurnActionListener stal) {
		userPanel.addStartButtonListener(stal);
	}

	/**
     * Enables or disables the start button.
     * @param b true to enable, false to disable
     */
	public void setStartButtonState(boolean b) {
		userPanel.setStartButtonState(b);
	}

	/**
     * Gets the current bet from the user panel.
     * @return the current bet amount
     */
	public int getBet() {
		return userPanel.getBet();
	}

	/**
     * Enables or disables the bet spinner.
     * @param b true to enable, false to disable
     */
	public void setBetSpinnerState(boolean b) {
		userPanel.setBetSpinnerState(b);
		
	}

	/**
     * Updates the panels based on changes in the game model.
     * @param o the observable object
     * @param arg the argument passed by the observable
     */
	public void reload(Observable o, Object arg) {
		if (o instanceof Game)
			if (arg instanceof Result)
				resultPanel.reload(arg);
			else {
				remove(resultPanel);
				ResultPanel base = new ResultBasePanel();
				resultPanel = new ResultTypingDecorator(
								new ResultColorDecorator(
									new ResultFadeDecorator(base)
								)
							);
				add(resultPanel, BorderLayout.CENTER);
			}
		else if (o instanceof Dealer)
			dealerPanel.reload(o, arg);
		else if (o instanceof User)
			userPanel.reload(o, arg);
	}
}