package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage{
    private static WebDriver driver;
    private final By searchResultsTitle = By.cssSelector("div.search__wrap > h1");
    private final By itemDescription = By.cssSelector("span[data-testid='itemDescription']");
    private final By nextPageArrow = By.cssSelector("li.inline-block.leading-4.align-top.rounded-r-md");
    private final By addToCartButton = By.cssSelector("input[data-testid='itemAddCart']");
    public SearchResultsPage(WebDriver driver) {
        SearchResultsPage.driver = driver;
    }
    public String getSearchResultsTitleText(){
        return driver.findElement(searchResultsTitle).getText();
    }
    //Get all item descriptions on the page
    public List<String> getItemDescriptions(){
        List<String> itemDescriptions = new ArrayList<>();
        List<WebElement> itemDescriptionsList = driver.findElements(itemDescription);
        for (WebElement element : itemDescriptionsList) {
            itemDescriptions.add(element.getText());
        }
        return itemDescriptions;
    }
    //Specifically adds the last "in stock" search item on the page to the cart
    //Could create a method that would take an index and add that specific item to the cart
    public void selectLastAddToCartButton(){
        List<WebElement> addToCartButtonList = driver.findElements(addToCartButton);
        addToCartButtonList.get(addToCartButtonList.size()-1).click();
    }
    public void advanceToNextPage(){
        driver.findElement(nextPageArrow).click();
    }
}
