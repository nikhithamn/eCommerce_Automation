package com.TechStory.eCommerce.eCommerce_Project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ProductPage {
    private WebDriver driver;

    // WebElement for price on the product page
    private By priceContainerLocator = By.className("price-container");

    // Locator for "Add to cart" button
    private By addToCartButtonLocator = By.className("btn btn-success btn-lg");

    // Locator for the "OK" button on the pop-up
    private By okButtonLocator = By.xpath("//button[text()='OK']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get the locator for the price element
    public By getPriceElementLocator() {
        return priceContainerLocator;
    }

    // Method to extract the price from the product page
    public String getProductPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priceContainerLocator));
        return priceElement.getText();
    }

//    // Method to click the "Add to Cart" button
//    public void clickAddToCart() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonLocator));
//        addToCartButton.click();
//    }
//
//    // Method to handle the pop-up and click the "OK" button
//    public void handlePopUp() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(okButtonLocator));
//        okButton.click();
//    }
}
