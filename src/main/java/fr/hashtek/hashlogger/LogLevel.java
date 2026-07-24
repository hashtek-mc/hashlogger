package fr.hashtek.hashlogger;

public enum LogLevel
{
	
	DEBUG		("DEBUG",		"DBG",	"\u001B[36m",	"&3",		false),
	TRACE_R3	("TRACE_R3",	"TR3",	"\u001B[90m",	"&7",		false),
	TRACE_R2	("TRACE_R2",	"TR2",	"\u001B[90m",	"&7",		false),
	TRACE_R1	("TRACE_R1",	"TR1",	"\u001B[90m",	"&7",		false),
	INFO		("INFO",		"IFO",	"\u001B[0m",	"&f",		false),
	ERROR		("ERROR",		"ERR",	"\u001B[31m",	"&c",		true),
	WARNING		("WARNING",	"WRN",	"\u001B[33m",	"&e",		false),
	CRITICAL	("CRITICAL",	"CRT",	"\u001B[31m",	"&4",		true),
	FATAL		("FATAL",		"FTL",	"\u001B[41m",	"&4&l",	true);
	
	
	private final String fullName;
	private final String shortName;
	private final String color;
	private final String minecraftColor;
	private final boolean syserr;
	
	
	/**
	 * Creates a new instance of LogLevel.
	 * 
	 * @param	fullName	Log level's full name
	 * @param	shortName	Log level's shortened name
	 * @param	color		Log level's color
	 * @param	syserr		Is in system error output ?
	 */
	LogLevel(
		String fullName,
		String shortName,
		String color,
		String minecraftColor,
		boolean syserr
	)
	{
		this.fullName = fullName;
		this.shortName = shortName;
		this.color = color;
		this.minecraftColor = minecraftColor;
		this.syserr = syserr;
	}
	
	
	/**
	 * @return	Log level's full name
	 */
	public String getFullName()
	{
		return this.fullName;
	}
	
	/**
	 * @return	Log level's shortened name
	 */
	public String getShortName()
	{
		return this.shortName;
	}
	
	/**
	 * @return	Log level's color
	 */
	public String getColor()
	{
		return this.color;
	}

	/**
	 * @return	Log level's Minecraft color
	 */
	public String getMinecraftColor()
	{
		return this.minecraftColor;
	}
	
	/**
	 * @return	Is in system error output ?
	 */
	public boolean isInSysErr()
	{
		return this.syserr;
	}

}
