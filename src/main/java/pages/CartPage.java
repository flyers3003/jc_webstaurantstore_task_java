package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartPage extends BasePage{
    private final By itemDescription = By.cssSelector("div[class^='cartItem'] > div > span > a");
    private final By emptyCartButton = By.cssSelector("button[class^='emptyCartButton']");
    private final By modalConfirmButton = By.xpath(".//footer/button[contains(text(),'Empty')]");
    private final By emptyCartText = By.cssSelector("div[class='empty-cart__text'] > p");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //Method to get the text of the Item description in the cart
    public String getItemDescriptionText (){
        //After having navigated to cart, may need time for item description to be visible
        new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOfElementLocated(itemDescription));
        return driver.findElement(itemDescription).getAttribute("title");
    }
    //Method to clear the contents of the cart
    public void clearCart(){
        //May need to update to allow for some time for either item to be visible
        new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(emptyCartButton)).click();
        new WebDriverWait(driver,waitTime)
                .until(ExpectedConditions.elementToBeClickable(modalConfirmButton)).click();
    }
    //Method to get the text of an empty cart
    public String getEmptyCartText (){
        //After emptying cart, a little time may be needed for confirmation text to be visible
        new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOfElementLocated(emptyCartText));
        return driver.findElement(emptyCartText).getText();
    }
}
