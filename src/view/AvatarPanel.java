package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Avatar;

/**
 * A panel that displays a player's avatar image.
 * <p>
 * The avatar image is loaded from the "resources" folder based on the avatar's name
 * and resized to a fixed width and height. The panel uses a green background and 
 * centers the avatar image.
 * </p>
 */
public class AvatarPanel extends JPanel {
	private final int avatarWidth = 110;
	private final int avatarHeight = 110;
	private final JLabel avatarLabel;
	
	/**
     * Constructs an AvatarPanel for the given Avatar.
     *
     * @param a the Avatar whose image will be displayed
     */
	public AvatarPanel(Avatar a) {
		setLayout(new BorderLayout());
		setBackground(Color.GREEN);
		ImageIcon avatarIcon = loadAvatarImage(a.name());
		ImageIcon resizedIcon = new ImageIcon(avatarIcon.getImage().getScaledInstance(avatarWidth, avatarHeight, Image.SCALE_SMOOTH));
		avatarLabel = new JLabel(resizedIcon);
		avatarLabel.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
		add(avatarLabel, BorderLayout.CENTER);
	}
	
	/**
     * Loads the avatar image from the "resources" folder.
     *
     * @param avatarName the name of the avatar (corresponding to a PNG file in the resources folder)
     * @return an ImageIcon containing the avatar image, or null if loading fails
     */
	private ImageIcon loadAvatarImage(String avatarName) {
        Path path = java.nio.file.FileSystems.getDefault().getPath("");
        path = path.toAbsolutePath();
        String pathCard = path + "/resources/" + avatarName + ".png";
        try {
            return new ImageIcon(pathCard);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}