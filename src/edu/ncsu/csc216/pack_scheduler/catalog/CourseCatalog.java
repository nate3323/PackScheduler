/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Creates a catalog that holds the available courses.
 * @author Nathan Cornelison
 */
public class CourseCatalog {

	/** SortedList of all available courses */
	private SortedList<Course> catalog;
	
	/**
	 * Constructs a CourseCatalog, with an empty SortedList
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}
	/**
	 * Resets the course catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	/**
	 * Loads course from file after being given a file name.
	 * @param fileName Name of  file that the courses are being loaded from
	 * @throws IllegalArgumentException if the file cannot be found
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	/**
	 * Adds a new course to the catalog
	 * @param name Name of the course being added
	 * @param title Title of the course being added
	 * @param section Section of the course being added
	 * @param credits Amount of credits the course is worth
	 * @param instructorId Id of the instructor of the course
	 * @param enrollmentCap How many seats are in the class
	 * @param meetingDays Days that the course meets
	 * @param startTime Start time of the course
	 * @param endTime End time of the course
	 * @return True if the course can be added to the catalog, false if it can't
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, 
			String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return false;
			}
		}
		Course test = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		catalog.add(test);
		return true;
	}
	/**
	 * Removes course from the catalog given the name and section of the course
	 * @param name Name of course being removed
	 * @param section Section of course being removed
	 * @return True if course can be removed, false if it can't
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	/**
	 * Gets course from catalog after being given name and section
	 * @param name Name of course being searched for
	 * @param section Section of course being searched for
	 * @return The course if it's in the catalog, null if it isn't
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}
	/**
	 * Represents the CourseCatalog in a 2D string array
	 * @return CourseCatalog in a 2D string array
	 */
	public String[][] getCourseCatalog() {
		if (catalog.size() == 0) {
			return new String[0][0];
		}
		String[][] courses = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			courses[i][0] = catalog.get(i).getName();
			courses[i][1] = catalog.get(i).getSection();
			courses[i][2] = catalog.get(i).getTitle();
			courses[i][3] = catalog.get(i).getMeetingString();
			courses[i][4] = catalog.get(i).getCourseRoll().getOpenSeats() + "";
		}
		return courses;
	}
	/** 
	 * Saves course to a file after the file name is given
	 * @param fileName Name of file being saved to
	 * @throws IllegalArgumentException If the file cannot be saved to.
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}
}
