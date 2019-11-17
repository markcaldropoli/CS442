package troubleshootsearch.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import troubleshootsearch.util.MyLogger;

/**
 * MyArrayList Element
 * @author Mark Caldropoli
 */
public class MyArrayList implements Element {
    private ArrayList<String> info;
    private Map<String,String> synonyms;
    private MyLogger logger;

    /**
     * Constructor to initialize info and synonyms fields.
     */
    public MyArrayList(MyLogger loggerIn) {
        logger = loggerIn;
        info = new ArrayList<String>();
        synonyms = new HashMap<String,String>();
        logger.writeMessage("Constructor Call", MyLogger.DebugLevel.MYARRAYLIST);
    }

    /**
     * Gets the technical info.
     * @return ArrayList of String containing the technical info.
     */
    public ArrayList<String> getInfo() {
        return info;
    }

    /**
     * Add a line of technical info to the arraylist.
     */
    public void addInfo(String str) {
        info.add(str);
        logger.writeMessage("Add Line: " + str, MyLogger.DebugLevel.MYARRAYLIST);
    }

    /**
     * Gets the synonyms.
     * @return Map of String to String containing the synonyms.
     */
    public Map<String, String> getSynonyms() {
        return synonyms;
    }

    /**
     * Add bi-directional synonyms to the map.
     */
    public void addSynonym(String str) {
        String[] split = str.split("=");
        synonyms.put(split[0],split[1]);
        logger.writeMessage("Add Synonym: Key = " + split[0] + ", Value = " + split[1], MyLogger.DebugLevel.MYARRAYLIST);
        synonyms.put(split[1],split[0]);
        logger.writeMessage("Add Synonym: Key = " + split[1] + ", Value = " + split[0], MyLogger.DebugLevel.MYARRAYLIST);
    }

    /**
     * Allows visitor to visit MyArrayList.
     */
    public void accept(Visitor visitor, String str) {
        logger.writeMessage("Accept Visitor: " + str, MyLogger.DebugLevel.MYARRAYLIST);
        visitor.visit(this, str);
    }

    /**
     * MyArrayList toString representation.
     * @return String displaying the values of MyArrayList fields.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Info:\n");
        for(String s : info) {
            str.append(s+"\n");
        }

        str.append("Synonyms:\n");
        for(String s : synonyms.keySet()) {
            str.append(s+"\n");
        }

        return str.toString();
    }
}
