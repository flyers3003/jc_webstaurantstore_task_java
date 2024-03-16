package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static WebDriver driver;
    private static By searchField = By.cssSelector("input[data-testid='searchval']");
    private static By searchButton = By.xpath("//*[@id=\"searchForm\"]/div/button");
    private static By cartButton = By.cssSelector("a[data-testid='cart-button']");
    private Duration waitTime = Duration.ofSeconds(5);
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
    }

    public static SearchResultsPage searchForText(String searchText){
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(searchText);
        driver.findElement(searchButton).click();
        return new SearchResultsPage(driver);
    }
    public CartPage selectCart(){
        new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        return new CartPage(driver);
    }
}
