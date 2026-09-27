package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Panel that displays the list of profiles in a table.
 * <p>
 * The table is backed by a {@link ProfileTableModel}, which provides
 * the data for the profiles.
 * </p>
 */
public class ProfilesPanel extends JPanel {
	
	/** The JTable displaying profiles. */
	private JTable table;
	
	/** The table model providing data for the profiles. */
	private ProfileTableModel modelProfile;
	
	/**
     * Constructs a ProfilesPanel and initializes the table with its model.
     */
	public ProfilesPanel() {
		modelProfile = new ProfileTableModel();
		table = new JTable(modelProfile);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	/**
     * Updates the table by refreshing its data from the model.
     * <p>
     * Typically called when profiles are added, removed, or modified.
     * </p>
     */
	public void reload() {
		modelProfile.setData();
	}
}