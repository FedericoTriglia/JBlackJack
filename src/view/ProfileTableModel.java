package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Menu;
import model.Profile;

/**
 * Table model for displaying user profiles in a JTable.
 * <p>
 * Each row corresponds to a {@link Profile}, and columns show
 * nickname, score, games played, won, lost, pushed, and blackjack count.
 * </p>
 */
public class ProfileTableModel extends AbstractTableModel {
	
	/** The list of profiles displayed in the table. */
	private List<Profile> profiles;
	
	/** Column names for the table. */
	private final String[] columns = {"NICKNAME", "SCORE", "PLAYED", "WON", "LOST", "PUSH", "BJs"};

	/**
     * Constructs a ProfileTableModel.
     */
	public ProfileTableModel() {
		profiles = Menu.getInstance().getUsers();
	}
	
	/**
     * Returns the number of rows in the table.
     * @return number of profiles
     */
	@Override
	public int getRowCount() {
		return profiles.size();
	}

	/**
     * Returns the number of columns in the table.
     * @return the number of columns
     */
	@Override
	public int getColumnCount() {
		return 7;
	}

	/**
     * Returns the value at a specific row and column.
     * @param rowIndex the row index
     * @param columnIndex the column index
     * @return the value for the specified cell
     */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Profile p = profiles.get(rowIndex);
		switch(columnIndex) {
			case 0: return p.getNickname();
			case 1: return p.getScore();
			case 2: return p.getPlayed();
			case 3: return p.getWon();
			case 4: return p.getLost();
			case 5: return p.getPush();
			case 6: return p.getBJ();
			default: return null;
		}
	}
	
	/**
     * Returns the name of a specific column.
     * @param column the column index
     * @return the column name
     */
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	/**
     * Reloads the profile data and notifies the table.
     */
	public void setData() {
		profiles = Menu.getInstance().getUsers();
		fireTableDataChanged();
	}
}