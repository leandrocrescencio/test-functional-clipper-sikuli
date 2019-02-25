package tasks;

import org.apache.log4j.Logger;

import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import commom.SikuliDisplay;
import commom.SikuliDriver;

public class AppAcessSikuliObject {

	private AppAcessSikuliObject() {
		throw new IllegalStateException("Utility class");
	}

	private static Screen screen = SikuliDriver.getDriver();
	private static final Logger LOGGER = Logger.getLogger(AppAcessSikuliObject.class);

	public static void optionSelect(String option) {
		try {
			SikuliDisplay.findElement("HELLO WORLD!! THIS IS YOUR CLIPPER APP");
			Thread.sleep(10);
			screen.write(option + Key.ENTER);
		}catch (Exception e) {
			LOGGER.error(e);
			LOGGER.warn(e.getMessage());
		}
	}
}
