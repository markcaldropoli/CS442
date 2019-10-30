package courseplanner.state;

/**
 * Represents Group-2: Courses E-H
 * @author Mark Caldropoli
 */
public class StateTwo implements CoursePlannerStateI {
    StateMachineContext context;

    /**
     * Constructor initializes context field.
     */
    public StateTwo(StateMachineContext contextIn) {
        context = contextIn;
    }

    /**
     * Process the next course.
     * @return true if course processed, false otherwise.
     */
    public boolean processNextCourse() {
        if(!context.getStudent().processCourse()) {
            return false;
        }

        int groupNum = context.getStudent().hasMostCourses(2);
        if(groupNum == 1) context.setState(context.getStateOne());
        else if(groupNum == 3) context.setState(context.getStateThree());
        else if(groupNum == 4) context.setState(context.getStateFour());
        else if(groupNum == 5) context.setState(context.getStateFive());

        return true;
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}
