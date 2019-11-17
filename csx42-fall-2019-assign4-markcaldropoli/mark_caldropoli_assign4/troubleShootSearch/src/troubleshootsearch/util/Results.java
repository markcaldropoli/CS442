package troubleshootsearch.util;

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
    private MyLogger logger;

    /**
     * Default results constructor to initialize fields.
     */
    public Results(String filenameIn, MyLogger loggerIn) {
        filename = filenameIn;
        output = new ArrayList<String>();
        logger = loggerIn;
    }

    /**
     * Read output from elements.
     */
    public void addLine(String lineIn) {
        output.add(lineIn);
    }

    /**
     * Get the Strings to be outputted.
     * @return ArrayList of String containing the output results.
     */
    public ArrayList<String> getOutput() {
        return output;
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
            logger.writeMessage("IO Exception: Filename was not a proper name.", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        } finally {
            try {
                writer.close();
            } catch(IOException e) {
                logger.writeMessage("BufferedWriter not found.", MyLogger.DebugLevel.ERROR);
                System.exit(1);
            }
        }
    }

    /**
     * Writes results to standard output.
     */
    public void writeToStdout() {
        if(logger.getDebugValue().equals(MyLogger.DebugLevel.SEARCH_RESULTS)) {
            for(String line : output) {
                System.out.println(line);
            }
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
