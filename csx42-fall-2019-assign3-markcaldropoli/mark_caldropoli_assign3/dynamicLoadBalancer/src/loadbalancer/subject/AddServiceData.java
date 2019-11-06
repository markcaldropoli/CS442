package loadbalancer.subject;

import java.util.Arrays;
import java.util.List;

/**
 * Holds AddService Data
 * @author Mark Caldropoli
 */
public class AddServiceData implements Data {
    private String serviceName;
    private String url;
    private List<String> hostnames;

    /**
     * Constructor to populate data fields.
     */
    public AddServiceData(String serviceIn, String urlIn, String hostsIn) {
        serviceName = serviceIn;
        url = urlIn;
        hostnames = Arrays.asList(hostsIn.split(","));
    }

    /**
     * Get the name of the service.
     * @return String representing the service name.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Get the URL of the service.
     * @return String representing the service URL.
     */
    public String getURL() {
        return url;
    }

    /**
     * Get the list of hosts that the service is on.
     * @return List<String> representing the hosts that the service
     * is located on.
     */
    public List<String> getHostnames() {
        return hostnames;
    }

    /**
     * AddServiceData toString representation.
     * @return String displaying the values of AddServiceData fields.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Service: " + serviceName + ", URL: " + url + ", Hostnames: ");
        for(int i = 0; i < hostnames.size(); i++) {
            str.append(hostnames.get(i) + " ");
        }
        return str.toString();
    }
}
