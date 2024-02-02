package fr.hashtek.hashlogger;

public enum LogLevel {
	
	DEBUG		("DEBUG",		"DBG",	"\u001B[36m",	false),
	INFO		("INFO",		"IFO",	"",				false),
	ERROR		("ERROR",		"ERR",	"\u001B[31m",	true),
	WARNING		("WARNING",		"WRN",	"\u001B[33m",	false),
	CRITICAL	("CRITICAL",	"CRT",	"\u001B[31m",	true),
	FATAL		("FATAL",		"FTL",	"\u001B[31m",	true);
	
	
	private String fullName;
	private String shortednedName;
	private String color;
	private boolean syserr;
	
	
	LogLevel(String fullName, String shortenedName, String color, boolean syserr)
	{
		this.fullName = fullName;
		this.shortednedName = shortenedName;
		this.color = color;
		this.syserr = syserr;
	}
	
	
	public String getFullName()
	{
		return this.fullName;
	}
		
	public String getShortenedName()
	{
		return this.shortednedName;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public boolean isInSysErr()
	{
		return this.syserr;
	}

}
