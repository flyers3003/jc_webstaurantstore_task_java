package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage{
    private WebDriver driver;

    private By itemDescription = By.cssSelector("div[class^='cartItem'] > div > span > a");
    private By emptyCartButton = By.cssSelector("button[class^='emptyCartButton']");
    private By modalConfirmButton = By.xpath(".//footer/button[contains(text(),'Empty')]");
    private By emptyCartText = By.cssSelector("div[class='empty-cart__text'] > p");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemDescriptionText (){
        return driver.findElement(itemDescription).getAttribute("title");
    }
    public void clearCart(){
        driver.findElement(emptyCartButton).click();
        driver.findElement(modalConfirmButton).click();
    }
    public String getEmptyCartText (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartText));
        return driver.findElement(emptyCartText).getText();
    }
}
