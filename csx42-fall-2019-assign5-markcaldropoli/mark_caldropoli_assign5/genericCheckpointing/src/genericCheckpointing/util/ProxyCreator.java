package genericCheckpointing.util;

import genericCheckpointing.server.StoreRestoreI;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ProxyCreator Class
 * @author Mark Caldropoli
 */
public class ProxyCreator {
    public StoreRestoreI createProxy(Class<?>[] interfaceArray, InvocationHandler handler) {
        StoreRestoreI storeRestoreRef = (StoreRestoreI) Proxy.newProxyInstance(getClass().getClassLoader(),interfaceArray,handler);
        return storeRestoreRef;
    }
}
