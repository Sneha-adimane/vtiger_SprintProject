package Pom_utilities;

import java.util.List;
import java.util.Set;

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

public class QuotePage {

	WebDriver d;
	ExplicitWait_utility e;
	
	public QuotePage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d , this);
		e = new ExplicitWait_utility(d);
	}
	
	@FindBy(name = "subject")
	private WebElement subject;
	
	@FindBy(id = "jscal_field_validtill")
	private WebElement date;
	
	@FindBy(name = "carrier")
	private WebElement Carrier;
	
	@FindBy(name = "assigned_user_id")
	private WebElement assignedto;
	
	@FindBy(name = "quotestage")
	private WebElement stage;
	
	@FindBy(xpath = "(//img[@title='Select'])[3]")
	private WebElement OrganizationName;
	
	@FindBy(name = "bill_street")
	private WebElement bilingaddress;
	
	@FindBy(name = "ship_street")
	private WebElement shipaddress;
	
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

	public void getSubject(String Subject) {
		subject.sendKeys(Subject);;
	}

	public void getDate(String tilldate) {
		date.sendKeys(tilldate);
	}

	public void getCarrier() throws InterruptedException {
		Select s1 = new Select(Carrier);
		s1.selectByIndex(1);
		Thread.sleep(1000);
	}

	public void getAssignedto() throws InterruptedException {
		Select s1 = new Select(assignedto);
		s1.selectByIndex(4);
		Thread.sleep(1000);;
	}

	public void getStage() throws InterruptedException {
		Select s1 = new Select(stage);
		s1.selectByIndex(1);
		Thread.sleep(1000);
	}
	
	public void getBilingaddress(String billaddress) {
		bilingaddress.sendKeys(billaddress);
	}

	public void getShipaddress(String saddress) {
		shipaddress.sendKeys(saddress);
	}

	public void getTerms(String TC) {
		terms.sendKeys(TC);
	}

	public void getDescription(String desc) {
		Description.sendKeys(desc);
	}

	public void getQty(String QTY) {
		qty.sendKeys(QTY);
	}

	public void getPrice(String Price) {
		price.sendKeys(Price);
	}

	public void getSave() {
		save.click();
	}
	
	public void getOrganizationName() throws InterruptedException {
	    String parentWindow = d.getWindowHandle();
	    OrganizationName.click();
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

}
