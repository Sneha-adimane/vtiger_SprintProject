package Object_repo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import Genric_Utility.ExplicitWait_utility;

public class VendorPage {

	
	WebDriver d;
	ExplicitWait_utility e;
	
	public VendorPage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d , this);
		e = new ExplicitWait_utility(d);
	}
	
	@FindBy(css = "[vtmodule='Vendors']")
	private WebElement vendors;
	
	@FindBy(name = "Edit")
	private WebElement editbtn;
	
	@FindBy(name = "vendorname")
	private WebElement vendorName;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	@FindBy(xpath = "(//a[text()='Vendors'])[3]")
	private WebElement vendorlink;
	
	@FindBy(xpath = "//tr[starts-with(@id,'row_')]")
	private List<WebElement> allrecords;
	
	@FindBy(css = "[title='Vendors']")
	private WebElement orders;
	
	public void getvendors() {
		vendors.click();
	}
	
	public void geteditbtn() {
		editbtn.click();
	}
	
	public void getvendorName(String name) {
		e.waitForElementVisible(vendorName);
		vendorName.clear();
		vendorName.sendKeys(name);
	}
	
	public void getsave() throws InterruptedException {
		save.click();
		Thread.sleep(2000);
	}
	
	public void getvendorlink() {
		vendorlink.click();
	}
	
	public void getorders() {
		orders.click();
	}
	
	public void verifyeditedsubject(String newName) throws InterruptedException {
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
	
	@FindBy(xpath = "//input[@class='txtBox']")
	private WebElement searchfortxt;
	
	@FindBy(id = "bas_searchfield")
	private WebElement searchIn;
	
	@FindBy(name = "submit")
	private WebElement searchnowbtn;
	
	public void getsearchnowbtn() {
		searchnowbtn.click();
	}
	
	public void getsearchIN() {
		Select s = new Select(searchIn);
		s.selectByIndex(1);
	}
	
	public void getsearchfield(String newName) {
		searchfortxt.sendKeys(newName);
	}
	
	public void verifybysearchIn() {
		int count = allrecords.size();
		Assert.assertTrue(count>0,"Records are not displayed for particular search");
		Reporter.log("Records are displayed for particular search",true);
		for(WebElement ele:allrecords) {
			Reporter.log("Edited Orders: "+ele.getText(),true);
		}
	}
}
