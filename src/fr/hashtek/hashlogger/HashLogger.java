package fr.hashtek.hashlogger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HashLogger {
	
	private LogLevel logLevel;
	private boolean showTimestamp;
	private boolean shortDisplay;
	
	/*
	 * @param logLevel	Minimum log level required for a log to be processed
	 */
	public HashLogger(LogLevel logLevel)
	{
		this.logLevel = logLevel;
	}
	
	/*
	 * Creates a formatted string to output to the console.
	 * 
	 * @param type			Log level type
	 * @param filenameLog 	Author's filename
	 * @param message		Message to output
	 */
	private String createLogOutput(LogLevel type, String filename, String message)
	{
		String output = "";
		
		if (this.showTimestamp) {
			Date date = new Date();
			String formattedDate = new SimpleDateFormat("MM-dd-yy HH:mm:ss.SSS ").format(date);
			
			output += formattedDate;
		}
		
		output += "[";
		
		output += this.shortDisplay
			? type.getShortenedName()
			: type.getFullName();
		
		if (type == LogLevel.DEBUG && !filename.isEmpty())
			output += ": " + filename;

		output += "] ";

		output += message;
		return output;
	}
	
	/*
	 * Creates a formatted string to output to the console.
	 * Filename is not needed here.
	 * 
	 * @param type			Log level type
	 * @param message		Message to output
	 */
	private String createLogOutput(LogLevel type, String message)
	{
		return createLogOutput(type, "", message);
	}
	
	/*
	 * Creates a formatted string to output to the console.
	 * Given exception error message will be outputted.
	 * This function must be only used for error logs.
	 * 
	 * @param type			Log level type
	 * @param message		Message to output
	 * @param exception		Raised exception
	 */
	private String createLogOutput(LogLevel type, String message, Exception exception)
	{
		String finalMessage = message;
		
		if (exception != null)
			finalMessage += "\n" + exception.getMessage();
		
		return createLogOutput(type, "", finalMessage);
	}
	
	/*
	 * Debugging log.
	 * 
	 * @param filename	Author's filename
	 * @param message	Message to output
	 */
	public void debug(String filename, String message)
	{
		LogLevel type = LogLevel.DEBUG;
		String output = createLogOutput(type, filename, message);
		
		if (this.logLevel.compareTo(type) >= 0)
			System.out.println(output);
	}
	
	/*
	 * Information log.
	 * 
	 * @param message	Message to output
	 */
	public void info(String message)
	{
		LogLevel type = LogLevel.INFO;
		String output = createLogOutput(type, message);
		
		if (this.logLevel.compareTo(type) >= 0)
			System.out.println(output);
	}
	
	/*
	 * Error log (with exception).
	 * 
	 * @param message	Message to output
	 * @param exception	Raised exception
	 */
	public void error(String message, Exception exception)
	{
		LogLevel type = LogLevel.ERROR;
		String output = createLogOutput(type, message, exception);
		
		if (this.logLevel.compareTo(type) >= 0)
			System.err.println(output);
	}
	
	/*
	 * Error log (without exception).
	 * 
	 * @param message	Message to output
	 */
	public void error(String message)
	{
		this.error(message, null);
	}
	
	/*
	 * Warning log.
	 * 
	 * @param message	Message to output
	 */
	public void warning(String message)
	{
		LogLevel type = LogLevel.WARNING;
		String output = createLogOutput(type, message);
		
		if (this.logLevel.compareTo(type) >= 0)
			System.out.println(output);
	}
	
	/*
	 * Critical log (with exception).
	 * 
	 * @param message	Message to output
	 * @param exception	Raised exception
	 */
	public void critical(String message, Exception exception)
	{
		LogLevel type = LogLevel.CRITICAL;
		String output = createLogOutput(type, message, exception);
		
		if (this.logLevel.compareTo(type) >= 0)
			System.err.println(output);
	}
	
	/*
	 * Critical log (without exception).
	 * 
	 * @param message	Message to output
	 */
	public void critical(String message)
	{
		this.critical(message, null);
	}
	
	/*
	 * Fatal log (with exception).
	 * 
	 * @param message	Message to output
	 * @param exception	Raised exception
	 */
	public void fatal(String message, Exception exception)
	{
		LogLevel type = LogLevel.FATAL;
		String output = createLogOutput(type, message, exception);
		
		if (this.logLevel.compareTo(type) >= 0)
			System.err.println(output);
	}
	
	/*
	 * Fatal log (without exception).
	 * 
	 * @param message	Message to output
	 */
	public void fatal(String message)
	{
		this.fatal(message, null);
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
