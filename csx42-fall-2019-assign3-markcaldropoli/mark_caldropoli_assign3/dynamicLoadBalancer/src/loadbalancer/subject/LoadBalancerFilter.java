package loadbalancer.subject;

/**
 * LoadBalancer Filter
 * @author Mark Caldropoli
 */
public class LoadBalancerFilter implements Filter {
    @Override
    public boolean check(Operation opIn) {
        if(opIn.equals(Operation.SERVICE_OP__ADD_SERVICE) ||
           opIn.equals(Operation.SERVICE_OP__REMOVE_SERVICE)) {
            return true;
        }
        return false;
    }
}
