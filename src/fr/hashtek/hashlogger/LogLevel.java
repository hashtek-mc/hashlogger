package fr.hashtek.hashlogger;

public enum LogLevel {
	
	DEBUG    ("DEBUG",    "DBG", "\u001B[36m"),
	INFO     ("INFO",     "IFO", ""),
	ERROR    ("ERROR",    "ERR", "\u001B[31m"),
	WARNING  ("WARNING",  "WRN", "\u001B[33m"),
	CRITICAL ("CRITICAL", "CRT", "\u001B[31m"),
	FATAL    ("FATAL",    "FTL", "\u001B[31m");
	
	
	private String fullName;
	private String shortednedName;
	private String color;
	
	
	LogLevel(String fullName, String shortenedName, String color)
	{
		this.fullName = fullName;
		this.shortednedName = shortenedName;
		this.color = color;
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

}
