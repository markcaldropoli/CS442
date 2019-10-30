package coursesRegistration.util;

import coursesRegistration.scheduler.ClassLevel;
import coursesRegistration.scheduler.Course;
import coursesRegistration.scheduler.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;

/**
 * Process input files for info on courses and students
 * @author Mark Caldropoli
 */
public class FileProcessor {

    /**
     * Parse student course preference file.
     * @return list of students.
     */
    public ArrayList<Student> processStudentCoursePrefs(String filename) {
        ArrayList<Student> students = new ArrayList<Student>();
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file)); 
            String line;
            while((line = reader.readLine()) != null) {
                int id = Integer.parseInt(line.substring(0,line.indexOf(' ')));
                ClassLevel level = ClassLevel.DEFAULT;

                if(line.substring(line.indexOf("::")+2,line.lastIndexOf('_')).equals("FIRST")) {
                    level = ClassLevel.FIRST_YEAR;
                } else if(line.substring(line.indexOf("::")+2,line.lastIndexOf('_')).equals("SECOND")) {
                    level = ClassLevel.SECOND_YEAR;
                } else if(line.substring(line.indexOf("::")+2,line.lastIndexOf('_')).equals("THIRD")) {
                    level = ClassLevel.THIRD_YEAR;
                }
                
                char[] classPrefs = new char[9];
                int index = 0;
                
                for(int i = 4; i < line.length() && line.charAt(i) != ':'; i += 2) {
                    classPrefs[index] = line.charAt(i);
                    index++;
                }

                students.add(new Student(id,level,classPrefs));
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
        return students;
    }

    /**
     * Parse course info file.
     * @return list of courses.
     */
    public Course[] processCourseInfo(String filename) {
        Course[] courses = new Course[9];
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            int index = 0;
            while((line = reader.readLine()) != null) {
                char name = line.charAt(0);
                int capacity = Integer.parseInt(line.substring(line.indexOf(':')+1,line.indexOf(';')));
                int timing = Integer.parseInt(line.substring(line.lastIndexOf(':')+1));

                courses[index] = new Course(capacity,timing,name);
                index++;
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found. Please check the name of the file.");
            System.exit(1);
        } catch(IOException e) {
            System.out.print("IO Exception.");
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                System.out.println("BufferedReader not found.");
                System.exit(1);
            }
        }
        return courses;
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}

