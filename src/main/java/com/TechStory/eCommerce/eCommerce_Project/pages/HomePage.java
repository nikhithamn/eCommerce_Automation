package com.TechStory.eCommerce.eCommerce_Project.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import org.openqa.selenium.By;

public class HomePage {

    private WebDriver driver;

    // WebElements for HomePage
    //private By loginButton = By.id("loginButton");
    private By searchBox = By.name("q");
    private By loginLink = By.id("login2");
    private By userName = By.id("loginusername");
    private By password = By.id("loginpassowrd");
    private By cardTitleLocator = By.className("card-title");

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
        driver.manage().window().maximize();
    }
    

    // Method to get the list of card title elements
    public List<WebElement> getCardTitles() {
        return driver.findElements(cardTitleLocator);
}
    
}
