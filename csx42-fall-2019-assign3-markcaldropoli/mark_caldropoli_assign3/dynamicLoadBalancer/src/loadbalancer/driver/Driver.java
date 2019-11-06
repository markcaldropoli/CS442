package loadbalancer.driver;

import loadbalancer.observer.LoadBalancer;
import loadbalancer.subject.Cluster;
import loadbalancer.util.FileProcessor;
import loadbalancer.util.Results;

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

        // Create Initial Cluster and LoadBalancer
        Cluster cluster = Cluster.getInstance();
        LoadBalancer loadBalancer = new LoadBalancer();
        Results results = new Results(args[1]);

        // Process input using the provided filename
        FileProcessor fp = new FileProcessor(cluster,loadBalancer,results);
        fp.processInput(args[0]);

        // Output Results
        results.writeToStdout();
        results.writeToFile();
    }
}
