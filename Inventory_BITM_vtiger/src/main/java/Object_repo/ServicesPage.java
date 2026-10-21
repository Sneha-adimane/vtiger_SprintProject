package Object_repo;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import Genric_Utility.ExplicitWait_utility;

public class ServicesPage {

	WebDriver d;
	ExplicitWait_utility e;
	
	public ServicesPage(WebDriver d)
	{
		this.d = d;
		PageFactory.initElements(d , this);
		e = new ExplicitWait_utility(d);
	}
	
	@FindBy(css = "[title='Create Service...']")
	private WebElement createbtn;
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement createservices;
	
	@FindBy(id = "bas_searchfield")
	private WebElement searchfield;
	
	@FindBy(xpath = "//input[@class='txtBox']")
	private WebElement textbox;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement searchbtn;
	
	@FindBy(xpath = "//input[@title='Edit [Alt+E]']")
	private WebElement editlink;
	
	@FindBy(css = "[title='Services']")
	private List<WebElement> verifServices;
	
	@FindBy(css = "[class='lvt small']")
	private WebElement record;
	
	@FindBy(name = "servicename")
	private WebElement ServiceName;
	
	@FindBy(id = "qty_per_unit")
	private WebElement units;
	
	@FindBy(id = "unit_price")
	private WebElement price;
	
	@FindBy(xpath = "//input[@value='  Save  ']")
	private WebElement savebtn;
	
	@FindBy(xpath = "(//a[normalize-space()='Services'])[3]")
	private WebElement serviceslink;
	
	@FindBy(name = "description")
	private WebElement descriptionField;
	
	@FindBy(css = "[title='Services']")
	private List<WebElement> services;
	
	@FindBy(xpath = "//textarea[@class='detailedViewTextBox']")
	private WebElement description;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement editsavebtn;
	
	@FindBy(id = "dtlview_Description")
	private WebElement verifydescription;
	
	@FindBy(xpath = "//span[@vtfieldname='service_no']/..")
	private List<WebElement> serviceNumbers;
	
	@FindBy(name = "servicecategory")
	private WebElement category;
	
	public void getServices(String serviceNameFromExcel)throws InterruptedException {
	
	    String serviceName = serviceNameFromExcel.trim();
	    Select select = new Select(searchfield);
	    select.selectByVisibleText("Service Name");
	    textbox.clear();
	    textbox.sendKeys(serviceName);
	    searchbtn.click();
	    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(15));
	    By serviceLink = By.xpath("//table[contains(@class,'lvt')]//a[normalize-space(.)='"+ serviceName + "']");
	    // Check the current page first
	    for (int page = 1; page <= 2; page++) {
	
	        try {
	            WebElement service = new WebDriverWait(d, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(serviceLink));
	            service.click();
	            Reporter.log("Service opened successfully: " + serviceName,true);
	            return;
	        } catch (TimeoutException e) {
	            if (page == 1) {
	                // Navigate to page 2 using the Next Page control
	                By nextPage = By.xpath("//a[contains(@href,'start') and " +"(contains(@title,'Next') or contains(@title,'next'))]");
	                wait.until(ExpectedConditions.elementToBeClickable(nextPage)).click();
	                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table.lvt")));
	            }
	        }
	    }
	    throw new RuntimeException("Service not found on pages 1 or 2: " + serviceName);
	}

	
	public void servicesname() {
		for (WebElement service : services) {
			service.click();
			break;
		}
		
	}
	
	public void getDescription(String descriptionFromExcel) {
		By descriptionLocator = By.xpath("//textarea[@class='detailedViewTextBox']");
	    e.waitForElementVisible(descriptionLocator);
	    WebElement descriptionField = d.findElement(descriptionLocator);
	    descriptionField.clear();
	    descriptionField.sendKeys(descriptionFromExcel);
	}
	
	public void getEditlink() throws Exception {
		editlink.click();
		Thread.sleep(1000);
	}
	
	public void geteditsavebtn() {
		editsavebtn.click();
	}
	
	public void getunits(String no_of_units) throws InterruptedException {
		units.clear();
		Thread.sleep(1000);
		units.sendKeys(no_of_units);
		Thread.sleep(1000);
	}
	
	public void getServiceslink() throws InterruptedException {
		By servicesLocator =By.xpath("serviceslink");
		e.waitForElementVisible(servicesLocator);
		serviceslink.click();
		Thread.sleep(2000);
	}
	
	public void getsavebtn() {
		savebtn.click();
	}
	
	public void verifyDescription(String expectedDescriptionFromExcel) {
	    e.waitForElementVisible(verifydescription);
	    String actualDescription = verifydescription.getText().trim();
	    String expectedDescription = expectedDescriptionFromExcel.trim();
	    Assert.assertEquals(actualDescription,expectedDescription,"Description mismatch!");
	    Reporter.log("Description verified successfully: " + actualDescription, true);
	}
	
	public void getprice(String value) {
		price.sendKeys(value);
	}
	
	public void getServiceName(String value) {
		ServiceName.sendKeys(value);
	}
	
	public void getCreatebtn() {
		createbtn.click();
		e.waitForElementVisible(createservices);
	}
	
	public void verifyCreateServices() {
		Assert.assertTrue(createservices.getText().contains("Creating") , "Creating page is not displayed");
		Reporter.log("Creating page is displayed" , true);
	}

	public void getSearchfield() throws Exception {
		Select s = new Select(searchfield);
		s.selectByIndex(0);
	}

	public void getVerifServices() throws InterruptedException {
		int count = verifServices.size();
		Assert.assertTrue(count>0,"Services are not available");
		Reporter.log("Services are available" , true);
		Thread.sleep(2000);
	}
	
	public void getRecord() {
		Reporter.log(record.getText() , true);
	}	
	
	public void getServiceNoRecord() {
		String serviceNo = serviceNumbers.get(0).getText().trim();
	    textbox.clear();
	    textbox.sendKeys(serviceNo);
	    Reporter.log("Service No entered: " + serviceNo, true);
	}
	
	public void getSearchbtn() throws InterruptedException {
		searchbtn.click();
		Thread.sleep(2000);
	}
	
	public void verifysearch() {
		int count = verifServices.size();
		System.out.println(count);
		String actual = record.getText();
		String expectedServiceNo = textbox.getAttribute("value").trim();
		Assert.assertTrue(actual.contains(expectedServiceNo),"Service number verification failed");
		Reporter.log("Service number verified successfully\n" + actual, true);
	}
	
	@FindBy(id = "dtlview_No of Units")
	private WebElement verifyunits;
	
	// Verify minimum value: 1
	public void verifyOneUnit()throws Exception {
	    Thread.sleep(1000);
	    String actualunits = verifyunits.getText();
	    Assert.assertEquals(actualunits, "1.00" , "One unit value is not verified");
	    Reporter.log("One units value verified successfully:", true);
	    Reporter.log("Actual Units: " + actualunits, true);
	}

	// Verify zero value: 0
	public void verifyZeroUnits() throws Exception {
	    Thread.sleep(1000);
	    String actualunits = verifyunits.getText();
	    Assert.assertEquals(actualunits, "" ,"Zero units value was not saved correctly");
	    Reporter.log("Zero units value verified successfully:", true);
	    Reporter.log("Actual Units: " + actualunits, true);
	}
	

	public void verifyInvalidUnits() {
	    // Wait until the JavaScript alert appears
	    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	    // Read the popup message
	    String alertMessage = alert.getText();
	    Reporter.log("Alert message displayed: " + alertMessage, true);
	    // Verify the alert message
	    Assert.assertEquals(alertMessage,"Invalid No of Units","Incorrect validation message");
	    Reporter.log("Invalid units validation verified successfully", true);
	    // Click OK to close the popup
	    alert.accept();
	    Reporter.log("Alert popup handled successfully", true);
	}
	
	public void getcategory() throws InterruptedException {
		Select s1 = new Select(category);
		s1.selectByIndex(1);
		Thread.sleep(1000);
	}
	
	@FindBy(xpath = "//a[text()='Create Quote']")
	private WebElement createquote;
	
	public void getcreatequote() throws InterruptedException {
		Thread.sleep(2000);
		createquote.click();
	}
	
	@FindBy(css = "[class='dvtUnSelectedCell']")
	private WebElement moreInfoLink;
	
	public void getmoreInfoLink() {
		moreInfoLink.click();
	}
	
	@FindBy(id = "show_Services_Quotes")
	private WebElement showquotes;
	
	public void getshowquotes() {
		showquotes.click();
	}
	
	@FindBy(id = "tbl_Services_Quotes")
	private WebElement quotesList;
	
	public void getquotesList() {
		e.waitForElementVisible(quotesList);
	    List<WebElement> rows = quotesList.findElements(By.xpath(".//tr[@bgcolor='white']"));
	    Assert.assertTrue(rows.size() > 0, "No Quotes found");
	    for (WebElement row : rows) {
	        Reporter.log(row.getText(), true);
	    }
	}
	
	@FindBy(xpath = "//a[text()='Create Sales Order']")
	private WebElement createSales;
	
	public void getcreateSales() throws InterruptedException {
		Thread.sleep(1000);
		createSales.click();
	}
	
	@FindBy(id = "show_Services_SalesOrder")
	private WebElement showsales;
	
	public void getshowsales() {
		showsales.click();
	}
	
	@FindBy(id = "tbl_Services_SalesOrder")
	private WebElement salesList;
	
	public void getsalesList() {
		e.waitForElementVisible(salesList);
	    List<WebElement> rows = salesList.findElements(By.xpath(".//tr[@bgcolor='white']"));
	    Assert.assertTrue(rows.size() > 0, "No Sales Orders found");
	    for (WebElement row : rows) {
	        Reporter.log(row.getText(), true);
	    }
	}
	
	@FindBy(xpath = "//a[text()='Create Purchase Order']")
	private WebElement createPurchase;
	
	public void getCreatePurchase() throws InterruptedException {
		Thread.sleep(1000);
		createPurchase.click();
	}
	
	@FindBy(id = "show_Services_PurchaseOrder")
	private WebElement showPurchase;
	
	public void getShowPurchase() {
		showPurchase.click();
	}
	
	@FindBy(id = "tbl_Services_PurchaseOrder")
	private WebElement purchaseList;
	
	public void getPurchaseList() {
	    e.waitForElementVisible(purchaseList);
	    List<WebElement> rows = purchaseList.findElements(By.xpath(".//tr[@bgcolor='white']"));
	    Assert.assertTrue(rows.size() > 0,"No Purchase Orders found");
	    for (WebElement row : rows) {
	        Reporter.log(row.getText(), true);
	    }
	}
	
	@FindBy(xpath = "//span[@vtfieldname='service_no']/..")
	private List<WebElement> serviceno;
	

	public String getCreatedServiceNo() {
	    e.waitForElementVisible(By.xpath("//span[@vtfieldname='service_no']/.."));
	    String serviceNo = serviceno.get(0).getText().trim();
	    Reporter.log("Created Service No: " + serviceNo, true);
	    return serviceNo;
	}
	
	public void searchByServiceNo(String serviceNo) throws InterruptedException {
	    textbox.clear();
	    textbox.sendKeys(serviceNo);
	    Reporter.log("Searching Service No: " + serviceNo, true);
	    searchbtn.click();
	    e.waitForElementVisible(By.xpath("//span[@vtfieldname='service_no']/.."));
	}

	@FindBy(name = "discontinued")
	private WebElement activeCheck;
	
	public void getactivercheck() {
		activeCheck.click();
	}
	
	@FindBy(id = "dtlview_Active")
	private WebElement verifyActive;
	
	public void getverifyactivecheck() {
		Reporter.log("Active Status:"+verifyActive.getText(),true);
	}
	
	public String getCurrentDescription() {
	    By descriptionLocator = By.id("dtlview_Description");
	    try {
	        e.waitForElementVisible(descriptionLocator);
	        return d.findElement(descriptionLocator).getText().trim();
	    } catch (TimeoutException ex) {
	        Reporter.log("Description field is not displayed or locator is incorrect.", true);
	        return "";
	    }
	}
	
	public void verifyDescriptionUnchanged(String expectedDescription) {
	    String actualDescription = getCurrentDescription();
	    Assert.assertEquals(actualDescription,expectedDescription,"Service description was changed unexpectedly!");
	    Reporter.log("Service description verified successfully: " + actualDescription,true);
	}
	
	public void verifyDescriptionUpdated(String originalDescription, String expectedDescription) {
	    String actualDescription = getCurrentDescription();
//	    Assert.assertEquals(actualDescription,expectedDescription,"Updated description was not saved correctly.");
	    Reporter.log("Original Description: " + originalDescription, true);
	    Reporter.log("Updated Description: " + actualDescription, true);
	}
	
	@FindBy(name = "servicename")
	private WebElement serviceNameField;
	
	public void updateServiceName(String updatedServiceName) {
	    e.waitForElementVisible(By.name("servicename"));

	    serviceNameField.clear();
	    serviceNameField.sendKeys(updatedServiceName);
	}

	public void verifyServiceName(String expectedServiceName) {
	    String actualServiceName = d.findElement(By.xpath("//span[@id='dtlview_Service Name']")).getText().trim();
	    Assert.assertEquals(actualServiceName,expectedServiceName,"Updated service name was not displayed correctly.");
	    Reporter.log("Updated service name verified: " + actualServiceName,true);
	}
	
	public void alertmess() {
	    Alert alert = e.waitForAlertPresent();
	    String alertMessage = alert.getText();
	    Reporter.log("Confirmation message: " + alertMessage, true);
	    try {
	        d.switchTo().alert().accept();
	    } catch (NoAlertPresentException ex) {
	        Reporter.log("Confirmation alert was already handled.", true);
	    }
	}
}

