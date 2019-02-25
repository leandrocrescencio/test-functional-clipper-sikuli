package commom;

import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Screen;

import appobjects.SikuliAppObject;

public class SikuliDriver {


	private static final Logger LOGGER = Logger.getLogger(SikuliDriver.class);
	
	
	private SikuliDriver() {
		throw new IllegalStateException("Utility class");
	}

	private static Screen screen;
	private static Location local;

	public static Screen getDriver() {
		if (screen == null) {
			screen = new Screen();
		}
		return screen;
	}

	public static Location getLocation() {
		try {
			if (local == null) {
				local = getDriver().find(SikuliAppObject.getApp()).getTarget();
			}
		} catch (FindFailed e) {
			LOGGER.error(e);
		}
		return local;
	}
}
