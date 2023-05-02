package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Handles course registration
 * @author Nathan Cornelison
 */
public class RegistrationManager {
	
	/** Instance of RegistrationManager being used */
	private static RegistrationManager instance;
	/** CourseCatalog being used by RegistrationManager */
	private CourseCatalog courseCatalog;
	private StudentDirectory studentDirectory;
	private FacultyDirectory facultyDirectory;
	private User registrar;
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	private static final String PW = "Regi5tr@r";
	private static String hashPW;

	// Static code block for hashing the registrar user's password
	{
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(PW.getBytes());
			hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	private RegistrationManager() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
		registrar = new Registrar();
		currentUser = null;
	}

	/**
	 * Gets the current instance of RegistrationManager
	 * @return Current instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/** 
	 * Gets the CourseCatalog being used by RegistrationManager
	 * @return CourseCatalog being used
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/** 
	 * Gets the StudentDirectory being used by RegistrationManager
	 * @return StudentDirectory being used
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Gets the Faculty Directory
	 * @return the FacultyDirectory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
		
	}
	
	/**
	 * Attempts to log a user into the RegistrationManager
	 * @param id Id of the user attempting to log in
	 * @param password User's password
	 * @return True if the user is successfully logged in
	 * @throws IllegalArgumentException if the hash algorithm is not found
	 */
	public boolean login(String id, String password) {
		if (currentUser != null) {
			return false;
		}
		Student s = studentDirectory.getStudentById(id);
		Faculty f = facultyDirectory.getFacultyById(id);
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if (s != null && s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			}
			if (f != null && f.getPassword().equals(localHashPW)) {
				currentUser = f;
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException();
		}

		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		if (s == null && f == null) {
			throw new IllegalArgumentException("User doesn't exist.");
		}
		return false;
	}

	/**
	 * Logs the User out of the current session
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Gets the current User
	 * @return Current User
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears the data of the current instance of RegistrationManager
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    if (c == null) {
	    	return false;
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}
	
	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    if (c == null) {
	    	return false;
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}
	
	/**
	 * Attempts to assign a faculty to a course
	 * @param course Course faculty is being added to
	 * @param faculty Faculty being added to the course
	 * @return True if the faculty is successfully added
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if (currentUser == null || course == null || faculty == null) {
			return false;
		}
		if (currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		faculty.getSchedule().addCourseToSchedule(course);
		return true;
	}
	
	/**
	 * Attempts to remove a course from a faculty's schedule
	 * @param course Course faculty is being added to
	 * @param faculty Faculty being added to the course
	 * @return True if the course is successfully removed
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if (currentUser == null) {
			return false;
		}
		if (currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		faculty.getSchedule().removeCourseFromSchedule(course);
		return true;
	}
	
	/**
	 * Resets a faculty's schedule
	 * @param faculty Faculty whose schedule is being reset
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if (currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		if (currentUser != null) {
			faculty.getSchedule().resetSchedule();
		}
	}
	
	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}

	/**
	 * Contains the information of RegistrationManager's current user
	 * @author Graham Swain
	 * @author Daniel Moody
	 * @author Grant Joiner
	 */
	private static class Registrar extends User {

		private static final String FIRST_NAME = "Wolf";
		private static final String LAST_NAME = "Scheduler";
		private static final String ID = "registrar";
		private static final String EMAIL = "registrar@ncsu.edu";

		/**
		 * Create a registrar user with the user id of registrar and password of
		 * Regi5tr@r. Note that hard coding passwords in a project is HORRIBLY
		 * INSECURE, but it simplifies testing here. This should NEVER be done
		 * in practice!
		 */
		public Registrar() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}
}