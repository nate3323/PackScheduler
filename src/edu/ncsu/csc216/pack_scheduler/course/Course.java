package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Stores information about courses
 * @author Nathan Cornelison
 *
 */
public class Course extends Activity {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor's id */
	private String instructorId;
	/** Validates Course name */
	private CourseNameValidator validator;
	
	private CourseRoll roll;
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name Course name
	 * @param title Course title
	 * @param section Course section
	 * @param credits Course credits
	 * @param instructorId Instructor's id
	 * @param enrollmentCap How many seats are available
	 * @param meetingDays Days course meets
	 * @param startTime Start time of course
	 * @param endTime End time of course
	 */
	public Course(String name, String title, String section, int credits, String instructorId,
				int enrollmentCap, String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays
	 * @param name Course name
	 * @param title Course title
	 * @param section Course section
	 * @param credits Course credits
	 * @param instructorId Instructor's id
	 * @param enrollmentCap How many seats are available
	 * @param meetingDays Days course meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId,
				int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
		
	}

	/**
	 * Returns course's name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name.  If the name is null, has a length less than 4 or 
	 * greater than 6, an IllegalArgumentException is thrown.
	 * @param name Name being set to course
	 * @throws IllegalArgumentException If name is null or length is less than 4 or 
	 * greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid name");
		}
		validator = new CourseNameValidator();
		try {
			if (!validator.isValid(name)) {
				throw new IllegalArgumentException("Invalid name");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		if (name.length() < 4 || name.length() > 6) {
			throw new IllegalArgumentException("Invalid name");
		}
		this.name = name;
	}

	/**
	 * Returns course section
	 * 
	 * @return section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets course section
	 * @param section Section being set to course
	 * @throws IllegalArgumentException If section number is not three digits or 
	 * if it is null
	 */
	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException("Invalid section");
		}
		if (section.length() != 3) {
			throw new IllegalArgumentException("Invalid section");
		}
		this.section = section;
	}

	/**
	 * Returns the amount of credit hours the course is worth
	 * 
	 * @return Credit hours
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the credit hours for course
	 * @param credits Credit hours being set to course
	 * @throws IllegalArgumentException If credit hours is less than 1 or greater than 5
	 */
	public void setCredits(int credits) {
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException("Invalid credits");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Id of the course's instructor
	 * 
	 * @return Instructor's ID
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets course instructor's ID
	 * @param instructorId ID being set to course
	 * @throws IllegalArgumentException If instructorId is null or a blank string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId != null && instructorId.length() <= 0) {
				throw new IllegalArgumentException("Invalid instructor id");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Sets the days the course meets
	 * @param meetingDays Days being set to course
	 * @throws IllegalArgumentException if meetingDays is null or blank, or contains letters other than MTWHFA
	 * or if it contains A and isn't one character long
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.length() <= 0) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		char[] m = meetingDays.toCharArray();
		for (int i = 0; i < m.length; i++) {
			if (!(m[i] == 'M' || m[i] == 'T' || m[i] == 'W' || m[i] == 'H' || m[i] == 'F' || m[i] == 'A')) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
			if (m[i] == 'A' && m.length != 1) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Generates hash code for Course
	 * @return Coure's hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Checks to see if two objects are equal
	 * @return true is objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}


	/** 
	 * Creates an array holding the course's Name, Section, Title, and MeetingString 
	 * @return An array holding the course's Name, Section, Title, and MeetingString 
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[5];
		shortDisplay[0] = getName();
		shortDisplay[1] = getSection();
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		shortDisplay[4] = "" + roll.getOpenSeats();
		return shortDisplay;
	}
	/** 
	 * Creates an array holding the courses information
	 * @return An array holding the course's Name, Section, Title, Credits, InstructorId, and MeetingString
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = getName();
		longDisplay[1] = getSection();
		longDisplay[2] = getTitle();
		longDisplay[3] = Integer.toString(getCredits());
		longDisplay[4] = getInstructorId();
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";
		return longDisplay;
	}

	/**
	 * Checks to see if two Courses are duplicates
	 * @param activity Activity being checked
	 * @return True if they're duplicates
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity;
			if (course.getName().equals(this.getName())) {
				return true;
			}
		} 
		return false;
	}
	/**
	 * Compares Course objects to see which comes first alphabetically
	 * Compares name and then section
	 * @param c Course being compared to
	 * @return a negative integer, zero, or a positive integer as this 
	 * 			object is less than, equal to, or greater than the specified object
	 * @throws NullPointerException if object is null
	 */
	@Override
	public int compareTo(Course c) {
		if (c == null) {
			throw new NullPointerException();
		}
		int compare;
		compare = this.getName().compareToIgnoreCase(c.getName());
		if (compare > 0) {
			return 1;
		} else if (compare < 0) {
			return -1;
		}
		compare = this.getSection().compareToIgnoreCase(c.getSection());
		if (compare > 0) {
			return 1;
		} else if (compare < 0) {
			return -1;
		}
		return 0;
	}
	
	/**
	 * Gets the Course roll
	 * @return Roll of the Course
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
}
