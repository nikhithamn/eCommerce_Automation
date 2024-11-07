package com.TechStory.eCommerce.eCommerce_Project.utilities;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionValidation {

    private WebDriver driver;

    // Constructor
    public ActionValidation(WebDriver driver) {
        this.driver = driver;
    }

    // Method for clicking an element
    public void clickElement(WebElement element) {
        element.click();
    }

    // Method for sending keys (typing into an input field)
    public void typeText(WebElement element, String text) {
        element.sendKeys(text);
    }

    // Method for mouse hover
    public void mouseHover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // Method for drag-and-drop
    public void dragAndDrop(WebElement source, WebElement target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }
}
