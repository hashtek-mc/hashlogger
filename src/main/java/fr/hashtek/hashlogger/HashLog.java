package fr.hashtek.hashlogger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HashLog
{

    private final Date createdAt;
    private final HashLoggable instance;
    private final HashLoggable author;
    private final LogLevel logLevel;
    private final String log;
    private final Exception exception;


    /**
     * Creates a new instance of HashLog
     * (without exception)
     *
     * @param   instance    HashLogger instance
     * @param   author      Log author
     * @param   logLevel    Log's level
     * @param   log         Message to output
     */
    public HashLog(
        HashLoggable instance,
        HashLoggable author,
        LogLevel logLevel,
        String log
    )
    {
        this(instance, author, logLevel, log, null);
    }

    /**
     * Creates a new instance of HashLog.
     *
     * @param   instance    HashLogger instance
     * @param   author      Log author
     * @param   logLevel    Log's level
     * @param   log         Message to output
     * @param   exception   Exception
     */
    public HashLog(
        HashLoggable instance,
        HashLoggable author,
        LogLevel logLevel,
        String log,
        Exception exception
    )
    {
        this.createdAt = new Date();
        this.instance = instance;
        this.author = author;
        this.logLevel = logLevel;
        this.log = log;
        this.exception = exception;
    }


    /**
     * Creates a formatted string to output to the console.
     *
     * @param	settings    Logger's settings
     * @return	Formatted string
     */
    private String createLog(HashLoggerSettings settings)
    {
        String date = "";

        String logName = settings.doesDisplayShortly()
            ? this.logLevel.getShortName()
            : this.logLevel.getFullName();

        String exceptionMessage = "";

        if (settings.doesShowTimestamp()) {
            date = new SimpleDateFormat(" (MM-dd-yy HH:mm:ss.SSS)").format(this.createdAt);
        }

        if (this.exception != null) {
            exceptionMessage = "\n" + this.exception.getMessage();
        }

        return String.format(
            "[%s: %s.java]%s %s<%s>%s %s%s",
            this.instance.getClass().getSimpleName(),
            this.author.getClass().getSimpleName(),
            date,
            this.logLevel.getColor(),
            logName,
            LogLevel.INFO.getColor(),
            this.log,
            exceptionMessage
        );
    }

    /**
     * Logs itself to the console, according to HashLogger's settings.
     *
     * @param settings  HashLogger's settings
     */
    public void log(HashLoggerSettings settings)
    {
        String output = this.createLog(settings);

        if (this.getLogLevel().isInSysErr()) {
            System.err.println(output);
        } else {
            System.out.println(output);
        }
    }


    /**
     * @return  Log's creation date.
     */
    public Date getCreatedAt()
    {
        return this.createdAt;
    }

    /**
     * @return  Log's author
     */
    public HashLoggable getInstance()
    {
        return this.instance;
    }

    /**
     * @return  Log's author
     */
    public HashLoggable getAuthor()
    {
        return this.author;
    }

    /**
     * @return  Log's level
     */
    public LogLevel getLogLevel()
    {
        return this.logLevel;
    }

    /**
     * @return  Log's message
     */
    public String getLog()
    {
        return this.log;
    }

}
