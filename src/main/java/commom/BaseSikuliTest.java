package commom;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.sikuli.basics.Settings;
import org.sikuli.script.Screen;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;

import appobjects.SikuliAppObject;
import utils.DisplaySikuliUtils;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.PropertiesUtils;
import utils.StaticValues;

public class BaseSikuliTest {

	public Screen driver = SikuliDriver.getDriver();
	public Properties propertie = new Properties();
	
	private static final Logger LOGGER = Logger.getLogger(BaseSikuliTest.class);

	/**
	 * Abre um novo cmd utilizando o .bat do legado
	 * @throws Exception
	 */
	@BeforeClass(alwaysRun=true)
	public void setUp() {	
		try {
			BasicConfigurator.configure();
			init();
			Runtime.getRuntime().exec(StaticValues.START_COMMAND +
					StaticValues.PATH_PROJECT + 
					PropertiesUtils.getValue("ambiente"));
			driver.exists(SikuliAppObject.getApp());
			DisplaySikuliUtils.statusNumLock();
		} catch (Exception e) {
			LOGGER.error(e);
			LOGGER.warn(e.getMessage());
		}
	}
	
	public static void init() {
		Settings.ActionLogs = false;
		Settings.InfoLogs = false;
		Settings.MoveMouseDelay = 0.1f;
		Settings.SlowMotionDelay = 0.1f;
		Settings.DebugLogs = true;
		Settings.OcrTextRead = true;
		Settings.OcrTextSearch = true;

	}
	
	@BeforeSuite(alwaysRun = true)
	public void extentSetup(ITestContext context) {
		if (!context.getName().contains("Default") && !context.getName().contains("Surefire")) {
			StaticValues.setTestName(context.getName());
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {
		if (StaticValues.getTestName().equalsIgnoreCase("teste")) {
			StaticValues.setTestName(this.getClass().getSimpleName());
		}
		ExtentTestManager.startTest(method.getName(),StaticValues.getTestName());
		if(!ExtentTestManager.getTest().getDescription().isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.INFO, ExtentTestManager.getTest().getDescription());	
		}
	}

	@AfterMethod(alwaysRun = true)
	protected void afterMethod(ITestResult result) {
		for (String group : result.getMethod().getGroups()) {
			ExtentTestManager.getTest().assignCategory(group);  // new
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "Test pulou " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Teste Passou");
		} else if (ExtentTestManager.getTest().getRunStatus().equals(LogStatus.UNKNOWN)) {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "This test method is skipped");
		} else {
			ExtentTestManager.endTest();
		}  
		ExtentManager.getReporter(StaticValues.getTestName()).endTest(ExtentTestManager.getTest());        
		ExtentManager.getReporter(StaticValues.getTestName()).flush();
	}

	protected String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}

	@AfterClass(alwaysRun=true)
	public void tearDown() throws InterruptedException {
		SikuliDisplay.killCmdProcess();
		SikuliDisplay.closeCMD();
		Thread.sleep(6000);
	}
}