package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartPage extends BasePage{
    private final WebDriver driver;

    private final By itemDescription = By.cssSelector("div[class^='cartItem'] > div > span > a");
    private final By emptyCartButton = By.cssSelector("button[class^='emptyCartButton']");
    private final By modalConfirmButton = By.xpath(".//footer/button[contains(text(),'Empty')]");
    private final By emptyCartText = By.cssSelector("div[class='empty-cart__text'] > p");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemDescriptionText (){
        return driver.findElement(itemDescription).getAttribute("title");
    }
    public void clearCart(){
        //May need to update to allow for some time for either item to be visible
        driver.findElement(emptyCartButton).click();
        driver.findElement(modalConfirmButton).click();
    }
    public String getEmptyCartText (){
        //After emptying cart, a little time may be needed for confirmation text to be visible
        new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOfElementLocated(emptyCartText));
        return driver.findElement(emptyCartText).getText();
    }
}
