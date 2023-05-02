package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Reads and writes Student objects to and from objects
 * 
 * @author Nathan Cornelison
 *
 */
public class StudentRecordIO {

	/**
	 * Reads files for student objects
	 * @param fileName Name of the file being scanned
	 * @return ArrayList of Student objects
	 * @throws FileNotFoundException if file is not found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				students.add(student);
			} catch (IllegalArgumentException e) {
				//skip line
			}
		}
		fileReader.close();
		return students;
	}

	/**
	 * Scans the line that is passed into it for a Student object
	 * @param nextLine Line that is being scanned
	 * @return Student object created from scanned line
	 */
	private static Student processStudent(String student) {
		Scanner line = new Scanner(student);
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
		String l = line.next().substring(1);
		if (l.lastIndexOf(",") == -1) {
			line.close();
			throw new IllegalArgumentException();
		}
		String password = l.substring(0, l.lastIndexOf(","));
		int maxCredit = Integer.parseInt(l.substring(l.lastIndexOf(",") + 1));
		line.close();
		Student s = new Student(input[0], input[1], input[2], input[3], password, maxCredit);
		return s;
	}

	/**
	 * Writes Student objects to a file
	 * @param fileName File being written to
	 * @param studentDirectory an ArrayList of students being written to the file
	 * @throws IOException If the file can't be found or created
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		File file = new File(fileName);
		PrintStream fileWriter = new PrintStream(file);
		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}
		fileWriter.close();
		
	}

}
