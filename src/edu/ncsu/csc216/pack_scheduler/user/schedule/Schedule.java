package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Creates a Student's schedule
 * @author Nathan Cornelison
 */
public class Schedule {
	
	private String title;
	
	private ArrayList<Course> schedule;
	
	/**
	 * Constructor for Schedule
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/**
	 * Adds a course to the schedule
	 * @param course course to add
	 * @return true if course was added, false if not
	 */
	public boolean addCourseToSchedule(Course course) {
		try {
			for (int i = 0; i < schedule.size(); i++) {
				if (course.isDuplicate(schedule.get(i))) {
					throw new IllegalArgumentException("You are already enrolled in " + course.getName());
				}
				course.checkConflict(schedule.get(i));
			}
			schedule.add(schedule.size(), course);
			return true;
		} catch (ConflictException e) {
			throw new IllegalArgumentException("You are already enrolled in " + course.getName());
		}
	}
	
	/**
	 * Removes a course from the schedule
	 * @param course course to be removed
	 * @return true if course was removed, false if not
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (course.equals(schedule.get(i))) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Resets the schedule completely
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
	}

	/**
	 * Shows the course scheduled
	 * @return 2D array of scheduled courses
	 */
	public String[][] getScheduledCourses() {
		if (schedule.size() == 0) {
			return new String[0][0];
		}
		String[][] courses = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			courses[i][0] = schedule.get(i).getName();
			courses[i][1] = schedule.get(i).getSection();
			courses[i][2] = schedule.get(i).getTitle();
			courses[i][3] = schedule.get(i).getMeetingString();
			courses[i][4] = schedule.get(i).getCourseRoll().getOpenSeats() + "";
		}
		return courses;
	}
	
	/**
	 * Sets the title of a schedule
	 * @param t title to be set to 
	 */
	public void setTitle(String t) {
		if (t == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		title = t;
	}
	
	/**
	 * Gets the schedule title
	 * @return title of schedule
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns a cumulative sum that returns the total credits in the Schedule
	 * @return Cumulative sum that returns the total credits in the Schedule
	 */
	public int getScheduleCredits() {
		int sum = 0;
		for (int i = 0; i < schedule.size(); i++) {
			sum += schedule.get(i).getCredits();
		}
		return sum;
	}
	
	/**
	 * Checks to see if a Course can be added to the schedule
	 * @param course Course being checked
	 * @return True if the course can be added to the schedule
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (course.isDuplicate(schedule.get(i))) {
				return false;
			}
		}
		try {
			for (int i = 0; i < schedule.size(); i++) {
				course.checkConflict(schedule.get(i));
			}
		} catch (ConflictException e) {
			return false;
		}
		return true;
	}
}
