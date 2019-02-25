package testcases;

import org.testng.annotations.Test;

import commom.BaseSikuliTest;
import tasks.AppAcessSikuliObject;

public class SikuliTest extends BaseSikuliTest {

	private static String option = "1";

	@Test(priority = 0, description = "Test 01.")
	public void Firsttest() throws Exception {
		AppAcessSikuliObject.optionSelect(option);
		
	}

}