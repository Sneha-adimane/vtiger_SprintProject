package inventory_vtiger_services;

import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Genric_Utility.DataProviderUtility;
import Genric_Utility.ScreenshotUtility;
import Object_repo.HomePage;
import Object_repo.PurchaseOrderPage;
import Object_repo.QuotePage;
import Object_repo.SalesOrderPage;
import Object_repo.ServicesPage;

public class ServicesTest extends BaseClass {

	//Verify that the Services list page opens successfully.
	@Test (priority = 1,groups = "Smoke")
	public void TC01() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.getVerifServices();
		s.getRecord();
	}

	//Verify that the Add Service form opens successfully.
	@Test (priority = 2,groups = "Smoke")
	public void TC02() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.getCreatebtn();
		s.verifyCreateServices();
	}
	
	//Verify that a service can be created with valid details.
	@Test (priority = 3,groups = "FT" , dataProvider = "getServiceData" , dataProviderClass = DataProviderUtility.class)
	public void FTC01(String servicename, int price , int no_of_units) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.getCreatebtn();
		s.getServiceName(servicename);
		s.getunits(String.valueOf(no_of_units));
		s.getprice(String.valueOf(price));
		s.getcategory();
		s.getsavebtn();
		Thread.sleep(2000);
		s.getServiceslink();
		s.getVerifServices();
		s.getRecord();
	}
	
	//Verify that an existing service's details can be updated.
	@Test (priority = 5,groups = "FT" , dataProvider = "getServiceUpdateData", dataProviderClass = DataProviderUtility.class)
	public void FTC02(String serviceName,String updatedDescription) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.getServices(serviceName);
		s.getEditlink();
		s.getDescription(updatedDescription);
		s.geteditsavebtn();
		s.verifyDescription(updatedDescription);
	}
	
	//Verify that services can be searched using the Service No field.
	@Test (priority = 4,groups = "FT")
	public void FTC03() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.getSearchfield();
		s.getServiceNoRecord();
		s.getSearchbtn();
		s.verifysearch();
	}
	
	//Verify validation of the No. of Units field at its numeric boundaries.
	@Test(priority = 6,groups = "FT" , dataProvider = "serviceData", dataProviderClass = DataProviderUtility.class)
	public void FTC04(String serviceName,String minimumValue,String zeroValue,String negativeValue) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.getServices(serviceName);
		s.getEditlink();
		s.getunits(minimumValue);
		s.getsavebtn();
		ScreenshotUtility.takeScreenshot(d, "BVA One Unit");
		s.verifyOneUnit();
		s.getEditlink();
		s.getunits(zeroValue);
		s.getsavebtn();
		ScreenshotUtility.takeScreenshot(d, "BVA Zero value");
		s.verifyZeroUnits();
		s.getEditlink();
		s.getunits(negativeValue);
		s.getsavebtn();
		s.verifyInvalidUnits();
		ScreenshotUtility.takeScreenshot(d, "BVA Negative Value");
	}
	
	//Verify that a service can be used to create a Quote.
	@Test (priority = 7,groups = "IT" , dataProvider = "quoteData" , dataProviderClass = DataProviderUtility.class)
	public void ITC01(String Subject , String Valid_Till, String Billing_Address, String Shipping_Address,String Terms, String Desc, String Qty, String Price) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.servicesname();
		s.getcreatequote();
		QuotePage q = new QuotePage(d);
		q.getSubject(Subject);
		q.getDate(Valid_Till);
		q.getAssignedto();
		q.getStage();
		q.getOrganizationName();
		q.getBilingaddress(Billing_Address);
		q.getShipaddress(Shipping_Address);
		q.getTerms(Terms);
		q.getDescription(Desc);
		q.getQty(Qty);
		q.getPrice(Price);
		q.getSave();
		s.getmoreInfoLink();
		s.getshowquotes();
		s.getquotesList();
	}
	
	//Verify that a service can be used to create a Sales Order.
	@Test(priority = 8,groups = "IT",dataProvider = "quoteData" , dataProviderClass = DataProviderUtility.class)
	public void ITC02(String Subject , String Date, String Billing_Address, String Shipping_Address,String Terms, String Desc, String Qty, String Price) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.servicesname();
		s.getcreateSales();
		SalesOrderPage sop = new SalesOrderPage(d);
		sop.getSubjecttxt(Subject);
		sop.getStatus();
		sop.getAssignedId();
		sop.getOrganizationName();
		sop.getdate(Date);
		sop.getInovicestatus();
		sop.getBill(Billing_Address);
		sop.getShip(Shipping_Address);
		sop.getTerms(Terms);
		sop.getDescription(Desc);
		sop.getQty(Qty);
		sop.getPrice(Price);
		sop.getsave();
		s.getmoreInfoLink();
		s.getshowsales();
		s.getsalesList();
	}
	
	//Verify that a service can be used to create a Purchase Order.
	@Test (priority = 9,groups = "IT",dataProvider = "quoteData" , dataProviderClass = DataProviderUtility.class)
	public void ITC03(String Subject , String Date, String Billing_Address, String Shipping_Address,String Terms, String Desc, String Qty, String Price) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.servicesname();
		s.getCreatePurchase();
		PurchaseOrderPage p = new PurchaseOrderPage(d);
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
		p.getsave();
		s.getmoreInfoLink();
		s.getShowPurchase();
		s.getPurchaseList();
	}
	
	//Verify the complete service creation, search, and detail-view workflow.
	@Test(priority = 10,groups = "ST", dataProvider = "serviceWorkflowData", dataProviderClass = DataProviderUtility.class)
	public void STC01(String serviceName, String price, String noOfUnits) throws Exception {
	    login();
	    HomePage h = new HomePage(d);
	    h.getMorelink();
	    h.getServiceslink();
	    ServicesPage s = new ServicesPage(d);
	    s.getCreatebtn();
	    s.getServiceName(serviceName);
	    s.getprice(price);
	    s.getunits(noOfUnits);
	    s.getcategory();
	    s.getsavebtn();
	    s.getServiceslink();
	    String serviceNo = s.getCreatedServiceNo();
	    s.searchByServiceNo(serviceNo);
	    s.getServices(serviceName);
	    Reporter.log("Service creation and search workflow completed", true);
	    logout();
	}

	//Verify that the Active field's saved value is retained after editing and reopening the service.
	@Test (priority = 11,groups = "ST")
	public void STC02() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.servicesname();
		s.getverifyactivecheck();
		s.getEditlink();
		s.getactivercheck();
		s.getsavebtn();
		Reporter.log("After",true);
		s.getverifyactivecheck();
		logout();
	}
	
	//Verify that editing one service does not modify another service's details.
	@Test(priority = 12,groups = "RT",dataProvider = "serviceIsolationData",dataProviderClass = DataProviderUtility.class)
	public void RTC01(String firstService,String secondService,String updatedDescription) throws Exception {
		login();
	    HomePage h = new HomePage(d);
	    h.getMorelink();
	    h.getServiceslink();
	    ServicesPage s = new ServicesPage(d);
	    s.getServices(firstService);
	    String firstOriginalDescription = s.getCurrentDescription();
	    s.getServiceslink();
	    s.getServices(secondService);
	    String secondOriginalDescription = s.getCurrentDescription();
	    s.getServiceslink();
	    s.getServices(firstService);
	    s.getEditlink();
	    s.getDescription(updatedDescription);
	    s.geteditsavebtn();
	    s.getServiceslink();
	    s.getServices(firstService);
	    s.verifyDescriptionUpdated(firstOriginalDescription, updatedDescription);
	    s.getServiceslink();
	    s.getServices(secondService);
	    s.verifyDescriptionUnchanged(secondOriginalDescription);
	    logout();
	}
	
	//Verify that service search works correctly after updating a service name.
	@Test(priority = 13,groups = "RT",dataProvider = "serviceRenameData",dataProviderClass = DataProviderUtility.class)
	public void RTC02(String originalServiceName,String updatedServiceName) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
	    h.getServiceslink();
		ServicesPage s = new ServicesPage(d);
		s.getServices(originalServiceName);
		s.getEditlink();
	    s.updateServiceName(updatedServiceName);
	    s.geteditsavebtn();
	    Thread.sleep(1000);
		s.getServiceslink();
	    s.getServices(updatedServiceName);
		s.verifyServiceName(updatedServiceName);
		logout();
	} 
	
	//Verify that a service cannot be saved when its mandatory Service Name is blank.
	@Test (priority = 14,groups = {"FT","Neg"}, dataProvider = "serviceWorkflowData", dataProviderClass = DataProviderUtility.class)
	public void NTC01(String serviceName, String price, String noOfUnits) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
	    h.getServiceslink();
	    ServicesPage s = new ServicesPage(d);
	    s.getCreatebtn();
	    s.getprice(price);
	    s.getunits(noOfUnits);
	    s.getcategory();
	    s.getsavebtn();
	    s.alertmess();
	    logout();
	}
	
	//Verify that negative values are handled correctly in the Price ($) field.
	@Test (priority = 15,groups = {"FT","Neg"}, dataProvider = "serviceWorkflowData", dataProviderClass = DataProviderUtility.class)
	public void NTC02(String serviceName, String price, String noOfUnits) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
	    h.getServiceslink();
	    ServicesPage s = new ServicesPage(d);
	    s.getCreatebtn();
	    s.getServiceName(serviceName);
	    double negativePrice = -Math.abs(Double.parseDouble(price));
	    s.getprice(String.valueOf(negativePrice));
	    ScreenshotUtility.takeScreenshot(d, "Negative Price");
	    s.getunits(noOfUnits);
	    s.getcategory();
	    s.getsavebtn();
	    s.alertmess();
	    logout();
	}
	    
}

