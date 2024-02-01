package fr.hashtek.hashlogger;

public class HashLogger {
	
	@SuppressWarnings("unused")
	private static final String FILENAME = "HashLogger.java";
	
	public static void info(String string)
	{
		System.out.println(string);
	}
	
	public static void debug(String filename, String string)
	{
		System.out.println("DEBUG (" + filename + ") : " + string);
	}
	
	public static void err(String filename, String string)
	{
		System.err.println("ERROR (" + filename + ") : " + string);
	}
	
	public static void err(String filename, String string, Exception exception)
	{
		HashLogger.err(filename, string);
		HashLogger.err(filename, exception.getMessage());
	}

}
