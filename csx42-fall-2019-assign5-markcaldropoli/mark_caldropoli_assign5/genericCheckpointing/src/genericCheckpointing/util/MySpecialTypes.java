package genericCheckpointing.util;

import java.util.Objects;

/**
 * MySpecialTypes Class
 * @author Mark Caldropoli
 */
public class MySpecialTypes extends MyAllTypesSecond {
    private int myInt1;
    private int myInt2;
    private String myString1;
    private String myString2;
    private double myDoubleT1;
    private double myDoubleT2;

    /**
     * Initialize fields with default values.
     */
    public MySpecialTypes() {
        myInt1 = 0;
        myInt2 = 0;
        myString1 = "";
        myString2 = "";
        myDoubleT1 = 0;
        myDoubleT2 = 0;
    }

    /**
     * Get myInt1 field value.
     * @return myInt1 field value.
     */
    public int getMyInt1() {
        return myInt1;
    }

    /**
     * Set myInt1 field value.
     */
    public void setMyInt1(int myInt1In) {
        myInt1 = myInt1In;
    }

    /**
     * Get myInt2 field value.
     * @return myInt2 field value.
     */
    public int getMyInt2() {
        return myInt2;
    }

    /**
     * Set myInt2 field value.
     */
    public void setMyInt2(int myInt2In) {
        myInt2 = myInt2In;
    }

    /**
     * Get myString1 field value.
     * @return myString1 field value.
     */
    public String getMyString1() {
        return myString1;
    }

    /**
     * Set myString1 field value.
     */
    public void setMyString1(String myString1In) {
        myString1 = myString1In;
    }

    /**
     * Get myString2 field value.
     * @return myString2 field value.
     */
    public String getMyString2() {
        return myString2;
    }

    /**
     * Set myString2 field value.
     */
    public void setMyString2(String myString2In) {
        myString2 = myString2In;
    }

    /**
     * Get myDoubleT1 field value.
     * @return myDoubleT1 field value.
     */
    public double getMyDoubleT1() {
        return myDoubleT1;
    }

    /**
     * Set myDoubleT1 field value.
     */
    public void setMyDoubleT1(double myDoubleT1In) {
        myDoubleT1 = myDoubleT1In;
    }

    /**
     * Get myDoubleT2 field value.
     * @return myDoubleT2 field value.
     */
    public double getMyDoubleT2() {
        return myDoubleT2;
    }

    /**
     * Set myDoubleT2 field value.
     */
    public void setMyDoubleT2(double myDoubleT2In) {
        myDoubleT2 = myDoubleT2In;
    }

    /**
     * MySpecialTypes's toString representation.
     * @return String representing MySpecialTypes
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n\t\t--- MySpecialTypes ---\n");
        str.append("\t\tmyInt1        = " + myInt1 + "\n");
        str.append("\t\tmyInt2        = " + myInt2 + "\n");
        str.append("\t\tmyString1     = " + myString1 + "\n");
        str.append("\t\tmyString2     = " + myString2 + "\n");
        str.append("\t\tmyDoubleT1    = " + myDoubleT1 + "\n");
        str.append("\t\tmyDoubleT2    = " + myDoubleT2);
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
        MySpecialTypes spec = (MySpecialTypes) o;
        return myInt1 == spec.getMyInt1() &&
               myInt2 == spec.getMyInt2() &&
               myString1.equals(spec.getMyString1()) &&
               myString2.equals(spec.getMyString2()) &&
               myDoubleT1 == spec.getMyDoubleT1() &&
               myDoubleT2 == spec.getMyDoubleT2();
    }

    /**
     * Get the object's hashCode representation.
     * @return hashCode representation of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myInt1, myInt2, myString1, myString2, myDoubleT1, myDoubleT2);
    }
}
