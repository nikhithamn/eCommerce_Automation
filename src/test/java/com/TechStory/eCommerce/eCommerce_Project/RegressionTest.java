package com.TechStory.eCommerce.eCommerce_Project;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.TechStory.eCommerce.eCommerce_Project.pages.HomePage;
import com.TechStory.eCommerce.eCommerce_Project.pages.ProductPage;
import com.TechStory.eCommerce.eCommerce_Project.utilities.ActionValidation;
import com.TechStory.eCommerce.eCommerce_Project.drivers.DriverFactory;

public class RegressionTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;

    // Store URL in a configuration file or system property
    private static final String URL = "https://www.demoblaze.com/index.html";  // You can fetch this URL from a properties/config file.

    @BeforeClass
    @Parameters("browser")  // Read the browser parameter from TestNG XML
    public void setUp(String browser) {
        // Initialize WebDriver based on the browser passed from testng.xml
        driver = DriverFactory.getDriver(browser);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        new ActionValidation(driver);
    }

    @Test
    public void launchUrl(){
        // Use HomePage class to navigate to the URL
    	 System.out.println("Navigating to home page...");
        homePage.navigateToHomePage(URL);
        System.out.println("Navigated to home page...");
       
    }
    

    // 2. @Test - Get the title of the page
    @Test(dependsOnMethods = {"launchUrl"}) 
    public void getTitleTest() {
        // Get the page title and assert it
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
        
        // Example assertion to ensure the title is correct
        //Assert.assertTrue(pageTitle.contains("STORE"));
        Assert.assertEquals(pageTitle, "STORE");
    }
    @Test(dependsOnMethods = {"launchUrl"})
    public void switchWin() throws InterruptedException {
        try {
            // Wait for the page to load
            Thread.sleep(3000);

            // Get the list of card titles using HomePage class
            List<WebElement> cardTitles = homePage.getCardTitles();

            // Make sure there are at least two elements to interact with
            if (cardTitles.size() >= 2) {
                // Open the first two card titles in new tabs using Actions
                Actions actions = new Actions(driver);
                for (int i = 0; i < 2; i++) {
                    WebElement card = cardTitles.get(i);
                    actions.keyDown(Keys.COMMAND).click(card).keyUp(Keys.COMMAND).build().perform();
                    Thread.sleep(3000);  // Wait for the tab to open
                }

                // Switch to the new tabs and check the price
                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                for (int i = 1; i <= 2; i++) {
                    driver.switchTo().window(tabs.get(i));  // Switch to the new tab
                    System.out.println("Page Title in new tab: " + driver.getTitle());

                    // Wait until the price is visible and extract it from the current tab using ProductPage class
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Updated WebDriverWait
                    try {
                        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productPage.getPriceElementLocator()));
                        String price = priceElement.getText();
                        System.out.println("Price in the new tab: " + price);

                        // Assert that the price is found and is not empty
                        Assert.assertNotNull(price, "Price should not be null in tab " + (i + 1));
                        Assert.assertFalse(price.isEmpty(), "Price should not be empty in tab " + (i + 1));
                    } catch (Exception e) {
                        System.out.println("Price not found in tab " + (i + 1));
                        Assert.fail("Price not found in tab " + (i + 1));  // Fail the test if price is not found
                    }
                }

                // Step 6: Switch back to the original window
                driver.switchTo().window(tabs.get(0));  // Switch back to the original window
                System.out.println("Switched back to the original window.");
                System.out.println("Original Page Title: " + driver.getTitle());

            } else {
                System.out.println("Not enough card-title elements found on the page.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Step 7: Clean up
            Thread.sleep(5000);  // Give some time to observe before quitting
            driver.quit();  // Close the driver and quit the browser
        }
    }
    @AfterClass
    public void tearDown() {
        // Quit the driver after the test is complete
        DriverFactory.quitDriver(driver);
    }
}
