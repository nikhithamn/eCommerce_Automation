package com.TechStory.eCommerce.eCommerce_Project.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class HomePage {

    private WebDriver driver;

    // WebElements for HomePage
    //private By loginButton = By.id("loginButton");
    private By searchBox = By.name("q");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Page-specific actions
//    public void clickLoginButton() {
//        driver.findElement(loginButton).click();
//    }
    // Method to navigate to the HomePage (instead of directly in test class)
    public void navigateToHomePage(String url) {
        driver.get(url);  // Open the URL
    }
    
    public void searchProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
    }
}
