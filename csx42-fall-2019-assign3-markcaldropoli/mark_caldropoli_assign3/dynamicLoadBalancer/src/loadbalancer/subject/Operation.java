package loadbalancer.subject;

/**
 * Operation Enum
 * @author Mark Caldropoli
 */
public enum Operation {
    CLUSTER_OP__SCALE_UP,
    CLUSTER_OP__SCALE_DOWN,
    SERVICE_OP__ADD_SERVICE,
    SERVICE_OP__REMOVE_SERVICE,
    SERVICE_OP__ADD_INSTANCE,
    SERVICE_OP__REMOVE_INSTANCE;
}
