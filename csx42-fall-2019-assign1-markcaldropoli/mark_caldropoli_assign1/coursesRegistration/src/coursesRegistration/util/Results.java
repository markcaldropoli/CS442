package coursesRegistration.util;

import coursesRegistration.scheduler.Course;
import coursesRegistration.scheduler.Student;
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
    private ArrayList<Student> students;
    private String filename;

    /**
     * Constructor assigns pairings field to be used for output.
     */
    public Results(ArrayList<Student> studentsIn, String filenameIn) {
        students = studentsIn;
        filename = filenameIn;
    }

    /**
     * Writes results to a file using the provided filename.
     */
    public void writeToFile() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            int rating = 0;
            int count = 0;

            for(Student s : students) {
                writer.write(s.getId() + ":");
                ArrayList<Character> enrolled = s.getEnrolled();

                for(int i = 0; i < enrolled.size()-1; i++) {
                    writer.write(enrolled.get(i) + ",");
                }

                writer.write(enrolled.get(enrolled.size()-1) + "::");
                writer.write("SatisfactionRating=" + s.getSatisfaction());
                writer.newLine();
                rating += s.getSatisfaction();
                count++;
            }
            writer.write("AverageSatisfactionRating=" + (rating*1.0/count));
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
        int rating = 0;
        int count = 0;

        for(Student s : students) {
            System.out.print(s.getId() + ":");
            ArrayList<Character> enrolled = s.getEnrolled();

            for(int i = 0; i < enrolled.size()-1; i++) {
                System.out.print(enrolled.get(i) + ",");
            }

            System.out.print(enrolled.get(enrolled.size()-1) + "::");
            System.out.print("SatisfactionRating=" + s.getSatisfaction() + "\n");
            rating += s.getSatisfaction();
            count++;
        }
        System.out.println("AverageSatisfactionRating=" + (rating*1.0/count));
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}

