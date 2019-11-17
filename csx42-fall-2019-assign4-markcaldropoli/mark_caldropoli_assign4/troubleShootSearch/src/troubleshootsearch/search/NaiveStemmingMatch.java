package troubleshootsearch.search;

import java.util.ArrayList;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.util.Results;

/**
 * NaiveStemmingMatch Visitor
 * @author Mark Caldropoli
 */
public class NaiveStemmingMatch extends BaseVisitor {
    private Results results;
    private MyLogger logger;

    /**
     * Constructor to populate Results field.
     */
    public NaiveStemmingMatch(Results resultsIn, MyLogger loggerIn) {
        results = resultsIn;
        logger = loggerIn;
    }

    /**
     * Visit NaiveStemmingMatch to determine if any naive
     * stemming matches exist.
     */
    @Override
    public void visit(MyTree tree, String str) {
        checkForMatches(tree, str);
    }

    /**
     * Check for naive stemming matches in the tree.
     */
    public void checkForMatches(MyTree tree, String str) {
        String key;

        if(!str.contains(" ")) key = str;
        else key = str.substring(0, str.indexOf(" "));

        ArrayList<Integer> matches = tree.searchTree(key); 
        if(matches == null || matches.size() == 0) {
            results.addLine("No naive stemming match");
            logger.writeMessage("No naive stemming match", MyLogger.DebugLevel.MYTREE);
        } else {
            results.addLine("Word Count = " + matches.size());

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < matches.size()-1; i++) {
                sb.append(matches.get(i) + ",");
            }
            sb.append(matches.get(matches.size()-1));

            results.addLine("Line Numbers = " + sb.toString());
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
