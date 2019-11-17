package troubleshootsearch.search;

/**
 * Element interface
 * @author Mark Caldropoli
 */
public interface Element {
    void accept(Visitor visitor, String str);
}
