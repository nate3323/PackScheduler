package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 */
public class CourseRecordIO {
	
	/**
	 * Writes the given list of Activities to the file passed through
	 * @param fileName Name of file to be written to
	 * @param activities SortedList of Activities to be written to file
	 * @throws IOException If file can't be written to
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> activities) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
	
		for (int i = 0; i < activities.size(); i++) {
		    fileWriter.println(activities.get(i).toString());
		}
	
		fileWriter.close();
	    
	}

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    SortedList<Course> courses = new SortedList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return courses;
	}
	/**
	 * Parses through each line the scanner passes through
	 * @param nextLine
	 * @return Course object from the line
	 */
	private static Course readCourse(String nextLine) {
		Course course = null;
		Scanner lineScan = new Scanner(nextLine);
		lineScan.useDelimiter(",");
		int counter = 0;
		int startTime = 0;
		int endTime = 0;
		String name = lineScan.next();
		counter++;
		String title = lineScan.next();
		counter++;
		String section = lineScan.next();
		counter++;
		if(!lineScan.hasNextInt()) {
			lineScan.close();
			throw new IllegalArgumentException();
		}
		int credits = lineScan.nextInt();
		counter++;
		String instructorId = lineScan.next();
		counter++;
		if(!lineScan.hasNextInt()) {
			lineScan.close();
			throw new IllegalArgumentException();
		}
		int cap = lineScan.nextInt();
		counter++;
		String meetingDays = lineScan.next();
		counter++;
		if (meetingDays.equals("A")) {
			if (lineScan.hasNext()) {
				lineScan.close();
				throw new IllegalArgumentException();
			}
		} else {
			if(!lineScan.hasNextInt()) {
				lineScan.close();
				throw new IllegalArgumentException();
			}
			startTime = lineScan.nextInt();
			counter++;
			if(!lineScan.hasNextInt()) {
				lineScan.close();
				throw new IllegalArgumentException();
			}
			endTime = lineScan.nextInt();
			counter++;
		}
		lineScan.close();
		if (meetingDays.contains("A") && meetingDays.length() != 1) {
			throw new IllegalArgumentException();
		}
		if (counter == 7) {
			course = new Course(name, title, section, credits, instructorId, cap, meetingDays);
		} else if (counter == 9) {
			course = new Course(name, title, section, credits, instructorId, cap, meetingDays, startTime, endTime);
		}
		if (course == null) {
			throw new IllegalArgumentException();
		}
		if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {
			RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule().addCourseToSchedule(course);
		} else {
			course.setInstructorId(null);
		}
		return course;
	}

}