package view;

import java.awt.*;
import javax.swing.*;
import model.Card;
import model.Dealer;
import model.Game;
import model.Hand;
import model.SplitStrategy;
import model.User;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * This panel displays cards as scaled images in a FlowLayout with spacing between them.
 */
public class CardPanel extends JPanel {
    private List<JLabel> cardLabels;
    private final int cardWidth = 110;
    private final int cardHeight = 154;
    private List<JLabel> secondHandLabels;
    
    /**
     * Constructs a CardPanel with a green background and default layout for card placement.
     */
    public CardPanel() {
        this.setBackground(Color.GREEN);
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Layout con spaziatura personalizzata
        cardLabels = new ArrayList<>();
    }
    
    /**
     * Loads a card image from the resources folder.
     *
     * @param cardName the name of the card image file (without extension)
     * @return the ImageIcon of the card, or null if the file could not be loaded
     */
    private ImageIcon loadCardImage(String cardName) {
        Path path = java.nio.file.FileSystems.getDefault().getPath("");
        path = path.toAbsolutePath();
        String pathCard = path + "/resources/" + cardName + ".png";
        try {
            return new ImageIcon(pathCard);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Updates the panel when an observable object (User or Dealer) changes.
     * <p>
     * Handles adding cards to the main hand, second hand (if splitting),
     * and dealer's revealed card. Introduces a small delay for card animation effect.
     * </p>
     *
     * @param o   the observable object
     * @param arg the argument passed by the observable, which can be a Card, Hand, or Boolean
     */
    public void reload(Observable o, Object arg) {
    	User u = Game.getInstance().getUser();
    	if (arg instanceof Hand) {
    		if (o instanceof User && u.getStrategy() instanceof SplitStrategy) {
				cardLabels.remove(1);
				remove(1);
				Hand h = (Hand)arg;
				secondHandLabels = new ArrayList<>();
				add(Box.createHorizontalStrut(80));
				addCardSplitted(h.get(0));
				try {
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                throw new RuntimeException(e);
	            }
    		}
    	}
    	else if(arg instanceof Card) {
    		if (o instanceof User && u.getSecondHand() != null) {
    			cardLabels.clear();
        		secondHandLabels.clear();
        		removeAll();
        		Hand first = u.getHand();
        		Hand second = u.getSecondHand();
        		for (int j = 0; j<first.size(); j++) {
        			Card c = first.get(j);
        			if (!u.playingSecond() && j == first.size()-1) {
        				try {
        		            Thread.sleep(500);
        		        } catch (InterruptedException e) {
        		            throw new RuntimeException(e);
        		        }
        			}
        			addCard(c);
        		}
        		add(Box.createHorizontalStrut(80));
        		for (int i = 0; i<second.size(); i++) {
        			Card c = second.get(i);
        			if (u.playingSecond() && i == second.size()-1) {
        				try {
        		            Thread.sleep(500);
        		        } catch (InterruptedException e) {
        		            throw new RuntimeException(e);
        		        }
        			}
        			addCardSplitted(c);
        		}
    		}
    		else {
		        addCard((Card) arg);
		        try {
		            Thread.sleep(500);
		        } catch (InterruptedException e) {
		            throw new RuntimeException(e);
		        }
    		}
        }
        else if (arg instanceof Boolean && ((Boolean) arg).booleanValue() && o instanceof Dealer) {
        	cardLabels.remove(1);
        	remove(1);
        	addCard(Game.getInstance().getDealer().getHand().get(1));
        	try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            cardLabels.clear();
            secondHandLabels = null;
            removeAll();
        }
        revalidate();
        repaint();
    }
    
    /**
     * Adds a card to the main hand display.
     *
     * @param card the card to be added
     */
    public void addCard(Card card) {
        ImageIcon cardIcon = loadCardImage(card.toString());
        ImageIcon resizedIcon = new ImageIcon(cardIcon.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
        JLabel cardLabel = new JLabel(resizedIcon);
        cardLabels.add(cardLabel);
        add(cardLabel);
    }
    
    /**
     * Adds a card to the second hand display (used when the player splits his hand).
     *
     * @param card the card to be added
     */
    public void addCardSplitted(Card card) {
        ImageIcon cardIcon = loadCardImage(card.toString());
        ImageIcon resizedIcon = new ImageIcon(cardIcon.getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
        JLabel cardLabel = new JLabel(resizedIcon);
        secondHandLabels.add(cardLabel);
        add(cardLabel);
    }
}