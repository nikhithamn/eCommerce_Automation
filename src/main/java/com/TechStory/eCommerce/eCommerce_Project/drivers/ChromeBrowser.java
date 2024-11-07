package com.TechStory.eCommerce.eCommerce_Project.drivers;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser {

    private WebDriver driver;

    // Initializes the Chrome driver with custom options
    public void initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Running Chrome in headless mode (no GUI)
        options.addArguments("--disable-gpu"); // Disable GPU for headless mode
        driver = new ChromeDriver(options);
    }

    // Get the WebDriver instance
    public WebDriver getDriver() {
        return driver;
    }

    // Clean up the driver after use
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
