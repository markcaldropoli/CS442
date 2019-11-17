package troubleshootsearch.search;

import troubleshootsearch.util.MyLogger;
import troubleshootsearch.util.Results;

/**
 * ExactMatch Visitor
 * @author Mark Caldropoli
 */
public class ExactMatch extends BaseVisitor {
    private Results results;
    private MyLogger logger;

    /**
     * Constructor to populate results field.
     */
    public ExactMatch(Results resultsIn, MyLogger loggerIn) {
        results = resultsIn;
        logger = loggerIn;
    }

    /**
     * Visit ExactMatch to determine if any exact matches exist.
     */
    @Override
    public void visit(MyArrayList list, String str) {
        int size = results.getOutput().size();
        checkForMatches(list, str);
        if(size == results.getOutput().size()) {
            results.addLine("No exact match");
            logger.writeMessage("No exact match", MyLogger.DebugLevel.MYARRAYLIST);
        }
    }

    /**
     * Check for exact matches between given string and technical info.
     */
    public void checkForMatches(MyArrayList list, String str) {
        int count = 1;
        for(String info : list.getInfo()) {
            if(info.toLowerCase().contains(str.toLowerCase())) {
                results.addLine(count + ". " + info);
                logger.writeMessage(info, MyLogger.DebugLevel.MYARRAYLIST);
                count++;
            }
        }
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}
