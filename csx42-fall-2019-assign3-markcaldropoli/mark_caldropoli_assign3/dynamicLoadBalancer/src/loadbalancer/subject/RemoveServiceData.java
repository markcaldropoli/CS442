package loadbalancer.subject;

/**
 * Hold RemoveService Data
 * @author Mark Caldropoli
 */
public class RemoveServiceData implements Data {
    private String serviceName;

    /**
     * Constructor to populate data fields.
     */
    public RemoveServiceData(String serviceIn) {
        serviceName = serviceIn;
    }

    /**
     * Get the service name.
     * @return String representing the service name.
     */
    //@Override
    public String getServiceName() {
        return serviceName;
    }

    /**
     * RemoveServiceData toString representation.
     * @return String displaying the values of the RemoveServiceData
     * field.
     */
    public String toString() {
        return "Service: " + serviceName;
    }
}
