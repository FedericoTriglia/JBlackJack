package view;

import javax.swing.Timer;
import java.awt.Color;

/**
 * A decorator for {@link ResultPanel} that applies a fade-out effect to the displayed result.
 * <p>
 * The fade works by holding the result fully opaque for a certain time, then gradually reducing
 * its opacity until it becomes invisible. The color to fade is set via {@link #setColor(Color)},
 * typically by a {@link ResultColorDecorator}.
 * </p>
 */
public class ResultFadeDecorator extends ResultPanelDecorator {
    private Timer colorTimer;
    private long startNano;
    private int holdMs;
    private int fadeDurationMs;
    private int tick;

    /** The color to use for fading; set by {@link #setColor(Color)}. */
    private Color pendingColor = Color.BLACK;

    /**
     * Constructs a fade decorator wrapping the given {@link ResultPanel}.
     *
     * @param component the panel to decorate
     */
    public ResultFadeDecorator(ResultPanel component) {
        super(component);
        holdMs = 800;
        fadeDurationMs = 1000;
        tick = 25;
    }

    /**
     * Stores the color to be used when showing the result. 
     * The color is not applied immediately; it is used when {@link #showResult(String)} is called.
     *
     * @param c the color to use for the fade effect
     */
    @Override
    public void setColor(Color c) {
        this.pendingColor = (c != null) ? c : Color.BLACK;
    }
    
    /**
     * Shows the result and starts the fade-out effect.
     * <p>
     * Stops any previous fade, displays the full text immediately, and then gradually fades
     * the text until it disappears.
     * </p>
     *
     * @param s the text to display
     */
    @Override
    public void showResult(String s) {
        if (colorTimer != null) {
            if (colorTimer.isRunning()) colorTimer.stop();
            colorTimer = null;
        }
        super.showResult(s);
        final Color baseColor = (pendingColor != null) ? pendingColor : Color.BLACK;
        component.setColor(new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 255));

        startNano = System.nanoTime();
        colorTimer = new Timer(tick, e -> {
            double elapsedMs = (System.nanoTime() - startNano) / 1_000_000.0;
            double afterHold = elapsedMs - holdMs;
            if (afterHold < 0) {
                component.setColor(new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 255));
                return;
            }
            double t = Math.min(1.0, afterHold / fadeDurationMs);
            int alpha = (int)Math.round(255 * (1 - t));
            if (alpha < 0) alpha = 0;
            component.setColor(new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), alpha));
            if (t >= 1.0) {
                colorTimer.stop();
                colorTimer = null;
                component.hideResult();
            }
        });
        colorTimer.setRepeats(true);
        colorTimer.start();
    }
    
    /**
     * Hides the result and stops any active fade effect.
     */

    @Override
    public void hideResult() {
        if (colorTimer != null) {
            if (colorTimer.isRunning())
            	colorTimer.stop();
            colorTimer = null;
        }
        super.hideResult();
    }

    /**
     * Sets the duration (in milliseconds) to hold the result fully opaque before fading.
     *
     * @param holdMs duration in milliseconds
     */
    public void setHoldMs(int holdMs) {
    	this.holdMs = holdMs;
    }
    
    /**
     * Sets the duration (in milliseconds) of the fade effect.
     *
     * @param fadeDurationMs duration in milliseconds
     */
    public void setFadeDurationMs(int fadeDurationMs) {
    	this.fadeDurationMs = fadeDurationMs;
    }
}