package courseplanner.student;

import java.util.ArrayList;

/**
 * Stores results for student being processed
 * @author Mark Caldropoli
 */
public class StudentResult {
    private int id;
    private ArrayList<Character> courseSequence;
    private int numSemesters;
    private int numStateChanges;
    private boolean isStudentGraduating;

    /**
     * Constructor that sets the private fields
     */
    public StudentResult(int idIn, ArrayList<Character> courseSequenceIn, int numSemestersIn, int numStateChangesIn, boolean isStudentGraduatingIn) {
        id = idIn;
        courseSequence = courseSequenceIn;
        numSemesters = numSemestersIn;
        numStateChanges = numStateChangesIn;
        isStudentGraduating = isStudentGraduatingIn;
    }

    /**
     * Gets the id of the Student.
     * @return the student's id number.
     */
    public int getStudentId() {
        return id;
    }

    /**
     * Gets the course sequence of the Student.
     * @return the student's course sequence.
     */
    public ArrayList<Character> getCourseSequence() {
        return courseSequence;
    }

    /**
     * Gets the number of semesters taken by the Student.
     * @return the student's number of semesters taken.
     */
    public int getNumSemesters() {
        return numSemesters;
    }

    /**
     * Gets the number of state changes.
     * @return the number of state changes.
     */
    public int getNumStateChanges() {
        return numStateChanges;
    }

    /**
     * Gets the value representing if the student is graduating.
     * @return true if student is graduating, false otherwise.
     */
    public boolean isGraduating() {
        return isStudentGraduating;
    }

    /**
     * StudentResult toString representation, displays the field values.
     * @return String displaying the values of the fields of
     * the StudentResult
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Student: " + id);
        str.append("   Course Sequence: ");
        for(Character c : courseSequence) {
            str.append(c + " ");
        }
        str.append("  # Semesters: " + numSemesters);
        str.append("   # State Changes: " + numStateChanges);
        str.append("   Is Graduating? " + isStudentGraduating);
        return str.toString();
    }
}
