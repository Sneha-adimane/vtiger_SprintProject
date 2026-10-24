package BaseClass_Utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Genric_Utility.PropertyFile;
import Genric_Utility.WebDriver_utility;
import Pom_utilities.LoginPage;
import Pom_utilities.LogoutPage;

public class BaseClass {

	public WebDriver d;
    
    @BeforeSuite(alwaysRun = true)
    public void bs() {
        Reporter.log("Open Database", true);
    }

    @AfterSuite(alwaysRun = true)
    public void as() {
        Reporter.log("Close Database connectivity", true);
    }

    @BeforeTest(alwaysRun = true)
    public void bt() {
        Reporter.log("PreCondition", true);
    }

    @AfterTest(alwaysRun = true)
    public void at() {
        Reporter.log("PostCondition", true);
    }

    @BeforeMethod (alwaysRun = true)
    @Parameters("browser")
    public void bc(@Optional("chrome") String browser) throws IOException, InterruptedException {
        Reporter.log("Opening browser", true);
        System.out.println("Browser received: " + browser);
        d = WebDriver_utility.launchbrowser(browser);
        d.get(PropertyFile.getDataFromProperty("url"));
        Thread.sleep(2000);
        Reporter.log("Application launched", true);
    }

    @AfterMethod(alwaysRun = true)
    public void ac() {
        Reporter.log("Test completed", true);
        if (d != null) {
            try {
                d.quit();
                Reporter.log("Browser closed successfully", true);
            } catch (Exception e) {
                Reporter.log("Error while closing browser: " + e.getMessage(),true);
            } finally {
                d = null;
            }
        }
    }
    
    public void login() throws Exception {
    	LoginPage l = new LoginPage(d);
    	l.getUn(PropertyFile.getDataFromProperty("UN"));
    	l.getPw(PropertyFile.getDataFromProperty("PW"));
    	l.getLoginBtn();
    }
    
    public void logout() {
    	LogoutPage l = new LogoutPage(d);
    	l.getBtn();
    	l.getSignoutbtn();
    }
}
