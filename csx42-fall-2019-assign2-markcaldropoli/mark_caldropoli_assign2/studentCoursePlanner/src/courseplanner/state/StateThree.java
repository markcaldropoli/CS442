package courseplanner.state;

/**
 * Represents Group-3: Courses I-L
 * @author Mark Caldropoli
 */
public class StateThree implements CoursePlannerStateI {
    StateMachineContext context;

    /**
     * Constructor initializes context field.
     */
    public StateThree(StateMachineContext contextIn) {
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

        int groupNum = context.getStudent().hasMostCourses(3);
        if(groupNum == 1) context.setState(context.getStateOne());
        else if(groupNum == 2) context.setState(context.getStateTwo());
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
