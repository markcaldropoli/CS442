package loadbalancer.subject;

import loadbalancer.observer.ObserverI;

/**
 * Subject Interface
 * @author Mark Caldropoli
 */
public interface SubjectI {
    boolean scaleUp(String hostIn);
    boolean scaleDown(ClusterData dataIn);
    int addService(AddServiceData dataIn);
    boolean removeService(RemoveServiceData dataIn);
    int addInstance(InstanceData dataIn);
    boolean removeInstance(InstanceData dataIn);
    void registerObserver(ObserverI obsIn, Filter filterIn);
    void removeObserver(String serviceIn);
    void notifyObservers(Operation opIn, Data dataIn);
}
