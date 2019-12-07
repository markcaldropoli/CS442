package genericCheckpointing.driver;

import java.io.File;
import java.util.List;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.Logger;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.MySpecialTypes;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

/**
 * Driver class
 * @author Mark Caldropoli
 */
public class Driver {
    public static void main(String[] args) {
        // Create logger
        Logger logger = new Logger();
        /*
         * [0:ERROR] - Errors only (prints nothing unless error detected)
         * [1:DATA] - Data and Errors (prints data and any detected errors to console)
         */
        if(args[3].equals("0") || args[3].equals("1")) logger.setDebugValue(Integer.parseInt(args[3]));
        else logger.setDebugValue(0); // Set to default if not valid
        System.out.println(logger.toString()); // Display logger mode

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used.
         */
        if(args.length != 4 || args[0].equals("${arg0}") || args[1].equals("${arg1}") ||
                args[2].equals("${arg2}") || args[3].equals("${arg3}")) {
            logger.writeMessage("Error: Incorrect number of arguments. Program accepts 4 arguments.", Logger.DebugLevel.ERROR);
            System.exit(1);
        }

        // Check to make sure mode is set to deserser
        if(!args[0].equals("deserser")) {
            logger.writeMessage("Error: Mode not set to deserser.", Logger.DebugLevel.ERROR);
            System.exit(1);
        }
        
        ProxyCreator pc = new ProxyCreator();
        
        // Create an instance of StoreRestoreHandler which implements the InvocationHandler
        StoreRestoreHandler handler = new StoreRestoreHandler(logger);
        
        // Create a proxy
        StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] {StoreI.class, RestoreI.class}, new StoreRestoreHandler(logger));
        
        // Invoke methods on the handler instance to set the filename for checkpointFile & checkpoint-verify
        handler.setFile("input", new File(args[1]));
        handler.setFile("output", new File(args[2]));
        // Open the files
        handler.openFile();
        
        MyAllTypesFirst myFirst;
        MyAllTypesSecond mySecond;
        MySpecialTypes mySpecialT;

        // Store myRecordRet in a data structure
        List<SerializableObject> myRecordRet;
        
        // Read in a loop until the end of file is indicated
        myRecordRet = ((RestoreI) cpointRef).readObj("XML");

        // Display data members
        for(int i = 0; i < myRecordRet.size(); i++) {
            logger.writeMessage(myRecordRet.get(i).toString(), Logger.DebugLevel.DATA);
        }
        
        // Write obj in a loop based on obj type
        for(int i = 0; i < myRecordRet.size(); i++) {
            Object obj = myRecordRet.get(i);

            if(obj instanceof MySpecialTypes) {
                mySpecialT = (MySpecialTypes) obj;
                ((StoreI) cpointRef).writeObj(mySpecialT, "XML");
            } else if(obj instanceof MyAllTypesSecond) {
                mySecond = (MyAllTypesSecond) obj;
                ((StoreI) cpointRef).writeObj(mySecond, "XML");
            } else if(obj instanceof MyAllTypesFirst) {
                myFirst = (MyAllTypesFirst) obj;
                ((StoreI) cpointRef).writeObj(myFirst, "XML");
            }
	}
        
        // Invoke a method on the handler to close the file
        handler.closeFile();
    }
}
