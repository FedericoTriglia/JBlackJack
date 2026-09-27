package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Menu;
import view.MainView;

/**
 * ActionListener implementation that handles the selection of game rules.
 * <p>
 * When triggered, this listener retrieves the selected rules option from the 
 * {@link MainView} and updates the {@link Menu} accordingly. 
 * It distinguishes between European rules ("EU") and USA rules.
 * </p>
 */
public class RulesActionListener implements ActionListener {

	/**
     * Invoked when a rules selection occurs.
     * <p>
     * This method retrieves the currently selected radio button's action command
     * from the main view. If the selection corresponds to "EU", it sets the rules
     * in {@link Menu} to European rules (false). Otherwise, it sets them to 
     * the alternative(USA) rules (true).
     * </p>
     *
     * @param e the action event that triggered this listener
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		String rules = MainView.getInstance().getRadio().getSelection().getActionCommand();
		Menu.getInstance().setRules(rules.equals("EU") ? false : true);
	}
}