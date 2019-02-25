package utils;

public class StaticValues {

	private StaticValues() {
		throw new IllegalStateException("Utility class");
	}

	public static final String START_COMMAND = "cmd /c start ";

	public static final String PATH_PROJECT = System.getProperty("user.dir");

	public static final String CMD = "C:/Windows/system32/cmd.exe";

	public static final String OS = System.getProperty("os.name");

	public static final int TIMEOUT = 20;
	
	protected static String testname;

	public static String getTestName() {
		return testname;
	}

	public static void setTestName(String className) {
		testname = className;
	}

		
}
