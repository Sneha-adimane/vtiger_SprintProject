package Genric_Utility;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait_utility {

    WebDriverWait wait;

    public ExplicitWait_utility(WebDriver d) {
        wait = new WebDriverWait(d, Duration.ofSeconds(50));
    }

    // Wait until element is visible
    public void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    // Wait until an element located by By is clickable
    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Wait until element is clickable
    public void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait until an element located by By is visible
    public WebElement waitForElementVisible(By locator) {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    // Wait until page title contains expected text
    public void waitForTitleContains(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }
    
    public void waitForWindowvisible(int no) {
    	wait.until(ExpectedConditions.numberOfWindowsToBe(no));
    }
    
    public Alert waitForAlertPresent() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}

