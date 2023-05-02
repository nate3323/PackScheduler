package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Creates the roll for a Course
 * @author Nathan Cornelison
 */
public class CourseRoll {

	private LinkedAbstractList<Student> roll;
	private int enrollmentCap;
	private static final int MIN_ENROLLMENT = 10;
	private static final int MAX_ENROLLMENT = 250;
	private static final int WAITLIST_SIZE = 10;
	private LinkedQueue<Student> waitlist;
	private Course course;
	
	/**
	 * Creates an empty LinkedAbstractList of given capacity
	 * @param cap Capacity of the class
	 * @param c the course that students are being added to
	 */
	public CourseRoll(Course c, int cap) {
		setEnrollmentCap(cap);
		if(c == null) {
			throw new IllegalArgumentException();
		}
		this.course = c;
		this.waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
	}
	
	/**
	 * Gets the enrollmentCap of the Course
	 * @return Amount of students able to take the class
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Sets the maximum amount of students allowed to take the class
	 * @param cap Capacity being set
	 */
	public void setEnrollmentCap(int cap) {
		if (cap < MIN_ENROLLMENT || cap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if (roll != null && cap < roll.size()) {
			throw new IllegalArgumentException();
		}
		enrollmentCap = cap;
		if(roll != null) {
		    roll.setCapacity(cap);
		}
	}
	
	/**
	 * Enrolls a student in the course
	 * @param student Student being enrolled
	 */
	public void enroll(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		if (canEnroll(student)) {
			//try {
				roll.add(student);
				if(student.canAdd(course)) {
				    student.getSchedule().addCourseToSchedule(this.course);
				}
			//} catch (Exception e) {
			//	throw new IllegalArgumentException("Can not add student to roll");
			//}
		} else if (roll.size() == enrollmentCap) {
			for (int i = 0; i < roll.size(); i++) {
				if (student.equals(roll.get(i))) {
					throw new IllegalArgumentException();
				}
			}
			if(waitlist.size() == WAITLIST_SIZE) {
				throw new IllegalArgumentException();
			}
			waitlist.enqueue(student);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Removes a student from a course
	 * @param student Student being removed
	 */
	public void drop(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		
		try {
			for (int i = 0; i < roll.size(); i++) {
				if (student.equals(roll.get(i))) {
					roll.remove(i);
					if (waitlist.size() != 0) {
						this.enroll(waitlist.dequeue());
					}
				}
			}
			if (getNumberOnWaitlist() != 0) {
				for(int j = 0; j < waitlist.size(); j++) {
					Student s = waitlist.dequeue();
					if(!student.equals(s)) {
						waitlist.enqueue(s);
					}
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Gets the amount of open seats left
	 * @return Amount of open seats left
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Checks to see if a Student can be enroll for the Course
	 * @param student Student trying to enroll
	 * @return True if student can enroll
	 */
	public boolean canEnroll(Student student) {
		if (roll.size() == enrollmentCap){
			return false;
		}
		for (int i = 0; i < roll.size(); i++) {
			if (student.equals(roll.get(i))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Gets the number of students that have been added to the wait list
	 * @return The number of students that have been added to the wait list
	 */
	public int getNumberOnWaitlist() {
		 
		return waitlist.size();
	}
}
