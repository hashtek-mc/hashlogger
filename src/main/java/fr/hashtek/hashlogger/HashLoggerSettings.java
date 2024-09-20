package fr.hashtek.hashlogger;

public class HashLoggerSettings implements HashLoggable
{

    private LogLevel logLevel;
    private boolean shortDisplay;
    private boolean showTimestamp;


    /**
     * Creates a new instance of HashLoggerSettings.
     *
     * @param   logLevel   Minimum log level for log to be outputted
     */
    public HashLoggerSettings(LogLevel logLevel)
    {
        this(logLevel, false, false);
    }

    /**
     * Creates a new instance of HashLoggerSettings.
     *
     * @param   logLevel        Minimum log level for log to be outputted
     * @param   shortDisplay    Should HashLogger use short names?
     * @param   showTimestamp   Should HashLogger output timestamps?
     */
    public HashLoggerSettings(LogLevel logLevel, boolean shortDisplay, boolean showTimestamp)
    {
        this.showTimestamp = showTimestamp;
        this.shortDisplay = shortDisplay;
        this.logLevel = logLevel;
    }


    /**
     * Displays the settings in the console.
     *
     * @param   logger  Logger
     */
    public void displaySettings(HashLogger logger)
    {
        final String output =
            "\tLog level: " + this.logLevel.getFullName() + "\n" +
            "\tShort display: " + this.shortDisplay + "\n" +
            "\tShow timestamp: " + this.showTimestamp + "\n";

        logger.info(this, "HashLogger settings:\n" + output);
    }


    /**
     * @return	Does logger logs timestamps
     */
    public boolean doesShowTimestamp()
    {
        return this.showTimestamp;
    }

    /**
     * @return	Does logger use short names
     */
    public boolean doesDisplayShortly()
    {
        return this.shortDisplay;
    }

    /**
     * @return	Logger's log level
     */
    public LogLevel getLogLevel()
    {
        return this.logLevel;
    }


    /**
     * Make the logger log timestamps.
     *
     * @param	showTimestamp	Show timestamp
     * @return	Returns itself.
     */
    public HashLoggerSettings setShowTimestamp(boolean showTimestamp)
    {
        this.showTimestamp = showTimestamp;
        return this;
    }

    /**
     * Make the logger log shortly.
     *
     * @param	shortDisplay	Short display
     * @return	Returns itself.
     */
    public HashLoggerSettings setShortDisplay(boolean shortDisplay)
    {
        this.shortDisplay = shortDisplay;
        return this;
    }

    public HashLoggerSettings setLogLevel(LogLevel logLevel)
    {
        this.logLevel = logLevel;
        return this;
    }

}
