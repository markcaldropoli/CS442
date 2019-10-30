package courseplanner.student;

import java.lang.StringBuilder;
import java.util.ArrayList;

/**
 * Represents a Student
 * @author Mark Caldropoli
 */
public class Student {
    private int id;
    private ArrayList<Character> coursePrefs;
    private ArrayList<Character> currentCourses;
    private boolean[] courseTaken = new boolean[26];

    /**
     * Constructor that sets the private fields id
     * and course preferences.
     */
    public Student(int idIn, ArrayList<Character> coursePrefsIn) {
        id = idIn;
        coursePrefs = new ArrayList<>();

        for(Character c : coursePrefsIn) {
            if(coursePrefs.contains(c)) {
                System.out.println("Duplicate course found.");
                System.exit(1);
            }
            coursePrefs.add(c);
        }
    }

    /**
     * Gets the id of the Student.
     * @return the student's id number.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the course preferences of the Student.
     * @return the student's course preferences.
     */
    public ArrayList<Character> getCoursePrefs() {
        return coursePrefs;
    }

    /**
     * Gets the current courses of the Student.
     * @return the student's current courses.
     */
    public ArrayList<Character> getCurrentCourses() {
        return currentCourses;
    }

    /**
     * Reset Student's current courses.
     */
    public void resetCourses() {
        currentCourses = new ArrayList<Character>();
    }

    /**
     * Determines whether a course can be processed.
     * @return true if course processed, false otherwise.
     */
    public boolean processCourse() {
        if(coursePrefs.isEmpty()) return false;

        for(int i = 0; i < coursePrefs.size(); i++) {
            Character course = coursePrefs.get(i);

            if(hasPreReqs(course)) {
                courseTaken[course - 'A'] = true;
                coursePrefs.remove(i);
                currentCourses.add(course);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if student has satisfied course prereqs.
     * @return true if course prereqs are satisfied, false otherwise.
     */
    private boolean hasPreReqs(Character course) {
        int courseNum = course - 'A';

        if(courseNum < 0 || courseNum > 25) {
            System.out.println("Invalid course: " + course);
            System.exit(1);
        } else if(courseNum == 0 || courseNum == 4 || courseNum == 8 || courseNum == 12 || courseNum >= 16) {
            return true;
        } else if(courseTaken[courseNum-1] && !currentCourses.contains((char)(course-1))) {
            return true;
        }
        return false;
    }

    /**
     * Gets the group number with the most courses.
     * @return group number with the most courses.
     */
    public int hasMostCourses(int group) {
        int max = getNumCourses(group);

        for(int i = 1; i <= 5; i++) {
            if(i == group) continue;
            if(getNumCourses(i) > max) return i;
        }
        return group;
    }

    /**
     * Gets the number of courses taken in a specific group.
     * @return number of courses taken in a group.
     */
    private int getNumCourses(int group) {
        int nCourses = 0;
        if(group == 1) {
            for(int i = 0; i < 4; i++) {
                if(courseTaken[i]) nCourses++;
            }
        } else if(group == 2) {
            for(int i = 4; i < 8; i++) {
                if(courseTaken[i]) nCourses++;
            }
        } else if(group == 3) {
            for(int i = 8; i < 12; i++) {
                if(courseTaken[i]) nCourses++;
            }
        } else if(group == 4) {
            for(int i = 12; i < 16; i++) {
                if(courseTaken[i]) nCourses++;
            }
        } else if(group == 5) {
            for(int i = 16; i < 25; i++) {
                if(courseTaken[i]) nCourses++;
            }
        } else {
            System.out.println("Incorrect group # in getNumCourses");
            System.exit(1);
        }
        return nCourses;
    }

    /**
     * Checks if a student is able to graduate.
     * @return true if student can graduate, false otherwise
     */
    public boolean canGraduate() {
        for(int i = 1; i <= 5; i++) {
            if(getNumCourses(i) < 2) return false;
        }
        return true;
    }

    /**
     * Student toString representation, displays the field values.
     * @return String displaying the values of the fields of
     * the Student
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Student: " + id + "   Course Prefs: ");
        for(Character c : coursePrefs) {
            str.append(c + " ");
        }
        return str.toString();
    }
}
