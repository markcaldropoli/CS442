package troubleshootsearch.search;

/**
 * Visitor interface
 * @author Mark Caldropoli
 */
public interface Visitor {
    public void visit(MyArrayList list, String str);
    public void visit(MyTree tree, String str);
}
