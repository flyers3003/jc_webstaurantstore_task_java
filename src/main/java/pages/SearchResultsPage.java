package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage{
    private final By searchResultsTitle = By.cssSelector("div.search__wrap > h1");
    private final By itemDescription = By.cssSelector("span[data-testid='itemDescription']");
    private final By nextPageArrow = By.cssSelector("li.inline-block.leading-4.align-top.rounded-r-md");
    private final By addToCartButton = By.cssSelector("input[data-testid='itemAddCart']");
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
    public String getSearchResultsTitleText(){
        return waitForElementVisibility(searchResultsTitle).getText();
    }
    //Get all item descriptions on the page
    public List<String> getItemDescriptions(){
        List<String> itemDescriptions = new ArrayList<>();
        List<WebElement> itemDescriptionsList = waitForElementsVisibility(itemDescription);
        for (WebElement element : itemDescriptionsList) {
            itemDescriptions.add(element.getText());
        }
        return itemDescriptions;
    }
    public int getNumberSearchResultsPerPage(){
        return waitForElementsVisibility(itemDescription).size();
    }
    //Specifically adds the last "in stock" search item on the page to the cart
    //Could create a method that would take an index and add that specific item to the cart
    public void selectLastAddToCartButton(){
        List<WebElement> addToCartButtonList = waitForElementsVisibility(addToCartButton);
        addToCartButtonList.get(addToCartButtonList.size()-1).click();
    }
    public void goToNextPage(){
        waitForElementVisibility(nextPageArrow).click();
    }
    private WebElement waitForElementVisibility(By locator) {
        return new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private List<WebElement> waitForElementsVisibility(By locator) {
        return new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
