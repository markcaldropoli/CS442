package courseplanner.state;

/**
 * Represents Group-4: Courses M-P
 * @author Mark Caldropoli
 */
public class StateFour implements CoursePlannerStateI {
    StateMachineContext context;

    /**
     * Constructor initializes context field.
     */
    public StateFour(StateMachineContext contextIn) {
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

        int groupNum = context.getStudent().hasMostCourses(4);
        if(groupNum == 1) context.setState(context.getStateOne());
        else if(groupNum == 2) context.setState(context.getStateTwo());
        else if(groupNum == 3) context.setState(context.getStateThree());
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
