package fr.hashtek.hashlogger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HashLogger implements HashLoggable {
	
	/*
	 * TODO:
	 * - Log history (create a HashLog object)
	 */
	
	private HashLoggable plugin;
	private LogLevel logLevel;
	private boolean showTimestamp;
	private boolean shortDisplay;
	
	/*
	 * @param logLevel	Minimum log level required for a log to be processed
	 */
	public HashLogger(HashLoggable plugin, LogLevel logLevel)
	{
		this.plugin = plugin;
		this.logLevel = logLevel;
	
		this.info(this, "HashLogger initialized. Log level: " + this.logLevel.getFullName());
	}
	
	/*
	 * Creates a formatted string to output to the console.
	 * 
	 * @param author 	Log author
	 * @param type		Log level type
	 * @param message	Message to output
	 */
	private String createLogOutput(HashLoggable author, LogLevel type, String message)
	{
		String output = "[" + this.plugin.getClass().getSimpleName() + "] ";
		
		if (this.showTimestamp) {
			Date date = new Date();
			String formattedDate = new SimpleDateFormat("(MM-dd-yy HH:mm:ss.SSS) ").format(date);
			
			output += formattedDate;
		}
		
		output += "<";
		
		output += this.shortDisplay
			? type.getShortenedName()
			: type.getFullName();
		
		output += ".java";
		
		output += ": " + author.getClass().getSimpleName();

		output += "> ";

		output += message;
		return output;
	}
	
	/*
	 * Creates a formatted string to output to the console.
	 * Given exception error message will be outputted.
	 * This function must be only used for error logs.
	 * 
	 * @param author 		Log author
	 * @param type			Log level type
	 * @param message		Message to output
	 * @param exception		Raised exception
	 */
	private String createLogOutput(HashLoggable author, LogLevel type, String message, Exception exception)
	{
		String finalMessage = message;
		
		if (exception != null)
			finalMessage += "\n" + exception.getMessage();
		
		return createLogOutput(author, type, finalMessage);
	}
	
	/*
	 * General log (with exception).
	 * 
	 * @param author	Log author
	 * @param type		Log level type
	 * @param message	Message to output
	 * @param exception	Raised exception
	 */
	private void log(HashLoggable author, LogLevel type, String message, Exception exception)
	{
		String output = createLogOutput(author, type, message, exception);
		
		if (this.logLevel.compareTo(type) >= 0) {
			if (type.isInSysErr())
				System.err.println(output);
			else
				System.out.println(output);
		}
	}
	
	/*
	 * General log (without exception).
	 * 
	 * @param author	Log author
	 * @param type		Log level type
	 * @param message	Message to output
	 */
	private void log(HashLoggable author, LogLevel type, String message)
	{
		this.log(author, type, message, null);
	}
	
	/*
	 * Debugging log.
	 * 
	 * @param author 	Log author
	 * @param message	Message to output
	 */
	public void debug(HashLoggable author, String message)
	{
		this.log(author, LogLevel.DEBUG, message);
	}
	
	/*
	 * Information log.
	 * 
	 * @param author 	Log author
	 * @param message	Message to output
	 */
	public void info(HashLoggable author, String message)
	{
		this.log(author, LogLevel.INFO, message);
	}
	
	/*
	 * Error log (with exception).
	 * 
	 * @param author 	Log author
	 * @param message	Message to output
	 * @param exception	Raised exception
	 */
	public void error(HashLoggable author, String message, Exception exception)
	{
		this.log(author, LogLevel.ERROR, message, exception);
	}
	
	/*
	 * Error log (without exception).
	 * 
	 * @param author 	Log author
	 * @param message	Message to output
	 */
	public void error(HashLoggable author, String message)
	{
		this.log(author, LogLevel.ERROR, message);
	}
	
	/*
	 * Warning log.
	 * 
	 * @param author 	Log author
	 * @param message	Message to output
	 */
	public void warning(HashLoggable author, String message)
	{
		this.log(author, LogLevel.WARNING, message);
	}
	
	/*
	 * Critical log (with exception).
	 * 
	 * @param author 	Log author
	 * @param message	Message to output
	 * @param exception	Raised exception
	 */
	public void critical(HashLoggable author, String message, Exception exception)
	{
		this.log(author, LogLevel.CRITICAL, message, exception);
	}
	
	/*
	 * Critical log (without exception).
	 * 
	 * @param author 	Log author
	 * @param message	Message to output
	 */
	public void critical(HashLoggable author, String message)
	{
		this.log(author, LogLevel.CRITICAL, message);
	}
	
	/*
	 * Fatal log (with exception).
	 * 
	 * @param author 	Log author
	 * @param message	Message to output
	 * @param exception	Raised exception
	 */
	public void fatal(HashLoggable author, String message, Exception exception)
	{
		this.log(author, LogLevel.FATAL, message, exception);
	}
	
	/*
	 * Fatal log (without exception).
	 * 
	 * @param author 	Log author
	 * @param message	Message to output
	 */
	public void fatal(HashLoggable author, String message)
	{
		this.log(author, LogLevel.FATAL, message);
	}
	
	
	public LogLevel getLogLevel()
	{
		return this.logLevel;
	}
	
	public boolean doesShowTimestamp()
	{
		return this.showTimestamp;
	}
	
	public boolean doesDisplayShortly()
	{
		return this.shortDisplay;
	}
	
	public void setLogLevel(LogLevel logLevel)
	{
		this.logLevel = logLevel;
	}
	
	public void setShowTimestamp(boolean showTimestamp)
	{
		this.showTimestamp = showTimestamp;
	}
	
	public void setShortDisplay(boolean shortDisplay)
	{
		this.shortDisplay = shortDisplay;
	}

}
