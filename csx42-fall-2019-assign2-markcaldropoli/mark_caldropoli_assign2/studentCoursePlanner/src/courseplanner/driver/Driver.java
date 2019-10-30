package courseplanner.driver;

import courseplanner.state.StateMachineContext;
import courseplanner.student.Student;
import courseplanner.student.StudentResult;
import courseplanner.util.FileProcessor;
import courseplanner.util.Results;

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
        if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
            System.err.println("Error: Incorrect number of arguments. Program accepts 2 arguments.");
            System.exit(1);
        }

        // Process input using the provided filename
        FileProcessor fp = new FileProcessor();
        Student student = fp.processInput(args[0]);

        /*
         * Process student's preferences to determine graduation and
         * course sequence.
         */
        StateMachineContext context = new StateMachineContext(student);
        StudentResult studentRes = context.processStudent();

        // File Output
        Results res = new Results(studentRes,args[1]);
        res.writeToStdout();
        res.writeToFile();
    }
}
