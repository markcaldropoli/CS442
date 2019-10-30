package coursesRegistration.scheduler;

import java.lang.StringBuilder;
import java.util.ArrayList;

/**
 * Represents a Student that can register for courses.
 * @author Mark Caldropoli
 */
public class Student {
    private int id;
    private ClassLevel level;
    private char[] coursePrefs;
    private int satisfaction;
    private ArrayList<Character> enrolled;

    /**
     * Constructor that sets the private fields id, level, and
     * course preferences.
     */
    public Student(int idIn, ClassLevel levelIn, char[] coursePrefsIn) {
        id = idIn;
        level = levelIn;
        coursePrefs = coursePrefsIn;
        satisfaction = 0;
        enrolled = new ArrayList<>();
    }

    /**
     * Gets the id of the Student.
     * @return the student's id number.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the class level of the Student.
     * @return the student's class level.
     */
    public ClassLevel getClassLevel() {
        return level;
    }

    /**
     * Gets the course preferences of the Student.
     * @return the student's course preferences.
     */
    public char[] getCoursePrefs() {
        return coursePrefs;
    }

    /**
     * Gets the satisfaction level of the Student.
     * @return the student's satisfaction level.
     */
    public int getSatisfaction() {
        return satisfaction;
    }

    /**
     * Adds integer value to satisfaction field.
     */
    public void addSatisfaction(int value) {
        satisfaction += value;
    }

    /**
     * Gets the courses the student is enrolled in.
     * @return ArrayList of Characters representing the student's
     * courses.
     */
    public ArrayList<Character> getEnrolled() {
        return enrolled;
    }

    /**
     * Adds a course to the list of enrolled courses.
     */
    public void addEnrolled(char c) {
        enrolled.add(c);
    }

    /**
     * Student toString representation, displays the field values.
     * @return String displaying the values of the fields of
     * the Student
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Student: " + id + "   Course Prefs: ");
        for(int i = 0; i < coursePrefs.length-1; i++) {
            str.append(coursePrefs[i] + ",");
        }
        str.append(coursePrefs[coursePrefs.length-1]);
        str.append("   Class Level: " + level);
        return str.toString();
    }
}
