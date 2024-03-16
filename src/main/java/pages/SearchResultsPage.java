package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage{
    private static WebDriver driver;
    private By searchResultsTitle = By.cssSelector("#react_0HN243FQOLSML > div > div.search__wrap > h1");
    private By itemDescription = By.cssSelector("#ProductBoxContainer > div > a > span[data-testid='itemDescription']");
    private By nextPageArrow = By.cssSelector("#paging > nav > ul > li.inline-block.leading-4.align-top.rounded-r-md > a");
    private By addToCartButton = By.cssSelector("#ProductBoxContainer > div.add-to-cart > form > div > div > input.btn.btn-cart.btn-small");
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getSearchResultsTitleText(){
        return driver.findElement(searchResultsTitle).getText();
    }
    public List<String> getItemDescriptions(){
        List<String> itemDescriptions = new ArrayList<String>();
        List<WebElement> itemDescriptionsList = driver.findElements(itemDescription);
        for (WebElement element : itemDescriptionsList) {
            itemDescriptions.add(element.getText());
        }
        return itemDescriptions;
    }
    public void selectLastAddToCartButton(){
        List<WebElement> addToCartButtonList = driver.findElements(addToCartButton);
        addToCartButtonList.get(addToCartButtonList.size()-1).click();
    }
    public void advanceToNextPage(){
        driver.findElement(nextPageArrow).click();
    }
}
