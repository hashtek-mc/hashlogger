package fr.hashtek.hashlogger;

import io.github.cdimascio.dotenv.Dotenv;

public class HashLogger implements HashLoggable
{
	
	private final HashLoggable plugin;
	private final HashLoggerSettings settings;


	/**
	 * Creates a new instance of HashLogger, with a
	 * minimum log level required for a log to be processed.
	 *
	 * @param 	plugin		Logger "user"
	 * @param	logLevel	Log level
	 */
	public HashLogger(HashLoggable plugin, LogLevel logLevel)
	{
		this(plugin, logLevel, false, false);
	}

	/**
	 * Creates a new instance of HashLogger, with full settings.
	 *
	 * @param	plugin			Logger "user"
	 * @param	logLevel		Log level
	 * @param	shortDisplay	Short display
	 * @param	showTimestamp	Timestamp display
	 */
	public HashLogger(
		HashLoggable plugin,
		LogLevel logLevel,
		boolean shortDisplay,
		boolean showTimestamp
	)
	{
		this.plugin = plugin;
		this.settings = new HashLoggerSettings(logLevel, shortDisplay, showTimestamp);

		this.info(this, "HashLogger initialized.");
		this.settings.displaySettings(this);
	}


	/**
	 * Creates a new instance of HashLogger, with the settings
	 * written in an environment variable file.
	 *
	 * @param	plugin	Logger "user"
	 * @param	env		Environment variable file
	 * @return	Created HashLogger
	 * @throws	NullPointerException		Key not found
	 * @throws	IllegalArgumentException	Value not valid
	 */
	public static HashLogger fromEnvConfig(
		HashLoggable plugin,
		Dotenv env
	)
		throws NullPointerException, IllegalArgumentException
	{
		final String envLogLevel = env.get("HL_LOG_LEVEL");
		final String envShortDisplay = env.get("HL_SHORT_LOG");
		final String envShowTimestamp = env.get("HL_TIMESTAMP");

		final LogLevel logLevel = LogLevel.valueOf(envLogLevel);
		final boolean shortDisplay = Boolean.parseBoolean(envShortDisplay);
		final boolean showTimestamp = Boolean.parseBoolean(envShowTimestamp);

		return new HashLogger(
			plugin,
			logLevel,
			shortDisplay,
			showTimestamp
		);
	}

	/**
	 * General log (with exception).
	 * 
	 * @param	author		Log author
	 * @param	type		Log level type
	 * @param	message		Message to output
	 * @param	exception	Raised exception
	 */
	private void log(
		HashLoggable author,
		LogLevel type,
		String message,
		Exception exception
	)
	{
		HashLog log = new HashLog(this.plugin, author, type, message, exception);

		if (this.getSettings().getLogLevel().compareTo(type) <= 0) {
			log.log(this.settings);
		}
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
	public void logFromLevel(
		LogLevel level,
		HashLoggable author,
		String message,
		Exception exception
	)
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
	 * Same as {@link HashLogger#logFromLevel(LogLevel, HashLoggable, String, Exception)},
	 * but without exception.
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

}
