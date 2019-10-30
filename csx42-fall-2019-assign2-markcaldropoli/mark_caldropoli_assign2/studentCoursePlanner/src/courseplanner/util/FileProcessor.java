package courseplanner.util;

import courseplanner.student.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;

/**
 * Process input file
 * @author Mark Caldropoli
 */
public class FileProcessor {

    /**
     * Parse student input file.
     * @return Student with id and course preferences
     */
    public Student processInput(String filename) {
        Student student = null;
        ArrayList<Character> coursePrefs = new ArrayList<>();
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            if((line = reader.readLine()) != null) {
                int studentId = Integer.parseInt(line.substring(0,line.indexOf(':')));

                if(studentId < 1000 || studentId > 9999) {
                    System.out.println("Student ID not in range [1000,9999]");
                    System.exit(1);
                }

                for(int i = line.indexOf(':')+1; i < line.length(); i++) {
                    if(line.charAt(i) == ' ') continue;
                    coursePrefs.add(line.charAt(i));
                }
                student = new Student(studentId,coursePrefs);
            } else {
                System.out.println("File is empty.");
                System.exit(1);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found. Please check the name of the file.");
            System.exit(1);
        } catch(IOException e) {
            System.out.println("IO Exception.");
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                System.out.println("BufferedReader not found.");
                System.exit(1);
            }
        }
        return student;
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}
