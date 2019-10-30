package courseplanner.util;

import courseplanner.student.StudentResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;

/**
 * Handles output of results.
 * @author Mark Caldropoli
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    private StudentResult studentResult;
    private String filename;

    public Results(StudentResult studentResultIn, String filenameIn) {
        studentResult = studentResultIn;
        filename = filenameIn;
    }

    /**
     * Writes results to a file using the provided filename.
     */
    public void writeToFile() {
        if(studentResult == null) {
            System.out.println("Error occurred during processStudent.");
            System.exit(1);
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(studentResult.getStudentId() + ": ");

            for(Character c : studentResult.getCourseSequence()) {
                writer.write(c + " ");
            }

            if(studentResult.isGraduating()) {
                writer.write("-- " + studentResult.getNumSemesters() + " " + studentResult.getNumStateChanges());
            } else {
                writer.write("-- 0 " + studentResult.getNumStateChanges());
                writer.newLine();
                writer.write("Student did not graduate.");
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("IO Exception: Filename was not a proper name.");
            System.exit(1);
        } finally {
            try {
                writer.close();
            } catch(IOException e) {
                System.out.println("BufferedWriter not found.");
                System.exit(1);
            }
        }
    }

    /**
     * Writes results to standard output.
     */
    public void writeToStdout() {
        if(studentResult == null) {
            System.out.println("Error occurred during processStudent.");
            System.exit(1);
        }
        
        System.out.print(studentResult.getStudentId() + ": ");
        
        for(Character c : studentResult.getCourseSequence()) {
            System.out.print(c + " ");
        }
        
        if(studentResult.isGraduating()) {
            System.out.print("-- " + studentResult.getNumSemesters() + " " + studentResult.getNumStateChanges());
        } else {
            System.out.println("-- 0 " + studentResult.getNumStateChanges());
            System.out.println("Student did not graduate.");
        }
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}
