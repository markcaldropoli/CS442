package loadbalancer.observer;

import loadbalancer.subject.Data;
import loadbalancer.subject.Operation;

/**
 * Observer Interface
 * @author Mark Caldropoli
 */
public interface ObserverI {
    void update(Operation opIn, Data dataIn);
}
