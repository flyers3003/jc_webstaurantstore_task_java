package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import pages.BasePage;

public class BaseTests {
    protected WebDriver driver;
    protected BasePage basePage;

    @BeforeClass
    public void setupClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setupTest(){
        //Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600,1200));
        //Navigate to the website
        driver.get("https://www.webstaurantstore.com/");
        //Initialize BasePage
        basePage = new BasePage(driver);
    }
    @AfterClass
    public void tearDown(){
        //Quit WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
