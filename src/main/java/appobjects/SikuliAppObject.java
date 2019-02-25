package appobjects;

import org.sikuli.script.Pattern;

import utils.StaticValues;

public class SikuliAppObject {

	/**
	 * Busca a tela inicial pelos pixels
	 * @return Tela Inicial Legado
	 */
	private static String appPath;

	private static Pattern getAppWin7() {
		appPath = "/src/test/resources/objects/app_win7.png";
		return new Pattern(StaticValues.PATH_PROJECT + appPath);
	}

	private static Pattern getAppWin10() {
		appPath = "/src/test/resources/objects/app_win10.png";
		return new Pattern(StaticValues.PATH_PROJECT + appPath);
	}

	private static Pattern getAppWin2016() {
		appPath = "/src/test/resources/objects/app_2016.png";
		return new Pattern(StaticValues.PATH_PROJECT + appPath);
	}

	public static Pattern getApp() {
		switch (StaticValues.OS) {
		case "Windows 10":
			return getAppWin10();
		case "Windows Server 2016":
			return getAppWin2016();
		default:
			return getAppWin7();
		}
	}
}