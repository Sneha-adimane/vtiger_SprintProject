package Genric_Utility;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver_utility {

	public static WebDriver launchbrowser(String browser) {

        if (browser == null || browser.trim().isEmpty()) {
            throw new IllegalArgumentException("Browser name is missing in the property file.");
        }

        browser = browser.trim();

        WebDriver d;

        if (browser.equalsIgnoreCase("chrome")) {

        	ChromeOptions settings = new ChromeOptions();
        	Map<String, Object> prefs = new HashMap<>();
        	// Disable password leak detection
        	prefs.put("profile.password_manager_leak_detection", false);
        	settings.setExperimentalOption("prefs", prefs);

        	d = new ChromeDriver(settings);

        } else if (browser.equalsIgnoreCase("edge")) {

            d = new EdgeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {

            d = new FirefoxDriver();

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        d.manage().window().maximize();

        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        return d;
    }
}
