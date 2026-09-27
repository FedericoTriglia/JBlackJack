package view;

import java.awt.Color;

/**
 * A {@link ResultPanelDecorator} that changes the text color based on the content of the result.
 * <p>
 * The color is determined by the text string. This decorator sets the color before delegating
 * the display to the wrapped {@link ResultPanel}, so it can work in combination with other decorators
 * like {@link ResultTypingDecorator}.
 * </p>
 */
public class ResultColorDecorator extends ResultPanelDecorator {

	/**
     * Constructs a color decorator for the specified {@link ResultPanel}.
     *
     * @param component the ResultPanel to decorate with color changes
     */
    public ResultColorDecorator(ResultPanel component) {
        super(component);
    }

    /**
     * Shows the result string and updates the text color based on its content.
     *
     * @param s the result string to display
     */
    @Override
    public void showResult(String s) {
        Color c = chooseColorForText(s);
        component.setColor(c);
        super.showResult(s);
    }

    /**
     * Determines the color for a given text.
     *
     * @param text the result string
     * @return the corresponding {@link Color} for the text
     */
    private Color chooseColorForText(String text) {
        if (text == null)
        	return Color.BLACK;
        switch(text) {
    		case "BLACK JACK!": return Color.MAGENTA;
    		case "YOU WON!": return Color.BLUE;
    		case "YOU LOST!": return Color.RED;
    		case "PUSH!": return Color.YELLOW;
    		default: return Color.BLACK;
        }
    }
}