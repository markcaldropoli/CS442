package genericCheckpointing.xmlStoreRestore;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.NoSuchMethodException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import genericCheckpointing.server.SerStrategyI;
import genericCheckpointing.util.Logger;

/**
 * XML Serialization Class
 * @author Mark Caldropoli
 */
public class XMLSerialization implements SerStrategyI {
    private static BufferedWriter writer;
    private static Logger logger;

    /**
     * Constructor to set field.
     */
    public XMLSerialization(Logger loggerIn, BufferedWriter writerIn) {
        logger = loggerIn;
        writer = writerIn;
    }

    /**
     * Process the obj to serialize into checkpoint verify file.
     */
    public void processInput(Object obj, Method meth) {
        try {
            String types = meth.getParameterTypes()[0].toString();
            String[] outputTypes = types.split(" ");
            Class<?> className = Class.forName(outputTypes[1]);
            Field[] fields = className.getDeclaredFields();

            // Begin writing new object
            writer.write("<DPSerialization>");
            writer.newLine();
            writer.write(" <complexType xsi:type=\"");
            writer.write(outputTypes[1]);
            writer.write("\">");
            writer.newLine();

            // Write object's fields
            for(int i = 0; i < fields.length; i++) {
                Class fClass = fields[i].getType();
                String fName = fields[i].getName();
                String fType = fClass.toString();

                // Format String to match method name
                String upFName = Character.toUpperCase(fName.charAt(0)) + fName.substring(1);

                // Handle String case
                if(fType.contains("String")) fType = "string";

                // Get field value using invoke
                Method getX = className.getMethod("get" + upFName);
                writer.write("  <" + fName + " xsi:type=\"xsd:" + fType + "\">");
                writer.write(getX.invoke(obj).toString());
                writer.write("</" + fName + ">");
                writer.newLine();
            }

            // Finish writing new object
            writer.write(" </complexType>");
            writer.newLine();
            writer.write("</DPSerialization>");
            writer.newLine();
        } catch(IOException e) {
            logger.writeMessage("Error: IOException in XMLSerialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } catch(ClassNotFoundException e) {
            logger.writeMessage("Error: ClassNotFoundException in XMLSerialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } catch(NoSuchMethodException e) {
            logger.writeMessage("Error: NoSuchMethodException in XMLSerialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } catch(IllegalAccessException e) {
            logger.writeMessage("Error: IllegalAccessException in XMLSerialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } catch(InvocationTargetException e) {
            logger.writeMessage("Error: InvocationTargetException in XMLSerialization - processInput", Logger.DebugLevel.ERROR);
            System.exit(1);
        } finally { }
    }
}
