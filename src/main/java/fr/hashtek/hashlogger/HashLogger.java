package fr.hashtek.hashlogger;

import java.util.ArrayList;
import java.util.List;

public class HashLogger implements HashLoggable
{
	
	private final HashLoggable plugin;
	private final HashLoggerSettings settings;
	private final List<HashLog> history;


	/**
	 * Creates a new instance of HashLogger, with a
	 * minimum log level required for a log to be processed.
	 * 
	 * @param	logLevel	Log level
	 */
	public HashLogger(HashLoggable plugin, LogLevel logLevel)
	{
		this(plugin, logLevel, new ArrayList<HashLog>());
	}

	/**
	 * Creates a new instance of HashLogger, with a
	 * minimum log level required for a log to be processed.
	 *
	 * @param	logLevel	Log level
	 */
	public HashLogger(HashLoggable plugin, LogLevel logLevel, List<HashLog> historyHolder)
	{
		this.plugin = plugin;
		this.settings = new HashLoggerSettings(logLevel);
		this.history = historyHolder;

		this.info(this, "HashLogger initialized. Log level: " + this.settings.getLogLevel().getFullName());
	}
	

	/**
	 * General log (with exception).
	 * 
	 * @param	author		Log author
	 * @param	type		Log level type
	 * @param	message		Message to output
	 * @param	exception	Raised exception
	 */
	private void log(HashLoggable author, LogLevel type, String message, Exception exception)
	{
		HashLog log = new HashLog(this.plugin, author, type, message, exception);

		this.history.add(log);

		if (this.getSettings().getLogLevel().compareTo(type) <= 0)
			log.log(this.settings);
	}
	
	/**
	 * General log (without exception).
	 * 
	 * @param	author		Log author
	 * @param	type		Log level type
	 * @param	message		Message to output
	 */
	private void log(HashLoggable author, LogLevel type, String message)
	{
		this.log(author, type, message, null);
	}
	
	/**
	 * Debugging log.
	 * 
	 * @param	author		Log author
	 * @param	message		Message to output
	 */
	public void debug(HashLoggable author, String message)
	{
		this.log(author, LogLevel.DEBUG, message);
	}
	
	/**
	 * Information log.
	 * 
	 * @param	author		Log author
	 * @param	message		Message to output
	 */
	public void info(HashLoggable author, String message)
	{
		this.log(author, LogLevel.INFO, message);
	}
	
	/**
	 * Error log (with exception).
	 * 
	 * @param	author		Log author
	 * @param	message		Message to output
	 * @param	exception	Raised exception
	 */
	public void error(HashLoggable author, String message, Exception exception)
	{
		this.log(author, LogLevel.ERROR, message, exception);
	}
	
	/**
	 * Error log (without exception).
	 * 
	 * @param	author		Log author
	 * @param	message		Message to output
	 */
	public void error(HashLoggable author, String message)
	{
		this.log(author, LogLevel.ERROR, message);
	}
	
	/**
	 * Warning log.
	 * 
	 * @param	author		Log author
	 * @param	message		Message to output
	 */
	public void warning(HashLoggable author, String message)
	{
		this.log(author, LogLevel.WARNING, message);
	}
	
	/**
	 * Critical log (with exception).
	 * 
	 * @param	author		Log author
	 * @param	message		Message to output
	 * @param	exception	Raised exception
	 */
	public void critical(HashLoggable author, String message, Exception exception)
	{
		this.log(author, LogLevel.CRITICAL, message, exception);
	}
	
	/**
	 * Critical log (without exception).
	 * 
	 * @param	author		Log author
	 * @param	message		Message to output
	 */
	public void critical(HashLoggable author, String message)
	{
		this.log(author, LogLevel.CRITICAL, message);
	}
	
	/**
	 * Fatal log (with exception).
	 * 
	 * @param	author		Log author
	 * @param	message		Message to output
	 * @param	exception	Raised exception
	 */
	public void fatal(HashLoggable author, String message, Exception exception)
	{
		this.log(author, LogLevel.FATAL, message, exception);
	}
	
	/**
	 * Fatal log (without exception).
	 * 
	 * @param	author		Log author
	 * @param	message		Message to output
	 */
	public void fatal(HashLoggable author, String message)
	{
		this.log(author, LogLevel.FATAL, message);
	}

	/**
	 * Logs according to the given log level.
	 * Mainly used in HashError.
	 *
	 * @param	level		Log level
	 * @param	author		Log author
	 * @param	message		Message to output
	 * @param	exception	Raised exception
	 */
	public void logFromLevel(LogLevel level, HashLoggable author, String message, Exception exception)
	{
		switch (level) {
			case DEBUG:
				this.debug(author, message);
				break;
			case INFO:
				this.info(author, message);
				break;
			case ERROR:
				this.error(author, message, exception);
				break;
			case WARNING:
				this.warning(author, message);
				break;
			case CRITICAL:
				this.critical(author, message, exception);
				break;
			case FATAL:
				this.fatal(author, message, exception);
				break;
		}
	}

	/**
	 * Same as {@link HashLogger#logFromLevel(LogLevel, HashLoggable, String, Exception)}, but without exception.
	 *
	 * @param	level		Log level
	 * @param	author		Log author
	 * @param	message		Message to output
	 */
	public void logFromLevel(LogLevel level, HashLoggable author, String message)
	{
		this.logFromLevel(level, author, message, null);
	}

	/**
	 * @return	Logger's settings
	 */
	public HashLoggerSettings getSettings()
	{
		return this.settings;
	}

	/**
	 * @return	Logger's history
	 */
	public List<HashLog> getHistory()
	{
		return this.history;
	}

}