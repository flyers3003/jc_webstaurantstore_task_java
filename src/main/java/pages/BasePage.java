package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static WebDriver driver;
    private static final By searchField = By.cssSelector("input[data-testid='searchval']");
    private static final By searchButton = By.cssSelector("button[value='Search']");
    private static final By cartButton = By.cssSelector("a[data-testid='cart-button']");
    public Duration waitTime = Duration.ofSeconds(10);
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public BasePage() {
    }

    public SearchResultsPage searchForText(String searchText){
        //Need to click into search field before we can send keys to it
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(searchText);
        driver.findElement(searchButton).click();
        return new SearchResultsPage(driver);
    }
    public CartPage selectCart(){
        //Cart button sometimes requires a little bit of time to be visible
        new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        return new CartPage(driver);
    }
    public static HomePage returnHomePage(){
        return new HomePage(driver);
    }
}
