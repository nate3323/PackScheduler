package edu.ncsu.csc216.pack_scheduler.course;
/**
 * A checked exception used when two activities occur at the same time
 * @author Nathan Cornelison
 */
public class ConflictException extends Exception {
	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructs the ConflictException and prints the parameter
	 * @param message Message to be printed when exception is thrown
	 */
	public ConflictException(String message) {
		super(message);
	}
	/**
	 * Constructs the Conflict Exception and prints the default message 'Schedule Conflict'
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
