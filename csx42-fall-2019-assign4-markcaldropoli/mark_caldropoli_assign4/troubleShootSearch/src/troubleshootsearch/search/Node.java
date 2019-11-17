package troubleshootsearch.search;

import java.util.ArrayList;

/**
 * Tree Node
 * @author Mark Caldropoli
 */
public class Node {
    private String word;
    private Node left;
    private Node right;
    private ArrayList<Integer> lineNumbersFoundIn;

    /**
     * Constructor to populate Node fields.
     */
    public Node(String wordIn, int line) {
        word = wordIn;
        left = null;
        right = null;
        lineNumbersFoundIn = new ArrayList<Integer>();
        lineNumbersFoundIn.add(line);
    }

    /**
     * Get the node's word.
     * @return String containing the word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Get the left subtree.
     * @return Node representing the left subtree.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Set the left subtree.
     */
    public void setLeft(Node node) {
        left = node;
    }

    /**
     * Get the right subtree.
     * @return Node representing the right subtree.
     */
    public Node getRight() {
        return right;
    }

    /**
     * Set the right subtree.
     */
    public void setRight(Node node) {
        right = node;
    }

    /**
     * Get the line numbers where the word exists.
     * @return ArrayList of Integer containing the line numbers of the word.
     */
    public ArrayList<Integer> getLineNums() {
        return lineNumbersFoundIn;
    }

    /**
     * Add a line number to the list.
     */
    public void addLineNum(int n) {
        lineNumbersFoundIn.add(n);
    }

    /**
     * Node toString representation.
     * @return String displaying the values of Node fields.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word: " + word + ", ");
        sb.append("Line Numbers: ");
        for(int i : lineNumbersFoundIn) sb.append(i + " ");
        return sb.toString();
    }
}
