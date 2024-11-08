package com.TechStory.eCommerce.eCommerce_Project;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SampleTest {

    private WebDriver driver;
    private static final String URL = "https://www.demoblaze.com/index.html";

    // 1. @BeforeClass - Set up the WebDriver and access the URL
    @BeforeClass
    public void setUp() {
        // Initialize the Safari WebDriver
        driver = new SafariDriver();
        
        // Navigate to the URL
        driver.get(URL);
        driver.manage().window().maximize();
    }

    // 2. @Test - Get the title of the page
    @Test
    public void getTitleTest() {
        // Get the page title and assert it
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
        
        // Example assertion to ensure the title is correct
        Assert.assertTrue(pageTitle.contains("STORE"));
        Assert.assertEquals(pageTitle, "STORE");
    }

    @Test
    public void switchWin() throws InterruptedException {
        try {
            // Step 1: Wait for the page to load
            Thread.sleep(3000);

            // Step 2: Identify the first two elements with class "card-title"
            List<WebElement> cardTitles = driver.findElements(By.className("card-title"));

            // Make sure there are at least two elements to interact with
            if (cardTitles.size() >= 2) {
                // Step 3: Open the first two card titles in new tabs using Actions
                Actions actions = new Actions(driver);
                for (int i = 0; i < 2; i++) {
                    WebElement card = cardTitles.get(i);
                    // Use Actions to simulate Ctrl/Cmd + Click to open in a new tab
                    actions.keyDown(Keys.COMMAND).click(card).keyUp(Keys.COMMAND).build().perform();
                    Thread.sleep(3000);  // Wait for the tab to open
                }

                // Step 4: Switch to the new tabs and print the page titles and prices
                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                for (int i = 1; i <= 2; i++) {
                    driver.switchTo().window(tabs.get(i));  // Switch to the new tab
                    System.out.println("Page Title in new tab: " + driver.getTitle());
                    //System.out.println("Page Source in new tab: " + driver.getPageSource());  // Print the page source to debug

                    // Step 5: Wait until the price is visible and extract it from the current tab
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Updated WebDriverWait
                    try {
                        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("price-container")));
                        String price = priceElement.getText();
                        System.out.println("Price in the new tab: " + price);
                    } catch (Exception e) {
                        System.out.println("Price not found in this tab.");
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

    // 3. @AfterClass - Quit the WebDriver after all tests
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Close the browser
        }
    }
}
