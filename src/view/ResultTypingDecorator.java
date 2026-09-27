package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * A {@link ResultPanelDecorator} that displays the result text with a typing animation effect.
 * <p>
 * Characters of the result are shown one by one with a configurable delay, simulating a typing effect.
 * </p>
 */
public class ResultTypingDecorator extends ResultPanelDecorator {
    private Timer typingTimer;
    private String fullText;
    private int index;
    private int delayMs;
    
    /**
     * Constructs a typing decorator for the specified {@link ResultPanel}.
     *
     * @param component the ResultPanel to decorate with typing animation
     */
    public ResultTypingDecorator(ResultPanel component) {
        super(component);
        fullText = "";
        index = 0;
        delayMs = 60;
    }
    
    /**
     * Shows the given result string with a typing animation.
     * <p>
     * Each character is displayed one by one with a delay specified by {@link #delayMs}.
     * If a previous typing animation is running, it is stopped before starting the new one.
     * </p>
     *
     * @param s the result string to display
     */
    @Override
    public void showResult(String s) {
    	hideResult();
        if (typingTimer != null) {
            if (typingTimer.isRunning()) typingTimer.stop();
            typingTimer = null;
        }

        fullText = (s == null ? "" : s);
        index = 0;

        super.showResult("");

        typingTimer = new Timer(delayMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typingTimer == null) return;

                if (index <= fullText.length()) {
                	String partial = fullText.substring(0, index);
                    component.showResult(partial);
                    index++;
                } else {
                    typingTimer.stop();
                    typingTimer = null;

                    component.showResult(fullText);
                }
            }
        });

        typingTimer.setRepeats(true);
        typingTimer.setInitialDelay(0);
        typingTimer.start();
    }
    
    /**
     * Stops any ongoing typing animation and clears the result display.
     */
    @Override
    public void hideResult() {
        if (typingTimer != null) {
            if (typingTimer.isRunning()) typingTimer.stop();
            typingTimer = null;
        }
        super.hideResult();
    }
    
    /**
     * Sets the delay between each character in the typing animation.
     *
     * @param ms the delay in milliseconds
     */
    public void setDelayMs(int ms) {
        this.delayMs = ms;
        if (typingTimer != null) typingTimer.setDelay(ms);
    }
}