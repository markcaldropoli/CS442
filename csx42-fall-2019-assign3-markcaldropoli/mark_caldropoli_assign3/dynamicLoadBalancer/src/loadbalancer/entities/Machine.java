package loadbalancer.entities;

import java.util.Map;
import java.util.HashMap;

/**
 * Machine that hosts various services.
 * @author Mark Caldropoli
 */
public class Machine {
    private String hostname;
    // Service name to hosted services
    private Map<String, Service> hostedServices;

    /**
     * Default constructor to set/initialize fields.
     */
    public Machine(String hostIn) {
        hostname = hostIn;
        hostedServices = new HashMap<String, Service>();
    }

    /**
     * Attempts to add service to the machine.
     * @return true if service added to machine, false otherwise.
     */
    public boolean addService(String serviceIn, String urlIn) {
        if(hostedServices.get(serviceIn) == null) {
            Service service = new Service(serviceIn, urlIn);
            hostedServices.put(serviceIn,service);
            return true;
        }
        return false;
    }

    /**
     * Attempts to remove service from the machine.
     * @return true if service removed from machine, false otherwise.
     */
    public boolean removeService(String serviceIn) {
        if(hostedServices.get(serviceIn) != null) {
            hostedServices.remove(serviceIn);
            return true;
        }
        return false;
    }

    /**
     * Check if the machine contains the specified service.
     * @return true if hostedServices contains the service,
     * false otherwise.
     */
    public boolean hasService(String serviceIn) {
        if(hostedServices.containsKey(serviceIn)) return true;
        return false;
    }

    /**
     * Remove all services from hostedServices.
     */
    public void clearServices() {
        hostedServices.clear();
    }

    /**
     * Machine toString representation, displays the field values.
     * @return String displaying the values of the fields of Machine.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Hostname: " + hostname + "\n");
        str.append("Services:\n");
        for(Service s : hostedServices.values()) {
            str.append(s.getName() + "\n");
        }
        return str.toString();
    }
}
