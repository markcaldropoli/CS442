package coursesRegistration.driver;

import coursesRegistration.scheduler.Course;
import coursesRegistration.scheduler.Scheduler;
import coursesRegistration.scheduler.Student;
import coursesRegistration.util.FileProcessor;
import coursesRegistration.util.Results;
import java.util.ArrayList;

/**
 * @author Mark Caldropoli
 */
public class Driver {
    public static void main(String[] args) {
        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used.
         */
        if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")) {
            System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
            System.exit(1);
        }

        // Process files using the provided filename args
        FileProcessor fp = new FileProcessor();
        ArrayList<Student> students = fp.processStudentCoursePrefs(args[0]);
        Course[] courses = fp.processCourseInfo(args[1]);

        // Create a scheduler to handle course/student assignments
        Scheduler schd = new Scheduler(students,courses);
        schd.assignCourses();

        // File Output & Satisfaction Calculation
        Results res = new Results(students,args[2]);
        res.writeToStdout();
        res.writeToFile();
    }
}

