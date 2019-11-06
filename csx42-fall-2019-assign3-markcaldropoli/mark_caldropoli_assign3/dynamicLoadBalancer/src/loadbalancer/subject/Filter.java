package loadbalancer.subject;

/**
 * Filter Interface
 * @author Mark Caldropoli
 */
public interface Filter {
    boolean check(Operation opIn);
}
