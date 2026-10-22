package inventory_vtiger_purchaseOrder;

import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Genric_Utility.DataProviderUtility;
import Genric_Utility.ScreenshotUtility;
import Object_repo.HomePage;
import Object_repo.PurchaseOrderPage;

public class PurchaseOrderTest extends BaseClass{
	
	//verify Basic and critical feature of this module
	@Test(groups="smoke")
	public void TC01() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getPurchaselink();
		logout();
		
	}

	//Verify that an existing Purchase Order name can be edited.
	@Test (priority = 1,groups = "FT", dataProvider = "orderNameData", dataProviderClass = DataProviderUtility.class)
	public void FTC01(String newname) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getPurchaselink();
		PurchaseOrderPage p = new PurchaseOrderPage(d);
		p.getorders();
		p.geteditbtn();
		p.editsubject(newname);
		p.getsave();
		p.getpurchaselink();
		p.getsearchfield(newname);
		p.getsearchIN();
		p.getsearchnowbtn();
		p.verifyeditedsubject(newname);
		ScreenshotUtility.takeScreenshot(d, "Purchase name edited");
	}
	
	//Verify that the Purchase Order search works using PurchaseOrder Name
	@Test (priority = 2,groups = "FT", dataProvider = "orderNameData", dataProviderClass = DataProviderUtility.class)
	public void FTC02(String newname) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getPurchaselink();
		PurchaseOrderPage p = new PurchaseOrderPage(d);
		p.getsearchfield(newname);
		p.getsearchIN();
		p.getsearchnowbtn();
		p.verifyeditedsubject(newname);
		p.verifybysearchIn();
	}
	
	//Verify that order details and the grand total remain consistent after reopening.
	@Test(priority = 3,groups = "ST",dataProvider = "quoteData",dataProviderClass = DataProviderUtility.class)
	public void STC03(String Subject , String Date, String Billing_Address, String Shipping_Address,String Terms, String Desc, String Qty, String Price) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getPurchaselink();
		PurchaseOrderPage p = new PurchaseOrderPage(d);
		p.getcreatepurchase();
		p.getSubjecttxt(Subject);
		p.getVendorName();
		p.getdate(Date);
		p.getPostStatus();
		p.getAssignedId();
		p.getBill(Billing_Address);
		p.getShip(Shipping_Address);
		p.getTerms(Terms);
		p.getDescription(Desc);
		p.getQty(Qty);
		p.getPrice(Price);
		p.getitemicon();
		p.getsave();
		p.gethomebtn();
		h.getMorelink();
		h.getPurchaselink();
		p.verifybysearchIn();
		logout();
	}
}
