package troubleshootsearch.search;

import java.util.HashMap;
import java.util.Map;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.util.Results;

/**
 * Semantic Visitor
 * @author Mark Caldropoli
 */
public class SemanticMatch extends BaseVisitor {
    private ExactMatch exact;
    private Results results;
    private MyLogger logger;

    /**
     * Constructor to populate ExactMatch and Results fields.
     */
    public SemanticMatch(Results resultsIn, Visitor visitorIn, MyLogger loggerIn) {
        results = resultsIn;
        exact = (ExactMatch)visitorIn;
        logger = loggerIn;
    }

    /**
     * Visit SemanticMatch to determine if any semantic matches exist.
     */
    @Override
    public void visit(MyArrayList list, String str) {
        checkForMatches(list, str);
    }

    /**
     * Check for semantic matches between given string with altered synonyms
     * and technical info.
     */
    public void checkForMatches(MyArrayList list, String str) {
        Map<String, String> synonyms = list.getSynonyms();
        String key;

        if(!str.contains(" ")) key = str;
        else key = str.substring(str.lastIndexOf(" ")+1);

        if(synonyms.get(key) != null) exact.checkForMatches(list, str.replace(key, synonyms.get(key)));
        else {
            results.addLine("No semantic match");
            logger.writeMessage("No semantic match", MyLogger.DebugLevel.MYARRAYLIST);
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
