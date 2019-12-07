package genericCheckpointing.xmlStoreRestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.NoSuchMethodException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import genericCheckpointing.server.SerStrategyI;
import genericCheckpointing.util.Logger;
import genericCheckpointing.util.SerializableObject;

/**
 * XML Deserialization Class
 * @author Mark Caldropoli
 */
public class XMLDeserialization implements SerStrategyI {
    private static BufferedReader reader;
    private static Logger logger;
    private List<SerializableObject> result;

    /**
     * Constructor to set fields.
     */
    public XMLDeserialization(Logger loggerIn, BufferedReader readerIn) {
        logger = loggerIn;
        reader = readerIn;
        result = new ArrayList<SerializableObject>();
    }

    /**
     * Process the checkpointFile line by line to deserialize.
     */
    public void processInput(Object obj, Method m) {
        try {
            Class<?> className = null;
            Field[] fields = null;
            String[] getFields = null;
            Object newObj = null;

            String line;
            while((line = reader.readLine()) != null) {
                if(line.contains("complexType xsi:type")) {
                    // Create new object
                    getFields = line.split("type=");
                    getFields[1] = getFields[1].replace("\"","").replace(">","");
                    className = Class.forName(getFields[1]);
                    fields = className.getDeclaredFields();
                    newObj = className.newInstance();
                } else if(!(line.contains("DPSerialization") || line.contains("complexType"))) {
                    // Extract field information
                    String param = line.substring(line.indexOf(">")+1,line.lastIndexOf("<"));
                    Method setX = null;

                    for(int i = 0; i < fields.length; i++) {
                        Class fClass = fields[i].getType();
                        String fName = fields[i].getName();
                        String fType = fClass.toString();
                        Class<?>[] signature = new Class<?>[1];
                        signature[0] = fClass;

                        String upFName = Character.toUpperCase(fName.charAt(0)) + fName.substring(1);
                        setX = className.getMethod("set" + upFName, signature);

                        String paramType = line.substring(line.indexOf("\"")+5,line.lastIndexOf("\""));
                        String objType = line.substring(line.indexOf("<")+1,line.indexOf(" xsi"));

                        // Set field value using invoke
                        if(fName.equals(objType)) {
                            switch(paramType) {
                                case "int": setX.invoke(newObj, Integer.parseInt(param)); break;
                                case "long": setX.invoke(newObj, Long.parseLong(param)); break;
                                case "string": setX.invoke(newObj, param); break;
                                case "boolean": setX.invoke(newObj, Boolean.parseBoolean(param)); break;
                                case "double": setX.invoke(newObj, Double.parseDouble(param)); break;
                                case "float": setX.invoke(newObj, Float.parseFloat(param)); break;
                                case "short": setX.invoke(newObj, Short.parseShort(param)); break;
                                case "char": setX.invoke(newObj, param.charAt(0)); break;
                                default: break;
                            }
                        }
                    }

                } else if(line.contains("</DPSerialization>")) {
                    // Add finished object to result
                    result.add((SerializableObject)newObj);
                }
            }
        } catch(IOException e) {
            logger.writeMessage("Error: IOException in XMLDeserialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } catch(InstantiationException e) {
            logger.writeMessage("Error: InstantiationException in XMLDeserialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
    	} catch(ClassNotFoundException e) {
            logger.writeMessage("Error: ClassNotFoundException in XMLDeserialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } catch(IllegalAccessException e) {
            logger.writeMessage("Error: IllegalAccessException in XMLDeserialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } catch(NoSuchMethodException e) {
            logger.writeMessage("Error: NoSuchMethodException in XMLDeserialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } catch(InvocationTargetException e) {
            logger.writeMessage("Error: InvocationTargetException in XMLDeserialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } finally { }
    }

    /**
     * Get result.
     * @return List of SerializableObject
     */
    public List<SerializableObject> getResult() {
        return result;
    }
}
