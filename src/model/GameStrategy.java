package model;

/**
 * Functional interface representing a generic game strategy.
 * <p>
 * Any class implementing this interface defines a specific action or decision in the game
 * by providing the logic inside the {@link #play()} method.
 * </p>
 */
@FunctionalInterface
public interface GameStrategy {
	
	/**
     * Executes the strategy.
     *
     * @return true if the action or decision was successfully executed, false otherwise
     */
	boolean play();
}
