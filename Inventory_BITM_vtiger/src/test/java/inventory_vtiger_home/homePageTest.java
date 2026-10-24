package inventory_vtiger_home;

import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Genric_Utility.ScreenshotUtility;
import Pom_utilities.HomePage;

public class homePageTest extends BaseClass {

	@Test(priority = 1,groups = "Smoke") 
	public void TC01() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.verifyHome();
		ScreenshotUtility.takeScreenshot(d, "HomePage");
		logout();
	}
	
	@Test(priority = 2,groups = "FT")
	public void TC02() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.verifyHome();
		h.getServiceslink();
		ScreenshotUtility.takeScreenshot(d, "Service Page");
		h.getMorelink();
		h.getAssetslink();
		ScreenshotUtility.takeScreenshot(d, "Assets Page");
		h.getMorelink();
		h.getPurchaselink();
		ScreenshotUtility.takeScreenshot(d, "Purchase Page");
		h.getMorelink();
		h.getPricelink();
		ScreenshotUtility.takeScreenshot(d, "Price Page");
		h.getMorelink();
		h.getVendorslink();
		ScreenshotUtility.takeScreenshot(d, "Vendors Page");
		logout();
	}
}
