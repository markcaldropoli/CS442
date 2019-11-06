package loadbalancer.subject;

import java.util.ArrayList;
import loadbalancer.observer.ServiceManager;

/**
 * Holds Cluster Data
 * @author Mark Caldropoli
 */
public class ClusterData implements Data {
    private String hostname;
    private ArrayList<ServiceManager> managers;

    /**
     * Constructor to populate data.
     */
    public ClusterData(String hostIn, ArrayList<ServiceManager> managersIn) {
        hostname = hostIn;
        managers = managersIn;
    }

    /**
     * Get the hostname.
     * @return String representing hostname.
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Get the ServiceManagers.
     * @return ArrayList of ServiceManagers.
     */
    public ArrayList<ServiceManager> getServiceManagers() {
        return managers;
    }

    /**
     * Cluster Data toString representation.
     * @return String displaying the hostname of ClusterData.
     */
    public String toString() {
        return "Hostname: " + hostname;
    }
}
