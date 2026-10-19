package Object_repo;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Genric_Utility.ExplicitWait_utility;

public class SalesOrderPage {

	WebDriver d;
	ExplicitWait_utility e;
	
	public SalesOrderPage(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d , this);
		e = new ExplicitWait_utility(d);
	}
	
	@FindBy(name = "subject")
	private WebElement subjecttxt;
	
	@FindBy(name = "sostatus")
	private WebElement status;
	
	@FindBy(name = "assigned_user_id")
	private WebElement assignedId;
	
	@FindBy(xpath = "(//img[@title='Select'])[4]")
	private WebElement organization;
	
	@FindBy(name = "invoicestatus")
	private WebElement Inovicestatus;
	
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
	
	@FindBy(name = "end_period")
	private WebElement date;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;

	public void getSubjecttxt(String Subject) {
		subjecttxt.sendKeys(Subject);
	}

	public void getStatus() throws InterruptedException {
		Select s = new Select(status);
		s.selectByIndex(0);
		Thread.sleep(1000);
	}

	public void getAssignedId() throws InterruptedException {
		Select s = new Select(assignedId);
		s.selectByIndex(0);
		Thread.sleep(1000);
	}

	public void getOrganizationName() throws InterruptedException {
	    String parentWindow = d.getWindowHandle();
	    organization.click();
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
	    // Wait for and read the confirmation alert
	    Alert alert = e.waitForAlertPresent();
	    String alertMessage = alert.getText();
	    Reporter.log("Confirmation message: " + alertMessage, true);
	    // Accept the alert if it is still present
	    try {
	        d.switchTo().alert().accept();
	    } catch (NoAlertPresentException ex) {
	        Reporter.log("Confirmation alert was already handled.", true);
	    }
	    // Switch back to the main quote window
	    d.switchTo().window(parentWindow);
	}

	public void getInovicestatus() throws InterruptedException {
		Select s = new Select(Inovicestatus);
		s.selectByIndex(0);
		Thread.sleep(1000);
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
	
	public void  getdate(String Date) {
		date.sendKeys(Date);
	}
	
	public void getsave() throws InterruptedException {
		save.click();
		Thread.sleep(2000);
	}
	
	
}
