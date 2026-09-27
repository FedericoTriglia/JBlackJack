package view;

import java.awt.BorderLayout;
import model.Game;

/**
 * Panel representing the dealer in the game interface.
 * <p>
 * Extends PlayerPanel and includes the dealer's avatar at the top
 * and the dealer's card panel in the center.
 * </p>
 */
public class DealerPanel extends PlayerPanel {
	
	/**
     * Constructs a DealerPanel with the dealer's avatar and card panel.
     */
	public DealerPanel() {
		super(new CardPanel());
		ap = new AvatarPanel(Game.getInstance().getDealer().getAvatar());
		add(ap, BorderLayout.NORTH);
		add(cardPanel, BorderLayout.CENTER);
	}
}