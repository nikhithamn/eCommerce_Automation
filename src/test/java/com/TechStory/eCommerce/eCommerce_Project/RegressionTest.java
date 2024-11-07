package com.TechStory.eCommerce.eCommerce_Project;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.TechStory.eCommerce.eCommerce_Project.pages.HomePage;
import com.TechStory.eCommerce.eCommerce_Project.drivers.DriverFactory;

public class RegressionTest {

    private WebDriver driver;
    private HomePage homePage;

    // Store URL in a configuration file or system property
    private static final String URL = "https://www.google.com";  // You can fetch this URL from a properties/config file.

    @BeforeClass
    @Parameters("browser")  // Read the browser parameter from TestNG XML
    public void setUp(@Optional("chrome") String browser) {
        // Initialize WebDriver based on the browser passed from testng.xml
        driver = DriverFactory.getDriver(browser);
        homePage = new HomePage(driver);
    }

    @Test
    public void testLoginButton() {
        // Use HomePage class to navigate to the URL
        homePage.navigateToHomePage(URL);
        
        // Perform actions using Page Object methods
        homePage.searchProduct("selenium");
        
        // Add assertions to validate test results
        Assert.assertTrue(driver.getTitle().contains("Google"));
    }

    @AfterClass
    public void tearDown() {
        // Quit the driver after the test is complete
        DriverFactory.quitDriver(driver);
    }
}
