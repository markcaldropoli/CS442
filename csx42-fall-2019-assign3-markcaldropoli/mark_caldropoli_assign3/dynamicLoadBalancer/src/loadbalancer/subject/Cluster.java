package loadbalancer.subject;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import loadbalancer.entities.Machine;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.observer.ObserverI;
import loadbalancer.observer.ServiceManager;

/**
 * Cluster - Subject of Observer Pattern
 * @author Mark Caldropoli
 */
public class Cluster implements SubjectI {
    // Unique instance of Cluster
    private static Cluster uniqueInstance;
    // List of observers watching the subject (cluster)
    private Map<ObserverI, Filter> observers;
    // Hostnames to corresponding machine instances.
    private Map<String, Machine> machines;

    /**
     * Default private singleton constructor to initialize machines.
     */
    private Cluster() {
        observers = new HashMap<ObserverI, Filter>();
        machines = new HashMap<String, Machine>();
    }

    /**
     * Get unique instance of Cluster.
     * @return unique instance of Cluster.
     */
    public static Cluster getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Cluster();
        }
        return uniqueInstance;
    }

    /**
     * Attempts to scale up the cluster.
     * @return true if cluster scaled up, false otherwise.
     */
    public boolean scaleUp(String hostIn) {
        if(machines.get(hostIn) == null) {
            Machine machine = new Machine(hostIn);
            machines.put(hostIn, machine);
            return true;
        }
        return false;
    }

    /**
     * Attempts to scale down the cluster.
     * @return true if cluster scaled down, false otherwise.
     */
    public boolean scaleDown(ClusterData dataIn) {
        String host = dataIn.getHostname();
        if(machines.get(host) != null) {
            machines.get(host).clearServices();
            machines.remove(host);
            notifyObservers(Operation.CLUSTER_OP__SCALE_DOWN, dataIn);
            return true;
        }
        return false;
    }

    /**
     * Attempts to add service to specified machines.
     * @return 1 on success, -1 if a host doesn't exist,
     * -2 if service already exists on a machine.
     */
    public int addService(AddServiceData dataIn) {
        String name = dataIn.getServiceName();
        String url = dataIn.getURL();
        List<String> hosts = dataIn.getHostnames();

        // Check if all hosts exist
        for(int i = 0; i < hosts.size(); i++) {
            if(machines.get(hosts.get(i)) == null) return -1;
        }

        // Add service to host(s)
        boolean addedService;
        for(int i = 0; i < hosts.size(); i++) {
            addedService = machines.get(hosts.get(i)).addService(name, url);
            if(!addedService) return -2;
        }

        ServiceManager manager = new ServiceManager(dataIn.getServiceName(), dataIn.getURL(), dataIn.getHostnames());
        registerObserver(manager, new ServiceManagerFilter());
        notifyObservers(Operation.SERVICE_OP__ADD_SERVICE, dataIn);
        return 1;
    }

    /**
     * Attempts to remove service from all machines.
     * @return true if service is removed from all machines,
     * false otherwise.
     */
    public boolean removeService(RemoveServiceData dataIn) {
        String service = dataIn.getServiceName();
        boolean result = true;

        for(Machine m : machines.values()) {
            if(m.hasService(service)) {
                result = m.removeService(service);
                if(!result) break;
            }
        }

        removeObserver(dataIn.getServiceName());
        notifyObservers(Operation.SERVICE_OP__REMOVE_SERVICE, dataIn);
        return result;
    }

    /**
     * Attempts to add an instance of a service to a given host.
     * @return 1 if instance of service added to the given
     * hostname, -1 if the service already exists on the machine,
     * or -2 if the service was not previously added to the cluster.
     */
    public int addInstance(InstanceData dataIn) {
        String service = dataIn.getServiceName();
        String host = dataIn.getHostname();
        ServiceManager manager = dataIn.getServiceManager();

        if(machines.get(host).hasService(service)) return -1;
        if(manager == null) return -2;

        machines.get(host).addService(service, manager.getURL());
        notifyObservers(Operation.SERVICE_OP__ADD_INSTANCE, dataIn);
        return 1;
    }

    /**
     * Attempts to remove an instance of a service from a given host.
     * @return true if instance of service removed, false otherwise.
     */
    public boolean removeInstance(InstanceData dataIn) {
        String service = dataIn.getServiceName();
        String host = dataIn.getHostname();

        if(!machines.get(host).hasService(service)) return false;

        machines.get(host).removeService(service);
        notifyObservers(Operation.SERVICE_OP__REMOVE_INSTANCE, dataIn);
        return true;
    }

    /**
     * Register specified observer/filter pair.
     */
    public void registerObserver(ObserverI obsIn, Filter filterIn) {
        observers.put(obsIn,filterIn);
    }

    /**
     * Remove specified observer.
     */
    public void removeObserver(String serviceIn) {
        for(ObserverI obs : observers.keySet()) {
            if(obs instanceof ServiceManager && ((ServiceManager)obs).getKey().equals(serviceIn)) {
                observers.remove(obs);
                return;
            }
        }
    }

    /**
     * Notify observers when command has been called.
     */
    public void notifyObservers(Operation opIn, Data dataIn) {
        for(Map.Entry<ObserverI, Filter> entry : observers.entrySet()) {
            if(entry.getValue().check(opIn)) {
                entry.getKey().update(opIn, dataIn);
            }
        }
    }
}
