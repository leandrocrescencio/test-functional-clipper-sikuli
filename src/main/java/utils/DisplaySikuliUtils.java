package utils;

import org.apache.log4j.Logger;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import commom.SikuliDriver;

public class DisplaySikuliUtils {

	private static final Logger LOGGER = Logger.getLogger(DisplaySikuliUtils.class);
	private static Screen screen = SikuliDriver.getDriver();
	private static boolean statusKey = true;

	
	public static void selectQuick() {
		try {
			screen.keyDown(Key.CTRL);
			screen.write("m");
			screen.keyUp(Key.CTRL);
			screen.write(Key.PAGE_UP);
			screen.write(Key.HOME);
			screen.keyDown(Key.SHIFT);
			screen.write(Key.PAGE_DOWN);
			screen.write(Key.END);
			screen.keyUp(Key.SHIFT);
	
		} catch (Exception e) {
			LOGGER.error(e);
			LOGGER.warn(e.getMessage());
		}
	}

	public static void selectWin7() {
		try {
			screen.keyDown(Key.ALT);
			screen.type(Key.SPACE);
			screen.keyUp(Key.ALT);
			screen.write("e");
			screen.write(Key.ENTER);
			screen.keyDown(Key.SHIFT);
			screen.write(Key.PAGE_DOWN);

			for(int cont=0; cont<80; cont++) {
				screen.delayType(0);
				screen.type(Key.RIGHT);
			}
			
			screen.keyUp(Key.SHIFT);
			
		} catch (Exception e) {
			LOGGER.error(e);
			LOGGER.warn(e.getMessage());
		}
	}
	
	public static void statusNumLock() {
		if (Key.isLockOn(Key.C_NUM_LOCK) && statusKey) {
			screen.type(Key.NUM_LOCK);
			statusKey = false;
		}
		if (Key.isLockOn(Key.C_CAPS_LOCK)) {
			screen.type(Key.CAPS_LOCK);
			statusKey = false;
		}
	}
	


	
}