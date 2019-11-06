package loadbalancer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System;
import loadbalancer.entities.Machine;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.subject.AddServiceData;
import loadbalancer.subject.Cluster;
import loadbalancer.subject.ClusterData;
import loadbalancer.subject.InstanceData;
import loadbalancer.subject.LoadBalancerFilter;
import loadbalancer.subject.RemoveServiceData;

/**
 * Process input file
 * @author Mark Caldropoli
 */
public class FileProcessor {
    private Cluster cluster;
    private LoadBalancer loadBalancer;
    private Results results;

    /**
     * Constructor to initialize private fields
     */
    public FileProcessor(Cluster clusterIn, LoadBalancer loadBalancerIn, Results resultsIn) {
        cluster = clusterIn;
        loadBalancer = loadBalancerIn;
        cluster.registerObserver(loadBalancer, new LoadBalancerFilter());
        results = resultsIn;
    }

    /**
     * Parse input file.
     */
    public void processInput(String filename) {
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            if(file.length() != 0) {
                String line;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    String[] lineContents = line.split(" ");
                    switch(lineContents[0]) {
                        case "CLUSTER_OP__SCALE_UP":
                            if(!checkLineContents(lineContents, 2)) break;
                            boolean scaledUp = cluster.scaleUp(lineContents[1]);
                            if(scaledUp) results.addLine("Cluster Scaled Up");
                            else results.addLine("Machine with the given hostname already exists");
                            break;
                        case "CLUSTER_OP__SCALE_DOWN":
                            if(!checkLineContents(lineContents, 2)) break;
                            ClusterData scaleDownData = new ClusterData(lineContents[1], loadBalancer.getServiceManagers());
                            boolean scaledDown = cluster.scaleDown(scaleDownData);
                            if(scaledDown) results.addLine("Cluster Scaled Down");
                            else results.addLine("No machine with the given hostname exists");
                            break;
                        case "SERVICE_OP__ADD_SERVICE":
                            if(!checkLineContents(lineContents, 4)) break;
                            AddServiceData addServiceData = new AddServiceData(lineContents[1], lineContents[2], lineContents[3]);
                            int addedService = cluster.addService(addServiceData);
                            if(addedService == 1) results.addLine("Service Added");
                            else if(addedService == -1) results.addLine("Machine does not exist for a given hostname");
                            else if(addedService == -2) results.addLine("Service with the given name already exists on the cluster");
                            break;
                        case "SERVICE_OP__REMOVE_SERVICE":
                            if(!checkLineContents(lineContents, 2)) break;
                            RemoveServiceData removeServiceData = new RemoveServiceData(lineContents[1]);
                            boolean removedService = cluster.removeService(removeServiceData);
                            if(removedService) results.addLine("Service Removed");
                            else results.addLine("Invalid Service");
                            break;
                        case "SERVICE_OP__ADD_INSTANCE":
                            if(!checkLineContents(lineContents, 3)) break;
                            InstanceData addInstanceData = new InstanceData(lineContents[1], lineContents[2], loadBalancer.getServiceManager(lineContents[1]));
                            int addedInstance = cluster.addInstance(addInstanceData);
                            if(addedInstance == 1) results.addLine("Instance Added");
                            else if(addedInstance == -1) results.addLine("Instance of the service already exists on the machine with the given hostname");
                            else if(addedInstance == -2) results.addLine("Service was not previously added to the cluster using SERVICE_OP__ADD_SERVICE");
                            break;
                        case "SERVICE_OP__REMOVE_INSTANCE":
                            if(!checkLineContents(lineContents, 3)) break;
                            InstanceData removeInstanceData = new InstanceData(lineContents[1], lineContents[2], loadBalancer.getServiceManager(lineContents[1]));
                            boolean removedInstance = cluster.removeInstance(removeInstanceData);
                            if(removedInstance) results.addLine("Instance Removed");
                            else results.addLine("Instance of service is not present on the machine with the given hostname");
                            break;
                        case "REQUEST":
                            if(!checkLineContents(lineContents, 2)) break;
                            String reqResult = loadBalancer.request(lineContents[1]);
                            results.addLine(reqResult);
                            break;
                        default:
                            System.out.println("Invalid Operation");
                    }
                }
            } else {
                System.out.println("File is empty.");
                System.exit(1);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found. Please check the name of the file.");
            System.exit(1);
        } catch(IOException e) {
            System.out.println("IO Exception.");
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                System.out.println("BufferedReader not found.");
                System.exit(1);
            }
        }
    }

    /**
     * Determine if correct number of line args provided.
     * @return true if correct number of line args, false otherwise.
     */
    public boolean checkLineContents(String[] contentsIn, int lenIn) {
        if(contentsIn.length == lenIn) return true;
        results.addLine("Invalid Operation");
        return false;
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}
