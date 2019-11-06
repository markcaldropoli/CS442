package loadbalancer.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;

/**
 * Handles output of results.
 * @author Mark Caldropoli
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    private String filename;
    private ArrayList<String> output;

    /**
     * Default results constructor to initialize fields.
     */
    public Results(String filenameIn) {
        filename = filenameIn;
        output = new ArrayList<String>();
    }

    /**
     * Add a line to the arraylist to be outputted.
     */
    public void addLine(String lineIn) {
        output.add(lineIn);
    }

    /**
     * Writes results to a file using the provided filename.
     */
    public void writeToFile() {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(filename));
            for(int i = 0; i < output.size(); i++) {
                writer.write(output.get(i));
                writer.newLine();
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("IO Exception: Filename was not a proper name.");
            System.exit(1);
        } finally {
            try {
                writer.close();
            } catch(IOException e) {
                System.out.println("BufferedWriter not found.");
                System.exit(1);
            }
        }
    }

    /**
     * Writes results to standard output.
     */
    public void writeToStdout() {
        for(String line : output) {
            System.out.println(line);
        }
    }

    /**
     * Default toString, not needed for debugging here.
     * @return String with value null
     */
    public String toString() {
        return null;
    }
}
