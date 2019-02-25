package commom;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.relevantcodes.extentreports.LogStatus;

import exceptions.ItemNotFoundException;
import exceptions.RequestFailedException;
import exceptions.TimeoutException;
import utils.DisplaySikuliUtils;
import utils.ExtentTestManager;
import utils.ScreenShotUtils;
import utils.StaticValues;

public class SikuliDisplay {

	public static final Screen driver = SikuliDriver.getDriver();
	private SikuliDisplay() {
		throw new IllegalStateException("Utility class");
	}

	private static final Logger LOGGER = Logger.getLogger(SikuliDisplay.class);
	private static Screen screen = SikuliDriver.getDriver();

	private static void selectAll() {
		try {
			Thread.sleep(850);
			screen.click(SikuliDriver.getLocation());
			if (StaticValues.OS.contains("7")) {
				DisplaySikuliUtils.selectWin7();
			}else {
				DisplaySikuliUtils.selectQuick();
			}
			screen.write(Key.ENTER);
		} catch (Exception e) {
			throw new RequestFailedException("Selection not allowed");
		}
	}

	private static String getText() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			return clipboard.getData(DataFlavor.stringFlavor).toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public static boolean findElement(String texto) {
		int cont = StaticValues.TIMEOUT;
		try {
			while (cont > 0) {
				selectAll();
				if (getText().contains(texto)) {
					ExtentTestManager.getTest().log(LogStatus.INFO, texto);
					ExtentTestManager.getTest().log(LogStatus.INFO, ExtentTestManager.getTest().addBase64ScreenShot(ScreenShotUtils.captureScreenShot()));
					return true;
				}
				cont--;
			}
		}catch(Exception e) {
			LOGGER.warn("Elemento nao encontrado: " + texto);
			throw new ItemNotFoundException(e);
		}
		LOGGER.warn("Elemento nao encontrado: " + texto);
		throw new ItemNotFoundException(texto);
	}

	public static boolean existElement(String texto) throws InterruptedException {
		int cont = 1;
		boolean exists = false;

		Thread.sleep(2000);

		while (cont > 0) {
			selectAll();
			if (getText().contains(texto)) {
				exists = true;
				ExtentTestManager.getTest().log(LogStatus.INFO, texto);
				return exists;
			}
			cont--;
		}
		return exists;
	}

	public static void killCmdProcess() {
		try {
			BufferedReader user = new BufferedReader(
					new InputStreamReader(Runtime.getRuntime().exec("whoami").getInputStream()));
			String usuario = user.readLine();
			BufferedReader taskkill = new BufferedReader(new InputStreamReader(Runtime.getRuntime()
					.exec("taskkill /IM cmd.exe /FI \"USERNAME eq " + usuario + "\" /f").getInputStream()));
			taskkill.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new TimeoutException(e.getMessage());

		}
	}

	public static void closeCMD() {
		if(StaticValues.OS.contains("7")) {
			driver.keyDown(Key.CTRL);
			driver.write("C");
			driver.keyUp(Key.CTRL);
			driver.write("Y");
		}
		driver.keyDown(Key.ALT);
		driver.write(Key.F4);
		driver.keyUp(Key.ALT);
	}

}
