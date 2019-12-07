package genericCheckpointing.util;

import java.util.Objects;

/**
 * MyAllTypesSecond Class
 * @author Mark Caldropoli
 */
public class MyAllTypesSecond extends MyAllTypesFirst {
    private double myDoubleT;
    private float myFloatT;
    private short myShortT;
    private char myCharT;
    private double myOtherDoubleT;

    /**
     * Initialize fields with default values.
     */
    public MyAllTypesSecond() {
        myDoubleT = 0;
        myFloatT = 0;
        myShortT = 0;
        myCharT = ' ';
        myOtherDoubleT = 0;
    }

    /**
     * Get myDoubleT field value.
     * @return myDoubleT field value.
     */
    public double getMyDoubleT() {
        return myDoubleT;
    }

    /**
     * Set myDoubleT field value.
     */
    public void setMyDoubleT(double myDoubleTIn) {
        myDoubleT = myDoubleTIn;
    }

    /**
     * Get myFloatT field value.
     * @return myFloatT field value.
     */
    public float getMyFloatT() {
        return myFloatT;
    }

    /**
     * Set myFloatT field value.
     */
    public void setMyFloatT(float myFloatTIn) {
        myFloatT = myFloatTIn;
    }

    /**
     * Get myShortT field value.
     * @return myShortT field value.
     */
    public short getMyShortT() {
        return myShortT;
    }

    /**
     * Set myShortT field value.
     */
    public void setMyShortT(short myShortTIn) {
        myShortT = myShortTIn;
    }

    /**
     * Get myCharT field value.
     * @return myCharT field value.
     */
    public char getMyCharT() {
        return myCharT;
    }

    /**
     * Set myCharT field value.
     */
    public void setMyCharT(char myCharTIn) {
        myCharT = myCharTIn;
    }

    /**
     * Get myOtherDoubleT field value.
     * @return myOtherDoubleT field value.
     */
    public double getMyOtherDoubleT() {
        return myOtherDoubleT;
    }

    /**
     * Set myOtherDoubleT field value.
     */
    public void setMyOtherDoubleT(double myOtherDoubleTIn) {
        myOtherDoubleT = myOtherDoubleTIn;
    }

    /**
     * MyAllTypesSeconds's toString representation.
     * @return String representing MyAllTypesSecond
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n\t\t--- MyAllTypesSecond ---\n");
        str.append("\t\tmyDoubleT         = " + myDoubleT + "\n");
        str.append("\t\tmyFloatT          = " + myFloatT + "\n");
        str.append("\t\tmyShortT          = " + myShortT + "\n");
        str.append("\t\tmyCharT           = " + myCharT + "\n");
        str.append("\t\tmyOtherDoubleT    = " + myOtherDoubleT);
        return str.toString();
    }

    /**
     * Determines if two objects are equal.
     * @return true if objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(this == null) return false;
        if(this.getClass() != o.getClass()) return false;
        MyAllTypesSecond sec = (MyAllTypesSecond) o;
        return myDoubleT == sec.getMyDoubleT() &&
               myFloatT == sec.getMyFloatT() &&
               myShortT == sec.getMyShortT() &&
               myCharT == sec.getMyCharT() &&
               myOtherDoubleT == sec.getMyOtherDoubleT();
    }

    /**
     * Get the object's hashCode representation.
     * @return hashCode representation of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myDoubleT, myFloatT, myShortT, myCharT, myOtherDoubleT);
    }
}
