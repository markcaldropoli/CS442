package coursesRegistration.scheduler;

/**
 * Represents a Course that students can register for.
 * @author Mark Caldropoli
 */
public class Course {
    private int capacity;
    private int timing;
    private char name;

    /**
     * Constructor sets the private fields of the course.
     */
    public Course(int capacityIn, int timingIn, char nameIn) {
        capacity = capacityIn;
        timing = timingIn;
        name = nameIn;
    }

    /**
     * Gets the capacity of the Course.
     * @return the course's capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the meeting time of the Course.
     * @return the course's meeting time.
     */
    public int getTiming() {
        return timing;
    }

    /**
     * Gets the name of the Course.
     * @return the course's name.
     */
    public char getName() {
        return name;
    }

    /**
     * Course toString representation, displays the field values.
     * @return String displaying the values of the fields of the Course
     */
    public String toString() {
        return ("Course: " + name + "   Capacity: " + capacity + "   Timing: " + timing);
    }
}
