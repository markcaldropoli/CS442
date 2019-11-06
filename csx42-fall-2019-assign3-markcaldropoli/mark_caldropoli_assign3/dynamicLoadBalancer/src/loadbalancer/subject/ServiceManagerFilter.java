package loadbalancer.subject;

/**
 * ServiceManager Filter
 * @author Mark Caldropoli
 */
public class ServiceManagerFilter implements Filter {
    @Override
    public boolean check(Operation opIn) {
        if(opIn.equals(Operation.CLUSTER_OP__SCALE_DOWN) ||
           opIn.equals(Operation.SERVICE_OP__ADD_INSTANCE) ||
           opIn.equals(Operation.SERVICE_OP__REMOVE_INSTANCE)) {
            return true;
        }
        return false;
    }
}
