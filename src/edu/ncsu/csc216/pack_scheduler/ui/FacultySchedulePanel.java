/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Creates a FacultySchedulePanel 
 * @author Nathan Cornelison
 *
 */
public class FacultySchedulePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTable tableSchedule;
    private JTable tableRoll;
    private CourseTableModel scheduleTableModel;
    private StudentTableModel rollTableModel;
    private Border lowerEtched;
    private JScrollPane scrollSchedule;
    private JScrollPane scrollRoll;
    private JPanel pnlCourseDetails;
    private JLabel lblNameTitle;
    private JLabel lblSectionTitle;
    private JLabel lblTitleTitle;
    private JLabel lblInstructorTitle;
    private JLabel lblCreditsTitle;
    private JLabel lblMeetingTitle;
    private JLabel lblEnrollmentCapTitle;
    private JLabel lblOpenSeatsTitle;
    private JLabel lblWaitlistTitle;
    private JLabel lblName;
    private JLabel lblSection;
    private JLabel lblTitle;
    private JLabel lblInstructor;
    private JLabel lblCredits;
    private JLabel lblMeeting;
    private JLabel lblEnrollmentCap;
    private JLabel lblOpenSeats;
    private JLabel lblWaitlist;
    private FacultySchedule schedule;
    
    /**
     * Initializes FacultySchedulePanel
     */
    public FacultySchedulePanel() {
        //Set up Schedule table
    	initFacultySchedule();
    			
    	//Set up Roll table
    	initCourseRoll();
    	
    	//Set up Course Details Panel
    	initCourseDetails();
    	
    	GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = .2;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlCourseDetails, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(scrollSchedule, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(scrollRoll, c);
		
		updateTables();
    }
    
    /**
     * Initializes FacultySchedule
     */
    private void initFacultySchedule() {
    	if(RegistrationManager.getInstance().getCurrentUser() != null) {
    	schedule = ((Faculty) RegistrationManager.getInstance().getCurrentUser()).getSchedule();
    	} else {
    	scheduleTableModel = new CourseTableModel();
		tableSchedule = new JTable(scheduleTableModel) {
			private static final long serialVersionUID = 1L;
			
			/**
			 * Set custom tool tips for cells
			 * @param e MouseEvent that causes the tool tip
			 * @return tool tip text
			 */
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				
				return (String)scheduleTableModel.getValueAt(rowIndex, realColumnIndex);
			}
		};
		
		
        scrollSchedule = new JScrollPane(tableSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Schedule");
		scrollSchedule.setBorder(boarder);
		scrollSchedule.setToolTipText("Schedule");
		
		scheduleTableModel.updateData();
    	}
    	}
    	
    
    /**
     * Initializes course details
     */
    private void initCourseDetails() {
    	pnlCourseDetails = new JPanel();
    	pnlCourseDetails.setLayout(new GridLayout(7, 2));
        lblNameTitle = new JLabel("Name");
        lblSectionTitle = new JLabel("Section");
        lblTitleTitle = new JLabel("Title");
        lblInstructorTitle = new JLabel("Instructor");
        lblCreditsTitle = new JLabel("Credits");
        lblMeetingTitle = new JLabel("Meeting Times");
        lblEnrollmentCapTitle = new JLabel("Enrollment Cap");
        lblOpenSeatsTitle = new JLabel("Open Seats");
        lblWaitlistTitle = new JLabel("Waitlist");
        lblName = new JLabel("");
        lblSection = new JLabel("");
        lblTitle = new JLabel("");
        lblInstructor = new JLabel("");
        lblCredits = new JLabel("");
        lblMeeting = new JLabel("");
        lblEnrollmentCap = new JLabel("");
        lblOpenSeats = new JLabel("");
        lblWaitlist = new JLabel("");
        
        pnlCourseDetails.add(lblNameTitle);
        pnlCourseDetails.add(lblSectionTitle);
        pnlCourseDetails.add(lblTitleTitle);
        pnlCourseDetails.add(lblInstructorTitle);
        pnlCourseDetails.add(lblCreditsTitle);
        pnlCourseDetails.add(lblMeetingTitle);
        pnlCourseDetails.add(lblEnrollmentCapTitle);
        pnlCourseDetails.add(lblOpenSeatsTitle);
        pnlCourseDetails.add(lblWaitlistTitle);
        pnlCourseDetails.add(lblName);
        pnlCourseDetails.add(lblSection);
        pnlCourseDetails.add(lblTitle);
        pnlCourseDetails.add(lblInstructor);
        pnlCourseDetails.add(lblCredits);
        pnlCourseDetails.add(lblMeeting);
        pnlCourseDetails.add(lblEnrollmentCap);
        pnlCourseDetails.add(lblOpenSeats);
        pnlCourseDetails.add(lblWaitlist);
        
        Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlCourseDetails.setBorder(boarder);
		pnlCourseDetails.setToolTipText("Course Details");
    }
    
    /**
     * Intializes course roll
     */
    private void initCourseRoll() {
    	rollTableModel = new StudentTableModel();
		tableRoll = new JTable(rollTableModel) {
			private static final long serialVersionUID = 1L;
			
			/**
			 * Set custom tool tips for cells
			 * @param e MouseEvent that causes the tool tip
			 * @return tool tip text
			 */
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				
				return (String)rollTableModel.getValueAt(rowIndex, realColumnIndex);
			}
		};
		
        scrollRoll = new JScrollPane(tableRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Course Roll");
		scrollRoll.setBorder(boarder);
		scrollRoll.setToolTipText("Course Roll");
		
		
    }
    
    /**
     * Updates the table we presume
     */
    public void updateTables() {
    	rollTableModel.updateData();
    	scheduleTableModel.updateData();
    }
    
    /**
     * Updates the course details
     * @param c Course being updated
     */
    private void updateCourseDetails(Course c) {
    	lblName.setText(c.getName());
    	lblSection.setText(c.getSection());
    	lblTitle.setText(c.getTitle());
    	lblInstructor.setText(c.getInstructorId());
    	lblCredits.setText(c.getCredits() + "");
    	lblMeeting.setText(c.getMeetingDays());
    	lblEnrollmentCap.setText(c.getCourseRoll().getEnrollmentCap() + "");
    	lblOpenSeats.setText(c.getCourseRoll().getOpenSeats() + "");
    	lblWaitlist.setText(c.getCourseRoll().getNumberOnWaitlist() + "");
    }
    
    /**
	 * {@link CourseTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of {@link Course}s to the user.
	 * @author Sarah Heckman
	 */
	private class CourseTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Section", "Title", "Meeting Days", "Open Seats"};
		/** Data stored in the table */
		private Object [][] data;

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Course} information from the {@link WolfScheduler}.
		 */
		private void updateData() {
			data = schedule.getScheduledCourses();
		}
	}
	
	/**
	 * Creates a Table of Student items to be displayed
	 * @author Graham Swain
	 * @author Nathan Cornelison
	 * @author Daniel Moody
	 */
	private class StudentTableModel extends AbstractTableModel {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Student ID"};
		/** Data stored in the table */
		private Object [][] data;

		/**
		 * Gets the row count
		 */
		@Override
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}

		/**
		 * Gets the column count
		 */
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Gets the value at the place
		 * @param row Index of the row
		 * @param col Index of the column
		 */
		@Override
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		/**
		 * Updates the given model with {@link Course} information from the {@link WolfScheduler}.
		 */
		private void updateData() {
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
