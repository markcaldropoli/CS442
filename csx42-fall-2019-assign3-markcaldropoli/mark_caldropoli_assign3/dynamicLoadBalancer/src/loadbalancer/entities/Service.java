package loadbalancer.entities;

/**
 * Service with name and url to be added to machines.
 * @author Mark Caldropoli
 */
public class Service {
    private String url;
    private String name;

    /**
     * Default constructor to set service name and url.
     */
    public Service(String nameIn, String urlIn) {
        name = nameIn;
        url = urlIn;
    }

    /**
     * Get the service's name.
     * @return service name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the service's URL.
     * @return service URL.
     */
    public String getURL() {
        return url;
    }

    /**
     * Service toString representation, displays the field values.
     * @return String displaying the values of the fields of service.
     */
    public String toString() {
        return "Service: " + name + ", URL: " + url;
    }
}
