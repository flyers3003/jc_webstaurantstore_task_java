package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private static final By SEARCH_FIELD_LOCATOR = By.cssSelector("input[data-testid='searchval']");
    private static final By SEARCH_BUTTON_LOCATOR = By.cssSelector("button[value='Search']");
    private static final By CART_BUTTON_LOCATOR = By.cssSelector("a[data-testid='cart-button']");
    private WebElement searchField;
    private WebElement searchButton;
    private WebElement cartButton;
    public Duration waitTime = Duration.ofSeconds(10);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        initializeElements();
    }

    //Initialize any elements being used on the page
    private void initializeElements(){
        searchField = driver.findElement(SEARCH_FIELD_LOCATOR);
        searchButton = driver.findElement(SEARCH_BUTTON_LOCATOR);
        cartButton = driver.findElement(CART_BUTTON_LOCATOR);
    }
    //Set the text in the search field
    public void setSearchText(String searchText) {
        //Need to click into search field before we can send keys to it
        searchField.click();
        searchField.sendKeys(searchText);
    }
    //Click the search button
    public void clickSearchButton() {
        searchButton.click();
    }
    //Perform a search on the given string
    public SearchResultsPage searchForText(String searchText){
        setSearchText(searchText);
        clickSearchButton();
        return new SearchResultsPage(driver);
    }
    //Click the cart button
    public CartPage clickCartButton(){
        //Cart button sometimes requires a little bit of time to be visible
        try{
            new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        }catch (StaleElementReferenceException e) {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            WebElement element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(CART_BUTTON_LOCATOR)));
            element.click();
        }
        return new CartPage(driver);
    }
    public HomePage returnHomePage(){
        return new HomePage(driver);
    }

}
