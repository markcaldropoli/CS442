package genericCheckpointing.xmlStoreRestore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import genericCheckpointing.server.SerStrategyI;
import genericCheckpointing.util.Logger;
import genericCheckpointing.util.SerializableObject;

/**
 * StoreRestoreHandler Class
 * @author Mark Caldropoli
 */
public class StoreRestoreHandler implements InvocationHandler {
    private static BufferedReader bReader;
    private static BufferedWriter bWriter;
    private static File input;
    private static File output;
    private static Logger logger;

    /**
     * Constructor to set the logger field.
     */
    public StoreRestoreHandler(Logger loggerIn) {
        logger = loggerIn;
    }

    /**
     * Set the specified file.
     */
    public void setFile(String type, File fileIn) {
        if(type.equals("input")) input = fileIn;
        else if(type.equals("output")) output = fileIn;
    }

    /**
     * Open the BufferedReader and BufferedWriter on their respective files.
     */
    public void openFile() {
        try {
            bReader = new BufferedReader(new FileReader(input));
            bWriter = new BufferedWriter(new FileWriter(output));
        } catch(FileNotFoundException e) {
            logger.writeMessage("Error: FileNotFoundException in StoreRestoreHandler - openFile", Logger.DebugLevel.ERROR);
            System.exit(1);
        } catch(IOException e) {
            logger.writeMessage("Error: IOException in StoreRestoreHandler - openFile", Logger.DebugLevel.ERROR);
            System.exit(1);
        } finally { }
    }

    /**
     * Close the BufferedReader and BufferedWriter.
     */
    public void closeFile() {
        try {
            bReader.close();
            bWriter.close();
        } catch(IOException e) {
            logger.writeMessage("Error: IOException in StoreRestoreHandler - closeFile", Logger.DebugLevel.ERROR);
            System.exit(1);
        } finally { }
    }

    /**
     * Strategy Pattern - apply XMLDeserialization or XMLSerialization 
     */
    public void serializeData(Object obj, SerStrategyI strat, Method meth) {
        strat.processInput(obj, meth);
    }

    /**
     * Invoke readObj/writeObj methods
     * @return List of SerializableObject:
     * null if Serialization
     * the deserialized objects if Deserialization
     */
    public Object invoke(Object proxy, Method meth, Object[] args) {
        String method = meth.getName();
        List<SerializableObject> result = new ArrayList<SerializableObject>();

        if(method.equals("readObj")) {
            XMLDeserialization deser = new XMLDeserialization(logger, bReader);
            serializeData(args[0], deser, meth);
            result = deser.getResult();
        } else if(method.equals("writeObj")) {
            XMLSerialization ser = new XMLSerialization(logger, bWriter);
            serializeData(args[0], ser, meth);
        }

        return result;
    }
}
