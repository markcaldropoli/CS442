package courseplanner.state;

/**
 * Represents Group-1: Courses A-D
 * @author Mark Caldropoli
 */
public class StateOne implements CoursePlannerStateI {
    StateMachineContext context;

    /**
     * Constructor initializes context field.
     */
    public StateOne(StateMachineContext contextIn) {
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

        int groupNum = context.getStudent().hasMostCourses(1);
        if(groupNum == 2) context.setState(context.getStateTwo());
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
