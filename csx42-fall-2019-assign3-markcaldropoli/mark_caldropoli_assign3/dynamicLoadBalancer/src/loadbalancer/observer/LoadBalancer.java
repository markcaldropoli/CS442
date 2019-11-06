package loadbalancer.observer;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import loadbalancer.subject.AddServiceData;
import loadbalancer.subject.Cluster;
import loadbalancer.subject.Data;
import loadbalancer.subject.Operation;
import loadbalancer.subject.RemoveServiceData;

/**
 * LoadBalancer to manage Service:ServiceManagers
 * @author Mark Caldropoli
 */
public class LoadBalancer implements ObserverI {
    // Index to find the URL and hostname for a given service name.
    private Map<String, ServiceManager> map;

    /**
     * Default constructor to initialize the trie.
     */
    public LoadBalancer() {
        map = new HashMap<String, ServiceManager>();
    }

    /**
     * Get ServiceManager of specified service.
     * @return ServiceManager for specified service.
     */
    public ServiceManager getServiceManager(String service) {
        return map.get(service);
    }

    /**
     * Get all ServiceManagers.
     * @return ArrayList of ServiceManagers.
     */
    public ArrayList<ServiceManager> getServiceManagers() {
        return new ArrayList<ServiceManager>(map.values());
    }

    /**
     * Get result of a request.
     * @return String representing request.
     */
    public String request(String serviceIn) {
        if(!map.containsKey(serviceIn)) return "Invalid Service";
        ServiceManager manager = map.get(serviceIn);
        if(manager.getHostnames().size() == 0) return "Service Inactive - Service::" + serviceIn;
        return "Processed Request - Service_URL::" + manager.getURL() + " Host::" + manager.useHost();
    }

    /**
     * Update map based on parameters.
     */
    public void update(Operation opIn, Data dataIn) {
        if(opIn.equals(Operation.SERVICE_OP__ADD_SERVICE)) {
            AddServiceData data = (AddServiceData)dataIn;
            map.put(data.getServiceName(), new ServiceManager(data.getServiceName(), data.getURL(), data.getHostnames()));
        }
        else if(opIn.equals(Operation.SERVICE_OP__REMOVE_SERVICE)) {
            map.remove(((RemoveServiceData)dataIn).getServiceName());
        }
    }
}
