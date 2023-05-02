package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Creates an interface to check if activities occur at the same time
 * @author Nathan Cornelison
 */
public interface Conflict {
	/**
	 * Checks to see if two Activities occur during the same time period
	 * @param possibleConflictingActivity Activity being checked
	 * @throws ConflictException If the activities occur at the same time as one another
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
