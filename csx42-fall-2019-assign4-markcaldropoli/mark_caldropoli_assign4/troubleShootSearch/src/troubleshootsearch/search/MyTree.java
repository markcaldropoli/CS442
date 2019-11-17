package troubleshootsearch.search;

import java.util.ArrayList;
import troubleshootsearch.util.MyLogger;

/**
 * MyTree Element
 * @author Mark Caldropoli
 */
public class MyTree implements Element {
    private Node root;
    private MyLogger logger;

    /**
     * Constructor to initialize root to null
     */
    public MyTree(MyLogger loggerIn) {
        logger = loggerIn;
        root = null;
        logger.writeMessage("Constructor Call", MyLogger.DebugLevel.MYTREE);
    }

    /**
     * Add a node containing the word into the tree.
     */
    public void addWord(String str, int line) {
        root = addWordHelper(root, str, line);
        logger.writeMessage("Add Word: " + str, MyLogger.DebugLevel.MYTREE);
    }

    /**
     * Insert node are correct position in tree.
     * @return Node containing the updated tree.
     */
    private Node addWordHelper(Node current, String str, int line) {
        if(current == null) return new Node(str, line);

        if(str.compareTo(current.getWord()) < 0) {
            current.setLeft(addWordHelper(current.getLeft(), str, line));
        } else if(str.compareTo(current.getWord()) > 0) {
            current.setRight(addWordHelper(current.getRight(), str, line));
        } else {
            if(!current.getLineNums().contains(line)) {
                current.addLineNum(line);
            }
        }

        return current;
    }

    /**
     * Retrieve naive stemming matches from the tree.
     * @return ArrayList of Integer containing line nums of matches.
     */
    public ArrayList<Integer> searchTree(String str) {
        ArrayList<Integer> matches = new ArrayList<>();
        logger.writeMessage("Search Tree", MyLogger.DebugLevel.MYTREE);
        searchTreeHelper(str, matches, root);
        return matches;
    }

    /**
     * Recursive helper to retrieve naive stemming matches.
     */
    private void searchTreeHelper(String str, ArrayList<Integer> matches, Node node) {
        if(node == null) return;

        searchTreeHelper(str, matches, node.getLeft());

        if(node.getWord().toLowerCase().contains(str.toLowerCase()) &&
           !node.getWord().toLowerCase().equals(str.toLowerCase())) {
            for(int i : node.getLineNums()) {
                if(!matches.contains(i)) {
                    matches.add(i);
                    logger.writeMessage("Match Found: \n\t\tNode = " + node.getWord() + "\n\t\tStr = " + str + "\n\t\tLine = " + i, MyLogger.DebugLevel.MYTREE);
                }
            }
        }

        searchTreeHelper(str, matches, node.getRight());
    }

    /**
     * Allows visitor to visit MyTree.
     */
    public void accept(Visitor visitor, String str) {
        logger.writeMessage("Accept Visitor: " + str, MyLogger.DebugLevel.MYTREE);
        visitor.visit(this, str);
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}
