package loadbalancer.observer;

import java.util.ArrayList;
import java.util.List;
import loadbalancer.subject.ClusterData;
import loadbalancer.subject.Data;
import loadbalancer.subject.InstanceData;
import loadbalancer.subject.Operation;

/**
 * ServiceManager to keep track of Service metadata
 * @author Mark Caldropoli
 */
public class ServiceManager implements ObserverI {
    private String key;
    // Information pertaining to the service
    private String url;
    private ArrayList<String> hostnames;
    private int nextHost;

    /**
     * Constructor to set fields.
     */
    public ServiceManager(String keyIn, String urlIn, List<String> hostsIn) {
        key = keyIn;
        url = urlIn;
        hostnames = new ArrayList<String>(hostsIn);
        nextHost = 0;
    }

    /**
     * Add host to hostnames.
     */
    public void addHost(String hostIn) {
        hostnames.add(hostIn);
    }

    /**
     * Remove host from hostnames.
     */
    public void removeHost(String hostIn) {
        hostnames.remove(hostIn);
    }

    /**
     * Get the next host for a request.
     * @return String host to use for request.
     */
    public String useHost() {
        if(nextHost == hostnames.size()) nextHost = 0;
        String res = hostnames.get(nextHost);
        nextHost++;
        return res;
    }

    /**
     * Update specific ServiceManager based on operation.
     */
    public void update(Operation opIn, Data dataIn) {
        if(opIn.equals(Operation.CLUSTER_OP__SCALE_DOWN)) {
            ClusterData data = (ClusterData)dataIn;
            for(ServiceManager manager : data.getServiceManagers()) {
                manager.removeHost(data.getHostname());
            }
        } else if(opIn.equals(Operation.SERVICE_OP__ADD_INSTANCE)) {
            InstanceData data = (InstanceData)dataIn;
            data.getServiceManager().addHost(data.getHostname());
        } else if(opIn.equals(Operation.SERVICE_OP__REMOVE_INSTANCE)) {
            InstanceData data = (InstanceData)dataIn;
            data.getServiceManager().removeHost(data.getHostname());
        }
    }

    /**
     * Get ServiceManager's key.
     * @return String representing ServiceManager's key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Get ServiceManager's URL.
     * @return String representing ServiceManager's URL.
     */
    public String getURL() {
        return url;
    }

    /**
     * Get ServiceManager's hostnames.
     * @return List of Strings representing ServiceManager's hostnames.
     */
    public List<String> getHostnames() {
        return hostnames;
    }

    /**
     * ServiceManager toString representation.
     * @return String displaying the values of the fields of
     * ServiceManager.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Key: " + key + ", URL: " + url + ", Hostnames: ");
        for(int i = 0; i < hostnames.size(); i++) {
            str.append(hostnames.get(i) + " ");
        }
        return str.toString();
    }
}
