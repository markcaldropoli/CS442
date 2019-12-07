package genericCheckpointing.server;

import java.util.List;
import genericCheckpointing.util.SerializableObject;

/**
 * RestoreI Interface
 * @author Mark Caldropoli
 */
public interface RestoreI extends StoreRestoreI {
    List<SerializableObject> readObj(String input);
}
