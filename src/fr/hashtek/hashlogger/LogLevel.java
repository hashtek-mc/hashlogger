package fr.hashtek.hashlogger;

public enum LogLevel {
	
	DEBUG		("DEBUG",		"DBG",	"\u001B[36m",	false),
	INFO		("INFO",		"IFO",	"\u001B[0m",	false),
	ERROR		("ERROR",		"ERR",	"\u001B[31m",	true),
	WARNING		("WARNING",		"WRN",	"\u001B[33m",	false),
	CRITICAL	("CRITICAL",	"CRT",	"\u001B[31m",	true),
	FATAL		("FATAL",		"FTL",	"\u001B[31m",	true);
	
	
	private final String fullName;
	private final String shortenedName;
	private final String color;
	private final boolean syserr;
	
	
	/**
	 * Creates a new instance of LogLevel.
	 * 
	 * @param	fullName		Log level's full name
	 * @param	shortenedName	Log level's shortened name
	 * @param	color			Log level's color
	 * @param	syserr			Is in system error output ?
	 */
	LogLevel(String fullName, String shortenedName, String color, boolean syserr)
	{
		this.fullName = fullName;
		this.shortenedName = shortenedName;
		this.color = color;
		this.syserr = syserr;
	}
	
	
	/**
	 * Returns the full name of the log level.
	 * 
	 * @return	Log level's full name
	 */
	public String getFullName()
	{
		return this.fullName;
	}
	
	/**
	 * Returns the shortened of the log level.
	 * 
	 * @return	Log level's shortened name
	 */
	public String getShortenedName()
	{
		return this.shortenedName;
	}
	
	/**
	 * Returns the color of the log level.
	 * 
	 * @return	Log level's color
	 */
	public String getColor()
	{
		return this.color;
	}
	
	/**
	 * Returns true if the log level aims to be in
	 * error output.
	 * 
	 * @return	Is in system error output ?
	 */
	public boolean isInSysErr()
	{
		return this.syserr;
	}

}
