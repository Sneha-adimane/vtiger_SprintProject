package inventory_vtiger_vrndors;

import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Genric_Utility.DataProviderUtility;
import Genric_Utility.ScreenshotUtility;
import Object_repo.HomePage;
import Object_repo.PriceBookPage;
import Object_repo.VendorPage;

public class vendorsTest extends BaseClass{

	//verify vendors page
	@Test (groups = "Smoke")
	public void STC01() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getVendorslink();
		logout();
	}

	//Verify that an existing vendor name can be edited.
	@Test (groups = "FT", dataProvider = "vendorNameData", dataProviderClass = DataProviderUtility.class)
	public void FTC01(String newName) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getVendorslink();
		VendorPage v = new VendorPage(d);
		v.getorders();
		v.geteditbtn();
		v.getvendorName(newName);
		v.getsave();
		v.getvendorlink();
		v.getsearchfield(newName);
		v.getsearchIN();
		v.getsearchnowbtn();
		v.verifyeditedsubject(newName);
		v.getsearchfield(newName);
		v.verifybysearchIn();
	}
	
	//Verify that vendor records can be searched using Vendor Name.
	@Test (groups = "FT", dataProvider = "vendorNameData", dataProviderClass = DataProviderUtility.class)
	public void FTC02(String newname) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getVendorslink();
		VendorPage v = new VendorPage(d);
		v.getsearchfield(newname);
		v.getsearchIN();
		v.getsearchnowbtn();
		ScreenshotUtility.takeScreenshot(d, "Vendors Records");
		v.verifyeditedsubject(newname);
		v.verifybysearchIn();
	}
}
