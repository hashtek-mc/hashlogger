package fr.hashtek.hashlogger;

public class HashLoggerSettings
{


    private LogLevel logLevel;
    private boolean showTimestamp;
    private boolean shortDisplay;


    public HashLoggerSettings(LogLevel level)
    {
        this(false, false, level);
    }

    public HashLoggerSettings(boolean showTimestamp, boolean shortDisplay, LogLevel logLevel)
    {
        this.showTimestamp = showTimestamp;
        this.shortDisplay = shortDisplay;
        this.logLevel = logLevel;
    }


    /**
     * @return	Does logger logs timestamps
     */
    public boolean doesShowTimestamp()
    {
        return this.showTimestamp;
    }

    /**
     * @return	Does logger logs shortly
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
