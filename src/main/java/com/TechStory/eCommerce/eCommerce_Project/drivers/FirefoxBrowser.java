package com.TechStory.eCommerce.eCommerce_Project.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBrowser {

    private WebDriver driver;

    // Initializes the Firefox driver with custom options
    public void initializeDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true); // Running Firefox in headless mode (no GUI)
        driver = new FirefoxDriver(options);
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