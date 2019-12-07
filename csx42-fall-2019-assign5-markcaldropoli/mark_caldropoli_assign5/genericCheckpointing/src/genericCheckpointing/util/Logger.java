package genericCheckpointing.util;

/**
 * Logger Class
 * @author Mark Caldropoli
 */
public class Logger {
    public static enum DebugLevel{ ERROR, DATA };
    private static DebugLevel debugLevel;
    private static int level;

    /**
     * Set the current debug level.
     */
    public static void setDebugValue(int levelIn) {
        level = levelIn;
	switch (levelIn) {
            case 1: debugLevel = DebugLevel.DATA; break;
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
    public static void setDebugValue(DebugLevel debugIn) {
        if(debugIn == DebugLevel.DATA) level = 1;
        else level = 0;
	debugLevel = debugIn;
    }

    /**
     * Output message if the current debug level matches the message debug level.
     */
    public static void writeMessage(String message, DebugLevel debugIn) {
        int levelIn;
        if(debugIn == DebugLevel.DATA) levelIn = 1;
        else levelIn = 0;
	if(levelIn <= level) System.out.println("[LOGGER] " + message);
    }

    /**
     * Logger toString representation, shows the current debug level.
     * @return String describing the current debug level.
     */
    public String toString() {
	return "[LOGGER] The debug level is set to [" + level + ":" + debugLevel + "]";
    }
}
