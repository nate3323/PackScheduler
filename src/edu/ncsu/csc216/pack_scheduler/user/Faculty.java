/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Creates a faculty object that stores the faculty's different attributes
 * @author Nathan Corneison
 */
public class Faculty extends User {

	private int maxCourses;
	private FacultySchedule facultySchedule;

	/**
	 * Constructs a faculty object using all fields
	 * @param firstName Faculty's first name
	 * @param lastName Faculty's last name
	 * @param id Faculty's id
	 * @param email Faculty's email
	 * @param hashPW Faculty's password
	 * @param maxCourses Faculty's maxCourses
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCourses) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCourses(maxCourses);
		this.facultySchedule = new FacultySchedule(id);
	}
	

	/**
	 * Gets the max number of courses the faculty is allowed to have
	 * @return maxCourses of courses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the max courses a Course is allowed to have
	 * @param maxCourses Max Courses a faculty can teach
	 * @throws IllegalArgumentException if the max credits is less than 3 or greater than 18
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses > 3 || maxCourses < 1) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}

	/** 
	 * Returns the Faculty object as a string
	 * @return Faculty as string
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCourses;
	}

	
	/**
	 * Overrides hashCode so equal objects hash
	 * to the same value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Gets the schedule of the faculty member
	 * @return schedule
	 */
	public FacultySchedule getSchedule() {
		return facultySchedule;
	}

	/**
	 * Checks to see if the faculty has too many courses schedule
	 * @return True is the factuly's schedule is overloaded
	 */
	public boolean isOverloaded() {
		if(facultySchedule.getNumScheduledCourses() > this.maxCourses) {
			return true;
		}
		return false;
	}

	

}
