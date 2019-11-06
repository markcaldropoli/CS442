package loadbalancer.subject;

import loadbalancer.observer.ServiceManager;

/**
 * Holds Instance Data
 * @author Mark Caldropoli
 */
public class InstanceData implements Data {
    private String serviceName;
    private String hostname;
    private ServiceManager manager;

    /**
     * Constructor to populate data fields.
     */
    public InstanceData(String serviceIn, String hostIn, ServiceManager managerIn) {
        serviceName = serviceIn;
        hostname = hostIn;
        manager = managerIn;
    }

    /**
     * Get the name of the service.
     * @return String representing the service name.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Get the host of the instance.
     * @return String representing the host of the instance
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Get the ServiceManager of the instance.
     * @return ServiceManager for the instance.
     */
    public ServiceManager getServiceManager() {
        return manager;
    }

    /**
     * InstanceData toString representation.
     * @return String displaying the values of the fields of
     * InstanceData.
     */
    public String toString() {
        return "Service: " + serviceName + ", Hostname: " + hostname;
    }
}
