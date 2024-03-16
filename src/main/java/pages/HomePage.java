package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private static WebDriver driver;

    private By homePageBanner = By.cssSelector("div[data-module='initHomePage']");

    public HomePage(WebDriver driver) {
        HomePage.driver = driver;
    }

    public boolean verifyHomePage(){
        return driver.findElement(homePageBanner).isDisplayed();
    }
}

