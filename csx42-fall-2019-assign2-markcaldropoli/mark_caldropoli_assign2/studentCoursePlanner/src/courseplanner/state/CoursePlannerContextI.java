package courseplanner.state;

import courseplanner.student.Student;
import courseplanner.student.StudentResult;

/**
 * Context Interface
 * @author Mark Caldropoli
 */
public interface CoursePlannerContextI {
    StudentResult processStudent();
    void setState(CoursePlannerStateI stateIn);
    CoursePlannerStateI getStateOne();
    CoursePlannerStateI getStateTwo();
    CoursePlannerStateI getStateThree();
    CoursePlannerStateI getStateFour();
    CoursePlannerStateI getStateFive();
    Student getStudent();
}
