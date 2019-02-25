package utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import commom.SikuliDriver;

public class ScreenShotUtils {

	private ScreenShotUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	private static String base64Encoded = "";
	private static final Logger LOGGER = Logger.getLogger(ScreenShotUtils.class);

	public static String captureScreenShot() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedImage image;
		try {
			image = new Robot().createScreenCapture(new Rectangle(0,0,2*SikuliDriver.getLocation().x,2*SikuliDriver.getLocation().y));
			ImageIO.write(image, "PNG", baos);
			base64Encoded = Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch (AWTException | IOException e) {
			LOGGER.warn(e.getMessage());
		}
		return "data:image/png;base64," + base64Encoded;
	}
}