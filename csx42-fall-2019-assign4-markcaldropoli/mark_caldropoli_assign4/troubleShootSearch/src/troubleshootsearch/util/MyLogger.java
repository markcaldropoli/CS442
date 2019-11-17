package troubleshootsearch.util;

/**
 * Logger Class
 * @author Mark Caldropoli
 */
public class MyLogger {
    public static enum DebugLevel{ ERROR, SEARCH_RESULTS, FILE_PROCESSOR, MYARRAYLIST, MYTREE };
    private static DebugLevel debugLevel;

    /**
     * Set the current debug level.
     */
    public static void setDebugValue(int levelIn) {
	switch (levelIn) {
            case 4: debugLevel = DebugLevel.MYTREE; break;
            case 3: debugLevel = DebugLevel.MYARRAYLIST; break;
            case 2: debugLevel = DebugLevel.FILE_PROCESSOR; break;
            case 1: debugLevel = DebugLevel.SEARCH_RESULTS; break;
            default: debugLevel = DebugLevel.ERROR; break;
	}
    }

    /**
     * Get the current debug level.
     * @return DebugLevel representing the current level.
     */
    public DebugLevel getDebugValue() {
        return debugLevel;
    }

    /**
     * Set the current debug level.
     */
    public static void setDebugValue(DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    /**
     * Output message if the current debug level matches the message debug level.
     */
    public static void writeMessage(String message, DebugLevel levelIn) {
	if (levelIn == debugLevel) System.out.println("[LOGGER] " + message);
    }

    /**
     * MyLogger toString representation, shows the current debug level.
     * @return String describing the current debug level.
     */
    public String toString() {
	return "The debug level has been set to the following " + debugLevel;
    }
}
