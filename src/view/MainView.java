package view;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;

import controller.DeleteActionListener;
import controller.DoubleDownActionListener;
import controller.NewGameActionListener;
import controller.RegistrationActionListener;
import controller.RulesActionListener;
import controller.SplitActionListener;
import controller.StartTurnActionListener;
import controller.UserSelectionActionListener;

/**
 * Singleton JFrame representing the window of the JBlackJack game.
 * <p>
 * It manages the transition between the MenuPanel and the GamePanel and acts 
 * as the main interface for adding listeners and retrieving user inputs.
 * </p>
 */
public class MainView extends JFrame implements Observer {
	public static final String TITLE = "JBlackJack";
	private static MainView instance;
	private MenuPanel mp;
	private GamePanel gp;
	
	/**
     * Returns the single instance of MainView.
     * @return the singleton instance
     */
	public static MainView getInstance() {
		if (instance == null)
			instance = new MainView();
		return instance;
	}
	
	/**
     * Private constructor for singleton pattern.
     * Initializes the window with the MenuPanel.
     */
	private MainView() {
		super(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1350, 370);
		setResizable(false);
		setLocationRelativeTo(null);
		mp = new MenuPanel();
		setContentPane(mp);
	}

	/**
     * Updates the view based on changes in observed objects.
     * Delegates updates to MenuPanel and GamePanel if they exist.
     * @param o the observable object
     * @param arg the argument passed by the observable
     */
	@Override
	public void update(Observable o, Object arg) {
		if (mp != null)
			mp.reload(arg);
		if (gp != null)
			gp.reload(o, arg);
	}
	
	/**
     * Sets the GamePanel as contentPane of the class.
     */
	public void setGamePanel() {
		mp.setVisible(false);
		mp = null;
		gp = new GamePanel();
		setContentPane(gp);
		setLocation(45, 10);
		setSize(1450, 800);
		setResizable(true);
		revalidate();
		repaint();
	}

	/** Adds a listener for the "New Game" button in the MenuPanel. */
	public void addNewGameListener(NewGameActionListener ngal) {
		mp.getStartingPanel().addNewGameListener(ngal);
	}

	/** Adds a listener for profile registration actions in the MenuPanel. */
	public void addRegistrationListener(RegistrationActionListener real) {
		mp.getSettingsPanel().addRegistrationListener(real);
	}

	/** Adds a listener to select the game rules from the MenuPanel. */
	public void addRulesListener(RulesActionListener rual) {
		mp.getSettingsPanel().addRulesListener(rual);
	}

	/** Adds a listener for selecting a profile from a list in the MenuPanel. */
	public void addUserSelectionListener(UserSelectionActionListener ssal) {
			mp.getSettingsPanel().addUserSelectionListener(ssal);
	}
	
	/** Adds a listener for the "Stand" button in the GamePanel. */
	public void addStandButtonListener(ActionListener al) {
		if (gp != null)
			gp.addStandButtonListener(al);
	}
	
	/** Adds a listener for the "Hit" button in the GamePanel. */
	public void addHitButtonListener(ActionListener al) {
		if (gp != null)
			gp.addHitButtonListener(al);
	}
	
	/** Enables or disables the "Hit" button in the GamePanel. */
	public void setHitButtonState(boolean enabled) {
		if (gp != null)
			gp.setHitButtonState(enabled);
	}

	/** Enables or disables the "Stand" button in the GamePanel. */
    public void setStandButtonState(boolean enabled) {
    	if (gp != null)
    		gp.setStandButtonState(enabled);
    }
    
    /** Enables or disables the "Split" button in the GamePanel. */
    public void setSplitButtonState(boolean enabled) {
    	if (gp != null)
    		gp.setSplitButtonState(enabled);
    }
    
    /** Enables or disables the "Double Down" button in the GamePanel. */
    public void setDoubleDownButtonState(boolean enabled) {
    	if (gp != null) {
    		gp.setDoubleDownButtonState(enabled);
    	}
    }

    /** Shows the result text in the GamePanel. */
    public void showResult(String s) {
		gp.showResult(s);
	}

    /** Hides the result text in the GamePanel. */
    public void hideResult() {
    	gp.hideResult();
    }
    
    /** Returns the number of decks selected in the MenuPanel. */
    public int getDecks() {
    	if (mp != null)
    		return mp.getDecks();
    	throw new IllegalStateException("Invalid state: MenuPanel does not exists");
    }
    
    /** Returns the current bet selected in the GamePanel. */
    public int getBet() {
    	if (gp != null)
    		return gp.getBet();
    	throw new IllegalStateException("Invalid state: GamePanel does not exists");
    }

    /** Returns the nickname entered for a new profile in the MenuPanel. */
	public String getNewNick() {
		if (mp != null)
    		return mp.getNewNick();
		throw new IllegalStateException("Invalid state: MenuPanel does not exists");
	}
	
	/** Returns the ButtonGroup representing user selection radio buttons. */
	public ButtonGroup getRadio() {
		if (mp != null)
			return mp.getRadio();
		throw new IllegalStateException("Invalid state: MenuPanel does not exists");
	}
	
	/** Returns the current selected user nickname in the MenuPanel. */
	public String getCurrentNick() {
		if (mp != null)
			return mp.getCurrentNick();
		throw new IllegalStateException("Invalid state: MenuPanel does not exists");
	}

	/** Adds a listener for the "Split" button in the GamePanel. */
	public void addSplitButtonListener(SplitActionListener spal) {
		if (gp != null)
			gp.addSplitButtonListener(spal);
	}

	/** Adds a listener for the "Double Down" button in the GamePanel. */
	public void addDoubleDownButtonListener(DoubleDownActionListener dal) {
		if (gp != null)
			gp.addDoubleDownButtonListener(dal);
	}

	/** Adds a listener for the "Start Turn" button in the GamePanel. */
	public void addStartButtonListener(StartTurnActionListener stal) {
		if (gp != null)
			gp.addStartButtonListener(stal);
	}

	/** Enables or disables the "Start Turn" button in the GamePanel. */
	public void setStartButtonState(boolean b) {
		if (gp != null)
			gp.setStartButtonState(b);
	}

	/** Enables or disables the bet spinner in the GamePanel. */
	public void setBetSpinnerState(boolean b) {
		if (gp != null)
			gp.setBetSpinnerState(b);
	}

	/** Returns the nickname of the profile selected for deletion in the MenuPanel. */
	public String getToDeleteNick() {
		if (mp != null)
    		return mp.getToDeleteNick();
		throw new IllegalStateException("Invalid state: MenuPanel does not exists");
	}

	/** Adds a listener for deleting a profile in the MenuPanel. */
	public void addDeleteActionListener(DeleteActionListener delAl) {
		mp.getSettingsPanel().addDeleteActionListener(delAl);
	}

	/** Returns the string representing the selected avatar in the MenuPanel. */
	public String getAvatarString() {
		if (mp != null)
			return mp.getAvatarString();
		throw new IllegalStateException("Invalid state: MenuPanel does not exists");
	}
}