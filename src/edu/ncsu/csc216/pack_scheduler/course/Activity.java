package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Stores information about activities
 * @author Nathan Cornelison
 *
 */
public abstract class Activity implements Conflict, Comparable<Course> {

	/** Activity's title */
	private String title;
	/** Day activity meets */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * Creates an Activity object
	 * @param title Title of activity
	 * @param meetingDays Days of the activity
	 * @param startTime Time activity starts
	 * @param endTime Time activity ends
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Returns activity's title
	 * 
	 * @return Title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets activity's title
	 * @param title Activity's title
	 * @throws IllegalArgumentException if title is null or a blank string
	 */
	public void setTitle(String title) {
		if (title == null || title.length() <= 0) {
			throw new IllegalArgumentException("Invalid title");
		}
		this.title = title;
	}

	/**
	 * Returns the days the activity meets
	 * 
	 * @return meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the days the activity meets
	 * @param meetingDays The days the activity meets
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns time activity starts
	 * 
	 * @return startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns time activity ends
	 * 
	 * @return endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets activity's meeting time
	 * @param startTime time the activity starts
	 * @param endTime time the activity ends
	 * @throws IllegalArgumentException if time is not valid military time, endTime is before startTime, a time is listed
	 * when meetingDays is "A"
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (!meetingDays.equals("A")) {
			if (startTime < 0 || startTime > 2359 || endTime < 0 || endTime > 2359) {
				throw new IllegalArgumentException("Invalid meeting times");
			}
			if (endTime < startTime) {
				throw new IllegalArgumentException("Invalid meeting times");
			}
			int startTemp = startTime;
			int endTemp = endTime;
			while (startTemp >= 100) {
				startTemp -= 100;
			}
			if (startTemp > 59) {
				throw new IllegalArgumentException("Invalid meeting times");
			}
			while (endTemp >= 100) {
				endTemp -= 100;
			}
			if (endTemp > 59) {
				throw new IllegalArgumentException("Invalid meeting times");
			}
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	/**
	 * Returns the meeting time in 12 hour format
	 * 
	 * @return String of the meeting days and time
	 */
	public String getMeetingString() {
		if (this.getMeetingDays().equals("A")) {
			return "Arranged";
		}
		String startMin = "";
		String endMin = "";
		String startHour = "";
		String endHour = "";
		String sTime = "";
		String eTime = "";
		int sT = 0;
		int eT = 0;
		String startM = "";
		String endM = "";
		if (this.startTime > 1259) {
			sT = this.startTime - 1200;
			startM = "PM";
			sTime = sT + "";
			if (sT > 959) {
				startHour = sTime.substring(0, 2);
				startMin = sTime.substring(2);
			} else {
				startHour = sTime.substring(0, 1);
				startMin = sTime.substring(1);
			}
		} else if (this.startTime > 1159 && this.startTime < 1300) {
			sT = this.startTime;
			startM = "PM";
			sTime = sT + "";
			startHour = sTime.substring(0, 2);
			startMin = sTime.substring(2);
		} else if (this.startTime < 1200) {
			sT = this.startTime;
			startM = "AM";
			sTime = sT + "";
			startHour = sTime.substring(0, 1);
			startMin = sTime.substring(1);
			if (sT > 959) {
				startHour = sTime.substring(0, 2);
				startMin = sTime.substring(2);
			} else {
				startHour = sTime.substring(0, 1);
				startMin = sTime.substring(1);
			}
		}
		if (this.endTime > 1259) {
			eT = this.endTime - 1200;
			endM = "PM";
			eTime = eT + "";
			if (eT > 959) {
				endHour = eTime.substring(0, 2);
				endMin = eTime.substring(2);
			} else {
				endHour = eTime.substring(0, 1);
				endMin = eTime.substring(1);
			}
		} else if (this.endTime > 1159 && this.endTime < 1300) {
			eT = this.endTime;
			endM = "PM";
			eTime = eT + "";
			endHour = eTime.substring(0, 2);
			endMin = eTime.substring(2);
		} else if (this.endTime < 1200) {
			eT = this.endTime;
			endM = "AM";
			eTime = eT + "";
			endHour = eTime.substring(0, 1);
			endMin = eTime.substring(1);
			if (eT > 959) {
				endHour = eTime.substring(0, 2);
				endMin = eTime.substring(2);
			} else {
				endHour = eTime.substring(0, 1);
				endMin = eTime.substring(1);
			}
		}
		//"MW 1:30PM-2:20PM"
		return this.getMeetingDays() + " " + startHour + ":" + startMin + startM + "-" + endHour + ":" + endMin + endM;
	}

	/** 
	 * Creates an array holding part of the activity's information
	 * @return An array with part of the activity details
	 */
	public abstract String[] getShortDisplayArray();
	/** 
	 * Creates an array holding the activity's information
	 * @return An array with the activity details
	 */
	public abstract String[] getLongDisplayArray();
	/**
	 * Checks to see if two activities are duplicates
	 * @param activity Activity being checked
	 * @return True if they are duplicates
	 */
	public abstract boolean isDuplicate(Activity activity);

	/** 
	 * Generates hashCode for activity
	 * @return Hash code for array
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/** 
	 * Checks two activities to see if they're equal
	 * @return True if the activities are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Checks to see if two Activities occur during the same time period
	 * @param possibleConflictingActivity Activity being checked
	 * @throws ConflictException If the activities occur at the same time  and day as one another
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if (!(this.getMeetingDays().equals("A") && possibleConflictingActivity.getMeetingDays().equals("A"))) {
			char[] thisChar = this.getMeetingDays().toCharArray();
			char[] thatChar = possibleConflictingActivity.getMeetingDays().toCharArray();
			for (int i = 0; i < thisChar.length; i++) {
				for (int j = 0; j < thatChar.length; j++) {
					if (thisChar[i] == thatChar[j]) {
						for (int k = this.getStartTime(); k <= this.getEndTime(); k++) {
							for (int l = possibleConflictingActivity.getStartTime(); l <= possibleConflictingActivity.getEndTime(); l++) {
								if (k == l) {
									throw new ConflictException();
								}
							}
						}
					}
				}
			}
		}
	}
}