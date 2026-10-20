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

public class AssetsPage {

	WebDriver d;
	ExplicitWait_utility e;
	
	public AssetsPage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d , this);
		e = new ExplicitWait_utility(d);
	}
	
	@FindBy(xpath = "//input[@class='txtBox']")
	private WebElement txtbox;
	
	@FindBy(id = "bas_searchfield")
	private WebElement searchIn;
	
	@FindBy(name = "submit")
	private WebElement searchnow;
	
	@FindBy(xpath = "//span[@vtfieldname='asset_no']/..")
	private WebElement assetnos;
	
	public void getsearchfor() {
		String assetno = assetnos.getText().trim();
		txtbox.clear();
		txtbox.sendKeys(assetno);
		Reporter.log("Service No entered: " + assetno, true);
	}
	
	public void getsearchIn() throws AWTException {
		searchIn.click();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void getsearchnow() {
		searchnow.click();
	}
	
	@FindBy(xpath = "//tr[starts-with(@id,'row_')]")
	private List<WebElement> allrecords;
	
	public void verifybysearchIn() throws InterruptedException {
		Thread.sleep(2000);
		int count = allrecords.size();
		Assert.assertTrue(count>0,"Records are not displayed for particular search");
		Reporter.log("Records are displayed for particular search",true);
		for(WebElement ele:allrecords) {
			Reporter.log("Edited Orders: "+ele.getText(),true);
		}
	}
	
	@FindBy(css = "[title='Assets']")
	private WebElement subject;
	
	public void getsubject() {
		subject.click();
	}
	@FindBy(xpath = "//a[text()='More Information']")
	private WebElement moreinfo;
	
	public void getmoreinfo() {
		moreinfo.click();
	}
	
	@FindBy(id = "show_Assets_HelpDesk")
	private WebElement showtickets;
	
	public void getshowtickets() throws InterruptedException {
		showtickets.click();
		Thread.sleep(2000);
	}
	
	@FindBy(id = "show_Assets_Documents")
	private WebElement showdocuments;
	
	public void getshowdocuments() throws InterruptedException {
		showdocuments.click();
		Thread.sleep(2000);
	}
	
	@FindBy(id = "tbl_Assets_HelpDesk")
	private List<WebElement> ticketslist;
	
	public void getverytickets() {
		for(WebElement ele : ticketslist) {
			Reporter.log(ele.getText(),true);
		}
	}
	
	@FindBy(id = "tbl_Assets_Documents")
	private List<WebElement> docList;
	
	public void verifydocList() {
		for(WebElement ele : docList) {
			Reporter.log(ele.getText(),true);
		}
	}
	
	@FindBy(css = "[title='Create Asset...']")
	private WebElement createassets;
	
	@FindBy(xpath = "//span[text()='Creating New Asset']")
	private By Assetfield;
	
	public void getcreateassets() {
		createassets.click();
	}
	
	@FindBy(name = "serialnumber")
	private WebElement serialNumber;
	
	public void getserialNo(String serialno) throws InterruptedException {
		serialNumber.sendKeys(serialno);
		Thread.sleep(1000);
	}
	
	@FindBy(name = "datesold")
	private WebElement datesold;
	
	public void getdateSold(String date) throws InterruptedException {
		datesold.clear();
		datesold.sendKeys(date);
		Thread.sleep(1000);
	}
	
	@FindBy(id = "jscal_field_dateinservice")
	private WebElement dateinservice;
	
	public void getdataservice(String dateService) throws InterruptedException {
		dateinservice.clear();
		dateinservice.sendKeys(dateService);
		Thread.sleep(1000);
	}
	
	@FindBy(name = "assetstatus")
	private WebElement status;
	
	public void getstatus() {
		Select s = new Select(status);
		s.selectByIndex(1);
	}
	
	@FindBy(id = "assetname")
	private WebElement Assetname;
	
	public void getAssetName(String name) throws InterruptedException {
		Assetname.sendKeys(name);
		Thread.sleep(1000);
	}
	
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement getproducticon;
	
	public void getproductName() throws InterruptedException {
		String parentWindow = d.getWindowHandle();
	    getproducticon.click();
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
	
	@FindBy(name = "assigned_user_id")
	private WebElement assignedId;
	
	public void getassignedId() {
		Select s = new Select(assignedId);
		s.selectByIndex(0);
	}
	
	@FindBy(xpath = "(//img[@title='Select'])[2]")
	private WebElement getcustomericon;
	
	public void getcustomerName() throws InterruptedException {
		String parentWindow = d.getWindowHandle();
	    getcustomericon.click();
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
	
	@FindBy(css = "[title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(css = "[class='dvHeaderText']")
	private By header;
	
	public void getsave() {
		savebtn.click();
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
	
	@FindBy(name = "Edit")
	private WebElement editbtn;
	
	public void geteditbtn() {
		editbtn.click();
	}
	
	@FindBy(id = "assetname")
	private WebElement changeAssetname;
	
	public void getchangeAssetName(String assetName) {
		changeAssetname.clear();
		changeAssetname.sendKeys(assetName);
	}
	
	@FindBy(xpath = "(//a[text()='Assets'])[3]")
	private WebElement assetLink;
	
	public void getassetLink() throws InterruptedException {
		e.waitForElementClickable(assetLink);
		assetLink.click();
		Thread.sleep(1000);
	}
	
	@FindBy(css = "[title='Assets']")
	private WebElement record;
	
	public void verifyAssetNameUpdated(String newName) throws AWTException, InterruptedException {
		txtbox.clear();
		txtbox.sendKeys(newName);
		searchIn.click();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		getsearchnow();
		Thread.sleep(2000);;
		String actualName = record.getText();
		Assert.assertEquals(actualName, newName,"Asset name was not updated correctly!");
		Reporter.log("Asset Name is updated successfully" , true);
	}
}

