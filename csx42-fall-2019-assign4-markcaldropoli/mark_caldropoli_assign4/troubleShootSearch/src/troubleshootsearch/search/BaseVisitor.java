package troubleshootsearch.search;

/**
 * BaseVisitor Abstract Superclass
 * @author Mark Caldropoli
 */
public abstract class BaseVisitor implements Visitor {
    public void visit(MyArrayList list, String str) { }
    public void visit(MyTree tree, String str) { }
}
