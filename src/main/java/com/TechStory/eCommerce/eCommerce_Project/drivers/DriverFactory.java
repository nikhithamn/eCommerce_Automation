package com.TechStory.eCommerce.eCommerce_Project.drivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    // This method will initialize the WebDriver based on the given browser name.
    public static WebDriver getDriver(String browser) {
        WebDriver driver = null;

        if ("chrome".equalsIgnoreCase(browser)) {
        	  System.out.println("WebDriverManagerone Cache Path: " + WebDriverManager.chromedriver().getDownloadedDriverPath());
            // Automatically fetch and setup the correct version of chromedriver using WebDriverManager
            WebDriverManager.chromedriver().setup();
            System.out.println("WebDriverManagertwo Cache Path: " + WebDriverManager.chromedriver().getDownloadedDriverPath());
        	//WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
         
            options.addArguments("--disable-extensions"); // Disable extensions
             driver = new ChromeDriver(options);
         
            //driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            // Automatically fetch and setup the correct version of geckodriver using WebDriverManager
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if ("safari".equalsIgnoreCase(browser)) {
            // Safari driver does not need an external driver to be set up in the same way as Chrome or Firefox
            // You must have Safari's driver already available (on macOS, it's built into the system)
            driver = new SafariDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        
     
 
        // Add any other global configurations (timeouts, implicit waits, etc.)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    // This method will quit the driver.
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();  // Proper cleanup
        }
    }
}
