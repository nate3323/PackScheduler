package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Constructs an object to hold information about students
 * 
 * @author Nathan Cornelison
 *
 */
public class Student extends User implements Comparable<Student> {

	/** Max credits the student is allowed to take */
	private int maxCredits;
	/** Default max credit value */
	public static final int MAX_CREDITS = 18;
	/** Student's Schedule */
	private Schedule schedule;
	
	
	/**
	 * Constructs a student object using all fields
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param hashPW Student's password
	 * @param maxCredits Student's maxCredits
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}

	/**
	 * Constructs a Student object using the default max credit value
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param password Student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
		schedule = new Schedule();
	}
	

	/**
	 * Gets the max credits the student is allowed to take
	 * @return maxCredits of student
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the max credits a student is allowed to take
	 * @param maxCredits Max credits a student can take
	 * @throws IllegalArgumentException if the max credits is less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits > 18 || maxCredits < 3) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/** 
	 * Returns the Student object as a string
	 * @return Student as string
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}

	
	/**
	 * Overrides hashCode so equal objects hash
	 * to the same value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	
	/**
	 * Overrides the equals so objects are 
	 * equal if the contain the same values
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/**
	 * Compares Student objects to see which comes first alphabetically
	 * Compares lastName, firstName, and then student id
	 * @param s Student being compared to
	 * @return a negative integer, zero, or a positive integer as this 
	 * 			object is less than, equal to, or greater than the specified object
	 * @throws NullPointerException if object is null
	 */
	@Override
	public int compareTo(Student s) {
		if (s == null) {
			throw new NullPointerException();
		}
		int compare;
		compare = this.getLastName().compareToIgnoreCase(s.getLastName());
		if (compare > 0) {
			return 1;
		} else if (compare < 0) {
			return -1;
		}
		compare = this.getFirstName().compareToIgnoreCase(s.getFirstName());
		if (compare > 0) {
			return 1;
		} else if (compare < 0) {
			return -1;
		}
		compare = this.getId().compareToIgnoreCase(s.getId());
		if (compare > 0) {
			return 1;
		} else if (compare < 0) {
			return -1;
		}
		return compare;
	}
	
	/**
	 * Gets the schedule of the student
	 * @return schedule of student
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * Checks to see if a Course can be added to the schedule
	 * @param course Course being checked
	 * @return True if the course can be added to the schedule
	 */
	public boolean canAdd(Course course) {
		if (!schedule.canAdd(course)) {
			return false;
		}
		if ((course.getCredits() + schedule.getScheduleCredits()) > this.getMaxCredits()) {
			return false;
		}
		return true;
	}
}
