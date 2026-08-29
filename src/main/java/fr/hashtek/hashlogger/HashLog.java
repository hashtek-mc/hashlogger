package fr.hashtek.hashlogger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HashLog {

    private final Date createdAt;
    private final HashLoggable instance;
    private final HashLoggable author;
    private final LogLevel logLevel;
    private final String log;
    private final Exception exception;

    public HashLog(HashLoggable instance, HashLoggable author, LogLevel logLevel, String log) {
        this(instance, author, logLevel, log, null);
    }

    public HashLog(HashLoggable instance, HashLoggable author, LogLevel logLevel, String log, Exception exception) {
        this.createdAt = new Date();
        this.instance = instance;
        this.author = author;
        this.logLevel = logLevel;
        this.log = log;
        this.exception = exception;
    }

    private String createLog(HashLoggerSettings settings) {
        StringBuilder logBuilder = new StringBuilder();
        String logName = settings.doesDisplayShortly() ? logLevel.getShortName() : logLevel.getFullName();

        if (settings.doesShowTimestamp()) {
            String timestamp = new SimpleDateFormat(" (MM-dd-yy HH:mm:ss.SSS)").format(createdAt);
            logBuilder.append(timestamp);
        }

        logBuilder.append(String.format("[%s: %s.java] %s<%s>%s ", 
            instance.getClass().getSimpleName(), 
            author.getClass().getSimpleName(), 
            logLevel.getColor(), 
            logName, 
            LogLevel.INFO.getColor()));

        logBuilder.append(log);

        if (exception != null) {
            logBuilder.append("\n").append(exception.getMessage());
        }

        return logBuilder.toString();
    }

    public void log(HashLoggerSettings settings) {
        String output = createLog(settings);
        if (logLevel.isInSysErr()) {
            System.err.println(output);
        } else {
            System.out.println(output);
        }
    }

    public Date getCreatedAt() {
        return new Date(createdAt.getTime());
    }

    public HashLoggable getInstance() {
        return instance;
    }

    public HashLoggable getAuthor() {
        return author;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getLog() {
        return log;
    }
}
