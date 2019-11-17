package troubleshootsearch.driver;

import troubleshootsearch.search.MyArrayList;
import troubleshootsearch.search.MyTree;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.util.Results;

/**
 * Driver class
 * @author Mark Caldropoli
 */
public class Driver {
    public static void main(String[] args) {
        // Create logger
        MyLogger logger = new MyLogger();
        /*
         * [0:ERROR] - Errors only (prints nothing unless error detected)
         * [1:SEARCH_RESULTS] - Search results (prints output to console)
         * [2:FILE_PROCESSOR] - File processor (prints all input lines processed)
         * [3:MYARRAYLIST] - MyArrayList element (prints all operations performed on MyArrayList)
         * [4:MYTREE] - MyTree element (prints all operations performed on MyTree)
         */
        logger.setDebugValue(1);

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used.
         */
        if (args.length != 4 || args[0].equals("${arg0}") || args[1].equals("${arg1}") ||
                args[2].equals("${arg2}") || args[3].equals("${arg3}")) {
            logger.writeMessage("Error: Incorrect number of arguments. Program accepts 4 arguments.", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        }

        // Create results
        Results results = new Results(args[3], logger);

        // Create elements
        MyArrayList list = new MyArrayList(logger);
        MyTree tree = new MyTree(logger);

        // Create file processor and process input files
        FileProcessor fp = new FileProcessor(results, logger);
        fp.processTechnicalInfo(args[0], list, tree);   // Process technical info
        fp.processSynonyms(args[1], list);              // Process synonyms
        fp.processUserInput(args[2], list, tree);       // Process user input

        // Output results
        results.writeToStdout();
        results.writeToFile();
    }
}
