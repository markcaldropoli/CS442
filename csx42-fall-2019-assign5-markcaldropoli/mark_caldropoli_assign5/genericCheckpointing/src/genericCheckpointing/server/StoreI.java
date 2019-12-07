package genericCheckpointing.server; 

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.MySpecialTypes;

/**
 * StoreI Interface
 * @author Mark Caldropoli
 */
public interface StoreI extends StoreRestoreI {
    void writeObj(MyAllTypesFirst aRecord, String wireFormat);
    void writeObj(MyAllTypesSecond aRecord, String wireFormat);
    void writeObj(MySpecialTypes sRecord, String wireFormat);
}
