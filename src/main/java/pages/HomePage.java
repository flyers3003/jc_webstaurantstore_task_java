package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Didn't really need to have HomePage to complete task since search bar is accessible as part of BasePage,
//but added it to show that further tests that accessed HomePage specific items could be done through here
public class HomePage extends BasePage{
    private final WebDriver driver;

    private final By homePageBanner = By.cssSelector("div[data-module='initHomePage']");

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean verifyHomePage(){
        return driver.findElement(homePageBanner).isDisplayed();
    }
}

