package inventory_vtiger_login;

import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Genric_Utility.DataProviderUtility;
import Genric_Utility.ScreenshotUtility;
import Object_repo.LoginPage;


public class LoginTest extends BaseClass {
	
	//Verify that the user can log in and log out successfully
	@Test(priority = 1,groups = {"Smoke,FT"})
	public void TC01() throws Exception {
		login();
		logout();
	}
	
	// Verify that an error message is displayed for invalid login credentials
    @Test(priority = 2,groups = {"FT","Neg"},dataProvider = "loginDetails",dataProviderClass = DataProviderUtility.class)
    public void invalidLoginTest(String username, String password) throws Exception {
        LoginPage l = new LoginPage(d);
        l.login(username, password);
        l.getErrorMessage();
        Reporter.log("Invalid login validation completed", true);
        ScreenshotUtility.takeScreenshot(d, "InvalidLogin");
    }
}
