/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Supports reading and writing faculty records to a file
 * @author Nathan Cornelison
 */
public class FacultyRecordIO {

	/**
	 * Reads files for student objects
	 * @param fileName Name of the file being scanned
	 * @return ArrayList of Student objects
	 * @throws FileNotFoundException if file is not found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty f = processFaculty(fileReader.nextLine());
				faculty.add(f);
			} catch (IllegalArgumentException e) {
				//skip line
			}
		}
		fileReader.close();
		return faculty;
	}

	/**
	 * Scans the line that is passed into it for a Student object
	 * @param nextLine Line that is being scanned
	 * @return Student object created from scanned line
	 */
	private static Faculty processFaculty(String faculty) {
		Scanner line = new Scanner(faculty);
		line.useDelimiter(",");
		String[] input = new String[4];
		for (int i = 0; i < input.length; i++) {
			if(!line.hasNext()) {
				line.close();
				throw new IllegalArgumentException();
			} else {
				input[i] = line.next();
			}
		}
		line.reset();
		if(line.hasNext()) {
		    String l = line.next().substring(1);
		    if (l.lastIndexOf(",") == -1) {
			    line.close();
			    throw new IllegalArgumentException();
		    }
			String password = l.substring(0, l.lastIndexOf(","));
			int maxCredit = Integer.parseInt(l.substring(l.lastIndexOf(",") + 1));
			line.close();
			Faculty f = new Faculty(input[0], input[1], input[2], input[3], password, maxCredit);
			return f;
		}
		line.close();
		return null;
	}

	/**
	 * Writes Faculty objects to a file
	 * @param fileName File being written to
	 * @param facultyDirectory an LinkedList of faculty being written to the file
	 * @throws IOException If the file can't be found or created
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		File file = new File(fileName);
		PrintStream fileWriter = new PrintStream(file);
		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		fileWriter.close();
		
	}

}
