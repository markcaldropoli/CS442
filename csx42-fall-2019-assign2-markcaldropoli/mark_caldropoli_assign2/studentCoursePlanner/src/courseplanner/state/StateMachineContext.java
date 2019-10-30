package courseplanner.state;

import courseplanner.student.Student;
import courseplanner.student.StudentResult;
import java.util.ArrayList;

/**
 * Context class that processes the Student input.
 * @author Mark Caldropoli
 */
public class StateMachineContext implements CoursePlannerContextI {
    private CoursePlannerStateI stateOne;
    private CoursePlannerStateI stateTwo;
    private CoursePlannerStateI stateThree;
    private CoursePlannerStateI stateFour;
    private CoursePlannerStateI stateFive;
    private CoursePlannerStateI currentState;
    private Student student;
    private int numSemesters;
    private int numStateChanges;
    private boolean isGraduating;

    /**
     * Constructor initializes fields.
     */
    public StateMachineContext(Student studentIn) {
        stateOne = new StateOne(this);
        stateTwo = new StateTwo(this);
        stateThree = new StateThree(this);
        stateFour = new StateFour(this);
        stateFive = new StateFive(this);
        currentState = stateOne;
        student = studentIn;
        numSemesters = 0;
        numStateChanges = 0;
        isGraduating = false;
    }

    /**
     * Process the Student and determine the result for graduation.
     * @return StudentResult containing the values obtained after
     * processing the Student input.
     */
    public StudentResult processStudent() {
        ArrayList<Character> courseList = new ArrayList<>();

        while(!isGraduating) {
            student.resetCourses();
            numSemesters++;

            for(int i = 0; i < 3; i++) {
                if(!currentState.processNextCourse()) break;
                if(student.canGraduate()) {
                    isGraduating = true;
                    break;
                }
            }

            for(Character c : student.getCurrentCourses()) {
                courseList.add(c);
            }

            if(!isGraduating && student.getCurrentCourses().size() < 3) break;
        }

        return new StudentResult(student.getId(),courseList,numSemesters,numStateChanges,isGraduating);
    }

    /**
     * Sets the state of the currentState and increments the number
     * of state changes.
     */
    public void setState(CoursePlannerStateI stateIn) {
        currentState = stateIn;
        this.numStateChanges++;
    }

    /**
     * Get StateOne's interface.
     * @return stateOne interface.
     */
    public CoursePlannerStateI getStateOne() {
        return stateOne;
    }

    /**
     * Get StateTwo's interface.
     * @return stateTwo interface.
     */
    public CoursePlannerStateI getStateTwo() {
        return stateTwo;
    }

    /**
     * Get StateThree's interface.
     * @return stateThree interface.
     */
    public CoursePlannerStateI getStateThree() {
        return stateThree;
    }

    /**
     * Get StateFour's interface.
     * @return stateFour interface.
     */
    public CoursePlannerStateI getStateFour() {
        return stateFour;
    }

    /**
     * Gets StateFive's interface.
     * @return stateFive interface.
     */
    public CoursePlannerStateI getStateFive() {
        return stateFive;
    }

    /**
     * Gets the Student.
     * @return the student.
     */
    public Student getStudent() {
        return student;
    }
}
