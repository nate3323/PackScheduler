package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Checks if a course name is valid
 * @author Nathan Cornelison
 */
public class CourseNameValidator {
	
	/** True if the course name is valid */
	private boolean validEndState;
	/** The amount of letters in the course name */
	private int letterCount;
	/* The amount of digits in the course name */
	private int digitCount;
	/** Current state of the FSM */
	private State currentState;
	
	/**
	 * Checks to see if the Course name is valid
	 * @param name Course name being checks
	 * @return True if the course name is valid
	 * @throws InvalidTransitionException If the course name is not valid
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		validEndState = false;
		currentState = new InitialState();
		digitCount = 0;
		letterCount = 0;
		
		int charIndex = 0;
		char c;
		
		while(charIndex < name.length()) {
			c = name.charAt(charIndex);
			if(!Character.isLetter(c) && !Character.isDigit(c)) {
				currentState.onOther();
			} else if (Character.isLetter(c)) {
				currentState.onLetter();
			} else if (Character.isDigit(c)) {
				currentState.onDigit();
			}
			charIndex++;
		}
		if (digitCount < 3) {
			validEndState = false;
		} else {
			validEndState = true;
		}
		
		return validEndState;
	}
	
	/**
	 * Creates the States that handle each input
	 * @author Graham Swain
	 * @author Daniel Moody
	 * @author Grant Joiner
	 */
	private abstract class State {
		/**
		 * Performs the necessary checks when the character is a letter
		 * @throws InvalidTransitionException If it is passed an invalid character
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		/**
		 * Performs the necessary checks when the character is a digit
		 * @throws InvalidTransitionException If it is passed an invalid character
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		/** 
		 * Is used when the character is neither a letter or a digit
		 * @throws InvalidTransitionException The course name can only have characters and digits
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * Is used when the character is a letter
	 * @author Graham Swain
	 * @author Daniel Moody
	 * @author Grant Joiner
	 */
	private class LetterState extends State {
		
		private static final int MAX_PREFIX_LETTERS = 3;
		
		private LetterState() {
			//Empty LetterState Constructor
		}
		
		@Override
		public void onLetter() throws InvalidTransitionException {
			
			if(letterCount > MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			letterCount++;
			currentState = new LetterState();
			
		}
		@Override
		public void onDigit() {
			digitCount++;
			currentState = new NumberState();
			
		}
	}
	
	/**
	 * Is used for the last letter of the Course name
	 * @author Graham Swain
	 * @author Daniel Moody
	 * @author Grant Joiner
	 */
	private class SuffixState extends State {
		
		private SuffixState() {
			//Empty SufficState constructor
		}
		@Override
		public void onLetter() throws InvalidTransitionException {
			if(letterCount > 1) {
				throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
			}
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");

		}
		
	}
	
	
	/**
	 * Is used for the first letter of the Course name
	 * @author Graham Swain
	 * @author Daniel Moody
	 * @author Grant Joiner
	 */
	private class InitialState extends State {
		
		private InitialState() {
			//Empty InitialState Constructor
		}
		
		@Override
		public void onLetter() {
			letterCount++;
			currentState = new LetterState();
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
			
		}
		
	}
	
	/**
	 * Is used when the character is a digit
	 * @author Graham Swain
	 * @author Daniel Moody
	 * @author Grant Joiner
	 */
	private class NumberState extends State {
		
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		private NumberState() {
			//Empty NumberState constructor
		}
		
		@Override
		public void onLetter() throws InvalidTransitionException {
			if(digitCount == COURSE_NUMBER_LENGTH) {
				currentState = new SuffixState();
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount >= COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			digitCount++;
			currentState = new NumberState();
		}
	}
}
