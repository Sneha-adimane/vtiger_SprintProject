package inventory_vtiger_pricebook;

import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Genric_Utility.DataProviderUtility;
import Genric_Utility.ScreenshotUtility;
import Object_repo.HomePage;
import Object_repo.PriceBookPage;

public class PriceBookTest extends BaseClass {
	
	//verify critical feature of pricebook
	@Test (priority = 1,groups = "Smoke")
	public void STC01() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getPricelink();
		logout();
	}

	//Verify that an existing Price Book name can be edited.
	@Test (priority = 3,groups = "FT", dataProvider = "priceBookNameData", dataProviderClass = DataProviderUtility.class)
	public void FTC01(String newName) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getPricelink();
		PriceBookPage p = new PriceBookPage(d);
		p.getorders();
		p.geteditbtn();
		p.getPricebookName(newName);
		p.getsave();
		p.getpricebooklink();
		p.getsearchfield(newName);
		p.getsearchIN();
		p.getsearchnowbtn();
		p.verifyeditedsubject(newName);
		ScreenshotUtility.takeScreenshot(d, "Edited Price Book");
		p.getsearchfield(newName);
		p.verifybysearchIn();
	}
	
	//Verify that Price Books can be searched using PriceBook Name
	@Test (priority = 2,groups = "FT", dataProvider = "priceBookNameData", dataProviderClass = DataProviderUtility.class)
	public void FTC02(String newname) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getPricelink();
		PriceBookPage p = new PriceBookPage(d);
		p.getsearchfield(newname);
		p.getsearchIN();
		p.getsearchnowbtn();
		p.verifyeditedsubject(newname);
		p.verifybysearchIn();
	}
}
