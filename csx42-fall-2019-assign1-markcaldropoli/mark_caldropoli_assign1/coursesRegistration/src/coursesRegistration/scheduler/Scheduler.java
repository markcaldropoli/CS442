package coursesRegistration.scheduler;

import java.util.ArrayList;

/**
 * Handles the assignment of courses/students.
 * @author Mark Caldropoli
 */
public class Scheduler {
    private static ArrayList<Student> students;
    private static Course[] courses;
    private static ArrayList<ArrayList<Student>> match = new ArrayList<>();

    /**
     * Constructor sets the students (sorted) and courses fields.
     */
    public Scheduler(ArrayList<Student> studentsIn, Course[] coursesIn) {
        students = sortByPriority(studentsIn);
        courses = coursesIn;
        for(int i = 0; i < 9; i++) match.add(new ArrayList<Student>());
    }

    /**
     * Sorts the students by their class level.
     * @return ArrayList of Students in sorted order in terms of class level
     */
    public static ArrayList<Student> sortByPriority(ArrayList<Student> studentsIn) {
        ArrayList<Student> newStudents = new ArrayList<Student>();

        // Add third year students
        for(Student s : studentsIn) {
            if(s.getClassLevel() == ClassLevel.THIRD_YEAR) {
                newStudents.add(s);
            }
        }
        
        // Add second year students
        for(Student s : studentsIn) {
            if(s.getClassLevel() == ClassLevel.SECOND_YEAR) {
                newStudents.add(s);
            }
        }
        
        // Add first year students
        for(Student s : studentsIn) {
            if(s.getClassLevel() == ClassLevel.FIRST_YEAR) {
                newStudents.add(s);
            }
        }

        return newStudents;
    }

    /**
     * Assigns students to courses based on class level and preference.
     */
    public void assignCourses() {
        assignCoursesHelper(ClassLevel.THIRD_YEAR);
        assignCoursesHelper(ClassLevel.SECOND_YEAR);
        assignCoursesHelper(ClassLevel.FIRST_YEAR);
    }

    /**
     * Algorithm for assigning students to classes.
     */
    private static void assignCoursesHelper(ClassLevel level) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getClassLevel() == level) {
                char[] crs = students.get(i).getCoursePrefs();
                ArrayList<Integer> timesRegistered = new ArrayList<>();

                for(int j = 0; j < 9 && students.get(i).getEnrolled().size() < 3; j++) {
                    if(match.get(charToInt(crs[j])).size() < courses[charToInt(crs[j])].getCapacity() &&
                            !timesRegistered.contains(courses[j].getTiming())) {
                        match.get(charToInt(crs[j])).add(students.get(i));
                        timesRegistered.add(courses[j].getTiming());
                        students.get(i).addEnrolled(crs[j]);
                        students.get(i).addSatisfaction(9-j);
                    }
                }
            }
        }
    }

    /**
     * Converts letter to number representation for indexing purposes.
     * @return integer representation of Course name
     */
    private static int charToInt(char character) {
        return character - 'A';
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}
