package genericCheckpointing.server;

import java.lang.reflect.Method;

/**
 * Serialization Strategy Interface
 * @author Mark Caldropoli
 */
public interface SerStrategyI {
    void processInput(Object obj, Method m);
}
