package pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private static WebDriver driver;

    public HomePage(WebDriver driver) {
        HomePage.driver = driver;
    }
}

