package Object_repo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import Genric_Utility.ExplicitWait_utility;

public class PurchaseOrderPage {

	WebDriver d;
	ExplicitWait_utility e;
	
	public PurchaseOrderPage(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d , this);
		e = new ExplicitWait_utility(d);
	}
	
	@FindBy(xpath = "//img[@title='Create Purchase Order...']")
	private WebElement createpurchase;
	
	@FindBy(name = "subject")
	private WebElement subjecttxt;
	
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement vendorname;
	
	@FindBy(name = "duedate")
	private WebElement date;
	
	@FindBy(name = "postatus")
	private WebElement status;
	
	@FindBy(name = "assigned_user_id")
	private WebElement assignedId;
	
	@FindBy(name = "bill_street")
	private WebElement bill;
	
	@FindBy(name = "ship_street")
	private WebElement ship;
	
	@FindBy(name = "terms_conditions")
	private WebElement terms;
	
	@FindBy(name = "description")
	private WebElement Description;
	
	@FindBy(id = "qty1")
	private WebElement qty;
	
	@FindBy(id = "listPrice1")
	private WebElement price;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	public void getcreatepurchase() {
		createpurchase.click();
	}
	
	public void getSubjecttxt(String Subject) {
		subjecttxt.sendKeys(Subject);
	}
	
	public void getBill(String BillAddress) {
		bill.sendKeys(BillAddress);
	}

	public void getShip(String ShipAddress) {
		ship.sendKeys(ShipAddress);
	}

	public void getTerms(String TC) {
		terms.sendKeys(TC);
	}

	public void getDescription(String desc) {
		Description.sendKeys(desc);
	}

	public void getQty(String Quantity) {
		qty.sendKeys(Quantity);
	}

	public void getPrice(String Price) {
		price.sendKeys(Price);
	}
	
	public void getsave() throws InterruptedException {
		save.click();
		Thread.sleep(2000);
	}
	
	public void  getdate(String Date) {
		date.sendKeys(Date);
	}
	
	public void getAssignedId() throws InterruptedException {
		Select s = new Select(assignedId);
		s.selectByIndex(0);
		Thread.sleep(1000);
	}
	
	public void getPostStatus() throws InterruptedException {
		Select s = new Select(status);
		s.selectByIndex(0);
		Thread.sleep(1000);
	}
	
	public void getVendorName() throws InterruptedException {
	    String parentWindow = d.getWindowHandle();
	    vendorname.click();
	    e.waitForWindowvisible(2);
	    // Switch to the organization popup
	    for (String window : d.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            d.switchTo().window(window);
	            break;
	        }
	    }
	    By organizationLocator = By.xpath("//a[@href='javascript:window.close();' and normalize-space()]");
	    e.waitForElementVisible(organizationLocator);
	    List<WebElement> organizations = d.findElements(organizationLocator);
	    if (organizations.isEmpty()) {
	        throw new RuntimeException("No organizations found in popup");
	    }
	    // Click the organization
	    organizations.get(0).click();
	    Thread.sleep(1000);
	    // Switch back to the main quote window
	    d.switchTo().window(parentWindow);
	}
	
	@FindBy(css = "[title='Purchase Order']")
	private WebElement orders;
	
	public void getorders() throws AWTException {
		orders.click();
	}
	
	@FindBy(name = "Edit")
	private WebElement editbtn;
	
	public void geteditbtn() {
		editbtn.click();
	}
	
	public void editsubject(String newName) {
	    By subjectLocator = By.name("subject");
	    WebElement subjectField = e.waitForElementVisible(subjectLocator);
	    subjectField.clear();
	    subjectField.sendKeys(newName);
	}
	
	@FindBy(name = "search_text")
	private WebElement searchfield;
	
	public void getsearchfield(String newName) {
		searchfield.sendKeys(newName);
	}
	
	@FindBy(id = "bas_searchfield")
	private WebElement searchfor;
	
	public void getsearchIN() {
		Select s = new Select(searchfor);
		s.selectByIndex(1);
	}
	
	@FindBy(xpath = "(//a[text()='Purchase Order'])[3]")
	private WebElement clickpurchase;
	
	public void getpurchaselink() {
		clickpurchase.click();
	}
	
	@FindBy(name = "submit")
	private WebElement searchnowbtn;
	
	public void getsearchnowbtn() {
		searchnowbtn.click();
	}
	
	@FindBy(xpath = "//tr[starts-with(@id,'row_')]")
	private List<WebElement> allrecords;
	
	public void verifyeditedsubject(String newName) throws InterruptedException {
		Thread.sleep(2000);
		String actualordername = orders.getText();
		String expectedordername = newName;
		Assert.assertEquals(actualordername, expectedordername, "updated name is not edited successfully");
		Reporter.log("updated name is displayed" , true);
		Reporter.log("Edited Order name: "+orders.getText(),true);
		Thread.sleep(2000);
		for(WebElement ele:allrecords) {
			Reporter.log("Edited Orders: "+ele.getText(),true);
		}
	}
	
	public void verifybysearchIn() {
		int count = allrecords.size();
		Assert.assertTrue(count>0,"Records are not displayed for particular search");
		Reporter.log("Records are displayed for particular search",true);
		for(WebElement ele:allrecords) {
			Reporter.log("Searched Orders: "+ele.getText(),true);
		}
	}
	
	@FindBy(xpath = "(//td[@class='tabUnSelected'])[1]")
	private WebElement homebtn;
	
	public void gethomebtn() {
		homebtn.click();
	}
	
	@FindBy(id = "searchIcon1")
	private WebElement itemicon;
	
	public void getitemicon() throws InterruptedException {
		String parentWindow = d.getWindowHandle();
	    itemicon.click();
	    e.waitForWindowvisible(2);
	    // Switch to the organization popup
	    for (String window : d.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            d.switchTo().window(window);
	            break;
	        }
	    }
	    By organizationLocator = By.xpath("//a[@href='javascript:window.close();' and normalize-space()]");
	    e.waitForElementVisible(organizationLocator);
	    List<WebElement> organizations = d.findElements(organizationLocator);
	    if (organizations.isEmpty()) {
	        throw new RuntimeException("No organizations found in popup");
	    }
	    // Click the organization
	    organizations.get(0).click();
	    Thread.sleep(1000);
	    // Switch back to the main quote window
	    d.switchTo().window(parentWindow);
	}
}
