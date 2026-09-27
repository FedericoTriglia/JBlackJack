package view;

import java.awt.BorderLayout;

import model.Result;

/**
 * Abstract decorator for {@link ResultPanel} implementing the Decorator design pattern.
 * <p>
 * Allows adding additional behavior to a {@link ResultPanel} without modifying its code.
 * </p>
 */
public abstract class ResultPanelDecorator extends ResultPanel {
	
	/** The wrapped {@link ResultPanel} component. */
	protected ResultPanel component;
	
	/**
     * Constructs a ResultPanelDecorator wrapping the given {@code ResultPanel}.
     *
     * @param component the ResultPanel to decorate
     */
	public ResultPanelDecorator(ResultPanel component) {
		this.component = component;
        add(component, BorderLayout.CENTER);
	}
	
	/**
     * Updates the panel with the given argument.
     */
	@Override
	public void reload(Object arg) {
	    if (arg instanceof Result) {
	        Result r = (Result) arg;
	        showResult(r.toString() + "!");
	    }
	    else
	    	component.reload(arg);
	}
	
	/**
     * Sets the color of the result text.
     * Delegates to the wrapped component.
     *
     * @param c the color to set
     */
	 @Override
	 public void setColor(java.awt.Color c) {
		 component.setColor(c);
	}
	
	 /**
     * Shows the given result string.
     * Delegates to the wrapped component.
     *
     * @param s the result string to show
     */
	 @Override
	 public void showResult(String s) {
		 component.showResult(s);
	 }
  
	/**
	* Hides the displayed result.
	* Delegates to the wrapped component.
	*/
	@Override
	public void hideResult() {
		component.hideResult();
	}
}