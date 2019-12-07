package genericCheckpointing.util;

import java.util.Objects;

/**
 * MyAllTypesFirst Class
 * @author Mark Caldropoli
 */
public class MyAllTypesFirst extends SerializableObject {
    private int myInt;
    private long myLong;
    private String myString;
    private boolean myBool;
    private int myOtherInt;

    /**
     * Initialize fields with default values.
     */
    public MyAllTypesFirst() {
        myInt = 0;
        myLong = 0;
        myString = "";
        myBool = false;
        myOtherInt = 0;
    }

    /**
     * Get myInt field value.
     * @return myInt field value.
     */
    public int getMyInt() {
        return myInt;
    }

    /**
     * Set myInt field value.
     */
    public void setMyInt(int myIntIn) {
        myInt = myIntIn;
    }

    /**
     * Get myLong field value.
     * @return myLong field value.
     */
    public long getMyLong() {
        return myLong;
    }

    /**
     * Set myLong field value.
     */
    public void setMyLong(long myLongIn) {
        myLong = myLongIn;
    }

    /**
     * Get myString field value.
     * @return myString field value.
     */
    public String getMyString() {
        return myString;
    }

    /**
     * Set myString field value.
     */
    public void setMyString(String myStringIn) {
        myString = myStringIn;
    }

    /**
     * Get myBool field value.
     * @return myBool field value.
     */
    public boolean getMyBool() {
        return myBool;
    }

    /**
     * Set myBool field value.
     */
    public void setMyBool(boolean myBoolIn) {
        myBool = myBoolIn;
    }

    /**
     * Get myOtherInt field value.
     * @return myOtherInt field value.
     */
    public int getMyOtherInt() {
        return myOtherInt;
    }

    /**
     * Set myOtherInt field value.
     */
    public void setMyOtherInt(int myOtherIntIn) {
        myOtherInt = myOtherIntIn;
    }

    /**
     * MyAllTypesFirst's toString representation.
     * @return String representing MyAllTypesFirst
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n\t\t--- MyAllTypesFirst ---\n");
        str.append("\t\tmyInt       = " + myInt + "\n");
        str.append("\t\tmyLong      = " + myLong + "\n");
        str.append("\t\tmyString    = " + myString + "\n");
        str.append("\t\tmyBool      = " + myBool + "\n");
        str.append("\t\tmyOtherInt  = " + myOtherInt);
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
        MyAllTypesFirst first = (MyAllTypesFirst) o;
        return myInt == first.getMyInt() &&
               myLong == first.getMyLong() &&
               myString.equals(first.getMyString()) &&
               myBool == first.getMyBool() &&
               myOtherInt == first.getMyOtherInt();
    }

    /**
     * Get the object's hashCode representation.
     * @return hashCode representation of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myInt, myLong, myString, myBool, myOtherInt);
    }
}
