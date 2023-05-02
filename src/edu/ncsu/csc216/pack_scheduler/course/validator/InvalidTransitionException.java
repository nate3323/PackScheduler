package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * An exception used when there is an invalid course name
 * @author Nathan Cornelison
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	/**
	 * Error message for and invalid FSM transistion 
	 * @param message message to be displayed
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	/** Error message for and invalid FSM transistion */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
